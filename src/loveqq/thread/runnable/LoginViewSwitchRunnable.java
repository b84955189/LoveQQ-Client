package loveqq.thread.runnable;

import loveqq.base.BaseFrame;
import loveqq.config.R;
import loveqq.model.entity.AccountInformation;
import loveqq.model.entity.LQUser;
import loveqq.utils.CommonUtils;
import loveqq.utils.UDPUtils;
import loveqq.view.components.AlertDialog;
import loveqq.view.frames.MainView;
import loveqq.view.panels.loginview.LoginLoadBottomPane;
import loveqq.view.panels.loginview.SignInBottomPane;
import net.sf.json.JSONObject;

import javax.swing.*;


/**
 * @author Jason
 * @version 1.0
 * @date 12/20/2019 8:29 AM
 * @describe:
 */
public class LoginViewSwitchRunnable implements Runnable{
    //Thread Synchronized Lock
    private Object lock;
    private LoginLoadBottomPane tempLoginLoadBottomPane;
    private SignInBottomPane tempSignInBottomPane;

    private BaseFrame frame;

    private String temp_account;
    private char[] temp_password;
    private  boolean isSavePass;



    //Default is False By JVM Init.
    private boolean isCancel;


    public LoginViewSwitchRunnable(BaseFrame frame){
        super();

        this.frame=frame;
        this.lock=new Object();
    }
    public LoginViewSwitchRunnable setDataAndGetInstance(String temp_account,char[] temp_password,boolean isSavePass){
        this.temp_account=temp_account;
        this.temp_password=temp_password;
        this.isSavePass=isSavePass;
        return this;

    }
    //Cancel Login
    public void setCancel(boolean cancel) {
        isCancel = cancel;
        switchPanelToSignInBottomPane();
    }
    @Override
    public void run(){
        //Switch Pane To LoginLoadBottomPane.
        switchPanelToLoginLoadBottomPane();


        //Code Block Synchronized In Thread.   (But,It's no use...)
        synchronized(lock) {

            //---------------test--------------
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //---------------test--------------
            System.out.println("我倒着1");
            //Block Tow Times If User Select Cancel. ---Before Send JSON Data.
            if (!isCancel) {
                System.out.println("我倒着2");

                //Packing JSON Data Object
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(R.JSONS.TYPE_JSON_FIELD, R.JSONS.LOGIN_REQUEST_TYPE_VALUE);
                jsonObject.put(R.JSONS.ACCOUNT_JSON_FIELD, temp_account);
                //Client Receiver Port
                jsonObject.put(R.JSONS.CLIENT_RECEIVE_PORT_FIELD,UDPUtils.getReceiveSocket().getLocalPort());

                jsonObject.put(R.JSONS.PASSWORD_JSON_FIELD, CommonUtils.getMD5(temp_password));
                //Judge Return Data Type
                Object unKnownTypeData = UDPUtils.sendUDPPacket(jsonObject, R.Configs.LOGIN_SEND_TYPE);
                if (unKnownTypeData instanceof Boolean) {
                    //Back Sign In Pane
                    switchPanelToSignInBottomPane();
                    //Prompt Login Request Timeout
                    //test
                    //System.out.println("登录超时！");
                    //test
                    new AlertDialog("登录超时！").setVisible(true);

                } else if (unKnownTypeData instanceof AccountInformation) {
                    AccountInformation userData = (AccountInformation) unKnownTypeData;
                    //Judge Result Value
                    switch (userData.getResult()) {
                        case R.JSONS.SUCCESS_RESULT_VALUE: {


                            //Handle UserDate.(There Should be a delay time for the LoginLoadBottomPane)
                            //test
                            new Thread(new HandleUserDataRunnable(userData,String.valueOf(temp_password),isSavePass)).start();

                            //Block Tow Times If User Select Cancel.----After Send JSON Data.
                            if (!isCancel) {
                                //Dispose Login View & Open LQ MainView
                                frame.dispose();
                                new MainView(userData).setVisible(true);

                                //test
                                System.out.println("登录了：" + userData.getMe());
                            }


                        }
                        ;
                        break;
                        case R.JSONS.FAILED_RESULT_VALUE: {

                            //Read Failed Reason & Show it.
                            //My Dialog
                            //test
                            //System.out.println(userData.getTips());
                            //test
                            new AlertDialog(userData.getTips()).setVisible(true);

                            //Back Sign In Pane
                            switchPanelToSignInBottomPane();

                        }
                        ;
                        break;
                    }


                }
            }
            isCancel = false;
        }
    }
    /**
     * @author: Jason
     * @date: 12/21/2019
     * @time: 12:05 AM
     * @param
     * @return
     * @describe: Switch Panel To LoginLoadBottomPane.
     */
    private void switchPanelToLoginLoadBottomPane(){
        //Update UI In Event Dispatch Thread(EDT).
        SwingUtilities.invokeLater(() -> {
            //Change Content Pane
            //Create Login Load Bottom Pane
            tempLoginLoadBottomPane = new LoginLoadBottomPane(temp_account,frame,this);
            //Save Temp Sign In Bottom Pane Object
            tempSignInBottomPane = (SignInBottomPane) (frame.getContentPane().getComponent(1));
            frame.getContentPane().remove(tempSignInBottomPane);
            frame.getContentPane().add(tempLoginLoadBottomPane);
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
        });
    }
    /**
     * @author: Jason
     * @date: 12/21/2019
     * @time: 12:05 AM
     * @param
     * @return
     * @describe: Switch Panel To SignInBottomPane.
     */
    private void switchPanelToSignInBottomPane(){
        //Update UI In Event Dispatch Thread(EDT).
        SwingUtilities.invokeLater(() -> {
            frame.getContentPane().remove(tempLoginLoadBottomPane);
            frame.getContentPane().add(tempSignInBottomPane);
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
        });
    }
}

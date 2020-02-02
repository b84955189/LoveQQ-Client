package loveqq.view.panels.loginview;

import loveqq.base.BaseFrame;
import loveqq.base.BasePanel;
import loveqq.config.R;

import loveqq.thread.runnable.LoginViewSwitchRunnable;
import loveqq.utils.CommonUtils;

import loveqq.utils.ImageUtils;
import loveqq.view.components.HeadPortraitPane;
import loveqq.view.components.LQButton;
import loveqq.view.components.LQLinkLabel;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * @author Jason
 * @version 1.0
 * @date 12/10/2019 9:55 PM
 * @describe:
 */
public class SignInBottomPane extends BasePanel {
    private JTextField accountTextField;
    private JPasswordField passwordField;
    private LQLinkLabel registerLabel,forgetPasswordField;
    private LQButton signInButton;

    private JCheckBox savePasswordCheckBox;

    private HeadPortraitPane headPortraitPane;

    private BaseFrame frame;

    private LoginViewSwitchRunnable loginViewSwitchRunnable;
    private LoginActionListener loginActionListener;

    public SignInBottomPane(BaseFrame frame){
        super();

        this.frame=frame;
        //Enable Drag
        this.enableDrag(frame);

        //Init Data
        this.initData();

    }

    private void initData(){
        loginViewSwitchRunnable=new LoginViewSwitchRunnable(frame);
    }
    @Override
    protected void initPanel() {
        this.setLayout(null);
        this.setFocusable(true);
    }

    @Override
    protected void initComponents() {

        headPortraitPane=new HeadPortraitPane(new Rectangle(50,15,R.Dimensions.DEFAULT_HEAD_PORTRAIT_WIDTH,R.Dimensions.DEFAULT_HEAD_PORTRAIT_HEIGHT));


        passwordField=new JPasswordField();
        CommonUtils.initHintFocus(headPortraitPane,passwordField,passwordField,R.Strings.PASSWORD_HINT_FIELD);
        passwordField.addActionListener(getLoginActionListener());
        passwordField.setBounds(185,18+R.Dimensions.ACCOUNT_FIELD_HEIGHT,R.Dimensions.PASSWORD_FIELD_WIDTH,R.Dimensions.PASSWORD_FIELD_HEIGHT);


        accountTextField=new JTextField();
        CommonUtils.initHintFocus(headPortraitPane,accountTextField,passwordField,R.Strings.ACCOUNT_HINT_FIELD);
        accountTextField.addActionListener(getLoginActionListener());
        accountTextField.setBounds(185,20,R.Dimensions.ACCOUNT_FIELD_WIDTH,R.Dimensions.ACCOUNT_FIELD_HEIGHT);



        registerLabel=new LQLinkLabel(R.Strings.REGISTER_ACCOUNT_LABEL,R.URLS.REGISTER_URL);
        registerLabel.setBounds(195+R.Dimensions.ACCOUNT_FIELD_WIDTH,20,80,30);

        forgetPasswordField=new LQLinkLabel(R.Strings.FORGOT_PASSWORD_LABEL,R.URLS.FORGET_URL);
        forgetPasswordField.setBounds(195+R.Dimensions.PASSWORD_FIELD_WIDTH,18+R.Dimensions.ACCOUNT_FIELD_HEIGHT,80,30);




        savePasswordCheckBox=new JCheckBox(R.Strings.SAVE_PASSWORD_CHECK_BOX);
        savePasswordCheckBox.setForeground(Color.GRAY);
        savePasswordCheckBox.setFont(CommonUtils.getDefaultFont(Font.PLAIN,15));
        savePasswordCheckBox.setBounds(185,18+R.Dimensions.ACCOUNT_FIELD_HEIGHT+R.Dimensions.PASSWORD_FIELD_HEIGHT,100,30);

        signInButton=new LQButton(R.Strings.SIGN_IN_BUTTON,new Rectangle(185,145,R.Dimensions.SIGN_IN_BUTTON_WIDTH,R.Dimensions.SIGN_IN_BUTTON_HEIGHT));

    }

    @Override
    protected void addComponents() {
        this.add(accountTextField);
        this.add(passwordField);

        this.add(registerLabel);
        this.add(forgetPasswordField);

        this.add(headPortraitPane);

        this.add(savePasswordCheckBox);

        this.add(signInButton);
    }

    @Override
    protected void addListeners() {
        //SignInButton
        signInButton.addActionListener(getLoginActionListener());
    }
    public LoginActionListener getLoginActionListener(){
        if(loginActionListener==null)
            loginActionListener=this.new LoginActionListener();
        return loginActionListener;
    }
    public HeadPortraitPane getHeadPortraitPane(){
        return headPortraitPane;
    }
    /**
     * @author: Jason
     * @date: 12/21/2019
     * @time: 5:23 PM
     * @param
     * @return
     * @describe: Login Action Listener.(Instance Inner Class)
     */
    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String temp_account=accountTextField.getText().trim();
            char[] temp_password=passwordField.getPassword();

            //Reset Password Field,Prevent User Duplicate Input.
            SwingUtilities.invokeLater(()->{
                passwordField.setEchoChar((char)0);
                passwordField.setForeground(Color.gray);
                passwordField.setText(R.Strings.PASSWORD_HINT_FIELD);
            });




            //IF no input || IF is empty.
            if(temp_account.equals(R.Strings.ACCOUNT_HINT_FIELD)||temp_account.equals(R.Strings.EMPTY_CONTENT)){
                //Prompt for input account.

                //test
                System.out.println("请输入账号！");


                //IF no input password
            }else if(String.valueOf(temp_password).equals(R.Strings.PASSWORD_HINT_FIELD)){
                //Prompt input password
                //test
                System.out.println("请输入密码！");


                //Normal Operation
            }else{

                //Check Whether Current Account is Logged In.


                //Start Work Thread,Prevent Block Main Thread.
                new Thread(loginViewSwitchRunnable.setDataAndGetInstance(temp_account,temp_password,savePasswordCheckBox.isSelected())).start();


                //Create Current User Data In the PC.(pending)








            }

        }
    }
}

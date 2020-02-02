package loveqq.view.components;

import loveqq.base.BaseDialog;
import loveqq.base.BaseFrame;
import loveqq.base.BasePanel;

import loveqq.config.R;
import loveqq.model.entity.LQUser;
import loveqq.utils.CommonUtils;
import loveqq.utils.UDPUtils;
import loveqq.view.frames.ChatView;
import loveqq.view.frames.LoginView;
import loveqq.view.frames.MainView;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


/**
 * @author Jason
 * @version 1.0
 * @date 12/10/2019 4:44 PM
 * @describe: Global Top Title Bar Class
 */
public class TopTitleBar extends BasePanel {


    //Include Components in this panel.
    private JLabel iconCarrierLabel,titleLabel,minimizeIconCarrierLabel,closeIconCarrierLabel;
    private BaseFrame frame;
    private BaseDialog dialog;
    private LQUser currentUser;

    private HeadPortraitPane headPortraitPane;


    private boolean isFrame;
    public TopTitleBar(BaseFrame frame){
        super();
        this.frame=frame;
        //Default Location is top.
        this.setBounds(0,0,frame.getWidth(),R.Dimensions.TOP_TITLE_BAR_HEIGHT);
        this.isFrame=true;
        //Prevent frame is null.
        this.childInitComponents();
        this.childAddComponents();
        this.childAddListeners();





        //Default is enable drag.
        this.enableDrag(frame);


    }
    public TopTitleBar(BaseDialog dialog){
        super();
        this.dialog=dialog;
        //Default Location is top.
        this.setBounds(0,0,dialog.getWidth(),R.Dimensions.TOP_TITLE_BAR_HEIGHT);
        this.isFrame=false;
        //Prevent frame is null.
        this.childInitComponents();
        this.childAddComponents();
        this.childAddListeners();





        //Default is enable drag.
        this.enableDrag(dialog);


    }
    public TopTitleBar(String displayName,HeadPortraitPane headPortraitPane,BaseFrame frame){
        super();
        this.frame=frame;
        this.isFrame=true;
        //Default Location is top.
        this.setBounds(0,0,frame.getWidth(),R.Dimensions.TOP_TITLE_BAR_HEIGHT);

        //Prevent frame is null.
        this.childInitComponents();
        this.childAddComponents();
        this.childAddListeners();


        this.setOpaque(true);

        loadTitleAndHeadPortrait(displayName,headPortraitPane);




        //Default is enable drag.
        this.enableDrag(frame);


    }
    public TopTitleBar(LQUser currentUser,BaseFrame frame){
        super();

        this.currentUser=currentUser;
        this.frame=frame;
        this.isFrame=true;
        //Default Location is top.
        this.setBounds(0,0,frame.getWidth(),R.Dimensions.TOP_TITLE_BAR_HEIGHT);

        //Prevent frame is null.
        this.childInitComponents();
        this.childAddComponents();
        this.childAddListeners();
    }
    private void loadTitleAndHeadPortrait(String displayName,HeadPortraitPane headPortraitPane){



        titleLabel=new JLabel(displayName);

        titleLabel.setBounds(R.Dimensions.HEAD_PORTRAIT_WIDTH+20,(this.getHeight()-30)/2,200,30);
        titleLabel.setHorizontalAlignment(JLabel.LEFT);
        titleLabel.setFont(CommonUtils.getDefaultFont(Font.PLAIN,25));
        titleLabel.setForeground(Color.WHITE);

        this.add(titleLabel);

        this.add(headPortraitPane);
    }
    @Override
    protected void initPanel() {
        //Don't use layout.
       this.setLayout(null);
       this.setOpaque(false);


    }


    protected void childInitComponents() {
//        iconCarrierLabel=new JLabel();
//        iconCarrierLabel.setBounds(5,5,R.Dimensions.ICON_WIDTH,R.Dimensions.ICON_HEIGHT);
//        iconCarrierLabel.setIcon(new ImageIcon(CommonUtils.getDIVImage(iconCarrierLabel.getWidth(),iconCarrierLabel.getHeight(), R.Images.ICON)));
        minimizeIconCarrierLabel=new JLabel();
        if(isFrame)
            minimizeIconCarrierLabel.setBounds(frame.getWidth()-R.Dimensions.TOP_TITLE_BAR_MINIMIZE_ICON_X,10,R.Dimensions.MINIMIZE_ICON_WIDTH,R.Dimensions.MINIMIZE_ICON_HEIGHT);
        else
            minimizeIconCarrierLabel.setBounds(dialog.getWidth()-R.Dimensions.TOP_TITLE_BAR_MINIMIZE_ICON_X,10,R.Dimensions.MINIMIZE_ICON_WIDTH,R.Dimensions.MINIMIZE_ICON_HEIGHT);
        minimizeIconCarrierLabel.setIcon(new ImageIcon(CommonUtils.getDIVImage(minimizeIconCarrierLabel.getWidth(),minimizeIconCarrierLabel.getHeight(),R.Images.MINIMIZE_ICON)));

        closeIconCarrierLabel=new JLabel();
        if(isFrame)
            closeIconCarrierLabel.setBounds(frame.getWidth()-R.Dimensions.TOP_TITLE_BAR_CLOSE_ICON_X,15,R.Dimensions.CLOSE_ICON_WIDTH,R.Dimensions.CLOSE_ICON_HEIGHT);
        else
            closeIconCarrierLabel.setBounds(dialog.getWidth()-R.Dimensions.TOP_TITLE_BAR_CLOSE_ICON_X,15,R.Dimensions.CLOSE_ICON_WIDTH,R.Dimensions.CLOSE_ICON_HEIGHT);
        closeIconCarrierLabel.setIcon(new ImageIcon(CommonUtils.getDIVImage(R.Dimensions.CLOSE_ICON_WIDTH,R.Dimensions.CLOSE_ICON_HEIGHT,R.Images.CLOSE_ICON)));

    }


    protected void childAddComponents() {
//        this.add(iconCarrierLabel);
        this.add(minimizeIconCarrierLabel);
        this.add(closeIconCarrierLabel);

    }


    protected void childAddListeners() {
        minimizeIconCarrierLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(()->{
                    minimizeIconCarrierLabel.setIcon(new ImageIcon(CommonUtils.getDIVImage(minimizeIconCarrierLabel.getWidth(),minimizeIconCarrierLabel.getHeight(),R.Images.MINIMIZE_ICON_COPY)));
                });

            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(()->{
                    minimizeIconCarrierLabel.setIcon(new ImageIcon(CommonUtils.getDIVImage(minimizeIconCarrierLabel.getWidth(),minimizeIconCarrierLabel.getHeight(),R.Images.MINIMIZE_ICON)));
                });
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setExtendedState(BaseFrame.HIDE_ON_CLOSE);
            }
        });
        closeIconCarrierLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(()->{
                    closeIconCarrierLabel.setIcon(new ImageIcon(CommonUtils.getDIVImage(R.Dimensions.CLOSE_ICON_WIDTH,R.Dimensions.CLOSE_ICON_HEIGHT,R.Images.CLOSE_ICON_COPY)));
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(()->{
                    closeIconCarrierLabel.setIcon(new ImageIcon(CommonUtils.getDIVImage(R.Dimensions.CLOSE_ICON_WIDTH,R.Dimensions.CLOSE_ICON_HEIGHT,R.Images.CLOSE_ICON)));
                });
            }

            public void mouseClicked(MouseEvent e){
                if(isFrame){
                    if(frame instanceof LoginView) {
                        System.exit(0);
                    }else if(frame instanceof MainView){
                        //Prompt Whether Minimize to System Tray.

                        //Close View
                        frame.dispose();

                        //Send Logout UDP Packet.
                        //Packing JSON Data Object
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put(R.JSONS.TYPE_JSON_FIELD, R.JSONS.LOGOUT_REQUEST_TYPE_VALUE);

                        //Client Receiver Port
                        jsonObject.put(R.JSONS.CLIENT_RECEIVE_PORT_FIELD, UDPUtils.getReceiveSocket().getLocalPort());

                        jsonObject.put(R.JSONS.MESSAGE_FROM_FIELD,currentUser.getUser_id());
                        //Send Logout UDP
                        System.out.println("登出："+UDPUtils.sendUDPPacket(jsonObject, R.Configs.LOGOUT_SEND_TYPE));

                        try {
                            UDPUtils.getSocket().close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }


                        //Test
                        System.exit(0);
                    }else if(frame instanceof ChatView){
                        frame.setVisible(false);
                    }
                    //else if etc...
                }else{
                    dialog.dispose();
                }

            }
        });


    }
    @Override
    protected void initComponents() {

    }

    @Override
    protected void addComponents() {

    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(CommonUtils.getImage(R.Images.SIGN_IN_TOP_BG),0,0,this.getWidth()+700,this.getHeight()+600,this);
    }
}

package loveqq.view.frames;

import loveqq.base.BaseFrame;
import loveqq.config.R;
import loveqq.model.entity.AccountInformation;
import loveqq.model.entity.LQUser;
import loveqq.thread.runnable.OperationRunnable;
import loveqq.utils.CommonUtils;
import loveqq.utils.UDPUtils;
import loveqq.view.components.TopTitleBar;
import loveqq.view.panels.mainview.BottomPane;
import loveqq.view.panels.mainview.FriendsListPane;
import loveqq.view.panels.mainview.TopUserInfoPane;
import net.sf.json.JSONObject;

import java.awt.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jason
 * @version 1.0
 * @date 12/23/2019 4:29 PM
 * @describe:
 */
public class MainView extends BaseFrame {

    private TopUserInfoPane topUserInfoPane;
    private FriendsListPane friendsListPane;
    private BottomPane bottomPane;
    //User Data.
    private AccountInformation accountInformation;
    public MainView(AccountInformation accountInformation){
        super();


        this.accountInformation=accountInformation;

        //Prevent AccountInformation is null.
        this.childInitComponents();
        this.childAddComponents();
        this.childAddListeners();

        initReceiverThread();
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
    private void initReceiverThread(){
        //Group
        new Thread(()->{
            try {
                DataInputStream dataInputStream=new DataInputStream(UDPUtils.getSocket().getInputStream());
                String message=null;
                while((message=dataInputStream.readUTF())!=null){
                    System.out.println("测试"+message);
                    new Thread(new OperationRunnable(message,friendsListPane)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        //Receive Message Thread
        new Thread(()->{
            UDPUtils.receivePacket(friendsListPane);
        }).start();
        //Update Online State Thread
        JSONObject jsonObject=new JSONObject();
        //Type
        jsonObject.put(R.JSONS.TYPE_JSON_FIELD,R.JSONS.CHECK_USER_IS_ONLINE_VALUE);
        //Current User ID
        jsonObject.put(R.JSONS.MESSAGE_FROM_FIELD,accountInformation.getMe().getUser_id());
        Timer onlineStateTimer=new Timer();
        onlineStateTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                UDPUtils.sendUDPPacket(jsonObject,R.Configs.CHECK_USER_IS_ONLINE_TYPE);
            }
        },0,3000);

    }
    @Override
    protected void initFrame() {

        this.setSize(R.Dimensions.MAIN_VIEW_WIDTH,R.Dimensions.MAIN_VIEW_HEIGHT);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.BLACK);
        super.initFrame();
    }

    protected void childInitComponents() {

        topUserInfoPane=new TopUserInfoPane(this,accountInformation.getMe(), CommonUtils.getImage(R.Images.SIGN_IN_TOP_BG));
        friendsListPane=new FriendsListPane(accountInformation.getMe(),accountInformation.getFriends(),new Rectangle(0,R.Dimensions.TOP_USER_INFORMATION_PANE_HEIGHT,this.getWidth(),this.getHeight()-R.Dimensions.TOP_USER_INFORMATION_PANE_HEIGHT-R.Dimensions.BOTTOM_PANE_HEIGHT));

        bottomPane=new BottomPane(accountInformation.getMe(),friendsListPane,new Rectangle(0,this.getHeight()-R.Dimensions.BOTTOM_PANE_HEIGHT,this.getWidth(),R.Dimensions.BOTTOM_PANE_HEIGHT));
    }

    protected void childAddComponents() {
        this.add(topUserInfoPane);
        this.add(friendsListPane);
        this.add(bottomPane);
    }

    protected void childAddListeners() {

    }
}

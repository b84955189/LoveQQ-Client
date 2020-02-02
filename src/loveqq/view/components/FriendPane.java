package loveqq.view.components;

import loveqq.base.BasePanel;
import loveqq.config.R;
import loveqq.model.entity.LQUser;
import loveqq.utils.CommonUtils;
import loveqq.view.frames.ChatView;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason
 * @version 1.0
 * @date 12/24/2019 7:55 PM
 * @describe:
 */
public class FriendPane extends BasePanel {
    private LQUser friendUser;
    private HeadPortraitPane headPortraitPane;
    private JLabel friendDisplayNameLabel,latestMessageLabel;
    private JLabel iconCarrier,latestMessageCountLabel;
    private LQUser currentUser;

    private Color bgColor;
    //User ID
    private int id;
    private List<String> recentMessageList;

    private ChatView chatView;
    public FriendPane(LQUser currentUser,LQUser friendUser){
        super();

        this.currentUser=currentUser;
        this.friendUser=friendUser;

        this.id=friendUser.getUser_id();

        recentMessageList=new ArrayList<String>();

        this.initComponentsInChild();
        this.addComponentsInChild();
        this.addListenersInChild();
    }

    public int getId() {
        return id;
    }


    @Override
    protected void initPanel() {
        this.setLayout(null);
        //---------test------------
//        this.setBackground(Color.BLACK);
        //-------------------------
        this.setSize(R.Dimensions.MAIN_VIEW_WIDTH,R.Dimensions.FRIEND_PANE_HEIGHT);
        this.setMinimumSize(R.Dimensions.FRIEND_PANE_SIZE);
        this.setMaximumSize(R.Dimensions.FRIEND_PANE_SIZE);
        this.setPreferredSize(R.Dimensions.FRIEND_PANE_SIZE);

        this.setBackground(Color.WHITE);
    }

    @Override
    protected void initComponents() {

    }
    protected void initComponentsInChild() {
        headPortraitPane=new HeadPortraitPane(currentUser.getUser_login(),friendUser,new Rectangle(15,(this.getHeight()-50)/2,50,50));

        friendDisplayNameLabel=new JLabel(friendUser.getDisplay_name());
        friendDisplayNameLabel.setFont(CommonUtils.getDefaultFont(Font.PLAIN,17));
        friendDisplayNameLabel.setForeground(Color.BLACK);
        //---------test-----------
//        friendDisplayNameLabel.setOpaque(true);
//        friendDisplayNameLabel.setBackground(Color.RED);
        //------------------------
        friendDisplayNameLabel.setBounds(70,10,R.Dimensions.FRIEND_DISPLAY_NAME_WIDTH,R.Dimensions.FRIEND_DISPLAY_NAME_HEIGHT);

        latestMessageLabel=new JLabel("测试");
        latestMessageLabel.setFont(CommonUtils.getDefaultFont(Font.PLAIN,15));
        latestMessageLabel.setForeground(Color.GRAY);
        //---------test-----------
//        latestMessageLabel.setOpaque(true);
//        latestMessageLabel.setBackground(Color.BLUE);
        //------------------------
        latestMessageLabel.setBounds(70,35,R.Dimensions.FRIEND_DISPLAY_NAME_WIDTH+100,R.Dimensions.FRIEND_DISPLAY_NAME_HEIGHT);

        iconCarrier=new JLabel(new ImageIcon(R.Images.MESSAGE_COUNT_ICON));
        iconCarrier.setBounds(this.getWidth()-R.Dimensions.ROUND_ICON_WIDTH-10,(this.getHeight()-R.Dimensions.ROUND_ICON_HEIGHT)/2-10,R.Dimensions.ROUND_ICON_WIDTH,R.Dimensions.ROUND_ICON_HEIGHT);

        latestMessageCountLabel=new JLabel("0");
        latestMessageCountLabel.setHorizontalAlignment(JLabel.CENTER);
        latestMessageCountLabel.setFont(CommonUtils.getDefaultFont(Font.PLAIN,15));
        latestMessageCountLabel.setForeground(Color.WHITE);
        //---------test-----------
//        latestMessageCountLabel.setOpaque(true);
//        latestMessageCountLabel.setBackground(Color.BLUE);
        //------------------------
        latestMessageCountLabel.setBounds(this.getWidth()-R.Dimensions.ROUND_ICON_WIDTH-10,(this.getHeight()-R.Dimensions.ROUND_ICON_HEIGHT)/2-10,R.Dimensions.ROUND_ICON_WIDTH,R.Dimensions.ROUND_ICON_HEIGHT);


    }
    public void addMessage(String message){
        //Play Sound.
        try {
            FileInputStream fileInputStream=new FileInputStream(R.Sounds.PROMPT_SOUND);
            AudioStream audioStream=new AudioStream(fileInputStream);
            AudioPlayer.player.start(audioStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(chatView==null){
            recentMessageList.add(message);
            System.out.println("如果消息面板为空！");
        }
        else{
            chatView.sendMessage(message,true);
            System.out.println("如果消息面板不为空！");
        }
        //IF Already Open,Then not to update message count.
        if(chatView!=null&&chatView.isVisible()){
            //No Operation
        }else{
            try {
                int count = Integer.parseInt(latestMessageCountLabel.getText());
                if (count + 1 > 99) {
                    latestMessageCountLabel.setText("99+");
                } else {
                    latestMessageCountLabel.setText(String.valueOf(count + 1));
                }
            }catch (NumberFormatException e){
                //e.printStackTrace();
            }
        }

    }
    @Override
    protected void addComponents() {

    }
    protected void addComponentsInChild() {
        this.add(headPortraitPane);

        this.add(friendDisplayNameLabel);

        this.add(latestMessageLabel);

        this.add(iconCarrier,1);

        this.add(latestMessageCountLabel,0);
    }

    @Override
    protected void addListeners() {

    }
    protected void addListenersInChild() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                if(e.getClickCount()==1){
                    //Change Background Color.
                    //changeBackground(false);
                }else if(e.getClickCount()>=2){
                    //Open Chat Dialog
                    //--------test-------
                    System.out.println("打开聊天面板！");
                    //-------------------
                    if(chatView==null)
                        chatView=new ChatView(currentUser,friendUser,recentMessageList);
                    chatView.setVisible(true);
                    SwingUtilities.invokeLater(()->{

                        latestMessageCountLabel.setText("0");
                    });

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //super.mouseEntered(e);
                //Change Background Color.
                changeBackground(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //super.mouseExited(e);
                //Change Background Color.
                changeBackground(true);

            }
        });
    }
    private void changeBackground(boolean isDefault){

        if(isDefault){
            bgColor=Color.WHITE;
        }else{
            bgColor=new Color(213,213,213);
        }
        SwingUtilities.invokeLater(()->{
            this.setBackground(bgColor);
        });
    }
}

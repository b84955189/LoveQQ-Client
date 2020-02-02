package loveqq.view.frames;

import loveqq.base.BaseFrame;
import loveqq.config.R;
import loveqq.model.entity.LQUser;
import loveqq.utils.CommonUtils;
import loveqq.utils.UDPUtils;
import loveqq.view.components.*;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Jason
 * @version 1.0
 * @date 12/26/2019 8:12 AM
 * @describe:
 */
public class ChatView extends BaseFrame {
    private TopTitleBar topTitleBar;
    private JScrollPane messageListScrollPane,messageScrollPane;
    private Box verticalBox;
    private JTextArea messageArea;

    private LQUser currentUser, friendUser;

    private LQButton sendMessageButton;

    private HeadPortraitPane currentUserPortrait,friendUserPortrait;

    private List<String> recentMessageList;
    public ChatView(LQUser currentUser,LQUser friendUser,List<String> recentMessageList){
        super();

        this.currentUser=currentUser;
        this.friendUser =friendUser;
        this.recentMessageList=recentMessageList;

        this.initComponentsInChild();
        this.addComponentsInChild();
        this.addListenersInChild();

        new Thread(new LoadMessagePaneRunnable(recentMessageList)).start();
    }
    @Override
    protected void initFrame() {
        this.setLayout(null);
        this.setSize(R.Dimensions.CHAT_VIEW_WIDTH,R.Dimensions.CHAT_VIEW_HEIGHT);
        //cancel default title bar.
        this.setUndecorated(true);
        //cancel LookAndFeel's default title bar  -->BeautyEye LAF
        //this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //set Frame location to center.
        this.setLocationRelativeTo(null);
        //this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(ChatView.DISPOSE_ON_CLOSE);
       //super.initFrame();
    }


    protected void initComponentsInChild() {




        topTitleBar=new TopTitleBar(friendUser.getDisplay_name(),new HeadPortraitPane(currentUser, friendUser,new Rectangle(5,(R.Dimensions.TOP_TITLE_BAR_HEIGHT-R.Dimensions.HEAD_PORTRAIT_HEIGHT)/2,R.Dimensions.HEAD_PORTRAIT_HEIGHT,R.Dimensions.HEAD_PORTRAIT_HEIGHT)),this);

        //currentUser=new HeadPortraitPane(currentUser.getUser_login(),new Rectangle());
        //friendUserPortrait=


        verticalBox=Box.createVerticalBox();


        messageListScrollPane =new JScrollPane(verticalBox);
        messageListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageListScrollPane.setBounds(0,50,this.getWidth()+1,R.Dimensions.MESSAGE_LIST_SCROLL_PANE_HEIGHT);



        messageArea=new JTextArea();
        messageArea.setLineWrap(true);
        messageArea.setMargin(new Insets(0,10,0,0));
        messageArea.setForeground(Color.BLACK);
        messageArea.setFont(CommonUtils.getDefaultFont(Font.PLAIN,15));

        messageScrollPane=new JScrollPane(messageArea);
        messageScrollPane.setBorder(null);
        messageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        messageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messageScrollPane.setBounds(0,490,this.getWidth()+1,R.Dimensions.MESSAGE_SCROLL_PANE_HEIGHT);

        sendMessageButton=new LQButton(R.Strings.SEND_MESSAGE_BUTTON,new Rectangle(this.getWidth()-120,this.getHeight()-35,R.Dimensions.SEND_MESSAGE_BUTTON_WIDTH,R.Dimensions.SEND_MESSAGE_BUTTON_HEIGHT));



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


    protected void addComponentsInChild() {
        this.add(topTitleBar);


        this.add(messageListScrollPane);

        this.add(messageScrollPane);

        this.add(sendMessageButton);
    }


    protected void addListenersInChild() {
        sendMessageButton.addActionListener(e->{
            //-------test-----
            System.out.println("添加消息");
            //----------------
            sendMessage(messageArea.getText(),false);


        });
        messageArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
               if(e.getKeyCode()==KeyEvent.VK_ENTER){
                   //Send Message
                   sendMessage(messageArea.getText(),false);
                   messageArea.setText(R.Strings.EMPTY_CONTENT);
                   //Prevent '\n'
                   e.consume();

               }
            }
        });
    }
    public void sendMessage(final String message,boolean isFriend){
        if(!isFriend){

            //Send Message By Network.
            if(friendUser.getUser_id()!=666) {
                new Thread(() -> {

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(R.JSONS.TYPE_JSON_FIELD, R.JSONS.MESSAGE_FORWARD_TYPE_VALUE);
                    jsonObject.put(R.JSONS.MESSAGE_FROM_FIELD, currentUser.getUser_id());
                    jsonObject.put(R.JSONS.MESSAGE_TO_FIELD, friendUser.getUser_id());
                    jsonObject.put(R.JSONS.MESSAGE_DATE_FIELD, new Date());
                    jsonObject.put(R.JSONS.MESSAGE_TYPE_FIELD, 0);
                    jsonObject.put(R.JSONS.MESSAGE_CONTENT_FIELD, message);
                    boolean resultSign = (boolean) (UDPUtils.sendUDPPacket(jsonObject, R.JSONS.MESSAGE_FORWARD_TYPE_VALUE));
                    //Check Message Whether Reach.
                    if (resultSign) {
                        //No Operation.
                        //-------test--------
                        System.out.println("消息发送成功！");
                        //-------------------

                    } else {
                        //Prompt User.
                        //---------test-------
                        System.out.println("消息内容为：[" + message + "]未发送成功！");
                        //--------------------
                    }


                }).start();
            } else{
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(R.JSONS.TYPE_JSON_FIELD, R.JSONS.MESSAGE_FORWARD_TYPE_VALUE);
                    jsonObject.put(R.JSONS.MESSAGE_FROM_FIELD, 666);
                    jsonObject.put(R.JSONS.MESSAGE_TO_FIELD, 666);
                    jsonObject.put(R.JSONS.MESSAGE_DATE_FIELD, new Date());
                    jsonObject.put(R.JSONS.MESSAGE_TYPE_FIELD, 0);
                    jsonObject.put(R.JSONS.MESSAGE_CONTENT_FIELD, message);
                    DataOutputStream dataOutputStream=new DataOutputStream(UDPUtils.getSocket().getOutputStream());
                    dataOutputStream.writeUTF(jsonObject.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        new Thread(new LoadMessagePaneRunnable(message,isFriend)).start();
    }
    public class LoadMessagePaneRunnable implements Runnable{
        private String message;
        private List<String> recentMessageList;
        private boolean isCurrentMessage,isFriend;
        public LoadMessagePaneRunnable(String message,boolean isFriend){
            super();
            this.message =message;
            isCurrentMessage =true;
            this.isFriend=isFriend;
        }
        public LoadMessagePaneRunnable(List<String> recentMessageList){
            super();
           this.recentMessageList=recentMessageList;
            isCurrentMessage =false;
        }
        @Override
        public void run() {

            if(isCurrentMessage){
                SwingUtilities.invokeLater(()->{
                    MessagePane messagePane=null;
                    BubblePane bubblePane=new BubblePane(new StringBuffer(message),BubblePane.EXPRESSION_MESSAGE_TYPE);
                    if(isFriend) {

                        messagePane = new MessagePane(currentUser, friendUser, bubblePane, FlowLayout.LEFT);

                    }else {
                        messagePane = new MessagePane(currentUser, friendUser, bubblePane, FlowLayout.RIGHT);


                    }
                    verticalBox.add(Box.createVerticalStrut(2));
                    verticalBox.add(messagePane);
                    verticalBox.add(Box.createVerticalStrut(2));
                    verticalBox.validate();
                    verticalBox.repaint();
                    //Move To ScrollPane Bottom.
                    messageListScrollPane.getViewport().setViewPosition(new Point(0,messageListScrollPane.getVerticalScrollBar().getMaximum()));
                    messageListScrollPane.validate();
                    messageListScrollPane.repaint();

                    //After View Visible ,then set size.
                    Dimension messagePaneSize=new Dimension(R.Dimensions.CHAT_VIEW_WIDTH-40,bubblePane.getHeight()+5);
                    messagePane.setSize(messagePaneSize);
                    messagePane.setMaximumSize(messagePaneSize);
                    messagePane.setMinimumSize(messagePaneSize);
                    messagePane.setPreferredSize(messagePaneSize);

                    verticalBox.validate();
                    verticalBox.repaint();
                    messageListScrollPane.validate();
                    messageListScrollPane.repaint();
                });

            }else{
                for(String tempMessage:recentMessageList){

                    SwingUtilities.invokeLater(()->{
                        BubblePane bubblePane=new BubblePane(new StringBuffer(tempMessage),BubblePane.EXPRESSION_MESSAGE_TYPE);
                        MessagePane messagePane=new MessagePane(currentUser,friendUser,bubblePane,FlowLayout.LEFT);
                        verticalBox.add(Box.createVerticalStrut(2));
                        verticalBox.add(messagePane);
                        verticalBox.add(Box.createVerticalStrut(2));
                        verticalBox.validate();
                        verticalBox.repaint();
                        //Move To ScrollPane Bottom.
                        messageListScrollPane.getViewport().setViewPosition(new Point(0,messageListScrollPane.getVerticalScrollBar().getMaximum()));
                        messageListScrollPane.validate();
                        messageListScrollPane.repaint();

                        //After View Visible ,then set size.
                        Dimension messagePaneSize=new Dimension(R.Dimensions.CHAT_VIEW_WIDTH-40,bubblePane.getHeight()+5);
                        messagePane.setSize(messagePaneSize);
                        messagePane.setMaximumSize(messagePaneSize);
                        messagePane.setMinimumSize(messagePaneSize);
                        messagePane.setPreferredSize(messagePaneSize);

                        verticalBox.validate();
                        verticalBox.repaint();
                        messageListScrollPane.validate();
                        messageListScrollPane.repaint();
                    });

                }
            }



        }
    }

}

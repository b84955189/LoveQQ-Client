package loveqq.view.panels.mainview;

import loveqq.base.BasePanel;
import loveqq.model.entity.LQUser;
import loveqq.view.components.FriendPane;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/24/2019 7:34 PM
 * @describe:
 */
public class FriendsListPane extends BasePanel {
    private Box verticalBox;
    private JScrollPane scrollPane;
    private java.util.List<LQUser> friendsData;
    private LQUser currentUser;
    public FriendsListPane(LQUser currentUser,java.util.List<LQUser> friendsData,Rectangle rectangle){
        super();

        this.currentUser=currentUser;
        this.friendsData=friendsData;

        this.setBounds(rectangle);

        LQUser groupTest=new LQUser();
        groupTest.setDisplay_name("交流群");
        groupTest.setUser_head_url("http://storage.lking.top/fb757153e73def4b7000c2a7321273a4_jieptjt2zsyug.jpg");
        groupTest.setUser_id(666);

        //----test--------
        SwingUtilities.invokeLater(()->{
            verticalBox.add(new FriendPane(currentUser,groupTest));
        });
        //----------test---------
        new Thread(()->{
            for(LQUser friend:this.friendsData){
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                SwingUtilities.invokeLater(()->{
                    //verticalBox.add(Box.createVerticalStrut(3));
                    verticalBox.add(new FriendPane(currentUser,friend));

                });

            }
        }).start();

        //-----------------------
    }
    @Override
    protected void initPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLUE);
    }
    public void addFriendRepaint(LQUser friendAddUser){
        new Thread(()->{

                SwingUtilities.invokeLater(()->{
                    //verticalBox.add(Box.createVerticalStrut(3));
                    verticalBox.add(new FriendPane(currentUser,friendAddUser));
                    verticalBox.validate();
                    verticalBox.repaint();
                    scrollPane.validate();
                    scrollPane.repaint();
                });


        }).start();
    }
    @Override
    protected void initComponents() {
        verticalBox=Box.createVerticalBox();
        scrollPane=new JScrollPane(verticalBox);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }

    @Override
    protected void addComponents() {
        this.add(scrollPane);
    }

    @Override
    protected void addListeners() {

    }
    public FriendPane getFriendPane(int id){
        Component[] components=verticalBox.getComponents();
        for(Component component:components){
            FriendPane tempFriendPane=(FriendPane)component;
            if(tempFriendPane.getId()==id){

                verticalBox.remove(tempFriendPane);
                verticalBox.add(tempFriendPane,0);
                verticalBox.validate();
                verticalBox.repaint();
                scrollPane.validate();
                scrollPane.repaint();

                return tempFriendPane;
            }
        }
        return null;
    }
}

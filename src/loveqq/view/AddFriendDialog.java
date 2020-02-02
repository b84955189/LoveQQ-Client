package loveqq.view;

import loveqq.base.BaseDialog;
import loveqq.config.R;
import loveqq.model.entity.AddFriendEntity;
import loveqq.model.entity.LQUser;
import loveqq.utils.CommonUtils;
import loveqq.utils.UDPUtils;
import loveqq.view.components.AlertDialog;
import loveqq.view.components.FriendPane;
import loveqq.view.components.LQButton;
import loveqq.view.components.TopTitleBar;
import loveqq.view.panels.mainview.FriendsListPane;
import net.sf.json.JSONObject;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/31/2019 2:52 PM
 * @describe:
 */
public class AddFriendDialog extends BaseDialog {
    private TopTitleBar topTitleBar;

    private JLabel tipLabel;
    private JTextField accountField;
    private JLabel iconCarrier;
    private LQButton searchButton;
    private JScrollPane scrollPane;
    private Box verticalBox;
    private LQUser currentUser;

    private FriendsListPane friendsListPane;
    public AddFriendDialog(LQUser currentUser,FriendsListPane friendsListPane){
        super();
        this.currentUser=currentUser;
        this.friendsListPane=friendsListPane;
        this.addListenersInChild();
    }

    @Override
    protected void initDialog() {
        super.initDialog();
        this.setSize(500,200);
        //this.setLocationRelativeTo(null);
        this.setTitle(R.Strings.ADD_FRIEND_PANE_TITLE);
        this.setLayout(null);

       // this.setDefaultCloseOperation(AddFriendDialog.DISPOSE_ON_CLOSE);
    }

    @Override
    protected void initComponents() {
        topTitleBar=new TopTitleBar(this);

        tipLabel=new JLabel(R.Strings.ADD_FRIEND_ACCOUNT);
        tipLabel.setFont(CommonUtils.getDefaultFont(Font.PLAIN,17));
        tipLabel.setBounds((this.getWidth()-205)/2-20,R.Dimensions.TOP_TITLE_BAR_HEIGHT+5,55,30);

        accountField=new JTextField();
        accountField.setFont(CommonUtils.getDefaultFont(Font.PLAIN,17));
        accountField.setBounds((this.getWidth()-205)/2+55-20,R.Dimensions.TOP_TITLE_BAR_HEIGHT+5,150,30);

        searchButton=new LQButton(R.Strings.ADD_FRIEND_PANE_TITLE,new Rectangle((this.getWidth()-205)/2+55+155-20,R.Dimensions.TOP_TITLE_BAR_HEIGHT+5,100,30));

        verticalBox=Box.createVerticalBox();
        verticalBox.setBackground(Color.RED);
        scrollPane=new JScrollPane(verticalBox);
        scrollPane.setBounds(0,R.Dimensions.TOP_TITLE_BAR_HEIGHT+5+35,this.getWidth(),this.getHeight()-(R.Dimensions.TOP_TITLE_BAR_HEIGHT+5+35));

    }

    @Override
    protected void addComponents() {
        this.add(topTitleBar);

        this.add(tipLabel);
        this.add(accountField);
        this.add(searchButton);
        this.add(scrollPane);
    }

    @Override
    protected void addListeners() {

    }

    protected void addListenersInChild() {
        searchButton.addActionListener(e->{
          new Thread(()->{
              String tempAccount=accountField.getText();
              if(tempAccount.trim().equals(R.Strings.EMPTY_CONTENT)){
                  new AlertDialog("不可为空！").setVisible(true);
              }else{
                  JSONObject jsonObject=new JSONObject();
                  jsonObject.put(R.JSONS.TYPE_JSON_FIELD,R.JSONS.SEARCH_FRIEND_TYPE_VALUE);
                  jsonObject.put(R.JSONS.MESSAGE_FROM_FIELD,currentUser.getUser_login());
                  jsonObject.put(R.JSONS.MESSAGE_TO_FIELD,tempAccount);

                  Object unKnownType=UDPUtils.sendUDPPacket(jsonObject,R.Configs.SEARCH_FRIEND_TYPE);
                  if(unKnownType instanceof AddFriendEntity){
                      AddFriendEntity addFriendEntity=(AddFriendEntity)(unKnownType);
                      int sign=addFriendEntity.getResult();
                      if(sign==200){
                          //Success
                          friendsListPane.addFriendRepaint(addFriendEntity.getFriend());
                         SwingUtilities.invokeLater(()->{
                             verticalBox.removeAll();
                             verticalBox.add(new FriendPane(currentUser,addFriendEntity.getFriend()));
                             verticalBox.validate();
                             verticalBox.repaint();
                             scrollPane.validate();
                             scrollPane.repaint();
                         });
                      }else{
                          //Failed
                          new AlertDialog(addFriendEntity.getTips()).setVisible(true);
                      }
                  }else if(unKnownType instanceof Boolean){
                      //Prompt Failed
                      //test
                      System.out.println("错误");
                  }
              }
          }).start();
        });
    }
}

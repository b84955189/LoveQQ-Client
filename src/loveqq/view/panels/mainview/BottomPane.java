package loveqq.view.panels.mainview;

import loveqq.base.BasePanel;
import loveqq.config.R;
import loveqq.model.entity.LQUser;
import loveqq.utils.CommonUtils;
import loveqq.view.AddFriendDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jason
 * @version 1.0
 * @date 12/31/2019 2:11 PM
 * @describe:
 */
public class BottomPane extends BasePanel {
    private JLabel addFriendCarrier;
    private LQUser currentUser;
    private FriendsListPane friendsListPane;
    public BottomPane(LQUser currentUser,FriendsListPane friendsListPane,Rectangle rectangle){
        super();
        this.currentUser=currentUser;
        this.friendsListPane=friendsListPane;
        this.setBounds(rectangle);
        this.addListenersInChild();
    }
    @Override
    protected void initPanel() {

    }

    @Override
    protected void initComponents() {
        addFriendCarrier=new JLabel(new ImageIcon(CommonUtils.getImage(R.Images.ADD_FRIEND_ICON)));
    }

    @Override
    protected void addComponents() {
        this.add(addFriendCarrier);
    }

    @Override
    protected void addListeners() {

    }

    protected void addListenersInChild() {
        addFriendCarrier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AddFriendDialog(currentUser,friendsListPane).setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(()->{
                    addFriendCarrier.setIcon(new ImageIcon(CommonUtils.getImage(R.Images.ADD_FRIEND_ICON_COPY)));
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(()->{
                    addFriendCarrier.setIcon(new ImageIcon(CommonUtils.getImage(R.Images.ADD_FRIEND_ICON)));
                });
            }
        });
    }
}

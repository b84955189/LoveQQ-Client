package loveqq.view.components;

import loveqq.base.BasePanel;
import loveqq.config.R;
import loveqq.model.entity.LQUser;

import java.awt.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/26/2019 11:05 PM
 * @describe:
 */
public class MessagePane extends BasePanel {
    private HeadPortraitPane headPortraitPane;
    private LQUser currentUser,friendUser;
    private BubblePane bubblePane;
    private int align;
    public MessagePane(LQUser currentUser,LQUser friendUser, BubblePane bubblePane,int align){
        super();


        this.currentUser =currentUser;
        this.friendUser=friendUser;

        this.bubblePane=bubblePane;
        this.align =align;

        this.initPanelInChild();
        this.initComponentsInChild();
        this.addComponentsInChild();
        this.addListenersInChild();

    }

    @Override
    protected void initPanel() {

    }

    protected void initPanelInChild() {
        this.setLayout(new FlowLayout(align));

        //-----test----------
        //this.setBackground(Color.BLUE);
        //-------------------
        //System.out.println("搞："+bubblePane.getHeight());


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

    protected void initComponentsInChild() {
            if(align==FlowLayout.LEFT)
                headPortraitPane=new HeadPortraitPane(currentUser, friendUser,new Rectangle(0,0,R.Dimensions.HEAD_PORTRAIT_HEIGHT,R.Dimensions.HEAD_PORTRAIT_HEIGHT));
            else
                headPortraitPane=new HeadPortraitPane(currentUser.getUser_login(),new Rectangle(0,0,R.Dimensions.HEAD_PORTRAIT_HEIGHT,R.Dimensions.HEAD_PORTRAIT_HEIGHT));
            headPortraitPane.setPreferredSize(new Dimension(R.Dimensions.HEAD_PORTRAIT_HEIGHT,R.Dimensions.HEAD_PORTRAIT_HEIGHT));

    }


    protected void addComponentsInChild() {
            if(align==FlowLayout.LEFT){
                this.add(headPortraitPane);
                this.add(bubblePane);
            }else{
                this.add(bubblePane);
                this.add(headPortraitPane);
            }

    }


    protected void addListenersInChild() {

    }
}

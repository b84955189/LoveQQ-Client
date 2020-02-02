package loveqq.view.panels.mainview;

import loveqq.base.BaseFrame;
import loveqq.base.BasePanel;
import loveqq.config.R;
import loveqq.model.entity.LQUser;
import loveqq.utils.CommonUtils;
import loveqq.view.components.HeadPortraitPane;
import loveqq.view.components.SearchPane;
import loveqq.view.components.TopTitleBar;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/23/2019 5:43 PM
 * @describe:
 */
public class TopUserInfoPane extends BasePanel {
    private BaseFrame frame;
    private TopTitleBar topTitleBar;
    private LQUser currentUser;

    private Image topBG;
    private HeadPortraitPane headPortraitPane;
    private JLabel softWareNameLabel,displayNameLabel;

    //Search Pane
    private SearchPane searchPane;
    public TopUserInfoPane(BaseFrame frame, LQUser currentUser,Image topBG){
        super();
        this.frame=frame;
        this.topBG=topBG;
        this.currentUser=currentUser;

        this.setBounds(0,0,frame.getWidth(), R.Dimensions.TOP_USER_INFORMATION_PANE_HEIGHT);

        //Prevent frame is null.
        initComponentInChildClass();
        addComponentsInChildClass();


        //Set Enable
        enableDrag(frame);


    }
    @Override
    protected void initPanel() {
        this.setLayout(null);
        this.setBackground(Color.RED);
    }

    @Override
    protected void initComponents() {
        softWareNameLabel =new JLabel(R.Strings.TITLE_LOGO);
        softWareNameLabel.setForeground(Color.WHITE);
        softWareNameLabel.setFont(CommonUtils.getDefaultFont(Font.BOLD,15));
        softWareNameLabel.setBounds(5,5,R.Dimensions.SOFT_WARE_NAME_WIDTH,R.Dimensions.SOFT_WARE_NAME_HEIGHT);
    }
    private void initComponentInChildClass(){

        topTitleBar=new TopTitleBar(currentUser,frame);

        displayNameLabel=new JLabel(currentUser.getDisplay_name());
        displayNameLabel.setForeground(Color.WHITE);
        //--------test-----------
        //displayNameLabel.setBackground(Color.BLACK);
        //displayNameLabel.setOpaque(true);
        //-----------------------
        displayNameLabel.setFont(CommonUtils.getDefaultFont(Font.PLAIN,25));
        displayNameLabel.setHorizontalAlignment(JLabel.CENTER);
        displayNameLabel.setBounds((R.Dimensions.MAIN_VIEW_WIDTH-R.Dimensions.DISPLAY_NAME_WIDTH)/2,(R.Dimensions.TOP_USER_INFORMATION_PANE_HEIGHT-R.Dimensions.DISPLAY_NAME_HEIGHT)/2+50,R.Dimensions.DISPLAY_NAME_WIDTH,R.Dimensions.DISPLAY_NAME_HEIGHT);

        headPortraitPane=new HeadPortraitPane(currentUser,new Rectangle((R.Dimensions.MAIN_VIEW_WIDTH-R.Dimensions.DEFAULT_HEAD_PORTRAIT_WIDTH)/2,(R.Dimensions.TOP_USER_INFORMATION_PANE_HEIGHT-R.Dimensions.DEFAULT_HEAD_PORTRAIT_HEIGHT)/2-30,R.Dimensions.DEFAULT_HEAD_PORTRAIT_WIDTH,R.Dimensions.DEFAULT_HEAD_PORTRAIT_HEIGHT));

        //-----------test---------
        searchPane=new SearchPane();
        searchPane.setBounds(0,200,R.Dimensions.MAIN_VIEW_WIDTH,50);
        //------------------------
    }

    @Override
    protected void addComponents() {
        this.add(softWareNameLabel);
    }
    private void addComponentsInChildClass(){
        this.add(topTitleBar,1);
        this.add(displayNameLabel);
        this.add(headPortraitPane,0);
        this.add(searchPane);

    }
    @Override
    protected void addListeners() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(topBG,0,0,this.getWidth()+600,this.getHeight()+900,this);
    }
}

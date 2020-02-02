package loveqq.view.panels.loginview;

import loveqq.base.BaseFrame;
import loveqq.base.BasePanel;
import loveqq.config.R;
import loveqq.utils.CommonUtils;
import loveqq.view.components.TopTitleBar;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/10/2019 9:55 PM
 * @describe: Sign In Top Pane
 */
public class SignInTopPane extends BasePanel {
    private BaseFrame frame;
    private Image topBG;

    private JLabel iconCarrierLabel;
    private JLabel titleLogoLabel;
    public SignInTopPane(BaseFrame frame,Image topBG){
        super();
        this.frame=frame;
        this.topBG=topBG;
        //Because can't use it variables before super operation.
        this.initTitleBar(new TopTitleBar(frame));
        //Enable Drag
        this.enableDrag(frame);


    }
    @Override
    protected void initPanel() {

        this.setLayout(null);


    }

    @Override
    protected void initComponents() {
        iconCarrierLabel=new JLabel();
        iconCarrierLabel.setBounds(110,45,R.Dimensions.SIGN_IN_TOP_ICON_WIDTH,R.Dimensions.SIGN_IN_TOP_ICON_HEIGHT);
        iconCarrierLabel.setIcon(new ImageIcon(CommonUtils.getDIVImage(iconCarrierLabel.getWidth(),iconCarrierLabel.getHeight(),R.Images.ICON)));

        titleLogoLabel=new JLabel(R.Strings.TITLE_LOGO);
        titleLogoLabel.setBounds(250,40,200,R.Dimensions.SIGN_IN_TOP_ICON_HEIGHT);
        titleLogoLabel.setFont(CommonUtils.getDefaultFont(Font.PLAIN,80));
        titleLogoLabel.setForeground(new Color(1,1,1,0.75f));

    }

    @Override
    protected void addComponents() {
        this.add(iconCarrierLabel);
        this.add(titleLogoLabel);

    }

    @Override
    protected void addListeners() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        //call super's method before paint image.
        super.paintComponent(g);
        g.drawImage(topBG,0,0,this.getWidth()+600,this.getHeight()+800,this);


    }



}

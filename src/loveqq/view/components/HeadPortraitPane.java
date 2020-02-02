package loveqq.view.components;

import loveqq.base.BasePanel;
import loveqq.config.R;
import loveqq.model.entity.LQUser;
import loveqq.utils.CommonUtils;
import loveqq.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author Jason
 * @version 1.0
 * @date 12/15/2019 8:42 PM
 * @describe: Head Portrait Class
 */
public class HeadPortraitPane extends BasePanel {
    private JLabel  carrierLabel;

    public HeadPortraitPane(Rectangle rectangle){
        super();

        this.setBounds(rectangle);

        //Default Head Portrait
        this.setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(),this.getHeight(),R.Images.DEFAULT_HEAD_PORTRAIT));

    }
    //Load Current User When Not Have Network.
    public HeadPortraitPane(String account,Rectangle rectangle){
        super();

        this.setBounds(rectangle);
        File headPortraitFile=new File(R.DataDirectory.PERSONAL_DATA_PATH+"\\"+account+"\\"+R.DataDirectory.ME_PARENT_PATH_NAME+"\\"+R.DataDirectory.FRIENDS_IMAGE_PATH_NAME+"\\"+R.DataDirectory.HEAD_PORTRAIT_NAME);
        //Test
        System.out.println("我"+headPortraitFile.getPath());
        if(headPortraitFile.exists()){
            setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(),this.getHeight(),headPortraitFile.getPath()));
        }else {
            //Default Head Portrait
            this.setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(), this.getHeight(), R.Images.DEFAULT_HEAD_PORTRAIT));
        }
    }
    //Load Current User When Have Network.
    public HeadPortraitPane(LQUser user, Rectangle rectangle){
        super();

        this.setBounds(rectangle);
        File headPortraitFile=new File(R.DataDirectory.PERSONAL_DATA_PATH+"\\"+user.getUser_login()+"\\"+R.DataDirectory.ME_PARENT_PATH_NAME+"\\"+R.DataDirectory.FRIENDS_IMAGE_PATH_NAME+"\\"+R.DataDirectory.HEAD_PORTRAIT_NAME);

        if(headPortraitFile.exists()){
            //Already Exists Head Portrait
            this.setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(),this.getHeight(),headPortraitFile.getPath()));
        }else{
            //Default Head Portrait
            this.setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(),this.getHeight(),R.Images.DEFAULT_HEAD_PORTRAIT));

        }

        //Network Download.
        new Thread(()->{
            ImageUtils.obtainNetworkImage(user.getUser_head_url(),headPortraitFile);
            SwingUtilities.invokeLater(()->{

                if(headPortraitFile.exists()){
                    setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(),this.getHeight(),headPortraitFile.getPath()));
                }else {
                    //Default Head Portrait
                    this.setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(), this.getHeight(), R.Images.DEFAULT_HEAD_PORTRAIT));
                }
            });


        }).start();
    }
    //Load Friend User When Have Network.
    public HeadPortraitPane(String currentUser,LQUser user, Rectangle rectangle){
        super();

        this.setBounds(rectangle);
        File headPortraitDirectory=new File(R.DataDirectory.PERSONAL_DATA_PATH+"\\"+currentUser+"\\"+R.DataDirectory.FRIENDS_PARENT_PATH_NAME+"\\"+user.getUser_login()+"\\"+R.DataDirectory.FRIENDS_IMAGE_PATH_NAME);
        File headPortraitFile=new File(headPortraitDirectory,R.DataDirectory.HEAD_PORTRAIT_NAME);

        if(headPortraitFile.exists()){
            //Already Exists Head Portrait
            this.setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(),this.getHeight(),headPortraitFile.getPath()));
        }else{
            if(!headPortraitDirectory.exists())
                headPortraitDirectory.mkdirs();
            //Default Head Portrait
            this.setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(),this.getHeight(),R.Images.DEFAULT_HEAD_PORTRAIT));

        }

        //Network Download.
        new Thread(()->{
            ImageUtils.obtainNetworkImage(user.getUser_head_url(),headPortraitFile);
            SwingUtilities.invokeLater(()->{

                if(headPortraitFile.exists()){
                    setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(),this.getHeight(),headPortraitFile.getPath()));
                }else {
                    //Default Head Portrait
                    this.setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(), this.getHeight(), R.Images.DEFAULT_HEAD_PORTRAIT));
                }
            });


        }).start();
    }
    //Load Friend User When Not Have Network.
    public HeadPortraitPane(LQUser currentUser,LQUser user,Rectangle rectangle) {
        super();

        this.setBounds(rectangle);
        File headPortraitFile = new File(R.DataDirectory.PERSONAL_DATA_PATH + "\\" + currentUser.getUser_login() + "\\" + R.DataDirectory.FRIENDS_PARENT_PATH_NAME + "\\" + user.getUser_login() + "\\" + R.DataDirectory.FRIENDS_IMAGE_PATH_NAME + "\\" + R.DataDirectory.HEAD_PORTRAIT_NAME);

        if (headPortraitFile.exists()) {
            //Already Exists Head Portrait
            this.setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(), this.getHeight(), headPortraitFile.getPath()));
        } else {
            //Default Head Portrait
            this.setHeadPortrait(CommonUtils.getDIVImage(this.getWidth(), this.getHeight(), R.Images.DEFAULT_HEAD_PORTRAIT));

        }
    }
        @Override
    protected void initPanel() {
        this.setLayout(null);
        this.setOpaque(false);
    }

    @Override
    protected void initComponents() {
        carrierLabel=new JLabel();
        carrierLabel.setLocation(0,0);
    }

    @Override
    protected void addComponents() {
        this.add(carrierLabel);
    }

    @Override
    protected void addListeners() {

    }
    /**
     * @author: Jason
     * @date: 12/16/2019
     * @time: 8:28 AM
     * @param  headPortrait
     * @return
     * @describe: Set Head Portrait
     */
    public void setHeadPortrait(Image headPortrait){

        SwingUtilities.invokeLater(()->{
            carrierLabel.setSize(this.getWidth(),this.getHeight());
            carrierLabel.setIcon(new ImageIcon(headPortrait));
            this.validate();
            carrierLabel.repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

//        Ellipse2D.Double ellipse=new Ellipse2D.Double(0,0,this.getWidth(),this.getHeight());
//
//        g.setClip(ellipse);



    }
}

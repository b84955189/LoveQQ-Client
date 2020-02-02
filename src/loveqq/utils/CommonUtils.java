package loveqq.utils;

import com.sun.awt.AWTUtilities;
import loveqq.base.BaseFrame;
import loveqq.config.R;
import loveqq.view.components.HeadPortraitPane;


import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.io.*;
import java.math.BigInteger;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jason
 * @version 1.0
 * @date 12/10/2019 5:02 PM
 * @describe: Global Common Util Class
 */
public class CommonUtils {
    private static DatagramSocket socket;
    static{
        try {
           socket=new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @author: Jason
     * @date: 12/16/2019
     * @time: 10:29 PM
     * @param
     * @return
     * @describe: Return DatagramSocket Global
     */
    public static DatagramSocket getUDPSocket(){
        return socket;
    }
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 7:45 PM
     * @param
     * @return Image
     * @describe: Get Custom Size Image By SRC.
     */
    public static Image getDIVImage(int width,int height,String src){

        return getImage(src).getScaledInstance(width,height,Image.SCALE_SMOOTH);
    }
    /**
     * @author: Jason
     * @date: 12/20/2019
     * @time: 10:26 AM
     * @param
     * @return
     * @describe: Get Custom Size Image By URL.
     */
    public static Image getDIVImage(int width,int height,URL url){
        return getImage(url).getScaledInstance(width,height,Image.SCALE_SMOOTH);
    }
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 10:33 PM
     * @param src
     * @return Image
     * @describe: Get Image By SRC.
     */
    public static Image getImage(String src){
        return Toolkit.getDefaultToolkit().getImage(src);
    }
    /**
     * @author: Jason
     * @date: 12/20/2019
     * @time: 10:24 AM
     * @param
     * @return
     * @describe: Get Image By URL.
     */
    public static Image getImage(URL url){

        //return Toolkit.getDefaultToolkit().createImage(url);
        return new ImageIcon(url).getImage();
    }

    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 11:06 PM
     * @param
     * @return Font
     * @describe: Get Default Font.
     */
    public static Font getDefaultFont(int style,int size){
        return new Font(R.Fonts.MICROSOFT_YAHEI,style,size);
    }
    /**
     * @author: Jason
     * @date: 12/11/2019
     * @time: 11:51 AM
     * @param  frame
     * @return void
     * @describe: Cross platform not guaranteed.
     */
    public static void setFrameRoundAngle(BaseFrame frame){
        AWTUtilities.setWindowShape(frame,new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(),
                frame.getHeight(), 10.0D, 10.0D));
    }

    /**
     * @author: Jason
     * @date: 12/11/2019
     * @time: 2:39 PM
     * @param
     * @return
     * @describe: Init Filed Input Style
     */
    public static void initHintFocus(HeadPortraitPane headPortraitPane,JTextComponent component,JPasswordField jPasswordField, String hint){


        //Initial Style
        component.setFont(CommonUtils.getDefaultFont(Font.PLAIN,17));
        //Set Component Margin
        component.setMargin(new Insets(0,10,0,0));
        component.setForeground(Color.GRAY);

        //IF it is Password Field
        if(component instanceof JPasswordField){
            ((JPasswordField) component).setEchoChar((char)0);
        }

        component.setText(hint);

        component.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String temp=component.getText();
                if(temp.equals(hint)||temp.equals(R.Strings.EMPTY_CONTENT)){
                    component.setText(R.Strings.EMPTY_CONTENT);
                    //Switch Input Style
                    SwingUtilities.invokeLater(()->{
                        //style
                        component.setForeground(Color.BLACK);
                        //IF it is Password Field
                        if(component instanceof JPasswordField){
                            ((JPasswordField) component).setEchoChar('●');
                        }

                    });
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String temp=component.getText();

                //IF it is Account Field.
                if((component instanceof JTextField)&&!(component instanceof JPasswordField)){
                    File headPortraitFile=new File(R.DataDirectory.PERSONAL_DATA_PATH+"\\"+temp+"\\"+R.DataDirectory.ME_PARENT_PATH_NAME+"\\"+R.DataDirectory.FRIENDS_IMAGE_PATH_NAME+"\\"+R.DataDirectory.HEAD_PORTRAIT_NAME);

                    //Check HeadPortrait Whether Exists.
                    if(headPortraitFile.exists()){
                        SwingUtilities.invokeLater(()->{
                            headPortraitPane.setHeadPortrait(CommonUtils.getDIVImage(headPortraitPane.getWidth(),headPortraitPane.getHeight(),headPortraitFile.getPath()));

                            headPortraitPane.repaint();
                        });
                    }else{
                        SwingUtilities.invokeLater(()->{

                            headPortraitPane.setHeadPortrait(CommonUtils.getDIVImage(headPortraitPane.getWidth(), headPortraitPane.getHeight(), R.Images.DEFAULT_HEAD_PORTRAIT));

                            headPortraitPane.repaint();
                        });
                    }
                    //Check the Password File whether existed.
                    File currentUserDataPath=new File(R.DataDirectory.PERSONAL_DATA_PATH+"\\"+component.getText()+"\\"+R.DataDirectory.ME_PARENT_PATH_NAME+"\\"+R.DataDirectory.ME_DATA_PATH_NAME+"\\"+R.DataDirectory.ME_DATA_NAME);
                    System.out.println(currentUserDataPath.getPath());
                    DataInputStream dataInputStream=null;
                    if(currentUserDataPath.exists()){
                        try {
                            dataInputStream=new DataInputStream(new FileInputStream(currentUserDataPath));
                            String tempPassword=dataInputStream.readUTF();

                            if(!(tempPassword.trim().equals(R.Strings.EMPTY_CONTENT))){
                                SwingUtilities.invokeLater(()->{
                                    jPasswordField.setForeground(Color.BLACK);
                                    jPasswordField.setEchoChar('●');
                                    jPasswordField.setText(tempPassword);
                                });
                            }


                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }finally {

                            if(dataInputStream!=null){
                                try {
                                    dataInputStream.close();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }

                    }

                }


                if(temp.equals(R.Strings.EMPTY_CONTENT)){
                    //Switch Input Style
                    SwingUtilities.invokeLater(()->{
                        //style
                        component.setForeground(Color.GRAY);

                        //IF it is Password Field.
                        if(component instanceof JPasswordField){
                            ((JPasswordField) component).setEchoChar((char)0);
                        }
                    });

                    component.setText(hint);
                }
            }

        });
    }
    /**
     * @author: Jason
     * @date: 12/11/2019
     * @time: 9:47 PM
     * @param
     * @return
     * @describe: Get Project Default Color.
     */
    public static Color getDefaultColor(){
        return new Color(0,132,255);
    }
    /**
     * @author: Jason
     * @date: 12/16/2019
     * @time: 8:57 PM
     * @param  md5Bytes
     * @return
     * @describe: md5 bytes to md5 hex string (No use the method)
     */
    public static String bytesToHexString(byte[] md5Bytes){
        StringBuffer hexBuffer=new StringBuffer();
        int digital;
        for(int i=0;i<md5Bytes.length;i++){
            digital=md5Bytes[i];
            if(digital<0)
                digital+=256;
            if(digital<16)
                hexBuffer.append("0");
            hexBuffer.append(Integer.toHexString(digital));
        }
        return hexBuffer.toString().toUpperCase();
    }
    /**
     * @author: Jason
     * @date: 12/16/2019
     * @time: 8:57 PM
     * @param  password
     * @return
     * @describe: Get MD5 HEX Code
     */
    public static String getMD5(char[] password){
        String md5=null;
        try {
            md5=new String(password);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //MD5 Array To Hex String
            md5=new BigInteger(1,messageDigest.digest(md5.getBytes("UTF-8"))).toString(16);

            return md5.toUpperCase();// Return Uppercase
        }catch(NoSuchAlgorithmException | UnsupportedEncodingException e){

            e.printStackTrace();
            return md5;

        }catch (Exception e){
            e.printStackTrace();
            return md5;
        }
    }


}

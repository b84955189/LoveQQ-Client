package loveqq.utils;

import loveqq.config.R;
import loveqq.model.entity.LQUser;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Jason
 * @version 1.0
 * @date 12/22/2019 2:40 PM
 * @describe: Image Utilities.
 */
public class ImageUtils {

    /**
     * @author: Jason
     * @date: 12/22/2019
     * @time: 7:22 PM
     * @param
     * @return
     * @describe: Obtain Network Image. By Image's Child Class -->BufferedImage
     */
    public static BufferedImage getImageByURL(String url){
        BufferedImage bufferedImage=null;
        HttpURLConnection connection=null;
        URL netURL=null;
       try{
            netURL=new URL(url);
            connection=(HttpURLConnection) netURL.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();
            int responseCode=connection.getResponseCode();
            //IF Connection Successful.
            if(responseCode==200){
                bufferedImage= ImageIO.read(connection.getInputStream());
            }
            return bufferedImage;
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }finally {
           if(connection!=null){
               connection.disconnect();
           }

       }

       return null;


    }
    /**
     * @author: Jason
     * @date: 12/22/2019
     * @time: 7:55 PM
     * @param
     * @return
     * @describe: Scale Image.(Shape is Square Size:200x200)
     */
    public static BufferedImage getScaledImageInHighQuality(BufferedImage originImage){
        //Origin Image's Size.
        int originWidth=originImage.getWidth();
        int originHeight=originImage.getHeight();
        //Origin Image Transparency Type.
        int imageType=originImage.getColorModel().getTransparency();

        //New Image's Size.
//        int smallWidth=originImage.getWidth()/R.Configs.IMAGE_SCALE_MULTIPLE;
//        int smallHeight=originImage.getHeight()/R.Configs.IMAGE_SCALE_MULTIPLE;

        int smallWidth=R.Configs.IMAGE_SCALED_SIZE;
        int smallHeight=R.Configs.IMAGE_SCALED_SIZE;

        BufferedImage smallImage=new BufferedImage(smallWidth,smallHeight,imageType);
        //Config Image Rendering Value.
        //New's Image Antialiasing
        RenderingHints renderingHints=new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //Create New's Image By High Quality
        renderingHints.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        Graphics2D graphics2D=smallImage.createGraphics();
        graphics2D.drawImage(originImage,0,0,smallWidth,smallHeight,0,0,originWidth,originHeight,null);
        graphics2D.dispose();

        return  smallImage;
    }
    public static BufferedImage getRoundImage(BufferedImage originImage){
        //Origin Image's Size.
        int originWidth=originImage.getWidth();
        int originHeight=originImage.getHeight();
        BufferedImage roundImage=new BufferedImage(originWidth,originHeight,BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics2D=roundImage.createGraphics();
        //Set Antialiasing
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Ellipse2D.Double ellipse=new Ellipse2D.Double(0,0,originWidth,originHeight);
        graphics2D.setClip(ellipse);
        graphics2D.drawImage(originImage,0,0,null);
        //graphics2D.setBackground(Color.green);
        graphics2D.dispose();

        return roundImage;
    }
    /**
     * @author: Jason
     * @date: 12/22/2019
     * @time: 8:19 PM
     * @param
     * @return
     * @describe: Write Image To File.
     */
    public static boolean writeToFile(BufferedImage image, File file){
        try {
            ImageIO.write(image,R.Configs.HEAD_PORTRAIT_FORMAT,file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @author: Jason
     * @date: 12/22/2019
     * @time: 11:14 PM
     * @param
     * @return
     * @describe:
     */
    public static boolean obtainNetworkImage(String url,File file){
        return writeToFile(getRoundImage(getScaledImageInHighQuality(getImageByURL(url))),file);
    }
}

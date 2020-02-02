package loveqq.main;



import loveqq.config.R;
import loveqq.model.entity.LQUser;
import loveqq.utils.CommonUtils;
import loveqq.utils.UDPUtils;
import loveqq.view.components.BubblePane;
import loveqq.view.frames.ChatView;
import loveqq.view.frames.LoginView;
import loveqq.view.frames.MainView;
import net.sf.json.JSONObject;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;


/**
 * @author Jason
 * @version 1.0
 * @date 12/10/2019 4:28 PM
 * @describe: Test Class
 */
public class Test {
    public static void main(String[] args) throws IOException {

//        try {
//
//            BeautyEyeLNFHelper.frameBorderStyle= BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
//            BeautyEyeLNFHelper.launchBeautyEyeLNF();
//            //cancel LAF setting button
//            UIManager.put("RootPane.setupButtonVisible", false);
////            JFrame.setDefaultLookAndFeelDecorated(true);
////            JDialog.setDefaultLookAndFeelDecorated(true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        SwingUtilities.invokeLater(()->{
//            //UIManager.setLookAndFeel("");
            new LoginView().setVisible(true);
//            //new MainView().setVisible(true);
//
//
//
//
//
        });

//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put(R.JSONS.TYPE_JSON_FIELD,R.JSONS.MESSAGE_FORWARD_TYPE_VALUE);
//        jsonObject.put(R.JSONS.MESSAGE_FROM_FIELD,10);
//        jsonObject.put(R.JSONS.MESSAGE_TO_FIELD,1);
//        jsonObject.put(R.JSONS.MESSAGE_DATE_FIELD,new Date());
//        jsonObject.put(R.JSONS.MESSAGE_TYPE_FIELD,0);
//        jsonObject.put(R.JSONS.MESSAGE_CONTENT_FIELD,"刘龙龙");
//        System.out.println(UDPUtils.sendUDPPacket(jsonObject,R.Configs.MESSAGE_FORWARD_SEND_TYPE));
//
//        System.out.println("Test:"+jsonObject.toString());
//
//        DatagramSocket socket=new DatagramSocket();
//            String tt="我哦";
//            byte[] aa=tt.getBytes("UTF-8");
//            DatagramPacket packet=new DatagramPacket(aa,0,aa.length,InetAddress.getByName("lking.top"),54199);
//
//            socket.send(packet);
//
//            DatagramPacket packet1=new DatagramPacket(new byte[1024],0,1024);
//
//        socket.receive(packet1);
//            String s=new String(packet1.getData(),0,packet1.getLength());
//            System.out.println(s+","+packet1.getPort());
//
//        LQUser me=new LQUser();
//        LQUser friend=new LQUser();
//        friend.setDisplay_name("测试");
//        SwingUtilities.invokeLater(()->{
//            new LoginView().setVisible(true);
//        });
//
//        JFrame frame=new JFrame();
//
//        frame.setLocationRelativeTo(null);
//
//
//        frame.setSize(600,400);
//
//
//        frame.setLayout(new FlowLayout());
//
//        BubblePane bubblePane=new BubblePane(new StringBuffer("啊aaa12啊啊啊啊啊啊asaha挨家挨户交换啊实打实啊实打实空间很快就会金卡和//困khaki很卡aaaaaaaaaaaaaaaaaaaaaaaa啊啊啊啊啊啊啊"),BubblePane.EXPRESSION_MESSAGE_TYPE);
//
//
//        System.out.println("gggaaaa：");

        //JScrollPane jScrollPane=new JScrollPane(bubblePane);
//        //bubblePane.setBounds(100,50,200,30);
//        //bubblePane.setBackground(Color.BLUE);
//        JTextPane jLabel=new JTextPane();
//        jLabel.setText("红火火恍\r\n恍惚惚"+"\r\n"+"哈哈哈");
//        jLabel.setOpaque(false);
//        jLabel.setMargin(new Insets(5,5,5,5));
//        //jLabel.setSize(200,300);
//
//        jLabel.setForeground(Color.WHITE);
//        jLabel.setFont(CommonUtils.getDefaultFont(Font.PLAIN,20));
//        jLabel.insertIcon(new ImageIcon(CommonUtils.getDIVImage(100,100,R.Images.TEST_HEAD_PORTRAIT)));
//        bubblePane.add(jLabel);
//
//        frame.add(bubblePane);
//
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);

    }
}

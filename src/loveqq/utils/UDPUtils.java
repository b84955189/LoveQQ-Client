package loveqq.utils;

import loveqq.config.R;
import loveqq.model.entity.AccountInformation;
import loveqq.model.entity.AddFriendEntity;
import loveqq.model.entity.LQUser;
import loveqq.thread.runnable.OperationRunnable;
import loveqq.view.panels.mainview.FriendsListPane;
import net.sf.json.JSONObject;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jason
 * @version 1.0
 * @date 12/16/2019 10:04 PM
 * @describe:
 */
public class UDPUtils {
    //TCP
    private static Socket socket;


//    //The boolean data default is false by JVM.
//   public static boolean signOfSuccess;
   private static DatagramSocket sendSocket,receiveSocket;
   private static InetSocketAddress serverAddress;
   //Load these statements only one.
   static{
       try{
           //TCP
           socket=new Socket();

           //System dispatch port to the program.
           sendSocket =new DatagramSocket();
           //SendSocket's Timeout is 5s!!!
           sendSocket.setSoTimeout(R.LQServer.SERVER_RESPONSE_TIMEOUT);

           receiveSocket=new DatagramSocket();

           //ReceiveSocket's Timeout is forever!!!
           receiveSocket.setSoTimeout(0);

           //Server address
           serverAddress =new InetSocketAddress(R.LQServer.LQ_SERVER_ADDRESS,R.LQServer.LQ_SERVER_PORT);
       }catch(SocketException e){
           e.printStackTrace();
       }catch(Exception e){
           e.printStackTrace();
       }
       
   }
    /**
     * @author: Jason
     * @date: 12/20/2019
     * @time: 3:41 PM
     * @param
     * @return
     * @describe: Return (Global) Receive UDP Socket.
     */
    public static DatagramSocket getReceiveSocket() {
        return receiveSocket;
    }


    /**
    * @author: Jason
    * @date: 12/17/2019
    * @time: 7:20 PM
    * @param
    * @return
    * @describe: Return (Global) Send UDP Socket.
    */
   public static DatagramSocket getSendSocket(){
       return sendSocket;
   }
   /**
    * @author: Jason
    * @date: 12/17/2019
    * @time: 7:31 PM
    * @param  
    * @return 
    * @describe: Get Server SocketAddress
    */
   public static InetSocketAddress getServerSocketAddress(){
       return serverAddress;
   }
   /**
    * @author: Jason
    * @date: 12/17/2019
    * @time: 8:22 PM
    * @param  jsonData
    * @return
    * @describe: Send UDP DatagramPacket to Server.
    */
   public static Object sendUDPPacket(JSONObject jsonData,int type){
       try {
           byte[] dataBytes=jsonData.toString().getBytes(R.Configs.DEFAULT_CHARSET);
           DatagramPacket packet = new DatagramPacket(dataBytes,0,dataBytes.length, serverAddress.getAddress(), serverAddress.getPort());
           //hole UDP Packet.
           String holeUDPData="{\"type\":"+R.JSONS.HOLE_UDP_TEST_PACKET+"}";
           byte[] holeUDPDataBytes=holeUDPData.getBytes(R.Configs.DEFAULT_CHARSET);
           DatagramPacket holeUDPPacket = new DatagramPacket(holeUDPDataBytes,0,holeUDPDataBytes.length, serverAddress.getAddress(), serverAddress.getPort());


           if(type!=R.Configs.CHECK_USER_IS_ONLINE_TYPE){
               sendSocket.send(packet);


               receiveSocket.send(holeUDPPacket);
           }else{
               receiveSocket.send(packet);
           }




           if(type==R.Configs.LOGOUT_SEND_TYPE||type==R.Configs.CHECK_USER_IS_ONLINE_TYPE||type==R.Configs.MESSAGE_FORWARD_SEND_TYPE){
               return true;
           }else{
             //   My Timeout Timer. -3000
               return responseTimer(sendSocket,type);
           }






       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
           return false;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
   }

    public static Socket getSocket() {
       if(socket.isConnected()){
          //No Operation
       }else{
           try {
               socket.connect(new InetSocketAddress(R.LQServer.LQ_SERVER_ADDRESS,33442));
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

        return socket;
    }

    /**
    * @author: Jason
    * @date: 12/17/2019
    * @time: 10:14 PM
    * @param
    * @return
    * @describe: Server Response Timer ---3s
    */
    private static Object responseTimer(DatagramSocket socket,int type){

        DatagramPacket responsePacket = new DatagramPacket(new byte[R.Configs.MESSAGE_BUFFER_SIZE], 0, R.Configs.MESSAGE_BUFFER_SIZE);

            try {
                socket.receive(responsePacket);
                JSONObject jsonObject=JSONObject.fromObject(new String(responsePacket.getData(),0,responsePacket.getLength(),R.Configs.DEFAULT_CHARSET));
                //--------test----------
                System.out.println(jsonObject);
                //----------------------
                //JSON Config Data,Very Important!!!
                Map<String,Class<?>> jsonConfig=new HashMap<String,Class<?>>();
                jsonConfig.put(R.JSONS.FRIENDS_JSON_FIELD,LQUser.class);

                //Switch Type
                switch(jsonObject.getInt(R.JSONS.RESULT_JSON_FIELD)){
                    case R.JSONS.SUCCESS_RESULT_VALUE:{
                        switch (type){
                            case R.Configs.LOGIN_SEND_TYPE:{
                                //Obtain Account Information
                                AccountInformation accountInformation=(AccountInformation) JSONObject.toBean(jsonObject,AccountInformation.class,jsonConfig);
                                //test
                                //System.out.println(accountInformation.getMe());
                                //Login Successful!
                                    return accountInformation;
                            }//Because ↑ Return sentence,So,"break" is unreachable forever! So,I delete this "break".
                            case R.JSONS.MESSAGE_FORWARD_TYPE_VALUE:{
                                  //Message Forward Successful
                                  return true;
                            }//Because ↑ Return sentence,So,"break" is unreachable forever! So,I delete this "break".
                            case R.Configs.CHECK_USER_IS_ONLINE_TYPE:{
                                return true;
                            }//Because ↑ Return sentence,So,"break" is unreachable forever! So,I delete this "break".
                            case R.Configs.ADD_FRIEND_TYPE:{

                            };break;
                            case R.Configs.SEARCH_FRIEND_TYPE:{
                                AddFriendEntity addFriendEntity=(AddFriendEntity)JSONObject.toBean(jsonObject,AddFriendEntity.class);
                                return addFriendEntity;
                            }

                        }
                    };break;
                    case R.JSONS.FAILED_RESULT_VALUE:{
                        switch (type){
                            case R.Configs.LOGIN_SEND_TYPE:{
                                    //Login Failed.
                                    //Obtain Account Failed Information
                                    AccountInformation accountInformation=(AccountInformation) JSONObject.toBean(jsonObject,AccountInformation.class,jsonConfig);
                                    //test
                                    //System.out.println(jsonObject.getString(R.JSONS.TIPS_JSON_FIELD));

                                    return accountInformation;
                            }//Because ↑ Return sentence,So,"break" is unreachable forever! So,I delete this "break".
                            case R.JSONS.MESSAGE_FORWARD_TYPE_VALUE:{
                                    //Message Forward Failed.
                                    return false;
                            }//Because ↑ Return sentence,So,"break" is unreachable forever! So,I delete this "break".
                            case R.Configs.CHECK_USER_IS_ONLINE_TYPE:{
                                return false;
                            }//Because ↑ Return sentence,So,"break" is unreachable forever! So,I delete this "break".

                            case R.Configs.SEARCH_FRIEND_TYPE:{
                                AddFriendEntity addFriendEntity=(AddFriendEntity)JSONObject.toBean(jsonObject,AddFriendEntity.class);
                                return addFriendEntity;
                            }
                        }
                    };break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;

    }
   public static void receivePacket(FriendsListPane friendsListPane){
       JSONObject messageJson=null;
       try {
           while(true) {
               DatagramPacket packet = new DatagramPacket(new byte[R.Configs.MESSAGE_BUFFER_SIZE], 0, R.Configs.MESSAGE_BUFFER_SIZE);
               receiveSocket.receive(packet);
               //---test------
               //System.out.println("这个地址收到了："+packet.getAddress()+","+packet.getPort());
               new Thread(new OperationRunnable(packet,friendsListPane)).start();
           }


           //messageJson=JSONObject.fromObject(new String(packet.getData(),0,packet.getLength(),R.Configs.DEFAULT_CHARSET));

//           //To respond to the LQ server.
//            receiveSocket.send(getResponseSuccessPacket());


       } catch (IOException e) {
           e.printStackTrace();

       }catch(Exception e){
           e.printStackTrace();
       }

   }
//   /**
//    * @author: Jason
//    * @date: 12/17/2019
//    * @time: 9:31 PM
//    * @param
//    * @return
//    * @describe: Return Response Packet for Server.
//    */
//   private static DatagramPacket getResponseSuccessPacket(){
//            byte[] data=R.JSONS.RESPONSE_JSON.getBytes();
//            return new DatagramPacket(data,0,data.length, serverAddress.getAddress(), serverAddress.getPort());
//   }
}

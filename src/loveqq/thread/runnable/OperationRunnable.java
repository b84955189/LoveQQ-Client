package loveqq.thread.runnable;

import loveqq.config.R;
import loveqq.model.entity.ResponseFriendEntity;
import loveqq.utils.UDPUtils;
import loveqq.view.components.FriendPane;
import loveqq.view.panels.mainview.FriendsListPane;
import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;

/**
 * @author Jason
 * @version 1.0
 * @date 12/25/2019 7:33 PM
 * @describe:
 */
public class OperationRunnable implements Runnable{
    private DatagramPacket packet;
    private FriendsListPane friendsListPane;
    private String tcpData;
    private boolean isTCP;
    public OperationRunnable(DatagramPacket packet, FriendsListPane friendsListPane){
        super();

        this.packet=packet;
        this.friendsListPane=friendsListPane;
        isTCP=false;
    }
    public OperationRunnable(String tcpData, FriendsListPane friendsListPane){
        super();

        this.tcpData=tcpData;
        this.friendsListPane=friendsListPane;
        isTCP=true;
    }

    @Override
    public void run() {
        try {
            String tempStr=null;
           if(!isTCP){
               tempStr = new String(packet.getData(), 0, packet.getLength(), R.Configs.DEFAULT_CHARSET);
           }else {
               tempStr=tcpData;
           }
            JSONObject jsonData = JSONObject.fromObject(tempStr);
           System.out.println("还是测试："+jsonData.toString());
            switch (jsonData.getInt(R.JSONS.TYPE_JSON_FIELD)) {
                case R.JSONS.MESSAGE_FORWARD_TYPE_VALUE:{
                    //---------test--------
///                    System.out.println("这个地址收到了转发信息："+packet.getAddress()+","+packet.getPort());
                    //---------------------
                    int fromID=jsonData.getInt(R.JSONS.MESSAGE_FROM_FIELD);

                    FriendPane friendPane=friendsListPane.getFriendPane(fromID);
                    if(friendPane!=null)
                        friendPane.addMessage(jsonData.getString(R.JSONS.MESSAGE_CONTENT_FIELD));


                };break;
                case R.JSONS.CHECK_USER_IS_ONLINE_VALUE:{
                    //---test----
                    System.out.println("这个地址收到了："+packet.getAddress()+","+packet.getPort());
                    //-----------
                    JSONObject tempJsonObject=new JSONObject();
                    tempJsonObject.put(R.JSONS.TYPE_JSON_FIELD,R.JSONS.CHECK_USER_IS_ONLINE_VALUE);
                    tempJsonObject.put(R.JSONS.CLIENT_RECEIVE_PORT_FIELD,UDPUtils.getReceiveSocket().getLocalPort());
                    UDPUtils.sendUDPPacket(tempJsonObject,R.Configs.CHECK_USER_IS_ONLINE_TYPE);
                };break;
                case R.JSONS.ADD_FRIEND_TYPE_VALUE:{
                    ResponseFriendEntity responseFriendEntity=(ResponseFriendEntity) JSONObject.toBean(jsonData,ResponseFriendEntity.class);
                    friendsListPane.addFriendRepaint(responseFriendEntity.getFriend());
                };break;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

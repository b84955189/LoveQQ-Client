package loveqq.model.entity;

/**
 * @author Jason
 * @version 1.0
 * @date 1/2/2020 12:35 AM
 * @describe:
 */
public class ResponseFriendEntity {

    /**
     * friend : {"user_head_url":"","user_id":0,"user_login":"","display_name":"","user_pass":""}
     * type : 43453
     */

    private LQUser friend;
    private int type;

    public LQUser getFriend() {
        return friend;
    }

    public void setFriend(LQUser friend) {
        this.friend = friend;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}

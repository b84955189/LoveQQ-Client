package loveqq.model.entity;

/**
 * @author Jason
 * @version 1.0
 * @date 1/1/2020 4:32 PM
 * @describe:
 */
public class AddFriendEntity {

    /**
     * result : 200
     * friend : {"user_head_url":"","user_id":0,"user_login":"","display_name":"","user_pass":""}
     * tips : 失败
     */

    private int result;
    private LQUser friend;
    private String tips;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public LQUser getFriend() {
        return friend;
    }

    public void setFriend(LQUser friend) {
        this.friend = friend;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }


}

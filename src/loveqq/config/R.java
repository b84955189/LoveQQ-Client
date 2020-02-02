package loveqq.config;

import java.awt.*;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @author Jason
 * @version 1.0
 * @date 12/10/2019 4:35 PM
 * @describe: Global Resource Reference Class
 */
public class R {

    public interface Configs{
        //Global Charset
        String DEFAULT_CHARSET="UTF-8";
        //Message Buffer Size
        int MESSAGE_BUFFER_SIZE=10240;

        //UDP Send Type
        //UDP Login Type
        int LOGIN_SEND_TYPE=3456;
        //UDP Message Forward Type
        int MESSAGE_FORWARD_SEND_TYPE=321;
        //Log out Type
        int LOGOUT_SEND_TYPE=32142;
        //CHeck User Is Online.
        int CHECK_USER_IS_ONLINE_TYPE=23452;

        int ADD_FRIEND_TYPE=43326;
        int SEARCH_FRIEND_TYPE=23454;
        //Image Scale' Multiple
        int IMAGE_SCALE_MULTIPLE=2;
        //Image Size After Scaled.
        int IMAGE_SCALED_SIZE=200;

        //Head Portrait Image Format
        String HEAD_PORTRAIT_FORMAT="JPG";



    }
    public interface Strings{
        //Empty Content
        String EMPTY_CONTENT="";
        //SignInTop
        String TITLE_LOGO="LIM";
        //LoginView
        String LOGIN_VIEW_TITLE="Welcome to LIM!";

        //SignInPane
        String SIGN_IN_BUTTON="登   录";
        String ACCOUNT_HINT_FIELD="LIM账号";
        String PASSWORD_HINT_FIELD="密码";
        String REGISTER_ACCOUNT_LABEL="注册账号";
        String FORGOT_PASSWORD_LABEL="忘记密码";
        String SAVE_PASSWORD_CHECK_BOX="记住密码";

        //Login Load Bottom Pane
        String CANCEL_BUTTON="取消登录";

        //Chat View
        String SEND_MESSAGE_BUTTON="发送";

        //Add Friend Pane
        String ADD_FRIEND_PANE_TITLE="添加好友";
        String ADD_FRIEND_ACCOUNT="账号：";
        String ADD_FRIEND_SEARCH_BUTTON="搜索";

    }
    public interface Dimensions{
        //Default Icon Size
        int ICON_WIDTH=40;
        int ICON_HEIGHT=40;

        int MINIMIZE_ICON_WIDTH=20;
        int MINIMIZE_ICON_HEIGHT=30;

        int CLOSE_ICON_WIDTH=20;
        int CLOSE_ICON_HEIGHT=20;

        int SIGN_IN_TOP_ICON_WIDTH=140;
        int SIGN_IN_TOP_ICON_HEIGHT=140;


        //LoginView
        int LOGIN_VIEW_WIDTH=540;
        int LOGIN_VIEW_HEIGHT=410;
        //MainView
        int MAIN_VIEW_WIDTH=330;
        int MAIN_VIEW_HEIGHT=800;

        int TOP_USER_INFORMATION_PANE_HEIGHT=250;

        int BOTTOM_PANE_HEIGHT=50;

        //Top User Information Pane
        int DISPLAY_NAME_WIDTH=200;
        int DISPLAY_NAME_HEIGHT=30;

        int SOFT_WARE_NAME_WIDTH=50;
        int SOFT_WARE_NAME_HEIGHT=20;

        //Friend Pane
        int FRIEND_PANE_HEIGHT=80;
        Dimension FRIEND_PANE_SIZE=new Dimension(R.Dimensions.MAIN_VIEW_WIDTH,FRIEND_PANE_HEIGHT);

        int ROUND_ICON_WIDTH=32;
        int ROUND_ICON_HEIGHT=32;

        int FRIEND_DISPLAY_NAME_WIDTH=100;
        int FRIEND_DISPLAY_NAME_HEIGHT=30;

        //SignInBottomPane
        int SIGN_IN_BUTTON_WIDTH=245;
        int SIGN_IN_BUTTON_HEIGHT=40;

        int ACCOUNT_FIELD_WIDTH=240;
        int ACCOUNT_FIELD_HEIGHT=38;

        int PASSWORD_FIELD_WIDTH=240;
        int PASSWORD_FIELD_HEIGHT=38;

        int DEFAULT_HEAD_PORTRAIT_WIDTH=120;
        int DEFAULT_HEAD_PORTRAIT_HEIGHT=120;

        //Chat View  740,660
        int CHAT_VIEW_WIDTH=740;
        int CHAT_VIEW_HEIGHT=660;

        int SEND_MESSAGE_BUTTON_WIDTH=100;
        int SEND_MESSAGE_BUTTON_HEIGHT=30;

        int MESSAGE_LIST_SCROLL_PANE_HEIGHT=410;

        int MESSAGE_SCROLL_PANE_HEIGHT=130;

        //TopTitle Bar
        int HEAD_PORTRAIT_HEIGHT=35;
        int HEAD_PORTRAIT_WIDTH=35;

        //Message Pane
        int MESSAGE_PANE_HEIGHT=60;

        //Default TopTitleBar Height
        int TOP_TITLE_BAR_HEIGHT=50;

        int TOP_TITLE_BAR_CLOSE_ICON_X=40;
        int TOP_TITLE_BAR_MINIMIZE_ICON_X=80;




    }
    public interface Sounds{
        String PROMPT_SOUND="./sounds/prompt_tone.wav";
    }
    public interface Images{
        //Global Icon
        String ICON="resources\\images\\icon.png";
        //Top Title Bar Panel
        String CLOSE_ICON="resources\\images\\close_icon.png";
        String CLOSE_ICON_COPY="resources\\images\\close_icon_copy.png";
        String MINIMIZE_ICON="resources\\images\\minimize_icon.png";
        String MINIMIZE_ICON_COPY="resources\\images\\minimize_icon_copy.png";
        //Background Image
        String SIGN_IN_TOP_BG="resources\\images\\sign_in_top_bg.jpg";
        String BUTTON_BG="resources\\images\\button_bg.png";
        //Default User Head Portrait
        String DEFAULT_HEAD_PORTRAIT="resources\\images\\default_head_portrait.png";
        String TEST_HEAD_PORTRAIT="resources\\images\\test_head_portrait.jpeg";

        //Friend Pane
        String MESSAGE_COUNT_ICON="resources\\images\\message_count_icon.png";

        //Chat Pane
        String CHAT_SKIN_BG="resources\\images\\chat_skin_bg.png";
        //Bubble Skin
        String CHAT_BUBBLE_SKIN="resources\\images\\chat_bubble_skin.png";

        //Add Friend Icon
        String ADD_FRIEND_ICON="resources\\images\\add_friend_icon.png";
        String ADD_FRIEND_ICON_COPY="resources\\images\\add_friend_icon_copy.png";


    }
    public interface Fonts{
        //Default Font
        String MICROSOFT_YAHEI="微软雅黑";
        //Default Font Size
        int DEFAULT_FONT_SIZE=15;
    }
    public interface URLS{
        String REGISTER_URL="https://www.lking.top/wp-login.php?action=register";
        String FORGET_URL="https://www.lking.top/wp-login.php?action=lostpassword";
    }
    public interface LQServer{
        //========服务器地址+端口==============
        String LQ_SERVER_ADDRESS="服务器IP";
        int LQ_SERVER_PORT=000;//服务器登录/消息转发端口
        int SERVER_RESPONSE_TIMEOUT=000;//服务器响应端口
        //==============================

    }
    public interface JSONS{
        //JSON FIELD
        String TYPE_JSON_FIELD="type";//---↓↓↓ Value
        String ACCOUNT_JSON_FIELD="account";
        String PASSWORD_JSON_FIELD="password";
        String RESULT_JSON_FIELD="result";
        String ME_JSON_FIELD="me";
        String FRIENDS_JSON_FIELD="friends";
        String TIPS_JSON_FIELD="tips";
        String FRIEND_JSON_FIELD="friend";

        String CLIENT_RECEIVE_PORT_FIELD="cport";

        String MESSAGE_FROM_FIELD="from";
        String MESSAGE_TO_FIELD="to";
        String MESSAGE_DATE_FIELD="date";
        String MESSAGE_TYPE_FIELD="mtype";
        String MESSAGE_CONTENT_FIELD="content";

        //JSON FIELD VALUE
        int LOGIN_REQUEST_TYPE_VALUE=10101;
        int MESSAGE_FORWARD_TYPE_VALUE=1221;
        int LOGOUT_REQUEST_TYPE_VALUE=13232;

        int ADD_FRIEND_TYPE_VALUE=43453;

        int SEARCH_FRIEND_TYPE_VALUE=24423;

        int CHECK_USER_IS_ONLINE_VALUE=43222;



        int HOLE_UDP_TEST_PACKET=12312;

        int SUCCESS_RESULT_VALUE=200;
        int FAILED_RESULT_VALUE=500;
    }
    public interface DataDirectory{
        String PERSONAL_DATA_PATH="./LIMPersonalData";


        //Every User's Directory Belong. ↓
        String ME_PARENT_PATH_NAME="Me";

        String FRIENDS_PARENT_PATH_NAME="Friends";
        String GROUPS_PARENT_PATH_NAME="Groups";

        //Child Directory
        String ME_DATA_PATH_NAME="data";//---Me Directory.

        String FRIENDS_IMAGE_PATH_NAME="image";
        String FRIENDS_MESSAGE_PATH_NAME="message";



        //Every User's Data Name.
        String HEAD_PORTRAIT_NAME="head_portrait.jpg";
        String MESSAGE_DATA_NAME="message.db";

        //Current User's File Name
        String ME_DATA_NAME="data.data";



    }
}

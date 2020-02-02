package loveqq.main;

import loveqq.config.R;
import loveqq.model.entity.LQUser;
import loveqq.view.frames.LoginView;
import net.sf.json.JSONObject;

import javax.swing.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/10/2019 4:27 PM
 * @describe: Main Test Class
 */
public class MainTest {
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            //UIManager.setLookAndFeel("");
            new LoginView().setVisible(true);






        });
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put(R.JSONS.RESULT_JSON_FIELD,200);
//        jsonObject.put(R.JSONS.TIPS_JSON_FIELD,"失败");
//        jsonObject.put(R.JSONS.FRIEND_JSON_FIELD,new LQUser());
//        System.out.println(jsonObject.toString());

    }
}

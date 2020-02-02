package loveqq.thread.runnable;

import loveqq.config.R;
import loveqq.model.entity.AccountInformation;
import loveqq.model.entity.LQUser;
import loveqq.utils.ImageUtils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @author Jason
 * @version 1.0
 * @date 12/22/2019 8:25 PM
 * @describe:
 */
public class HandleUserDataRunnable implements Runnable{
    private AccountInformation userData;
    private  String temp_password;
    private boolean isSavePass;
    public HandleUserDataRunnable(AccountInformation userData,String temp_password,boolean isSavePass){
        super();
        this.userData=userData;
        this.temp_password=temp_password;
        this.isSavePass=isSavePass;
    }
    @Override
    public void run() {
        LQUser currentUser = userData.getMe();
        List<LQUser> friends = userData.getFriends();
        //List<File> friendsDataPath=new ArrayList<File>();
        //Parent Path
        File personalUserData = new File(R.DataDirectory.PERSONAL_DATA_PATH);
        //Current User Path
        File currentUserDataPath = new File(personalUserData, currentUser.getUser_login());
        //IF Path Not Exists.
        isPathExists(currentUserDataPath);
        //Current User Resources Path.
        File mePath = new File(currentUserDataPath, R.DataDirectory.ME_PARENT_PATH_NAME);
        //Current User Friends Data Path.
        File friendsPath = new File(currentUserDataPath, R.DataDirectory.FRIENDS_PARENT_PATH_NAME);
        //Current User Group Data Path.
        File groupsPath = new File(currentUserDataPath, R.DataDirectory.GROUPS_PARENT_PATH_NAME);
        //IF Path Not Exists.
        isPathExists(mePath);
        isPathExists(friendsPath);
        isPathExists(groupsPath);

        //Create Me Data.
        createMeData(mePath,currentUser);

        //Create Friends Path And Data By Cycle.
        createFriendsPathAndData(friendsPath,friends);

        //Message Data




    }
    /**
     * @author: Jason
     * @date: 12/23/2019
     * @time: 8:20 AM
     * @param
     * @return
     * @describe: Create Me Data.
     */
    private void createMeData(File mePath,LQUser me){
        File tempImagePath=new File(mePath,R.DataDirectory.FRIENDS_IMAGE_PATH_NAME);
        File meDataPath=new File(mePath,R.DataDirectory.ME_DATA_PATH_NAME);

        File tempHeadPortraitFile=new File(tempImagePath,R.DataDirectory.HEAD_PORTRAIT_NAME);
        File currentUserDataFile=new File(meDataPath,R.DataDirectory.ME_DATA_NAME);

        isPathExists(tempImagePath);
        isPathExists(meDataPath);


    if(isSavePass){
        //Save Current User Data
        DataOutputStream dataOutputStream=null;
        try {
            dataOutputStream=new DataOutputStream(new FileOutputStream(currentUserDataFile));
            //Save Password(Hex MD5)
            // dataOutputStream.writeUTF(me.getUser_pass());
            //Save pass
            dataOutputStream.writeUTF(temp_password);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(dataOutputStream!=null) {
                try {
                    dataOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
        //DownLoad HeadPortrait
        //ImageUtils.obtainNetworkImage(me.getUser_head_url(),tempHeadPortraitFile);
    }
    /**
     * @author: Jason
     * @date: 12/23/2019
     * @time: 8:13 AM
     * @param
     * @return
     * @describe: Create Friends Path And Data By Cycle.
     */
    private void createFriendsPathAndData(File currentUserFriendPath,List<LQUser> friends){
        for(LQUser lqUser:friends){
            File tempFriendPath=new File(currentUserFriendPath,lqUser.getUser_login());

            File tempImagePath=new File(tempFriendPath,R.DataDirectory.FRIENDS_IMAGE_PATH_NAME);
            File tempHeadPortraitFile=new File(tempImagePath,R.DataDirectory.HEAD_PORTRAIT_NAME);



            File tempMessagePath=new File(tempFriendPath,R.DataDirectory.FRIENDS_MESSAGE_PATH_NAME);


            //IF Path Not Exists.
            isPathExists(tempImagePath);
            isPathExists(tempMessagePath);



            //DownLoad HeadPortrait
            //ImageUtils.obtainNetworkImage(lqUser.getUser_head_url(),tempHeadPortraitFile);


        }
    }
    /**
     * @author: Jason
     * @date: 12/22/2019
     * @time: 8:29 PM
     * @param
     * @return
     * @describe: Check Path Whether Exists.
     */
    private void isPathExists(File file){

        if (!file.exists()) {
            file.mkdirs();
        }
    }


}

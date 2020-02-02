package loveqq.view.panels.loginview;

import loveqq.base.BaseFrame;
import loveqq.base.BasePanel;
import loveqq.config.R;
import loveqq.thread.runnable.LoginViewSwitchRunnable;
import loveqq.view.components.HeadPortraitPane;
import loveqq.view.components.LQButton;

import java.awt.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/20/2019 10:14 PM
 * @describe:
 */
public class LoginLoadBottomPane extends BasePanel {
    private BaseFrame frame;
    private HeadPortraitPane headPortraitPane;
    private LQButton cancelButton;

    private LoginViewSwitchRunnable loginViewSwitchRunnable;

    public LoginLoadBottomPane(String account,BaseFrame frame, LoginViewSwitchRunnable loginViewSwitchRunnable){
        super();

        this.frame=frame;
        this.loginViewSwitchRunnable=loginViewSwitchRunnable;


        //Prevent Parent Class Method Can't Init Account.
        headPortraitPane=new HeadPortraitPane(account,new Rectangle(210,5, R.Dimensions.DEFAULT_HEAD_PORTRAIT_WIDTH,R.Dimensions.DEFAULT_HEAD_PORTRAIT_HEIGHT));
        this.add(headPortraitPane);
        //Enable Drag
        enableDrag(frame);


    }
    @Override
    protected void initPanel() {
        //Set Layout is null.
        this.setLayout(null);
    }

    @Override
    protected void initComponents() {

        cancelButton=new LQButton(R.Strings.CANCEL_BUTTON,new Rectangle(147,145,R.Dimensions.SIGN_IN_BUTTON_WIDTH,R.Dimensions.SIGN_IN_BUTTON_HEIGHT));

    }

    @Override
    protected void addComponents() {


        this.add(cancelButton);
    }

    @Override
    protected void addListeners() {
        //Cancel Button
        cancelButton.addActionListener(e->{
            //Cancel Login
            loginViewSwitchRunnable.setCancel(true);
        });

    }
}

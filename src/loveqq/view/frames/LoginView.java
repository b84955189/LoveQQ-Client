package loveqq.view.frames;

import loveqq.base.BaseFrame;
import loveqq.config.R;
import loveqq.utils.CommonUtils;
import loveqq.view.panels.loginview.SignInBottomPane;
import loveqq.view.panels.loginview.SignInTopPane;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jason
 * @version 1.0
 * @date 12/10/2019 4:22 PM
 * @describe: Login View Class
 */
public class LoginView extends BaseFrame {
    private SignInTopPane signInTopPane;
    private SignInBottomPane signInBottomPane;
    @Override
    protected void initFrame() {
        this.setTitle(R.Strings.LOGIN_VIEW_TITLE);
        this.setSize(R.Dimensions.LOGIN_VIEW_WIDTH,R.Dimensions.LOGIN_VIEW_HEIGHT);

        //Set Frame Always On Top
        this.setAlwaysOnTop(true);
        //Use Grid Layout
        this.setLayout(new GridLayout(2,1,0,0));

        super.initFrame();
        //CommonUtils.setFrameRoundAngle(this);

    }

    @Override
    protected void initComponents() {
        signInTopPane=new SignInTopPane(this, CommonUtils.getImage(R.Images.SIGN_IN_TOP_BG));
        signInBottomPane=new SignInBottomPane(this);
    }

    @Override
    protected void addComponents() {
        this.add(signInTopPane);
        this.add(signInBottomPane);
    }

    @Override
    protected void addListeners() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //This is switch focus.
                LoginView.this.requestFocus();
            }
        });
    }
}

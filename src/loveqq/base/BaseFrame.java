package loveqq.base;

import javax.swing.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/5/2019 3:22 PM
 * @describe  Base JFrame Object
 */
public abstract class BaseFrame extends JFrame {
    public BaseFrame(){
        super();
        //BaseFrame.setDefaultLookAndFeelDecorated(true);
        this.initFrame();
        this.initComponents();
        this.addComponents();
        this.addListeners();

    }
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 3:49 PM
     * @param  null
     * @return void
     * @describe: Init Frame
     */
    protected void initFrame(){


        //cancel default title bar.
        this.setUndecorated(true);
        //cancel LookAndFeel's default title bar  -->BeautyEye LAF
        //this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        //set Frame location to center.
        this.setLocationRelativeTo(null);
        //set default close method -- only swing belong this method.
        this.setDefaultCloseOperation(BaseFrame.EXIT_ON_CLOSE);
    }
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 3:50 PM
     * @param  null
     * @return void
     * @describe: Init Component
     */
    protected abstract void initComponents();
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 3:51 PM
     * @param null
     * @return void
     * @describe: Add Components for Frame.
     */
    protected abstract void addComponents();
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 3:53 PM
     * @param  null
     * @return void
     * @describe: Add Listener for Components
     */
    protected abstract void addListeners();
}

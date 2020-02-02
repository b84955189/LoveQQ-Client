package loveqq.base;

import javax.swing.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/10/2019 3:59 PM
 * @describe: Base JDialog Class
 */
public abstract class BaseDialog extends JDialog {
    public BaseDialog(){
        super();
        this.initDialog();
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
     * @describe: Init Dialog
     */
    protected void initDialog(){
        this.setUndecorated(true);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(BaseDialog.DISPOSE_ON_CLOSE);
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
     * @describe: Add Components for Dialog.
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

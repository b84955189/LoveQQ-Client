package loveqq.view.components;

import loveqq.base.BaseDialog;
import loveqq.config.R;
import loveqq.utils.CommonUtils;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jason
 * @version 1.0
 * @date 1/1/2020 3:46 PM
 * @describe:
 */
public class AlertDialog extends BaseDialog {
    private TopTitleBar topTitleBar;
    private JLabel tipLabel;
    public AlertDialog(String tipString){
        super();

        SwingUtilities.invokeLater(()->{
            tipLabel.setText(tipString);
        });

    }

    @Override
    protected void initDialog() {

        this.setLayout(null);
        this.setSize(300,200);
        this.setAlwaysOnTop(true);
        super.initDialog();
    }

    @Override
    protected void initComponents() {
        topTitleBar=new TopTitleBar(this);
        tipLabel=new JLabel();
        tipLabel.setFont(CommonUtils.getDefaultFont(Font.PLAIN,40));
        tipLabel.setBounds((this.getWidth()-200)/2,(this.getHeight()- R.Dimensions.TOP_TITLE_BAR_HEIGHT-150)/2,200,150);
        tipLabel.setHorizontalAlignment(JLabel.CENTER);
        tipLabel.setVerticalAlignment(JLabel.CENTER);
    }

    @Override
    protected void addComponents() {
        this.add(topTitleBar);
        this.add(tipLabel);
    }

    @Override
    protected void addListeners() {

    }
}

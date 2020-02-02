package loveqq.view.components;

import loveqq.base.BaseDialog;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/16/2019 9:19 PM
 * @describe:
 */
public class Toast extends BaseDialog {
    public static final int TYPE_INPUT_TIP=0;
    public static final int TYPE_DEFAULT_TIP=1;

    private JLabel triangleBGCarrierLabel,rectangleBGCarrierLabel;
    private Toast(){
        super();
    }

    @Override
    protected void initDialog() {

    }

    @Override
    protected void initComponents() {

    }

    @Override
    protected void addComponents() {

    }

    @Override
    protected void addListeners() {

    }
    public static void showText(String message, Point point,int type){

    }
}

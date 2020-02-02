package loveqq.view.components;

import loveqq.config.R;
import loveqq.utils.CommonUtils;


import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jason
 * @version 1.0
 * @date 12/11/2019 9:08 AM
 * @describe: Project Default Theme Button
 */
public class LQButton extends JButton{
    public LQButton(String content,Rectangle rectangle){
        super(content);
        this.initButton(rectangle);
        this.addListeners();


    }
    /**
     * @author: Jason
     * @date: 12/11/2019
     * @time: 12:04 PM
     * @param
     * @return void
     * @describe: Init Button
     */
    private void initButton(Rectangle rectangle){



        this.setBackground(new Color(0,163,255));
        this.setBounds(rectangle);
        this.setFont(CommonUtils.getDefaultFont(Font.PLAIN,R.Fonts.DEFAULT_FONT_SIZE));
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setForeground(Color.WHITE);
        //this.setContentAreaFilled(false);
        //this.setIcon(new ImageIcon(CommonUtils.getDIVImage(this.getWidth()-10,this.getHeight(), R.Images.BUTTON_BG)));
    }
    /**
     * @author: Jason
     * @date: 12/11/2019
     * @time: 12:40 PM
     * @param
     * @return void
     * @describe: Add Listeners
     */
    private void addListeners(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(()->{
                    LQButton.this.setBackground(new Color(0,199,244));
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(()->{
                    LQButton.this.setBackground(new Color(0,163,255));
                });
            }
        });
    }


}

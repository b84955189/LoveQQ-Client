package loveqq.view.components;

import loveqq.utils.CommonUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

/**
 * @author Jason
 * @version 1.0
 * @date 12/11/2019 4:59 PM
 * @describe:
 */
public class LQLinkLabel extends JLabel {
    private String url;
    public LQLinkLabel(String content,String url){
        super(content);
        this.url=url;
        this.initLabel();
        this.addListeners();
    }
    private void initLabel(){
        this.setForeground(CommonUtils.getDefaultColor());
        //Set Cursor
        LQLinkLabel.this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setFont(CommonUtils.getDefaultFont(Font.PLAIN,15));
    }
    private void addListeners(){
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(Desktop.isDesktopSupported()&&Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
                    try {
                        //Attention here URI&URL&URN.
                        Desktop.getDesktop().browse(URI.create(url));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SwingUtilities.invokeLater(()->{
                    LQLinkLabel.this.setForeground(new Color(0,199,244));
                });
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SwingUtilities.invokeLater(()->{
                    LQLinkLabel.this.setForeground(CommonUtils.getDefaultColor());
                });
            }
        });
    }
}

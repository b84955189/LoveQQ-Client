package loveqq.base;

import loveqq.view.components.TopTitleBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * @author Jason
 * @version 1.0
 * @date 12/10/2019 3:55 PM
 * @describe: Base JPanel Class
 */
public abstract class BasePanel extends JPanel {
    //Pressed Point
    private Point pressPoint;


    public BasePanel(){
        super();
        this.initPanel();
        this.initComponents();
        this.addComponents();
        this.addListeners();
    }

    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 3:49 PM
     * @param
     * @return void
     * @describe: Init Panel
     */
    protected abstract void initPanel();
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 3:50 PM
     * @param
     * @return void
     * @describe: Init Component
     */
    protected abstract void initComponents();
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 3:51 PM
     * @param
     * @return void
     * @describe: Add Components for Panel.
     */
    protected abstract void addComponents();
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 3:53 PM
     * @param
     * @return void
     * @describe: Add Listener for Panel
     */
    protected abstract void addListeners();
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 11:36 PM
     * @param
     * @return void
     * @describe: Enable Panel Drag
     */
    protected void enableDrag(BaseFrame frame){
        //Control Frame Move
        this.addMouseListener(new MouseAdapter(){

            public void mousePressed(MouseEvent e){
                pressPoint=e.getPoint();
                BasePanel.this.requestFocus();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                Point currentPoint=e.getPoint();
                Point locationPoint=frame.getLocation();
                int x=locationPoint.x+currentPoint.x-pressPoint.x;
                int y=locationPoint.y+currentPoint.y-pressPoint.y;
                frame.setLocation(x,y);
            }

        });
    }
    protected void enableDrag(BaseDialog dialog){
        //Control Frame Move
        this.addMouseListener(new MouseAdapter(){

            public void mousePressed(MouseEvent e){
                pressPoint=e.getPoint();
                BasePanel.this.requestFocus();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
                Point currentPoint=e.getPoint();
                Point locationPoint=dialog.getLocation();
                int x=locationPoint.x+currentPoint.x-pressPoint.x;
                int y=locationPoint.y+currentPoint.y-pressPoint.y;
                dialog.setLocation(x,y);
            }

        });
    }
    /**
     * @author: Jason
     * @date: 12/10/2019
     * @time: 11:43 PM
     * @param
     * @return void
     * @describe: Init Title Bar
     */
    protected void initTitleBar(TopTitleBar topTitleBar){
        this.add(topTitleBar);
    }

}

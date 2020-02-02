package loveqq.view.components;

import loveqq.base.BasePanel;
import loveqq.config.R;
import loveqq.utils.CommonUtils;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/26/2019 9:17 AM
 * @describe:
 */
public class BubblePane extends BasePanel {
    public final static int COMMON_MESSAGE_TYPE=0;
    public final static int EXPRESSION_MESSAGE_TYPE=1;
    private final static int MAX_WIDTH=500;
    private JTextPane contentPane;
    private JTextArea contentArea;
    public BubblePane(StringBuffer message,int type){
        super();

        switch(type){
            case COMMON_MESSAGE_TYPE:{this.commonMessage(message);};break;

            case EXPRESSION_MESSAGE_TYPE:{this.expressionMessage(message);};break;
        }





    }
    @Override
    protected void initPanel() {
        this.setOpaque(true);

    }
    private void expressionMessage(StringBuffer message){
        contentPane=new JTextPane();
        contentPane.setOpaque(false);
        contentPane.setEditable(false);
        contentPane.setForeground(Color.WHITE);
        contentPane.setFont(CommonUtils.getDefaultFont(Font.PLAIN,20));
        contentPane.setMargin(new Insets(5,5,5,5));


        int tempWidth=0;
        int len=message.length();
        FontMetrics fontMetrics=contentPane.getFontMetrics(contentPane.getFont());
        for(int i=0;i<len;i++){
            tempWidth+=fontMetrics.charWidth(message.charAt(i));
            if(tempWidth>MAX_WIDTH){
                message.insert(i,"\n");
                tempWidth=0;
            }
        }


        contentPane.setText(message.toString());


        this.add(contentPane);




    }

    private void commonMessage(StringBuffer message){

        contentArea=new JTextArea();
        //contentArea.setMaximumSize(new Dimension(MAX_WIDTH,200));
        contentArea.setForeground(Color.WHITE);
        contentArea.setFont(CommonUtils.getDefaultFont(Font.PLAIN,20));
        contentArea.setMargin(new Insets(5,5,5,5));


        contentArea.setOpaque(false);






        int tempWidth=0;
        int len=message.length();
        FontMetrics fontMetrics=contentArea.getFontMetrics(contentArea.getFont());

        for(int i=0;i<len;i++){
            tempWidth+=fontMetrics.charWidth(message.charAt(i));
            if(tempWidth>MAX_WIDTH){
                message.insert(i,"\n\r");
                tempWidth=0;
            }
        }
        contentArea.setText(message.toString());
        //contentArea.setLineWrap(true);
        this.add(contentArea);
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


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        System.out.println("ggg："+this.getHeight()+","+this.getWidth());

        g.drawImage(CommonUtils.getImage(R.Images.CHAT_BUBBLE_SKIN),0,0,this.getWidth(),this.getHeight(),this);

    }
}

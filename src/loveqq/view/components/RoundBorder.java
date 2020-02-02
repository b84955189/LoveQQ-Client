package loveqq.view.components;

import javax.swing.border.Border;
import java.awt.*;

/**
 * @author Jason
 * @version 1.0
 * @date 12/11/2019 9:24 AM
 * @describe: Custom Border Class
 */
public class RoundBorder implements Border {
    private Color color;
    public RoundBorder(){
        super();
        //Default Border Color.
        color=Color.WHITE;
    }
    public RoundBorder(Color color){
        super();
        this.color=color;
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(color);
            //g.drawRoundRect(0,0,c.getWidth()-1,c.getHeight()-1,10,10);
            g.drawArc(0,0,c.getWidth()-1,c.getHeight()-1,0,180);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}

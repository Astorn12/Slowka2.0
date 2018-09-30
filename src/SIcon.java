import javax.swing.*;
import java.awt.*;

/**
 * Created by osiza on 19.02.2018.
 */
public class SIcon implements Icon {

    int green;
    int red;
    SIcon(int green,int red)
    {
        this.green=green;
        this.red=red;
    }

    

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
       // Rectangle rec=new Rectangle(x,y,c.getWidth(),c.getHeight());
        g.setColor(Color.cyan);
        g.fillRect(x,y,c.getWidth(),c.getHeight());

    }

    @Override
    public int getIconWidth() {
        return 0;
    }

    @Override
    public int getIconHeight() {
        return 0;
    }
}

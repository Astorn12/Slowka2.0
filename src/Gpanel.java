import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by osiza on 05.02.2018.
 */
public class Gpanel extends JPanel {
    private BufferedImage background;
    int height;
    int width;
    public Gpanel(BufferedImage background,int width,int height)
    {

        this.background=background;
        this.height=height;
        this.width=width;
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(this.background!=null)
        {
            int w = background.getWidth();
            int h = background.getHeight();
            Graphics2D g2d=(Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            int newW=this.width;
                    int newH=this.height;
            g2d.drawImage(background, 0, 0, newW, newH, 0, 0, w, h, null);
           // int x = getWidth();// - background.getWidth();
           // int y = getHeight();// - background.getHeight();
            //g2d.drawImage(background,0,0,this);
            g2d.dispose();
        }
    }

}

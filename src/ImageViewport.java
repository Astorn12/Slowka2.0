import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by osiza on 26.10.2017.
 */
public class ImageViewport extends JViewport {
    Paint texture;

    public ImageViewport(Component component) {
        BufferedImage image = loadImage();
        int w=250;
        int h=600;
        //int w = component.getY();//image.getWidth() / 2;
        //int h = component.getX();//image.getHeight() / 2;

        //int w = image.getWidth() / 2;
        //int h = image.getHeight() / 2;
        Rectangle2D r = new Rectangle2D.Double(0, 0, w, h);
        texture = new TexturePaint(image, r);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        int w = getWidth();
        int h = getHeight();
        g2.setPaint(texture);
        g2.fillRect(0, 0, w, h);
    }

    private BufferedImage loadImage() {
        BufferedImage image = null;
        String fileName ="Pictures\\BigBen.jpg" ;//"images/cougar.jpg";
        try {
            URL url = getClass().getResource(fileName);
            image = ImageIO.read(url);
        } catch (MalformedURLException mue) {
            System.out.println("url: " + mue.getMessage());
        } catch (IOException ioe) {
            System.out.println("read: " + ioe.getMessage());
        }
        return image;
    }
}




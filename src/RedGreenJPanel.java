import javax.swing.*;
import java.awt.*;

/**
 * Created by osiza on 06.02.2018.
 */
public class RedGreenJPanel extends JPanel {

    int green;
    int red;
    String text;

    public RedGreenJPanel(int green,int red, String text)
    {
        this.green=green;
        this.red=red;
        this.text=text;

    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        JButton vert= new JButton();
        JButton rouge=new JButton();
    }
}

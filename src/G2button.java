import javax.swing.*;
import java.awt.*;

/**
 * Created by osiza on 21.09.2018.
 */
public class G2button extends JTextPane{
    protected int green;
    protected int red;
    int white;
    int black;
    String text;

    public G2button(int green,int red,int white,int black, String text)
    {
        this.green=green;
        this.red=red;
        this.white=white;
        this.black=black;
        //this.setText(text);

    }





    public void paintComponent(Graphics g)
    {

        Graphics2D g2d = (Graphics2D) g;


        /*Color color1 =Color.green;
        Color color2 = Color.RED;
        int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);*/


        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //g2d.setColor(Color.GREEN);
        //g2d.drawOval(10,10,100,100);//I like fill :P
        //g2d.fillOval(10,10,100,100);
        //this.setText(this.getText());
        //   g.setColor(Color.GREEN);
        //g.fillOval(this.getX()+this.getWidth()/2,this.getY(),this.getWidth(),this.getHeight());
        //this.setBackground(null);
        g2d.setColor(Color.GREEN);
        int licznik=this.green+this.red;
        //g2d.drawRect(0,0,this.getWidth()/2,this.getHeight());

        g2d.fillRect(0,0,(int)(((float)this.getWidth()*((float)this.green/(float)licznik))*0.9),this.getHeight());
        g2d.setColor(Color.RED);
        // System.out.println("trąba"+this.getX());
        // System.out.println("bąba"+this.getY());

        // g2d.drawRect(0+(int)((float)this.getWidth()*((float)this.green/(float)licznik)),0,this.getWidth(),this.getHeight());
        g2d.fillRect(0+(int)(((float)this.getWidth()*((float)this.green/(float)licznik))*0.9),0,(int)(this.getWidth()*0.9),this.getHeight());
        g2d.setColor(Color.white);
       g2d.fillRect((int)(this.getWidth()-15),0,this.getWidth(),(int)((float)this.getHeight()*((float)this.white/(float)(black+white))));
        g2d.setColor(Color.black);
       g2d.fillRect((int)(this.getWidth()-15),(int)((float)this.getHeight()*((float)this.white/(float)(white+black))),this.getWidth(),this.getHeight());
        //g2d.drawRect(0,0,this.getWidth(),this.getHeight());
        // this.setBackground(null);
        super.paintComponent(g);




    }
}

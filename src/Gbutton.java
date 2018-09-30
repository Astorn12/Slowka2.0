import javafx.scene.layout.BackgroundImage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by osiza on 06.02.2018.
 */
public class Gbutton extends JLabel {
    protected int green;
    protected int red;
       // String text;
    public Gbutton(int green,int red, String text)
    {
        super(text);
        this.green=green;
        this.red=red;
       // text="";
        //this.setText(text);

    }




    @Override
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

        g2d.fillRect(0,0,(int)((float)this.getWidth()*((float)this.green/(float)licznik)),this.getHeight());
        g2d.setColor(Color.RED);
       // System.out.println("trąba"+this.getX());
       // System.out.println("bąba"+this.getY());

       // g2d.drawRect(0+(int)((float)this.getWidth()*((float)this.green/(float)licznik)),0,this.getWidth(),this.getHeight());
        g2d.fillRect(0+(int)((float)this.getWidth()*((float)this.green/(float)licznik)),0,(int)(this.getWidth()),this.getHeight());
        g2d.setColor(Color.black);
        g2d.drawRect(0,0,this.getWidth(),this.getHeight());
       // this.setBackground(null);
        super.paintComponent(g);




    }

}

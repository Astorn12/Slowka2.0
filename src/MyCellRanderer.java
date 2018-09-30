import net.miginfocom.swing.MigLayout;
import sun.java2d.pipe.BufferedBufImgOps;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

/**
 * Created by osiza on 26.10.2017.
 */
public class MyCellRanderer extends DefaultTreeCellRenderer implements SwingConstants,TreeCellRenderer {
        Zbiornik z;
        List<Pakiet> zbiornik;
        MyCellRanderer(List<Pakiet> zbiornik,Zbiornik z)
        {
            this.zbiornik=zbiornik;
            this.z=z;
        }

    @Override
    public Color getBackgroundNonSelectionColor() {
        return null;
    }


    @Override
    public Color getBackgroundSelectionColor() {

        return Color.BLUE;
    }

    @Override
    public Color getBackground() {
        return (null);
    }

    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean sel, final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {
        final Component ret = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        final DefaultMutableTreeNode node = ((DefaultMutableTreeNode) (value));


        // System.out.println("TESTY renderera BEGINNING");

      //  System.out.println(value.toString());
       // System.out.println(node.getPath()[0]);

      //  System.out.println("TESTY renderera ENDING");

        if(leaf){

        //this.setText(value.toString());

        /*JLayeredPane jLayeredPane= new JLayeredPane();
        JPanel jPanel= new JPanel();
        JButton jButton = new JButton();
        JButton jButton2 = new JButton();
        //jButton.setSize(50,20);
        //jButton.setBackground(Color.GREEN,"Behind");
        // jButton2.setBackground(Color.RED,"Behind");
        JLabel jLabel = new JLabel();
        //JLabel jButton= new JLabel();
        //JLabel jButton2= new JLabel();
        jButton.setBackground(Color.GREEN);
        jButton2.setBackground(Color.red);

        jLabel.setText(value.toString());
        //JPanel jPanel= new JPanel();
        jPanel.setLayout(new MigLayout());
        //jPanel.add(jButton, "w 60!,h 30!");
        //jPanel.add(jButton2, "w 60!,h 30!");
        this.add(jButton);
        this.add(jButton);
       // jLayeredPane.setLayer(jLabel, 1);
        //jLayeredPane.setLayer(jButton, 0);
      //  jLayeredPane.setLayer(jButton2, 0);
            jLayeredPane.add(jPanel,1,0);
            FontMetrics fm = getFontMetrics(getFont());

            int width = fm.stringWidth(value.toString());
            int height = fm.getHeight();
            /*System.out.println("POCZATEK");
            System.out.println(ret.getX());
            System.out.println(ret.getY());
            System.out.println(width);
            System.out.println(height);
            System.out.println("KONIEC");*/
            //jLabel.setBounds(ret.getX(),ret.getY(),ret.getWidth(),ret.getHeight());
         //   jLabel.setBounds(ret.getX(),ret.getY(),width,height);
            //jLayeredPane.add(jLabel,new Integer(1));
            //jLayeredPane.setSize(120,30);

        //jButton.setText(value.toString());
        //this.setText(value.toString());



            /*ret.getName();
            ret.getBounds();
            ret.setSize(100,30);
            System.out.println(ret.getBounds());
            System.out.println("Polooo "+ret.getName());
            System.out.println("tratatata"+ret.getAlignmentY());*/



           /* int height=15;
            int width=40;
            BufferedImage img= new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
            for(int i=0;i<width;i++)
            {
                for(int j=0;j<height;j++)
                {
                    int p;
                    if(i<width/3) {

                        p=20480;

                    }
                    else
                    {
                        p=5242880;

                    }

                    img.setRGB(i, j, p);




                }
            }
           /* ImageIcon imageIcon = new ImageIcon(img);
            JLabel jLabel=new JLabel(imageIcon);
            JButton jButton = new JButton(value.toString());
            jButton.setIcon(imageIcon);
            //jButton.setText(value.toString());
           //this.setIcon((Icon) img);
//jButton.setIcon((Icon) img);*/
            JPanel jPanel= new JPanel();

            MigLayout migLayout= new MigLayout("gap rel 0","grow");
            //F



            //jPanel.setSize(80,40);
            //jPanel.add(jButton,"w 40!,h 15!, cell 0 0" ,0);
           // jPanel.add(jButton1,"w 40!,h 15!, cell 1 0",0);



            /*jButton.setSize(30,60);
            jButton1.setSize(30,60);
            jPanel.setSize(30,120);
            jPanel.add(jButton);
            jPanel.add(jButton1);

            jButton.setBackground(Color.GREEN);
            jButton1.setBackground(Color.RED);*/


            //jPanel.add(label,"cell 0 0 0 1",1);
            //Panel.add(label, "cell 0 0 1 0");

            JButton jButton1= new JButton();
            JButton jButton2= new JButton();

            //JLabel jButton1= new JLabel();
            //JLabel jButton2= new JLabel();


            jButton1.setBackground(Color.GREEN);
            jButton2.setBackground(Color.red);
            JLayeredPane jLayeredPane= new JLayeredPane();
            //jLayeredPane.setLayout(new MigLayout("gap rel 0", "grow"));
            FlowLayout flowLayout= new FlowLayout();
           // flowLayout.setVgap(0);
           // flowLayout.setHgap(0);
           jLayeredPane.setLayout(flowLayout);
            jLayeredPane.add(jButton1);
            jLayeredPane.add(jButton2);



           // jLayeredPane.setLayer();
            JLabel label= new JLabel(value.toString());
            JButton jButton3= new JButton(value.toString());
            label.setBackground(null);
            //jLayeredPane.validate();



            //jLayeredPane.add(jButton3,"cell 0 0 ");

           // jLayeredPane.setLayout(flowLayout);

            PomocnikDrzewowy pomocnikDrzewowy= new PomocnikDrzewowy();

            Vector<Pakiet> vector= new Vector<>();
            for(int i=0;i<zbiornik.size();i++)
            {
                vector.add(zbiornik.get(i));
            }


         int red= pomocnikDrzewowy.zwrocPlikPoSciezce(node.getPath(),vector).iloscSlowekDonauczenia();
            int green=pomocnikDrzewowy.zwrocPlikPoSciezce(node.getPath(),vector).opanowaneSlowka();
            int white=pomocnikDrzewowy.zwrocPlikPoSciezce(node.getPath(),vector).getWhite();
            int black=pomocnikDrzewowy.zwrocPlikPoSciezce(node.getPath(),vector).getBlack();
            String nazwaDoZmiany=value.toString();



            Gbutton gbutton;//= new Gbutton(green,red ,"<html><font color=blue>value.toString()</font</html");
            ObsługaNazwowa obsługaNazwowa= new ObsługaNazwowa();
            String nazwa=obsługaNazwowa.oddzielNazeOdDaty(value.toString()).nazwa;
            Calendar calendar= Calendar.getInstance();
            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("DDDyy");

            String teraz=simpleDateFormat.format(calendar.getTime());
            //System.out.println("Nazwa z drzewa "+value.toString());
            int dni=obsługaNazwowa.roznicaDni(teraz,obsługaNazwowa.oddzielNazeOdDaty(value.toString()).date);
           // System.out.println("COstam"+value.toString());
           // gbutton.setText(value.toString());
          //  System.out.println("jestem tutaj i ilość dni to: "+dni+ret.getName());
            int i=(int)(((float)black/(float)(white+black))*255f);
            String tmp=  Integer.toHexString(i);
            String color="#"+tmp+""+tmp+""+tmp;

            if(nazwa.contains("Metoda ciekawskiego dziecka")||nazwa.substring(0,1).contains("^"))
            {
                if(!nazwa.substring(0,1).contains("^")) {
                    gbutton= new Gbutton(green,red ,"<html>"+nazwa+"   <font color="+color+">"+dni+"</font</html>");

                    // gbutton.setText(nazwa);
                }
                else
                {
                    gbutton= new Gbutton(green,red ,"<html>"+nazwa.substring(1,nazwa.length())+"   <font color="+color+">"+dni+"</font</html>");

                    //gbutton.setText(nazwa.substring(1,nazwa.length()));
                }}
            else if(nazwa.substring(0,1).contains("&"))
            {

                gbutton= new Gbutton(green,red ,"<html>"+nazwa.substring(1,nazwa.length())+"   <font color="+color+">"+dni+"</font</html>");

               // gbutton.setText(nazwa.substring(1,nazwa.length())+dni);
            }
            else if(nazwa.substring(0,1).contains("@"))
            {

                gbutton= new Gbutton(green,red ,"<html>"+nazwa.substring(1,nazwa.length())+"   <font color="+color+">"+dni+"</font</html>");

//                gbutton.setText(nazwa.substring(1,nazwa.length())+"   "+dni);
            }
            else{
                gbutton= new Gbutton(green,red ,"<html>"+nazwa+"   <font color="+color+">"+dni+"</font</html>");

                //gbutton.setText(nazwa+"    "+dni+"  ");
            }

            //PomocnikDrzewowy pomocnikDrzewowy1= new PomocnikDrzewowy();


           // pomocnikDrzewowy1.nazwaPoNumerze(pomocnikDrzewowy1.numerPoNazwie(value.toString()),z);
            String nazwa1=pomocnikDrzewowy.zwrocPlikPoSciezce(node.getPath(),vector).get_nazwa_pliku();
           // System.out.println(nazwa1);
            gbutton.setFont(new Font("Dialog",Font.BOLD,16));
            gbutton.setBorder(new EmptyBorder(0,12,0,12));
            gbutton.setLayout(new BorderLayout(50,50));


            //return jLayeredPane;
            gbutton.setBackground(null);
            this.revalidate();
            return gbutton;
        }
        else
            {
                PomocnikDrzewowy pomocnikDrzewowy= new PomocnikDrzewowy();
                Vector<Pakiet> vector= new Vector<>();
                for(int i=0;i<zbiornik.size();i++)
                {
                    vector.add(zbiornik.get(i));
                }
                TreeNode[] n=node.getPath();
                Pakiet pakiet=pomocnikDrzewowy.zwrocPakietPoSciezce(node.getPath(),vector);
                ImageIcon imageIcon=null;
                if(pakiet.numer.size()==1) {
                     imageIcon = new ImageIcon(new ImageIcon("src/Pictures/" + z.zwrocJezykGrupy(pakiet) + ".png").getImage().getScaledInstance(34, 17, Image.SCALE_DEFAULT));
                }
                else if(pakiet.numer.size()==2)
                {
                    //pakiet.nazwa_pakietu.contains("Piosenki");
                    imageIcon = new ImageIcon(new ImageIcon("src/Pictures/"+pakiet.nazwa_pakietu.toLowerCase()+".png").getImage().getScaledInstance(34, 17, Image.SCALE_DEFAULT));

                }


                setIcon(imageIcon);

               setForeground(new Color(255,255,255));
                return this;
            }

    }




}





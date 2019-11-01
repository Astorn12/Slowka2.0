import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.*;
/**
 * Created by osiza on 11.10.2017.
 */
public class Okno extends JFrame{
    List<Pakiet> aktualne;
    Okno(Zbiornik zbiornik)
    {
        aktualne= zbiornik.zbiornik;
        this.setLayout(new MigLayout());
        this.setMenuBar(createMenu());
        this.setVisible(true);
        //Drzewo drzewo= new Drzewo(aktualne);
        //this.add(panelWyboruPakietów(zbiornik),"align left");
       // add(drzewo.createDrzewo());
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    private void stworzEkranWyboru(Zbiornik zbiornik) {

    }
    public JPanel panelWyboruPakietów(Zbiornik zbiornik)
    {
       // Vector<Pakiet> aktualne= new Vector<>();
        //JList<Pakiet> przyciski= new JList<>(aktualne);
        int a=0;

        for(int i=0;i<zbiornik.zbiornik.size();i++)
        {
            if(zbiornik.zbiornik.get(i).numer.size()==1)
            {
                aktualne.add(a,zbiornik.zbiornik.get(i));
                a++;
            }
        }
        DefaultListModel d= new DefaultListModel();
        final JPanel panel=new JPanel();


        for(int i=0;i<aktualne.size();i++)
        {
        final JButton b=new JButton(zbiornik.zbiornik.get(i).get_nazwa_pakietu());
        b.setName(aktualne.get(i).get_nazwa_pakietu());
        panel.add(b);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JButton b1=new JButton("zostaw");panel.add(b1);
                panel.removeAll();
                panel.repaint();

               // aktualne= zbiornik.poziom_nizej(zwrocPakietPoPrzycisku(b,aktualne));
               // zwroc_panel_Listy(aktualne,panel);

                panel.add(b1,BorderLayout.NORTH);
                panel.repaint();
                //panel.setVisible(true);
            }
        });
        //panel.add(przyciski);
        }
        //panel.add(new JScrollPane(przyciski));
        return panel;
    }



    public Pakiet zwrocPakietPoPrzycisku(JButton przycisk, Vector<Pakiet> pakiety)
    {
        Pakiet pakiet= new Pakiet();
        for(int i=0;i<pakiety.size();i++)
        {
            if(przycisk.getName().equals(pakiety.get(i).get_nazwa_pakietu()))
            {
                pakiet=pakiety.get(i);
            }
        }
        return pakiet;
    }

    private void zwroc_panel_Listy(Vector<Pakiet> lista,JPanel panel )
    {


        JList jList= new JList(lista);
        for(int i=0;i<lista.size();i++)
        {
        panel.add(new JButton(lista.get(i).get_nazwa_pakietu()),BorderLayout.EAST);
        }

    }



    private MenuBar createMenu()
    {
        MenuBar menuBar= new MenuBar();
        Menu menu= new Menu("Menu");
        menuBar.add(menu);

        MenuItem naukaSlowek= new MenuItem("Nauka słówek");
        naukaSlowek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(naukaSlowek);
        MenuItem test= new MenuItem("Test");
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menu.add(test);
        return menuBar;



    }
}
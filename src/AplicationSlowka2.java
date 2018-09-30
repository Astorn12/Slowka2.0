


import com.sun.javafx.tk.*;
import com.sun.jmx.snmp.tasks.Task;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import net.miginfocom.swing.MigLayout;
import sun.awt.image.ImageWatched;
import sun.plugin2.util.ColorUtil;
import sun.reflect.generics.tree.Tree;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URL;
import java.util.List;
import javax.sound.sampled.*;
import static java.awt.Font.*;
import static java.lang.Thread.sleep;
//https://vk.com/video61950003_456239891
/**
 * Created by osiza on 12.10.2017.
 */
public class AplicationSlowka2 extends JFrame implements TreeSelectionListener{
    List<Pakiet> p;
    DefaultListModel<Slowo> defaultLista;
    DefaultListModel<WordFromBook> defaultListaWord;
    Plik aktualny_plik=new Plik();
    JPanelWithBackgrounds panelRodzajuPracy;
    InterfacePodsumowania podsumowanie;
    int flaga=0;
    String jezyk= "ang";
    Zbiornik z;
    LineBorder standardBorder= new LineBorder(Color.black,1);
    Color standardJButtonColor= new Color(204,153,102);
    JTree tree;
    UstawieniaSystemowe ustawieniaSystemowe= new UstawieniaSystemowe();
    List<Jezyk> jezyki= new LinkedList();
    DefaultMutableTreeNode pocz;
    List<ListaSepcyficznychLiterek> zbiornikLiter;
    List<Slownik> slowniki;


    public AplicationSlowka2(String sciezkaDoUstawienSystemowych) throws IOException, InterruptedException {
        //----------------Wstępne wczytanie plików systemowych + mechanizm pierwszego rozruchu-------------------------------//
        wczytaniePlikowSystemowych(sciezkaDoUstawienSystemowych);

        //----------------------------Fake Panel--------------------------------------------------------------------------------//
        getContentPane().setLayout(new MigLayout());
        JPanel fakepanel= new JPanel();
        fakepanel.setBackground(Color.black);
        JLabel fakeLabel= new JLabel("0%");
        fakeLabel.setFont(new Font("Dialog",Font.PLAIN,30));
        //fakepanel.add(fakeLabel,"wrap");
        getContentPane().add(fakepanel,"width 1100!,height 700!");
        fakepanel.setLayout(new MigLayout());
        Gbutton gbutton= new Gbutton(0,100,"");
        gbutton.setFont(new Font("Dialog",Font.BOLD,40));
        gbutton.setBorder(new EmptyBorder(0,12,0,12));
        gbutton.setLayout(new BorderLayout(5,5));
        gbutton.setText("           ");
        gbutton.setSize(300,50);
        //return jLayeredPane;
        gbutton.setBackground(null);
        fakepanel.add(gbutton,"width 300!,height 50!,dock center,align 50% 50%");
        pack();
       // Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //getContentPane().setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setLocationRelativeTo(null);
        setVisible(true);
        //---------------fake pasek ładowania----------------------------------------------------------------------\\
       /* for(int i=0;i<101;i++)
        {
//            fakeLabel.setText("Loading "+i+"%"+"...");
            gbutton.setText("Loading "+i+"%"+"...");

            gbutton.red-=1;
            gbutton.green+=1;
            gbutton.repaint();
            sleep();//średnio 50
        }*/

        double f=0;

        for(int i=0;i<101;i++)
        {
//            fakeLabel.setText("Loading "+i+"%"+"...");
            gbutton.setText("Loading "+i+"%"+"...");

            gbutton.red-=1;
            gbutton.green+=1;
            gbutton.repaint();
            if(i==99)sleep(1000);
            else sleep((long)(Math.sin(f)*100));

            f+=0.0314;
        }
        getContentPane().removeAll();
        getContentPane().setBackground(Color.black);
        this.p=z.zbiornik;


        getContentPane().setLayout(new MigLayout());
        //panelRodzajuPracy= new JPanel();
        panelRodzajuPracy= new JPanelWithBackgrounds("src/Pictures/Eiffla.jpg");
        //panelRodzajuPracy.setBackground(Color.YELLOW);
        panelRodzajuPracy.setLayout(new MigLayout("fill"));

        //panelRodzajuPracy.setBackground(null);
       /* DefaultMutableTreeNode pocz= new DefaultMutableTreeNode("Nauka");
        Pakiet pakiet= new Pakiet();
        pakiet.set_nazwa_pakietu("Słówka");
        pakiet.numer=new Vector<>();
        uzupelnij_dziecmi(p,pocz,pakiet);

        JTree tree= new JTree(pocz);
        tree.addTreeSelectionListener(this);
        JScrollPane sp= new JScrollPane(tree);
        tree.setFont(new Font("Dialog",Font.PLAIN,18));

        sp.setPreferredSize(new Dimension(200,400));

        getContentPane().add(sp,BorderLayout.WEST);*/
        BufferedImage background = ImageIO.read(new File("src/Pictures/BigBen.jpg"));
       // Gpanel panel= new Gpanel(background,250,300);
       JPanelWithBackgrounds panel= new JPanelWithBackgrounds("src/Pictures/BigBen.jpg");
        //JPanel panel= new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setLayout(new MigLayout("fill"));
        JScrollPane js=createDrzewo();
        js.setOpaque(false);
        js.getViewport().setOpaque(false);
        js.setBorder(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
      panel.add(js,"align center,grow,w 150:200:"+width+",h 600:700:"+height);



      //  panel.add(js,"align center,w "+panel.getWidth()+"!,h "+panel.getHeight()+"!");
        //panel.add(js,"align center");
        getContentPane().setLayout(new MigLayout("fill"));


        //getContentPane().add(panel,"w 250:400:"+width*0.3+",h 600:700:"+height+",grow,align left");
        getContentPane().add(panel,"w 25%!,h 600:700:"+height+",grow,align left");
        //getContentPane().setLayout(new MigLayout());
        //getContentPane().add(panelRodzajuPracy,"w 740:850:"+width+",h 600:700:"+height+",grow,align right");
        getContentPane().add(panelRodzajuPracy,"w 75%,h 600:700:"+height+",grow,align right");
      //  JPanel panelTestowy= new JPanel();
        //panelTestowy.setBackground(Color.red);
        //getContentPane().add(panelTestowy,"w 740:8500:100000,h 600:7000:2000000,align center");
        /*getContentPane().add(createPanelListySlowek());
        //setMenuBar(createMenu());
        getContentPane().add(createTest());
        createTest();*/
        //podsumowanie= new InterfacePodsumowania(podsumowanie1);
       /* zbiornikLiter= new LinkedList<>();
        for(Jezyk j:jezyki)
        {

            ListaSepcyficznychLiterek l= new ListaSepcyficznychLiterek();
            l.jezyk=j.nazwaJezyka;


            pomocnik_plikowy.zczytywanie_z_pliku(j.nazwaJezyka+".txt",',',l.list,SpecyficznaLiterka.class);

            zbiornikLiter.add(l);
        }*/


        pack();
       // setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


       // z.zakutualizujPobrane();
      //  z.zapisPlikow();//złuży do dodania nowego atrybutu w słówkach
      // z.zaktualizujZmiany();
       // z.masoweZmienianieNazwPlikow();


    }
    private void wczytaniePlikowSystemowych(String systemowe)
    {try {
        this.slowniki = new LinkedList<>();
        List<UstawieniaSystemowe> list = new LinkedList<>();
        Pomocnik_plikowy.zczytywanie_z_pliku(systemowe, ',', list, UstawieniaSystemowe.class);
        this.ustawieniaSystemowe = list.get(0);
        Pomocnik_plikowy.zczytywanie_z_pliku(this.ustawieniaSystemowe.plikZJezykami, ',', jezyki, Jezyk.class);
        podsumowanie = new InterfacePodsumowania(this.ustawieniaSystemowe.podsumowanie);
        zbiornikLiter = new LinkedList<>();
        for (Jezyk j : jezyki) {

            ListaSepcyficznychLiterek l = new ListaSepcyficznychLiterek();
            l.jezyk = j.nazwaJezyka;


            Pomocnik_plikowy.zczytywanie_z_pliku(j.nazwaJezyka + ".txt", ',', l.list, SpecyficznaLiterka.class);

            zbiornikLiter.add(l);
        }
        this.z = new Zbiornik(ustawieniaSystemowe.zbiornik);
        z.pelne_zaladowanie_zbiornika();
        zczytywanieSlownikow(ustawieniaSystemowe.slowniki);
        if(!new File(ustawieniaSystemowe.plikZJezykami).exists())throw new Exception();
        if(!new File(ustawieniaSystemowe.podsumowanie).exists())throw new Exception();
        if(!new File(ustawieniaSystemowe.slowniki).exists())throw new Exception();
        if(!new File(ustawieniaSystemowe.zbiornik).exists())throw new Exception();
    }
    catch(Exception e)
    {
        Pomocnik_plikowy.CreateFile("Systemowe.txt");
        List<UstawieniaSystemowe> l= new LinkedList<>();
        l.add(new UstawieniaSystemowe("Jezyki.txt","Słowniki.txt","Podsumowanie.txt","Zbiornik.txt","Tutorial.txt"));
        this.ustawieniaSystemowe=l.get(0);
        Pomocnik_plikowy.zapisywanie_do_pliku("Systemowe.txt",',',l,UstawieniaSystemowe.class);
        Pomocnik_plikowy.CreateFile(ustawieniaSystemowe.plikZJezykami);


        Pomocnik_plikowy.CreateFile(ustawieniaSystemowe.slowniki);


        Pomocnik_plikowy.CreateFile(ustawieniaSystemowe.podsumowanie);
        Podsumowanie podsumowanie1= new Podsumowanie(0,0,0);
        List<Podsumowanie> podsumowanieList= new LinkedList<>();
        podsumowanieList.add(podsumowanie1);
        Pomocnik_plikowy.zapisywanie_do_pliku(ustawieniaSystemowe.podsumowanie,',',podsumowanieList,Podsumowanie.class);




        Pomocnik_plikowy.CreateFile(ustawieniaSystemowe.zbiornik);
        this.z=new Zbiornik(ustawieniaSystemowe.zbiornik);

        //addNewLanguage("Angielski");
        addDefaultEnglish();


        this.slowniki = new LinkedList<>();


        Pomocnik_plikowy.zczytywanie_z_pliku(this.ustawieniaSystemowe.plikZJezykami, ',', jezyki, Jezyk.class);
        podsumowanie = new InterfacePodsumowania(this.ustawieniaSystemowe.podsumowanie);
        zbiornikLiter = new LinkedList<>();
        for (Jezyk j : jezyki) {

            ListaSepcyficznychLiterek l2 = new ListaSepcyficznychLiterek();
            l2.jezyk = j.nazwaJezyka;


            Pomocnik_plikowy.zczytywanie_z_pliku(j.nazwaJezyka + ".txt", ',', l2.list, SpecyficznaLiterka.class);

            zbiornikLiter.add(l2);
        }
        this.z = new Zbiornik(ustawieniaSystemowe.zbiornik);
        z.pelne_zaladowanie_zbiornika();

    }
    }

    private JPanel wepnijDrzewo() throws IOException {
        JPanel panel= new JPanel();
        panel.add(createDrzewo());

        return panel;
    }
    public void valueChanged(TreeSelectionEvent e )
    {

        JTree tree =(JTree) e.getSource();

        DefaultMutableTreeNode node =(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();


        if(node!=null && node.isLeaf()) {


            Zbiornik z = new Zbiornik("p");

            z.zbiornik = p;
            Vector<Integer> tmp = new Vector<>();

            /*for(int i=0;i<numer_po_sciezce(e,this.p).size();i++) {
                tmp.add(new Integer((int)numer_po_sciezce(e, this.p).get(i)));
            }*/

            // z.wypisz_zbiornik();
            tmp = numer_po_sciezce(e, this.p);

           // int a = tmp.get(tmp.size() - 1);

            //tmp.remove(tmp.size()-1);

            Vector<Integer> tmp2 = new Vector<>();
            for (int i = 0; i < tmp.size() - 1; i++) {
                tmp2.add(tmp.get(i));
            }



                Pakiet pakiet = z.poszukiwanie_pakietu(tmp2);

            aktualny_plik = z.poszukiwanie_pakietu(tmp2).getPlik(tmp);

            if(aktualny_plik.get_nazwa_pliku().contains("^")){
            Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
            ObsługaNazwowa ob = new ObsługaNazwowa();
            String plikZTekstem = ob.oddzielNazeOdDaty(aktualny_plik.get_nazwa_pliku()).nazwa;
            plikZTekstem = "Skrót" + plikZTekstem;
            plikZTekstem += ".txt";


            LinkedList<Word> words = new LinkedList<>();
            Pomocnik_plikowy pomocnik_plikowy1 = new Pomocnik_plikowy();
            pomocnik_plikowy1.zczytywanie_z_pliku(plikZTekstem, ',', words, Word.class);
            int flaga=1;
            for (Word wd : words) {


                    for (Slowo sl : aktualny_plik.lista) {
                        if (wd.slowo1.equals(sl.pol) && wd.slowo2.equals(sl.fore)) {
                            flaga=0;
                            break;
                        }
                    }
                if(flaga==1) {
                    Slowo slowo = new Slowo(wd);
                    aktualny_plik.lista.add(slowo);
                }
                flaga=1;
            }

            flaga=1;
            for (Slowo sl : aktualny_plik.lista) {
                for (Word wd : words) {
                    if (wd.slowo1.equals(sl.pol) && wd.slowo2.equals(sl.fore)) {
                        flaga=0;
                        break;
                    }

                }
                if(flaga==1)
                {
                    Word word = new Word(sl);
                    words.add(word);
                }
                flaga=1;
            }
            aktualny_plik.zapis_zmian();
            //z.zaktualizujZmiany();
          //  z.zapisPlikow();
            Pomocnik_plikowy.zapisywanie_do_pliku(plikZTekstem, ',', words, Word.class);
        }

            Pakiet pakietu = z.poszukiwanie_pakietu(tmp2);



            tmp2.clear();
            defaultLista= new DefaultListModel<>();
            for(int i=0;i<this.aktualny_plik.lista.size();i++)
            {
                defaultLista.add(i,this.aktualny_plik.lista.get(i).clone());
            }
            //tmp.add(a);
            //defaultLista.addElement("Tutej");
           //aktualizujListeSlowek();
        }
        if(node==null)return;
        Object value = node.getUserObject();



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

    private void wepnijPanelListySlowek1() throws IOException {
        JPanel panel= panelRodzajuPracy;
        panel.setLayout(new MigLayout());
        JMenuBar jMenuBar=new JMenuBar();


        JMenu ustawienia= new JMenu("Ustawienia");
        jMenuBar.add(ustawienia);
        JMenuItem powtorka= new JMenuItem("powtórka pol-ang");
        JMenuItem powtorkaAngPol= new JMenuItem("powtórka ang-pol");
        JMenuItem wszystko_na_1_ang=new JMenuItem("Wszystko na 1 pol-ang");
        JMenuItem wszystko_na_1_pol=new JMenuItem("Wszystko na 1 ang-pol");

        ustawienia.add(powtorka);
        ustawienia.add(powtorkaAngPol);
        ustawienia.add(wszystko_na_1_ang);
        ustawienia.add(wszystko_na_1_pol);
        powtorka.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<aktualny_plik.lista.size();i++)
                {
                    if(aktualny_plik.lista.get(i).priority<=0)
                    {
                        aktualny_plik.lista.get(i).priority=1;
                    }
                    aktualny_plik.zapis_zmian();
                }
            }
        });
        wszystko_na_1_ang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<aktualny_plik.lista.size();i++)
                {

                        aktualny_plik.lista.get(i).priority=1;


                }
                aktualny_plik.zapis_zmian();
            }
        });
        powtorkaAngPol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<aktualny_plik.lista.size();i++)
                {
                    if(aktualny_plik.lista.get(i).repriority<=0)
                    {
                        aktualny_plik.lista.get(i).repriority=1;
                    }
                    aktualny_plik.zapis_zmian();
                }
            }
        });
        wszystko_na_1_pol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0;i<aktualny_plik.lista.size();i++)
                {
                        aktualny_plik.lista.get(i).repriority=1;
                }
                aktualny_plik.zapis_zmian();
            }
        });
        panel.add(jMenuBar,"wrap,h 20:30:40,gapbefore 100");
        JButton zaznaczWszystkie= new JButton("Zaznacz wszystkie");
        JButton odznaczWszystkie= new JButton("Odznacz wszystkie");

        final JScrollPane jScrollPane=createPanelListySlowek();
        //panel.add(jScrollPane,"w "+jScrollPane.getMinimumSize().getWidth()+":"+jScrollPane.getPreferredSize().getWidth()+":"+jScrollPane.getMaximumSize().getHeight()+","+ "h 500:600:700");
        panel.add(jScrollPane,"w 400:500:600, h 500:600:700,wrap,dock center,align 30% 70%");

        panel.setBackground(null);
        panel.add(zaznaczWszystkie,"split 2,gapbefore 100");
        panel.add(odznaczWszystkie);
        zaznaczWszystkie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JViewport jViewport= jScrollPane.getViewport();
                JList jList= (JList) jViewport.getView();



                for(int i=0;i<defaultLista.getSize();i++)
                {
                    if(!defaultLista.getElementAt(i).language.contains("i"))
                    {
                        defaultLista.getElementAt(i).language+="i";
                        aktualny_plik.lista.get(i).language+="i";

                    }
                }
                aktualny_plik.zapis_zmian();
                jList.repaint();
                /*for(int i=0;i<jList.getModel().getSize();i++)
                {
                    jList.getComponent(i).setBackground(Color.PINK);
                }*/

            }
        });

        odznaczWszystkie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JViewport jViewport= jScrollPane.getViewport();
                JList jList= (JList) jViewport.getView();



                for(int i=0;i<defaultLista.getSize();i++)
                {
                    if(defaultLista.getElementAt(i).language.contains("i"))
                    {
                        defaultLista.getElementAt(i).language=defaultLista.getElementAt(i).language.substring(defaultLista.getElementAt(i).language.length());
                        aktualny_plik.lista.get(i).language=defaultLista.getElementAt(i).language.substring(defaultLista.getElementAt(i).language.length());



                    }
                }
                aktualny_plik.zapis_zmian();

                jList.repaint();


            }
        });


        // panel.add(jMenuBar,"dock north");





    }

    public void aktualizujListeSlowek()
    {
       defaultLista.removeAllElements();


        for(int i=0;i<this.aktualny_plik.get_lista().size();i++)
        {
            defaultLista.add(i,this.aktualny_plik.lista.get(i));

        }
    }
    private void uzupelnij_dziecmi(List<Pakiet> p,DefaultMutableTreeNode node,Pakiet pakiet)
    {


        for(int i=0;i<p.size();i++)
        {

        if(pakiet.czy_moje_Dziecko(p.get(i)))
        {


            DefaultMutableTreeNode nowy=new DefaultMutableTreeNode(p.get(i).get_nazwa_pakietu());

            if(!p.get(i).get_nazwy_plikow().isEmpty())
            {
                for(int a=0;a<p.get(i).get_nazwy_plikow().size();a++)
                {
                    DefaultMutableTreeNode o=new DefaultMutableTreeNode(p.get(i).get_nazwy_plikow().get(a).get_nazwa_pliku());
                    nowy.add(o);
                }
            }
            if(p.get(i).get_nazwy_plikow().isEmpty()&&nowy.getChildCount()==0)
            {
                nowy.setAllowsChildren(true);


            }
            node.add(nowy);

            packageBubbleSort(p);


            uzupelnij_dziecmi(p,nowy,p.get(i));

        }
        }

       /* for(int i=0;i<p.size();i++)
        {
            for(int j=0;j<p.get(i).get_nazwy_plikow().size();j++)
            {
                nodePoNumerze(new JTree(node),new Vector<Integer>(p.get(i).numer),node);
            }
        }*/

    }

    private void  packageBubbleSort(List<Pakiet> pakiety)
    {



        Collections.sort(pakiety,new Comparator<Pakiet>()
        {
            @Override
            public int compare(Pakiet p1,Pakiet p2)
            {

                if(p1.numer.size()<p2.numer.size())
                {
                    return -1;
                }
                else if(p1.numer.size()>p2.numer.size())
                {
                    return 1;
                }
                else if(p1.numer.size()==p2.numer.size())
                {
                    for(int i=0;i<p1.numer.size();i++)
                    {
                        if(p1.numer.get(i)<p2.numer.get(i))
                        {
                            return -1;

                        }
                        else if(p1.numer.get(i)>p2.numer.get(i))
                        {
                            return 1;
                        }

                    }
                    return 0;


                }
                else
                {
                    return 0;
                }




            }
        });
        for(Pakiet p: pakiety)
        {
            p.filesBubbleSort();
        }
    }

    private static DefaultMutableTreeNode nodePoNumerze(JTree jtree,Vector<Integer> vector,DefaultMutableTreeNode node)
    {
        DefaultMutableTreeNode wybrany= new DefaultMutableTreeNode();
        jtree.getRootPane();





        return wybrany;
    }

    private Vector<Integer> numer_po_sciezce(TreeSelectionEvent e, List<Pakiet>pakiety)
    {
        Pakiet pakiet= new Pakiet();
        pakiet.numer= new Vector<>();
        //Vector<Integer> vector=new Vector<>();
        //Vector<String> ePath= new Vector<>();
        /*for(int i=0;i<e.getOldLeadSelectionPath().getPathCount();i++)
        {
            ePath.add(e.getOldLeadSelectionPath().getPathComponent(i).toString());
        }*/


        for(int i=0;i<e.getNewLeadSelectionPath().getPathCount();i++)
        {
            if(!pakiet.get_nazwy_plikow().isEmpty())
            {
                for(int a=0;a<pakiet.get_nazwy_plikow().size();a++)
                {
                    Pakiet tmp= new Pakiet();
                    tmp.numer=pakiet.get_nazwy_plikow().get(a).numer;
                    if(pakiet.get_nazwy_plikow().get(a).get_nazwa_pliku().equals(e.getPath().getPathComponent(i).toString())&&pakiet.czy_moje_Dziecko(tmp))
                    {
                        pakiet=tmp;
                    }
                }
            }
            for(int j=0;j< pakiety.size();j++)
            {


                if(pakiety.get(j).get_nazwa_pakietu().equals(e.getNewLeadSelectionPath().getPathComponent(i).toString())&&pakiet.czy_moje_Dziecko(pakiety.get(j)))
                {
                    //pakiet.set_nazwa_pakietu(get(j));
                    //pakiet.numer.add(new Integer(pakiety.get(i).numer.lastElement()));
                    pakiet=pakiety.get(j);


                    continue;
                }
            }
        }


        return pakiet.numer;
    }

    private Vector<Integer> numer_po_sciezce2(TreePath p, List<Pakiet>pakiety)
    {


        Pakiet pakiet= new Pakiet();
        pakiet.numer= new Vector<>();
int flaga=0;


        for(int i=0;i<p.getPathCount();i++)
        {

            if(!pakiet.get_nazwy_plikow().isEmpty())
            {
                for(int a=0;a<pakiet.get_nazwy_plikow().size();a++)
                {
                    Pakiet tmp= new Pakiet();
                    tmp.numer=pakiet.get_nazwy_plikow().get(a).numer;
                    if((pakiet.get_nazwy_plikow().get(a).get_nazwa_pliku().equals(p.getPathComponent(i).toString())&&pakiet.czy_moje_Dziecko(tmp))/*&&pakiet.get_nazwy_plikow().get(a).numer.size()==p.getPathCount()-1*/)
                    {


                        pakiet=tmp;
                        //flaga=1;
                    }
                }
            }
            if(flaga==0){
            for(int j=0;j< pakiety.size();j++)
            {

                if((pakiety.get(j).get_nazwa_pakietu().equals(p.getPathComponent(i).toString())&&pakiet.czy_moje_Dziecko(pakiety.get(j)))/*&&pakiety.get(j).numer.size()==p.getPathCount()-1*/)
                {
                    //pakiet.set_nazwa_pakietu(get(j));
                    //pakiet.numer.add(new Integer(pakiety.get(i).numer.lastElement()));

                    pakiet=pakiety.get(j);

                    break;
                }

               /* else
                {

                    for(int a=0;a<pakiety.get(j).get_nazwy_plikow().size();a++)
                    {
                        Pakiet tmp= new Pakiet();
                        tmp.numer=pakiety.get(i).get_nazwy_plikow().get(a).numer;
                        if(pakiety.get(j).get_nazwy_plikow().get(a).get_nazwa_pliku().equals(p.getPathComponent(i).toString())&&pakiet.czy_moje_Dziecko(tmp))
                        {
                            pakiet=tmp;
                        }
                    }
                }*/
            }
           // flaga=0;
            }
        }




        return pakiet.numer;
    }
    private Vector<Integer> numer_po_sciezce2doUsuwaniaGrup(TreePath p, List<Pakiet>pakiety)
    {


        Pakiet pakiet= new Pakiet();
        pakiet.numer= new Vector<>();
        int flaga=0;
        for(int i=0;i<p.getPathCount();i++)
        {

            if(!pakiet.get_nazwy_plikow().isEmpty())
            {
                for(int a=0;a<pakiet.get_nazwy_plikow().size();a++)
                {
                    Pakiet tmp= new Pakiet();
                    tmp.numer=pakiet.get_nazwy_plikow().get(a).numer;
                    if((pakiet.get_nazwy_plikow().get(a).get_nazwa_pliku().equals(p.getPathComponent(i).toString())&&pakiet.czy_moje_Dziecko(tmp))&&pakiet.get_nazwy_plikow().get(a).numer.size()==p.getPathCount()-1)
                    {
                        pakiet=tmp;
                    }
                }
            }
            if(flaga==0){
                for(int j=0;j< pakiety.size();j++)
                {

                    if((pakiety.get(j).get_nazwa_pakietu().equals(p.getPathComponent(i).toString())&&pakiet.czy_moje_Dziecko(pakiety.get(j)))&&pakiety.get(j).numer.size()==p.getPathCount()-1)
                    {
                        //pakiet.set_nazwa_pakietu(get(j));
                        //pakiet.numer.add(new Integer(pakiety.get(i).numer.lastElement()));
                        pakiet=pakiety.get(j);

                        continue;
                    }
               /* else
                {

                    for(int a=0;a<pakiety.get(j).get_nazwy_plikow().size();a++)
                    {
                        Pakiet tmp= new Pakiet();
                        tmp.numer=pakiety.get(i).get_nazwy_plikow().get(a).numer;
                        if(pakiety.get(j).get_nazwy_plikow().get(a).get_nazwa_pliku().equals(p.getPathComponent(i).toString())&&pakiet.czy_moje_Dziecko(tmp))
                        {
                            pakiet=tmp;
                        }
                    }
                }*/
                }}
        }




        return pakiet.numer;
    }
    private BufferedImage loadImage()
    {
        String fileName = "Pictures\\BigBen.jpg";
        BufferedImage image = null;
        try
        {
            URL url = getClass().getResource(fileName);
            image = ImageIO.read(url);
        }
        catch(MalformedURLException mue)
        {
            System.err.println("url: " + mue.getMessage());
        }
        catch(IOException ioe)
        {
            System.err.println("read: " + ioe.getMessage());
        }
        return image;
    }

    public JScrollPane createDrzewo() {

        if(pocz==null){
        pocz= new DefaultMutableTreeNode("Aplikacja");}
        /*else
        {
            pocz.removeAllChildren();
            DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
            model.reload(root);
        }*/
        final Pakiet pakiet= new Pakiet();
        pakiet.set_nazwa_pakietu("Słówka");
        pakiet.numer=new Vector<>();



        uzupelnij_dziecmi(p,pocz,pakiet);
         tree= new JTree(pocz);
        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {

            }
        });




        tree.setOpaque(false);

        Color bgColor = UIManager.getColor("Panel.background");
       // DefaultTreeCellRenderer renderer =
             //   (DefaultTreeCellRenderer)tree.getCellRenderer();
    //    renderer.setBackgroundNonSelectionColor(Color.GREEN);
///////////////////////////////////////////////////////////////////
        final AplicationSlowka2 ap=this;

        tree.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {


               // PomocnikDrzewowy pomocnikDrzewowy= new PomocnikDrzewowy();

                //pomocnikDrzewowy.numer_po_sciezce( path)
                final TreePath path = tree.getPathForLocation(e.getX(), e.getY());

                tree.setSelectionPath(path);

                PomocnikDrzewowy pomocnikDrzewowy= new PomocnikDrzewowy();
                //pomocnikDrzewowy.
                final DefaultMutableTreeNode node =(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if(SwingUtilities.isRightMouseButton(e))
                {


                    if (path != null && node.isLeaf()) {


                        // RozwijaneMenuPliku menu1 = new RozwijaneMenuPliku();



                            JPopupMenu menu = new JPopupMenu();
                           // aktualny_plik.rightClickPopupMenu(menu,ap,node);
                            JMenuItem listaSlowek = new JMenuItem("Lista słówek");
                                JMenuItem test = new JMenuItem("Test");
                                JMenuItem wpisywanie = new JMenuItem("Wpisywanie");
                                JMenuItem zmienNazwe = new JMenuItem("Zmień nazwę");
                                JMenuItem usunTemat = new JMenuItem("Usuń temat");
                                JMenuItem przemieszczenie = new JMenu("Przemieszczenie");

                                menu.add(listaSlowek);
                                menu.add(wpisywanie);
                                menu.add(test);
                                menu.add(zmienNazwe);
                                menu.add(usunTemat);
                                menu.add(przemieszczenie);

                                JMenuItem wGórę = new JMenuItem("W górę");
                                JMenuItem wDół = new JMenuItem("W dół");
                                przemieszczenie.add(wGórę);
                                przemieszczenie.add(wDół);

                                wGórę.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (aktualny_plik.numer.get(aktualny_plik.numer.size() - 1) > 1) {
                                            Vector<Integer> vector = new Vector<>();
                                            for (int i = 0; i < aktualny_plik.numer.size() - 1; i++) {
                                                vector.add(aktualny_plik.numer.get(i));
                                            }

                                            Pakiet pakiet = z.poszukiwanie_pakietu(vector);
                                            vector.add(aktualny_plik.numer.get(aktualny_plik.numer.size() - 1) - 1);
                                            Plik poszkodowany = pakiet.getPlik(vector);
                                            aktualny_plik.wGore(getArchiwumForFile(aktualny_plik, z));
                                            poszkodowany.wDol(getArchiwumForFile(aktualny_plik, z));
                                            aktualny_plik.juzZmienione = false;
                                            poszkodowany.juzZmienione = false;
                                            String s = getExpansionState();
                                            z.zaktualizujZmiany();
                                            drzewoReload(s);
                                        }

                                    }
                                });

                                wDół.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        Vector<Integer> vector = new Vector<>();
                                        for (int i = 0; i < aktualny_plik.numer.size() - 1; i++) {
                                            vector.add(aktualny_plik.numer.get(i));
                                        }

                                        Pakiet pakiet = z.poszukiwanie_pakietu(vector);
                                        vector.add(aktualny_plik.numer.get(aktualny_plik.numer.size() - 1) + 1);
                                        Plik poszkodowany = pakiet.getPlik(vector);
                                        if (poszkodowany != null) {
                                            aktualny_plik.wDol(getArchiwumForFile(aktualny_plik, z));
                                            poszkodowany.wGore(getArchiwumForFile(aktualny_plik, z));
                                            aktualny_plik.juzZmienione = false;
                                            poszkodowany.juzZmienione = false;
                                            String s = getExpansionState();
                                            z.zaktualizujZmiany();
                                            drzewoReload(s);
                                        }
                                    }


                                });


                                listaSlowek.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {

                                        //panelRodzajuPracy=wepnijPanelListySlowek();
                                        panelRodzajuPracy.removeAll();
                                        try {
                                            // panelRodzajuPracy.add(wepnijPanelListySlowek(),"gapbefore 100,w 400:500:600, h 500:600:700");
                                            wepnijPanelListySlowek1();

                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                        //panelRodzajuPracy.add(createPanelListySlowek(),"gapbefore 100,w 400:500:600, h 500:600:700");
                                        panelRodzajuPracy.setVisible(true);
                                        panelRodzajuPracy.repaint();
                                        panelRodzajuPracy.revalidate();
                                    }
                                });
                                wpisywanie.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        panelRodzajuPracy.removeAll();
                                        //panelRodzajuPracy.add(createDodawanieSlowek(),"c,w 1000:1050:1100,h 1000:1050:10100");
                                        createDodawanieSlowek1();

                                        panelRodzajuPracy.revalidate();
                                        panelRodzajuPracy.setFocusable(true);
                                        //add(createDodawanieSlowek(),"w 1000:1050:1100,h 1000:1050:10100");
                                        // panelRodzajuPracy.setVisible(true);
                                        // panelRodzajuPracy.repaint();
                                    }
                                });
                                test.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        panelRodzajuPracy.removeAll();
                                        //panelRodzajuPracy.add(createTest(),"gaptop 150,gapbefore 120,w 400:500:600,h 300:400:500,dock south");
                                        createTest1();
                                        panelRodzajuPracy.revalidate();
                                        panelRodzajuPracy.setVisible(true);
                                        panelRodzajuPracy.repaint();

                                    }
                                });
                                zmienNazwe.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        final String staraNazwa;
                                        final String[] nowaNazwa = new String[1];
                                        staraNazwa = node.getUserObject() + "";
                                        tree.revalidate();
                                        tree.repaint();
                                        final Vector<Vector<Integer>> numer = new Vector<>();
                                        final JFrame jFrame = new JFrame();
                                        JPanel jPanel = new JPanel();
                                        jPanel.setLayout(new MigLayout());
                                        JLabel jLabel = new JLabel("Nazwa nowej grupy");
                                        JButton jButton = new JButton("Ustaw");
                                        final JTextField jTextField = new JTextField();
                                        jFrame.add(jPanel);

                                        jPanel.add(jLabel);
                                        jPanel.add(jTextField, "w 100:200:300,h 40:50:60,wrap");
                                        jPanel.add(jButton, "cell 1 1 1 2");
                                        jFrame.pack();
                                        jFrame.setVisible(true);
                                        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                        jButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {


                                                numer.add(0, numer_po_sciezce2(path, z.zbiornik));

                                                nowaNazwa[0] = jTextField.getText();
                                                if (nowaNazwa[0].contains("&")) {
                                                    Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                                                    ObsługaNazwowa ob = new ObsługaNazwowa();
                                                    String plikZTekstem = ob.oddzielNazeOdDaty(nowaNazwa[0]).nazwa;
                                                    plikZTekstem = "Tekst()" + plikZTekstem;
                                                    plikZTekstem += ".txt";
                                                    pomocnik_plikowy.CreateFile(plikZTekstem);
                                                } else if (node.getUserObject().toString().contains("&")) {
                                                    Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                                                    ObsługaNazwowa ob = new ObsługaNazwowa();
                                                    String plikZTekstem = ob.oddzielNazeOdDaty(nowaNazwa[0]).nazwa;
                                                    plikZTekstem = "Tekst()" + plikZTekstem;
                                                    plikZTekstem += ".txt";
                                                    pomocnik_plikowy.DeleteFile(plikZTekstem);
                                                } else if (nowaNazwa[0].substring(0, 1).contains("@")) {
                                                    Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                                                    ObsługaNazwowa ob = new ObsługaNazwowa();
                                                    String plikZTekstem = ob.oddzielNazeOdDaty(nowaNazwa[0]).nazwa;
                                                    plikZTekstem = "Tekst()" + plikZTekstem;
                                                    plikZTekstem += ".txt";
                                                    pomocnik_plikowy.CreateFile(plikZTekstem);
                                                } else if (node.getUserObject().toString().contains("@")) {
                                                    Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                                                    ObsługaNazwowa ob = new ObsługaNazwowa();
                                                    String plikZTekstem = ob.oddzielNazeOdDaty(nowaNazwa[0]).nazwa;
                                                    plikZTekstem = "Tekst()" + plikZTekstem;
                                                    plikZTekstem += ".txt";
                                                    pomocnik_plikowy.DeleteFile(plikZTekstem);
                                                } else if (nowaNazwa[0].substring(0, 1).contains("^")) {
                                                    Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                                                    ObsługaNazwowa ob = new ObsługaNazwowa();
                                                    String plikZTekstem = ob.oddzielNazeOdDaty(nowaNazwa[0]).nazwa;
                                                    plikZTekstem = "Skrót" + plikZTekstem;
                                                    plikZTekstem += ".txt";
                                                    pomocnik_plikowy.CreateFile(plikZTekstem);
                                                    LinkedList<Word> wl = new LinkedList<>();
                                                    wl.add(new Word(aktualny_plik.statyplik));
                                                    for (Slowo s : aktualny_plik.lista) {
                                                        wl.add(new Word(s));
                                                    }
                                                    Pomocnik_plikowy.zapisywanie_do_pliku(plikZTekstem, ',', wl, Word.class);
                                                } else if (node.getUserObject().toString().contains("^")) {
                                                    Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                                                    ObsługaNazwowa ob = new ObsługaNazwowa();
                                                    String plikZTekstem = ob.oddzielNazeOdDaty(nowaNazwa[0]).nazwa;
                                                    plikZTekstem = "Skrót" + plikZTekstem;
                                                    plikZTekstem += ".txt";
                                                    pomocnik_plikowy.DeleteFile(plikZTekstem);

                                                }





                                        /*if(nowaNazwa[0].contains("!"))
                                        {
                                            Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
                                            ObsługaNazwowa ob=new ObsługaNazwowa();
                                            String plikZTekstem=ob.oddzielNazeOdDaty(nowaNazwa[0]).nazwa;
                                            plikZTekstem="Tekst()"+plikZTekstem;
                                            plikZTekstem+=".txt";
                                            pomocnik_plikowy.CreateFile(plikZTekstem);
                                        }*/


                                                Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                                                ObsługaNazwowa ob = new ObsługaNazwowa();
                                                String plikZTekstem = ob.oddzielNazeOdDaty(staraNazwa).nazwa;
                                                plikZTekstem = "Tekst()" + plikZTekstem;
                                                plikZTekstem += ".txt";

                                                pomocnik_plikowy.DeleteFile(plikZTekstem);
                                                String kolejen = nowaNazwa[0];
                                                ObsługaNazwowa obsługaNazwowa = new ObsługaNazwowa();
                                                nowaNazwa[0] += "%" + obsługaNazwowa.oddzielNazeOdDaty(staraNazwa).date;
                                                jFrame.dispose();
                                                Calendar calendar = Calendar.getInstance();
                                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DDDyy");

                                                String teraz = simpleDateFormat.format(calendar.getTime());

                                                obsługaNazwowa.roznicaDni(teraz, obsługaNazwowa.oddzielNazeOdDaty(nowaNazwa[0]).date);

                                                node.setUserObject(nowaNazwa[0]);
                                                //node.setUserObject(kolejen+"    "+obsługaNazwowa.roznicaDni(teraz,obsługaNazwowa.oddzielNazeOdDaty(nowaNazwa[0]).date));
                                       /* tree.revalidate();
                                        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
                                        ;
                                        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
                                        //root.add(new DefaultMutableTreeNode("another_child"));
                                        model.reload(root);
                                        //node.getPath();
                                        TreePath  treePath= new TreePath(node.getPath());
                                        //tree.expandPath(treePath);
                                        tree.setSelectionPath(new TreePath(node.getPath()));*/
/* kara ? */
                                                List<TreePath> expanded = new ArrayList<>();
                                                for (int i = 0; i < tree.getRowCount() - 1; i++) {
                                                    TreePath currPath = tree.getPathForRow(i);
                                                    TreePath nextPath = tree.getPathForRow(i + 1);
                                                    if (currPath.isDescendant(nextPath)) {
                                                        expanded.add(currPath);
                                                    }
                                                }
                                                ((DefaultTreeModel) tree.getModel()).reload();
                                                for (TreePath path : expanded) {
                                                    tree.expandPath(path);
                                                }


                                                for (int i = 0; i < z.zbiornik.size(); i++) {
                                                    for (int j = 0; j < z.zbiornik.get(i).get_nazwy_plikow().size(); j++) {

                                                        if (z.zbiornik.get(i).get_nazwy_plikow().get(j).numer.equals(numer.get(0))) {

                                                            z.zbiornik.get(i).get_nazwy_plikow().get(j).change_nazwa_pliku(nowaNazwa[0]);
                                                            z.zbiornik.get(i).get_nazwy_plikow().get(j).aktualizujNazweSystemowa();

                                                        }
                                                    }
                                                }
                                                z.zmienPobrane(staraNazwa, nowaNazwa[0], numer.get(0));
                                                z.zakualizuj_zbiornik();

                                                tree.revalidate();
                                                tree.repaint();

                                                p = z.zbiornik;

                                            }
                                        });

                                    }
                                });
                                usunTemat.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (!aktualny_plik.get_nazwa_pliku().contains("Archiwum")) {
                                            Vector<Integer> vector = numer_po_sciezce2(path, z.zbiornik);
                                            Vector<Integer> numerOjca = new Vector<>();

                                            for (int i = 0; i < vector.size() - 1; i++) {
                                                numerOjca.add(vector.get(i));
                                            }
                                            Pakiet ojciec = z.zwrocPakietPoNumerze(numerOjca);

                                            if ((ojciec.get_nazwy_plikow().size() > 1 || z.czyMamDziecko(ojciec)) || z.poziom_nizej(z.getOjciec(ojciec.numer)).size() > 1) {
                                                final JFrame frame = new JFrame();
                                                JLabel czyUsunac = new JLabel("Jesteś pewnien, że chcesz usunąć temat: " + aktualny_plik.get_nazwa_pliku());
                                                JButton tak = new JButton("Tak");
                                                JButton nie = new JButton("Nie");
                                                frame.setLayout(new MigLayout());
                                                frame.add(czyUsunac, "cell 0 0 1 0,wrap");
                                                frame.add(tak);
                                                frame.add(nie);
                                                frame.pack();
                                                frame.setVisible(true);
                                                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                                tak.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        Vector<Integer> wybranyNumer = numer_po_sciezce2(path, z.zbiornik);
                                                        String nazwaWybranegoTematu = node.toString();

                                                        // node.removeFromParent();
                                                        DefaultTreeModel m = (DefaultTreeModel) tree.getModel();
                                                        DefaultMutableTreeNode d = (DefaultMutableTreeNode) path.getLastPathComponent();
                                                        m.removeNodeFromParent(d);
                                                        nazwaWybranegoTematu += "$";
                                                        for (int i = 0; i < wybranyNumer.size(); i++) {
                                                            nazwaWybranegoTematu += wybranyNumer.get(i) + ",";
                                                        }
                                                        nazwaWybranegoTematu += ".txt";
                                                        Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                                                        pomocnik_plikowy.DeleteFile(nazwaWybranegoTematu);
                                                        z.removeChosenTemat(wybranyNumer, getArchiwumForFile(aktualny_plik, z));

                                                        ObsługaNazwowa ob = new ObsługaNazwowa();
                                                        String plikZTekstem = ob.oddzielNazeOdDaty(nazwaWybranegoTematu).nazwa;
                                                        plikZTekstem = "Tekst()" + plikZTekstem;
                                                        plikZTekstem += ".txt";

                                                        pomocnik_plikowy.DeleteFile(plikZTekstem);


                                                        tree.revalidate();
                                                        tree.repaint();
                                                        frame.dispose();
                                                    }
                                                });
                                                nie.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        frame.dispose();
                                                    }
                                                });
                                            }
                                        /*Vector < Integer > wybranyNumer = numer_po_sciezce2(path, z.zbiornik);
                                String nazwaWybranegoTematu=node.toString();

                               // node.removeFromParent();
                                DefaultTreeModel m= (DefaultTreeModel) tree.getModel();
                                DefaultMutableTreeNode d=(DefaultMutableTreeNode)path.getLastPathComponent();
                                m.removeNodeFromParent(d);
                                nazwaWybranegoTematu+="$";
                                for(int i=0;i<wybranyNumer.size();i++)
                                {
                                    nazwaWybranegoTematu+=wybranyNumer.get(i)+",";
                                }
                                nazwaWybranegoTematu+=".txt";
                                Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
                                pomocnik_plikowy.DeleteFile(nazwaWybranegoTematu);
                                z.removeChosenTemat(wybranyNumer);


                                tree.revalidate();
                                tree.repaint();*/


                                        }
                                    }
                                });


                                if (aktualny_plik.nazwa_systemowa.contains("&")) {
                                    JMenuItem tekstPiosenki = new JMenuItem("Tekst piosenki");
                                    menu.add(tekstPiosenki);


                                    tekstPiosenki.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            panelRodzajuPracy.removeAll();
                                            final JTextArea piosenka = new JTextArea();
                                            piosenka.setFont(new Font("Serif", Font.ITALIC, 20));//ustalanie czcionki tekstu piosenki
                                            piosenka.setBackground(new Color(204, 255, 204));//ustalanie koloru tła
                                            piosenka.setMargin(new Insets(30, 40, 10, 0));//ustalanie marginesów
                                            JScrollPane jScrollPane = new JScrollPane(piosenka);
                                            jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                                            //jScrollPane.setSize(500,600);
                                            panelRodzajuPracy.setLayout(new MigLayout());

                                            panelRodzajuPracy.add(jScrollPane, "w 400:500:600,h 500:600:700,gapbefore 50,gaptop 40");
                                            Thread th[];
                                            JButton play = new JButton("Play");
                                            JButton off = new JButton("Off");
                                            JButton ustawTekst = new JButton(("Ustaw Tekst"));
                                            JButton otworzWNowymOknie = new JButton("Nowe okno");
                                            ObsługaNazwowa obs = new ObsługaNazwowa();
                                            String nazwaKlipu = obs.oddzielNazeOdDaty(aktualny_plik.get_nazwa_pliku()).nazwa;
                                            nazwaKlipu = nazwaKlipu.substring(1, nazwaKlipu.length()) + ".mp3";

                                            final PuszMiTenMiusik[] miusik = new PuszMiTenMiusik[1];

                                            final String finalNazwaKlipu = nazwaKlipu;
                                            Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                                            List<Strin> list = new LinkedList<>();
                                            ObsługaNazwowa ob = new ObsługaNazwowa();
                                            //  PomocnikDrzewowy pomocnikDrzewowy= new PomocnikDrzewowy();
                                            String tekst = "Tekst()" + ob.oddzielNazeOdDaty(aktualny_plik.get_nazwa_pliku()).nazwa;
                                            // Vector<Integer> vector=pomocnikDrzewowy.numerPoNazwie(aktualny_plik.)
                                    /*tekst+="$";
                                    for(int i=0;i<aktualny_plik.numer.size();i++)
                                    {
                                        tekst+=aktualny_plik.numer.get(i)+",";
                                    }*/
                                            tekst += ".txt";
                                            play.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {


                                                    miusik[0] = new PuszMiTenMiusik("music//" + finalNazwaKlipu);
                                                    miusik[0].start();

                                                }
                                            });
                                            off.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    //puszczaczPiosenek.stop1();
                                                    //  miusik.stop();
                                                    miusik[0].terminate();


                                                }
                                            });
                                            final String finalTekst = tekst;
                                            ustawTekst.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    String text = piosenka.getText();
                                                    Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                                                    LinkedList<Strin> l = new LinkedList<>();
                                                    Strin strin = new Strin(text);
                                                    l.add(strin);
                                                    pomocnik_plikowy.zapisywanie_do_pliku(finalTekst, '&', l, Strin.class);

                                                }
                                            });
                                            otworzWNowymOknie.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    JFrame jFrame = new JFrame();
                                                    JPanel jPanel = new JPanel();
                                                    jPanel.setLayout(new MigLayout());
                                                    JTextArea jTextArea = new JTextArea(piosenka.getText());

                                                    jTextArea.setFont(new Font("Serif", Font.ITALIC, 20));//ustalanie czcionki tekstu piosenki
                                                    jTextArea.setBackground(new Color(204, 255, 204));//ustalanie koloru tła
                                                    jTextArea.setMargin(new Insets(30, 40, 10, 0));//ustalanie marginesów
                                                    JScrollPane jScrollPane = new JScrollPane(jTextArea);
                                                    jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                                                    jPanel.add(jScrollPane, "w 400:500:600,h 500:600:700");
                                                    jFrame.add(jPanel);
                                                    jFrame.pack();
                                                    jFrame.setVisible(true);
                                                    jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


                                                }
                                            });
                                            panelRodzajuPracy.add(play);
                                            panelRodzajuPracy.add(off, "wrap");
                                            panelRodzajuPracy.add(ustawTekst);
                                            panelRodzajuPracy.add(otworzWNowymOknie);


                                            pomocnik_plikowy.zczytywanie_z_pliku(tekst, '&', list, Strin.class);
                                            for (int i = 0; i < list.size(); i++) {
                                                if (i != 0) {
                                                    piosenka.append("\n");
                                                }
                                                piosenka.append(list.get(i).string);
                                            }
                                            piosenka.setCaretPosition(0);
                                            panelRodzajuPracy.setVisible(true);
                                            panelRodzajuPracy.repaint();
                                            panelRodzajuPracy.revalidate();
                                        }
                                    });

                                }

                                if (aktualny_plik.getClass().equals(PlikZTekstem.class)||aktualny_plik.nazwa_systemowa.contains("@")) {
                                    JMenuItem pracaZTekstem = new JMenuItem("Praca z tekstem");
                                    menu.add(pracaZTekstem);
                                    pracaZTekstem.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            createPracaZTekstem();
                                        }
                                    });
                                }


                                menu.show((JComponent) e.getSource(), e.getX(), e.getY());
                                menu.setVisible(true);

                            }
                    else if(path!=null&&!node.isLeaf())
                            {

                                JPopupMenu menu= new JPopupMenu();
                                JMenuItem zmienNazwe= new JMenuItem("Zmień nazwę");
                                JMenuItem dodajTemat=new JMenuItem("Dodaj Temat");
                                JMenuItem usunGrupe= new JMenuItem("Usuń grupę");
                                JMenuItem dodajGrupe= new JMenuItem("Dodaj grupę");
                                final JMenuItem przemieszczenie= new JMenu("Przemieszczenie");


                                menu.add(zmienNazwe);
                                menu.add(dodajTemat);
                                menu.add(usunGrupe);
                                menu.add(dodajGrupe);
                                menu.add(przemieszczenie);
                                JMenuItem wGórę= new JMenuItem("W górę");
                                JMenuItem wDół= new JMenuItem("W dół");
                                przemieszczenie.add(wGórę);
                                przemieszczenie.add(wDół);
                                wGórę.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        Vector<Integer> numer=(Vector)numer_po_sciezce2(path,z.zbiornik).clone();

                                        Pakiet grupa=z.poszukiwanie_pakietu(numer);

                                        z.zamianaWGórę(grupa,getArchiwumForPackage(grupa,z));
                                        z.zaktualizujZmiany();
                                String s=getExpansionState();
                                drzewoReload(s);

                            }
                        });
                        wDół.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Vector<Integer> numer=(Vector)numer_po_sciezce2(path,z.zbiornik).clone();
                                Pakiet grupa=z.poszukiwanie_pakietu(numer);

                                //z.zamaianaWDół(grupa,getArchiwumForFile(aktualny_plik,z));
                                Vector<Integer> vector= new Vector<>();
                                for(int i=0;i<grupa.numer.size();i++)
                                {
                                    vector.add(grupa.numer.get(i));
                                }
                                vector.set(vector.size()-1,vector.get(vector.size()-1)+1);
                                Pakiet poszkodowany =z.poszukiwanie_pakietu(vector);
                                if(poszkodowany!=null){
                                z.zamianaWGórę(poszkodowany,getArchiwumForPackage(poszkodowany,z));


                                z.zaktualizujZmiany();
                                String s=getExpansionState();
                                drzewoReload(s);}
                            }
                        });


                        zmienNazwe.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                final String staraNazwa;
                                final String[] nowaNazwa = new String[1];
                                staraNazwa=node.getUserObject()+"";
                                final Vector<Vector<Integer>> numer= new Vector<>();

                                final JFrame jFrame= new JFrame();
                                JPanel jPanel= new JPanel();
                                jPanel.setLayout(new MigLayout());
                                JLabel jLabel= new JLabel("Nazwa nowej grupy");
                                JButton jButton= new JButton("Ustaw");
                                final JTextField jTextField= new JTextField();
                                jFrame.add(jPanel);

                                jPanel.add(jLabel);
                                jPanel.add(jTextField,"w 100:200:300,h 40:50:60,wrap");
                                jPanel.add(jButton,"cell 1 1 1 2");
                                jFrame.pack();
                                jFrame.setVisible(true);
                                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                jButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {


                                        numer.add(0, numer_po_sciezce2(path, z.zbiornik));

                                        nowaNazwa[0] = jTextField.getText();
                                        jFrame.dispose();

                                        node.setUserObject(nowaNazwa[0]);

                                        for(int i=0;i<z.zbiornik.size();i++)
                                        {
                                            if(z.zbiornik.get(i).numer.equals(numer.get(0)))
                                            {

                                                z.zbiornik.get(i).set_nazwa_pakietu(nowaNazwa[0]);
                                            }
                                        }

                                        //z.zakutualizujPobrane();
                                        //z.zakualizuj_zbiornik();


                                       z.zmienPobrane(staraNazwa,nowaNazwa[0],numer.get(0));
                                        //p=z.zbiornik;

                                       z.zakualizuj_zbiornik();
                                       // p=z.zbiornik;

                                        tree.revalidate();;
                                        tree.repaint();



                                    }
                                });





                            }
                        });
                        dodajTemat.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                Vector<Integer> numer=(Vector)numer_po_sciezce2(path,z.zbiornik).clone();

                                int magazyn=-1;

                                Vector<Integer> numerGrupy= new Vector<>();
                                int flaga=0;
                                int tmpIterator=1;

                                Pakiet grupa=z.poszukiwanie_pakietu(numer);

                                while(flaga==0)
                                {

                                    numer.addElement(tmpIterator);
                                    int tmpItereator2=0;
                                    flaga=1;



                                    while(tmpItereator2<grupa.get_nazwy_plikow().size())
                                    {


                                        if(grupa.get_nazwy_plikow().get(tmpItereator2).numer.equals(numer))
                                        {
                                            flaga=0;

                                        }

                                        tmpItereator2++;
                                    }

                                    tmpIterator++;
                                    if(flaga==0) {

                                        numer.remove(numer.size() - 1);

                                    }

                                }

                                flaga=0;
                                tmpIterator=1;

                                Strin strinNowy=new Strin();
                                Vector<Integer> tmp=(Vector)numer.clone();
                                tmp.remove(tmp.size()-1);
                                Pakiet wskaznik=z.zwrocPakietPoNumerze(tmp);
                                String wlasciwaNazwa="NowyTemat";

                                int flagaNowyTematn=0;
                                ObsługaNazwowa obsługaNazwowa= new ObsługaNazwowa();

                                while(flagaNowyTematn==0)
                                {flagaNowyTematn=1;
                                    for(int i=0;i<wskaznik.get_nazwy_plikow().size();i++)
                                    {


                                        if(obsługaNazwowa.oddzielNazeOdDaty(wskaznik.get_nazwy_plikow().get(i).get_nazwa_pliku()).nazwa.equals(wlasciwaNazwa))
                                        {
                                            flagaNowyTematn=0;
                                            if(i!=0&&i!=1)
                                            {
                                                wlasciwaNazwa=wlasciwaNazwa.substring(0,wlasciwaNazwa.length()-1);
                                            }
                                            wlasciwaNazwa+=(i+1)+"";
                                            break;
                                        }

                                    }
                                }

                                Calendar calendar= Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("DDDyy");



                                strinNowy.string=wlasciwaNazwa+"%"+simpleDateFormat.format(calendar.getTime())+"$";

                                for(int i=0;i<numer.size();i++)
                                {
                                    strinNowy.string+=numer.get(i)+",";
                                }
                                strinNowy.string+=".txt";


                                Plik plik;
                                if(wlasciwaNazwa.contains("@")){
                                    plik= new PlikZTekstem();
                                }
                                else plik= new Plik();
                                plik.numer=numer;
                                plik.set_nazwa_pliku(wlasciwaNazwa+"%"+simpleDateFormat.format(calendar.getTime()));
                                plik.nazwa_systemowa=strinNowy.string;
                                Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();

                               pomocnik_plikowy.CreateFile(strinNowy.string);


                                plik.lista= new LinkedList<>();
                                Slowo slowo= new Slowo();
                                slowo.pol="pol";
                                slowo.fore=z.zwrocJezykGrupy(grupa);
                                slowo.priority=0;
                                slowo.enumeracja=0;
                                slowo.repriority=0;
                                slowo.language="";
                                slowo.reArchiwum=0;
                                slowo.jezyk="angielski";
                                plik.statyplik=new StatyPlik(slowo);
                                //plik.lista.add(0,slowo);
                                wskaznik.get_nazwy_plikow().add(plik);
                                //plik.wypisanie_listy();
                                plik.zapis_zmian();
                                z.zaktualizujZmiany();
                                //plik.wypisanie_listy();
                                //plik.lista.remove(0);
                                DefaultMutableTreeNode nowy= new DefaultMutableTreeNode(wlasciwaNazwa+"%"+simpleDateFormat.format(calendar.getTime()));
                                DefaultTreeModel model= (DefaultTreeModel) tree.getModel();


                                DefaultMutableTreeNode aktualny=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                                aktualny.add(new DefaultMutableTreeNode(wlasciwaNazwa+"%"+simpleDateFormat.format(calendar.getTime())));
                               //model.reload();
                                //model.reload(aktualny);
                                List<TreePath> expanded = new ArrayList<>();
                                for (int i = 0; i < tree.getRowCount() - 1; i++) {
                                    TreePath currPath = tree.getPathForRow(i);
                                    TreePath nextPath = tree.getPathForRow(i + 1);
                                    if (currPath.isDescendant(nextPath)) {
                                        expanded.add(currPath);
                                    }
                                }
                                ((DefaultTreeModel)tree.getModel()).reload();
                                for (TreePath path : expanded) {
                                    tree.expandPath(path);
                                }

                                //((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).add(nowy);
                               // tree.revalidate();
                                //t/ree.repaint();

                            }
                        });
                        dodajGrupe.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Vector<Integer> numer=(Vector)numer_po_sciezce2(path,z.zbiornik).clone();
                                Pakiet grupaParent=z.poszukiwanie_pakietu(numer);
                                Pakiet nowaGrupa= new Pakiet();

                                int flag=0;
                                int i=1;
                                String nazwaNowejGrupy="NowaGrupa";
                                while(flag==0)
                                {
                                    flag=1;
                                    if(i!=1)
                                    {
                                        nazwaNowejGrupy=nazwaNowejGrupy.substring(0,nazwaNowejGrupy.length()-1);
                                    }
                                    nazwaNowejGrupy+=i+"";
                                    for(int j=0;j<z.zbiornik.size();j++)
                                    {
                                        if(z.pokrewienstwo(grupaParent,z.zbiornik.get(j))&&z.zbiornik.get(j).nazwa_pakietu.equals(nazwaNowejGrupy))
                                        {
                                            flag=0;
                                        }
                                    }

                                    i++;

                                }
                                flag=0;
                                i=1;

                                while(flag==0)
                                {
                                    flag=1;
                                    if(i==1) numer.add(i);
                                    else
                                    {
                                        numer.remove(numer.size()-1);
                                        numer.add(i);
                                    }

                                        if(z.zwrocPakietPoNumerze2(numer)!=null)
                                        {
                                            flag=0;
                                        }


                                    i++;

                                }
                                nowaGrupa.set_nazwa_pakietu(nazwaNowejGrupy);
                                nowaGrupa.numer=numer;

                                String nazwaDoZbiornika="";
                                nazwaDoZbiornika+=nowaGrupa+"$";
                                for(int x=0;x<numer.size();x++)
                                {
                                    nazwaDoZbiornika+=numer.get(x)+",";
                                }
                                z.zbiornik.add(nowaGrupa);
                                z.zaktualizujZmiany();
                                DefaultMutableTreeNode aktualny=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                                DefaultMutableTreeNode nowyNode=new DefaultMutableTreeNode(nazwaNowejGrupy);
                                aktualny.add(nowyNode);
                                DefaultTreeModel model= (DefaultTreeModel) tree.getModel();
                                Plik plikWNowejGrupie= new Plik();
                                String nazwaPliku="NowyTemat";
                                Calendar calendar= Calendar.getInstance();
                                SimpleDateFormat simpleDateFormat= new SimpleDateFormat("DDDyy");


                                nazwaPliku+="%"+simpleDateFormat.format(calendar.getTime());
                                String nazwaSystemowa=nazwaPliku+"$";
                                Vector<Integer> numerPliku=new Vector<>();
                                for(int a=0;a<numer.size();a++)
                                {
                                    numerPliku.add(numer.get(a));
                                }

                                numerPliku.add(1);

                                plikWNowejGrupie.set_nazwa_pliku(nazwaPliku);
                                plikWNowejGrupie.numer=numerPliku;
                                for(int a=0;a<numerPliku.size();a++)
                                {
                                    nazwaSystemowa+=numerPliku.get(a)+",";
                                }
                                nazwaSystemowa+=".txt";
                                plikWNowejGrupie.nazwa_systemowa=nazwaSystemowa;
                                nowaGrupa.get_nazwy_plikow().add(plikWNowejGrupie);
                                Slowo slowo= new Slowo();
                                slowo.pol="pol";
                                slowo.fore=z.zwrocJezykGrupy(grupaParent);
                                slowo.priority=0;
                                slowo.enumeracja=0;
                                slowo.repriority=0;
                                slowo.language="";
                                slowo.reArchiwum=3;
                                slowo.jezyk="angielski";
                                plikWNowejGrupie.statyplik=new StatyPlik(slowo);
                                //plikWNowejGrupie.lista.add(0,slowo);
                                z.zaktualizujZmiany();

                                plikWNowejGrupie.wypisanie_listy();
                                plikWNowejGrupie.zapis_zmian();
                                //plikWNowejGrupie.lista.remove(0);

                                plikWNowejGrupie.wypisanie_listy();

                                //plikWNowejGrupie.wypisanie_listy();
                                DefaultMutableTreeNode pierwszyPlik= new DefaultMutableTreeNode(plikWNowejGrupie.get_nazwa_pliku());
                                nowyNode.add(pierwszyPlik);
                                List<TreePath> expanded = new ArrayList<>();

                                for (int j = 0; j < tree.getRowCount() - 1; j++) {
                                    TreePath currPath = tree.getPathForRow(j);
                                    TreePath nextPath = tree.getPathForRow(j + 1);
                                    if (currPath.isDescendant(nextPath)) {
                                        expanded.add(currPath);
                                    }
                                }
                                ((DefaultTreeModel)tree.getModel()).reload();
                                for (TreePath path : expanded) {
                                    tree.expandPath(path);
                                }
                                Pomocnik_plikowy.CreateFile(plikWNowejGrupie.nazwa_systemowa);

                                //model.reload(aktualny);
                                tree.revalidate();
                                tree.repaint();





                            }
                        });
                        usunGrupe.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                final Vector<Integer> numer=(Vector)numer_po_sciezce2(path,z.zbiornik).clone();

                               // Vector<Integer> vector=numer_po_sciezce2(path,z.zbiornik);
                                final Vector<Integer> numerOjca=new Vector<>();

                                for(int i=0;i<numer.size()-1;i++)
                                {
                                    numerOjca.add(numer.get(i));
                                }

                                final Pakiet grupaParent=z.poszukiwanie_pakietu(numer);

                                if(!grupaParent.nazwa_pakietu.contains("Archiwum")){
                                    final JFrame frame = new JFrame();
                                    JLabel czyUsunac = new JLabel("Jesteś pewnien, że chcesz usunąć grupę: " + grupaParent.nazwa_pakietu);
                                    JButton tak = new JButton("Tak");
                                    JButton nie = new JButton("Nie");
                                    frame.setLayout(new MigLayout());
                                    frame.add(czyUsunac, "cell 0 0 1 0,wrap");
                                    frame.add(tak);
                                    frame.add(nie);
                                    frame.pack();
                                    frame.setVisible(true);
                                    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                                    tak.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            Pakiet ojciec=z.zwrocPakietPoNumerze(numerOjca);
                                            if(numer.size()==1||z.poziom_nizej(ojciec).size()>1|| ojciec.get_nazwy_plikow().size()>0) {

                                                DefaultTreeModel m = (DefaultTreeModel) tree.getModel();
                                                DefaultMutableTreeNode d = (DefaultMutableTreeNode) path.getLastPathComponent();
                                                m.removeNodeFromParent(d);


                                                z.usunPakiet(grupaParent,getArchiwumForPackage(grupaParent,z));
                                                tree.revalidate();
                                                tree.repaint();
                                                frame.dispose();
                                            }
                                        }
                                    });
                                    nie.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            frame.dispose();
                                        }
                                    });




                                /*DefaultMutableTreeNode aktualny=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                                aktualny.removeFromParent();
                                DefaultTreeModel model= (DefaultTreeModel) tree.getModel();
                                model.reload(aktualny);*/
                              /*  Pakiet ojciec=z.zwrocPakietPoNumerze(numerOjca);

                                    if(numer.size()==1||z.poziom_nizej(ojciec).size()>1|| ojciec.get_nazwy_plikow().size()>0) {

                                    DefaultTreeModel m = (DefaultTreeModel) tree.getModel();
                                    DefaultMutableTreeNode d = (DefaultMutableTreeNode) path.getLastPathComponent();
                                    m.removeNodeFromParent(d);


                                    //f  Vector<Pakiet> pradzieci= z.wszystkiePraDzieci(grupaParent);

                                    // z.usunWszystkiePradzieci(grupaParent);


                                   /* z.usunPakiet(grupaParent);
                                }*/
                                //tree.revalidate()
                                //tree.repaint();



                            }
                        }});
                        menu.show((JComponent) e.getSource(),e.getX(),e.getY());

                    }

                }
                else if(SwingUtilities.isLeftMouseButton(e))
            {
                if(path!=null&&node.isLeaf())
                {

                    createStatystyki();}
                    else if(path!=null&&node!=null)
                {

                    if(node.getUserObject().toString().contains("Aplikacja"))
                    {
                        createUstawieniaCalosciowe();
                    }
                    else
                    {

                        Vector<Integer> numer=(Vector)numer_po_sciezce2(path,z.zbiornik).clone();
                        Pakiet grupa=z.poszukiwanie_pakietu(numer);
                        createUstawieniaGrupy(grupa);

                    }
                }
            }

            //tree.getSelectionPath().getLastPathComponent()
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        // final ImageIcon imageIcon=new ImageIcon("Photo\\BigBen.jpg");
        tree.addTreeSelectionListener(this);
        //tree.add(new PopClickPlikListener());
        JScrollPane sp= new JScrollPane(tree);
        tree.setFont(new Font("Dialog", PLAIN,18));
        //sp.setPreferredSize(new Dimension(200,400));
        tree.setCellRenderer(new MyCellRanderer(this.z.zbiornik,z));

        // tree.setBackground(Color.GREEN);
        //tree.setBackground(Color.PINK);
        sp.setBackground(Color.GREEN);
       /*JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(sp.getWidth(),sp.getHeight()));
       sp.setViewport(new ImageViewport(sp));
        sp.getViewport().setView(label);*/
        //sp.set


//        tree.setCellRenderer( new MyCellRanderer());



        sp.setBackground(null);
        return sp;
    }

    public void createDodawanieSlowek1() {
        JPanel panel= panelRodzajuPracy;
        panel.setLayout(new MigLayout());
        JLabel slowoNapis= new JLabel("Dodawanie");
        slowoNapis.setFont(new Font("Dialog", Font.BOLD,16));
        panel.add(slowoNapis,"gapbefore 50");
        final JTextField slowo= new JTextField();
        slowo.setFont(new Font("Dialog", Font.PLAIN,20));
        panel.add(slowo,"wrap,w 200:250:300,h 50:60:70,cell 0 0");
        final JTextArea slowa= new JTextArea();
        slowa.setFont(new Font("Dialog", Font.BOLD,16));
       panel.add(slowa,"w 200:300:400,h 500:550:600,cell 0 1 0 13,gapbefore 50");
        final char[] francuskieLitery={'à','â','ç','é','è','ê','ë','î','ï','ô','û','ù','ü','ÿ'};
        final char[] capsLock={'À','Â', 'Ç', 'É', 'È','Ê','Ë' , 'Î','Ï', 'Ô', 'Û' ,'Ù', 'Ü', 'Ÿ'};
        final JButton capsLk= new JButton("CapsLk");
        final int[] flagaCapsLka = {0};
       panel.add(capsLk,"cell 1 0,wrap");
        JButton masoweDodanie= new JButton("masowe dodawanie");
        panel.add(masoweDodanie,"wrap");
        masoweDodanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

               /* String text=piosenka.getText();
                Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
                LinkedList<Strin> l= new LinkedList<>();
                Strin strin= new Strin(text);
                l.add(strin);
                pomocnik_plikowy.zapisywanie_do_pliku(finalTekst,'&',l,Strin.class);*/


                Strin kod= new Strin(slowa.getText());
                //kod.string= slowa.getText();
                LinkedList<Strin> l= new LinkedList<>();
                l.add(kod);
                Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
                //pomocnik_plikowy.CreateFile("Testowy.txt");

                pomocnik_plikowy.zapisywanie_do_pliku("Testowy.txt",'&',l,Strin.class);
                LinkedList<Slowo> li= new LinkedList<>();
                pomocnik_plikowy.zczytywanie_z_pliku("Testowy.txt",',',li,Slowo.class);
               // pomocnik_plikowy.DeleteFile("Testowy.txt");
                for(int i=0;i<li.size();i++)
                {

                    aktualny_plik.lista.add(li.get(i));
                }



                aktualny_plik.zapis_zmian();

            }
        });
        capsLk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flagaCapsLka[0] ==0) {
                    flagaCapsLka[0] = 1;
                    capsLk.setBackground(Color.GREEN);
                }
                else {
                    flagaCapsLka[0] = 0;
                    capsLk.setBackground(Color.cyan);
                }
            }
        });
        ListaSepcyficznychLiterek l=null;
        for(ListaSepcyficznychLiterek li:zbiornikLiter)
        {
            if(aktualny_plik.statyplik.obcy.toLowerCase().equals(li.jezyk))
            {
                l=li;
            }
        }

        if(l!=null){

            //final char[] francuskieLitery={'à','â','ç','é','è','ê','ë','î','ï','ô','û','ù','ü','ÿ'};
           // final char[] capsLock={'À','Â', 'Ç', 'É', 'È','Ê','Ë' , 'Î','Ï', 'Ô', 'Û' ,'Ù', 'Ü', 'Ÿ'};
      //  if(aktualny_plik.statyplik.obcy.toLowerCase().equals("francuski")){
        //for(int i=0;i<francuskieLitery.length;i++)
        for(int i=0;i<l.list.size();i++)
        {
            final JButton jButton= new JButton(l.list.get(i).mala);
            //final JButton jButton= new JButton("ẞ");
            if(l.list.get(i).mala.equals("ẞ")){jButton.setText("ß");
               }


            jButton.setFont(new Font("Dialog", Font.PLAIN,14));
            panel.add(jButton,"wrap,h 30:50:60,w 35:50:60,cell 1 "+(i+2));

            final ListaSepcyficznychLiterek finalL = l;
            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }
                @Override
                public void mousePressed(MouseEvent e) {
                    if(flagaCapsLka[0]==0) {
                        slowo.setText(slowo.getText() + jButton.getText());
                        slowo.requestFocusInWindow();
                    }
                    else
                    {
                        int a=0;
                        while(!jButton.getText().equals(finalL.list.get(a).mala+""))
                        {
                            a++;
                        }



                        slowo.setText(slowo.getText()+ finalL.list.get(a).wielka);
                        if(finalL.list.get(a).wielka.equals("ẞ")) slowo.setText(slowo.getText()+"ẞ");
                        slowo.requestFocusInWindow();

                    }
                    //else //slowo.setText(slowo.getText()+jButton.getText());
                }
                @Override
                public void mouseReleased(MouseEvent e) {

                }
                @Override
                public void mouseEntered(MouseEvent e) {

                }
                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }}//}


        Action wczytajSlowo= new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("łapie enter");

                if(!slowo.getText().equals(null)&&slowo.getText().contains(","))
                {
                    String pobrane= slowo.getText();
                    String pol="";
                    String ang="";
                    char[] pob=pobrane.toCharArray();
                    char ch=pob[0];
                    int i=1;
                    while(ch!=',')
                    {
                        pol+=ch;
                        ch=pob[i];
                        i++;
                    }
                    for(i=i;i<pob.length;i++)
                    {
                        ang+=pob[i];
                    }
                    slowo.setText("");
                    Slowo sl= new Slowo(pol,ang,jezyk,3);
                    aktualny_plik.lista.add(sl);
                    aktualny_plik.zapis_zmian();

                    slowa.setText(sl.pol+"-"+sl.fore+"\n"+slowa.getText());
                }




            }
        };
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),"wczytaj Slowo");
        panel.getActionMap().put("wczytaj Slowo",wczytajSlowo);

        /*addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {


            }

            @Override
            public void keyPressed(KeyEvent e) {


                if(e.getKeyChar()==KeyEvent.VK_ENTER)
                {

                if(!slowo.getText().equals(null)&&slowo.getText().contains("-"))
                {
                    String pobrane= slowo.getText();
                    String pol="";
                    String ang="";
                    char[] pob=pobrane.toCharArray();
                    char ch=pob[0];
                    int i=0;
                    while(ch!='-')
                    {
                        pol+=ch;
                        ch=pob[i];
                        i++;
                    }
                    for(i=i+1;i<pob.length;i++)
                    {
                      ang+=pob[i];
                    }
                    slowo.setText("");
                    Slowo sl= new Slowo();
                    sl.set_fore(jezyk);
                    sl.set_priority(3);
                    sl.fore=ang;
                    sl.pol=pol;
                    aktualny_plik.lista.add(sl);

                }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });*/

        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.setBackground(null);



    }
    public JScrollPane createPanelListySlowek()  {
        defaultLista= new DefaultListModel<>();

        for(int i=0;i<aktualny_plik.get_lista().size();i++)
        {
            if(this.aktualny_plik.getClass().equals(Plik.class)){

            Slowo slowo=(Slowo)this.aktualny_plik.get_lista().get(i);
            defaultLista.add(i, slowo.clone());}
            else
            {
                WordFromBook slowo=(WordFromBook) this.aktualny_plik.get_lista().get(i);
                defaultLista.add(i, slowo.clone());
            }

        }
        /*for(int i=0;i<this.aktualny_plik.get_lista().size();i++)
        {
            defaultLista.add(i,this.aktualny_plik.lista.get(i).pol);

        }*/
      /*  if(aktualny_plik.lista.get(0).getClass().equals(WordFromBook.class))
        {
            final JList<WordFromBook> jList= new JList<>(defaultLista);
        }*/
       // else {
            final JList<Slowo> jList = new JList<>(defaultLista);
      //  }
        jList.setCellRenderer(new DefaultListCellRenderer()
        {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
              //  if (value instanceof Slowo) {
                if(value.getClass().equals(Slowo.class)){
                    Slowo nextUser = (Slowo) value;
                    setText(index+1+". "+ nextUser.pol);
                    if (nextUser.language.contains("i")) {
                        setBackground(Color.GREEN);
                    } else {
                        setBackground(Color.RED);
                    }
                    if (isSelected) {
                        setBackground(getBackground().darker());
                    }
                }
             //  else if (value instanceof WordFromBook) {
            else if(value.getClass().equals(WordFromBook.class)){
                    WordFromBook nextUser = (WordFromBook) value;
                    setText(index+1+". "+ nextUser.pol);
                    if (nextUser.language.contains("i")) {
                        setBackground(Color.GREEN);
                    } else {
                        setBackground(Color.RED);
                    }
                    if (isSelected) {
                        setBackground(getBackground().darker());
                    }
                }
                return c;
            }});
        jList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if(aktualny_plik.getClass().equals(PlikZTekstem.class))
                {

                }

                if(SwingUtilities.isLeftMouseButton(e)) {
                    if (e.getClickCount() == 1) {
                        if (aktualny_plik.getClass().equals(PlikZTekstem.class)){
                            WordFromBook slowo=(WordFromBook) defaultLista.getElementAt(jList.locationToIndex(e.getPoint()));
                            if (!slowo.pol.contains("-")) {
                                defaultLista.setElementAt(new WordFromBook(slowo.pol + "-" + slowo.fore, slowo.fore, slowo.language, slowo.priority,slowo.link), jList.locationToIndex(e.getPoint()));
                            } else {
                                int n = slowo.pol.indexOf("-");
                                slowo.pol = slowo.pol.substring(0, n);
                                defaultLista.setElementAt(slowo, jList.locationToIndex(e.getPoint()));
                            }}

                        else {
                            Slowo slowo = defaultLista.getElementAt(jList.locationToIndex(e.getPoint()));
                        if (!slowo.pol.contains("-")) {
                            defaultLista.setElementAt(new Slowo(slowo.pol + "-" + slowo.fore, slowo.fore, slowo.language, slowo.priority), jList.locationToIndex(e.getPoint()));
                        } else {
                            int n = slowo.pol.indexOf("-");
                            slowo.pol = slowo.pol.substring(0, n);
                            defaultLista.setElementAt(slowo, jList.locationToIndex(e.getPoint()));}

                        }
                    } else if (e.getClickCount() == 2) {
                        if (!defaultLista.getElementAt(jList.locationToIndex(e.getPoint())).language.contains("i")) {

                            defaultLista.getElementAt(jList.locationToIndex(e.getPoint())).language += "i";
                            aktualny_plik.lista.get(jList.locationToIndex(e.getPoint())).language=aktualny_plik.lista.get(jList.locationToIndex(e.getPoint())).language+"i";
                        }
                        else {
                            defaultLista.getElementAt(jList.locationToIndex(e.getPoint())).language = defaultLista.getElementAt(jList.locationToIndex(e.getPoint())).language.substring(defaultLista.getElementAt(jList.locationToIndex(e.getPoint())).language.length());
                            aktualny_plik.lista.get(jList.locationToIndex(e.getPoint())).language= aktualny_plik.lista.get(jList.locationToIndex(e.getPoint())).language.substring(0, aktualny_plik.lista.get(jList.locationToIndex(e.getPoint())).language.length()-1);
                        }


                        jList.repaint();

                        aktualny_plik.zapis_zmian();
                    }
                }
                else if(SwingUtilities.isRightMouseButton(e))
                {
                    Point point=e.getPoint();
                    final Slowo slowo=defaultLista.getElementAt(jList.locationToIndex(e.getPoint()));
                    final Slowo orginal= aktualny_plik.lista.get(jList.locationToIndex(e.getPoint()));
                    JPopupMenu menuSlowka= new JPopupMenu();
                    JMenuItem edytuj= new JMenuItem("Edytuj słówko");
                    menuSlowka.add(edytuj);
                    edytuj.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            final JFrame jFrame= new JFrame();
                            jFrame.setLayout(new MigLayout());
                            final JTextField jTextField1= new JTextField();
                            final JTextField jTextField2= new JTextField();
                            jFrame.add(jTextField1,"w 100:150:200");
                            jFrame.add(jTextField2,"w 100:150:200");
                            jTextField1.setFont(new Font("Dialog", Font.BOLD,18));
                            jTextField2.setFont(new Font("Dialog", Font.BOLD,18));
                            jTextField1.setText(orginal.pol);
                            jTextField2.setText(orginal.fore);

                            final char[] francuskieLitery={'à','â','ç','é','è','ê','ë','î','ï','ô','û','ù','ü','ÿ'};
                            final char[] capsLock={'À','Â', 'Ç', 'É', 'È','Ê','Ë' , 'Î','Ï', 'Ô', 'Û' ,'Ù', 'Ü', 'Ÿ'};
                            JButton francuskieLiterki= new JButton("Literki");
                            jFrame.add(francuskieLiterki);
                            //JButton capsLk= new JButton("CapsLk");
                           // jFrame.add(capsLk);
                            //final int[] flagaCapsLka = {0};
                            final boolean[] flagaCapsLka = {Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)};
                            //boolean state= Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);

                            /*capsLk.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(flagaCapsLka[0] ==0) {
                                        flagaCapsLka[0] = 1;
                                        capsLk.setBackground(Color.GREEN);
                                    }
                                    else {
                                        flagaCapsLka[0] = 0;
                                        capsLk.setBackground(Color.cyan);
                                    }
                                }
                            });*/

                            francuskieLiterki.addMouseListener(new MouseListener() {
                                @Override
                                public void mouseClicked(MouseEvent e) {

                                }

                                @Override
                                public void mousePressed(MouseEvent e) {
                                    flagaCapsLka[0] = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);
                                    JPopupMenu menu= new JPopupMenu();



                                    for(int i=0;i<francuskieLitery.length;i++) {
                                        final JMenuItem jMenuItem= new JMenuItem(francuskieLitery[i]+"");
                                        jMenuItem.setFont(new Font("Dialog", Font.BOLD,18));
                                        menu.add(jMenuItem);
                                        jMenuItem.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (!flagaCapsLka[0]) {
                                                    jTextField2.setText(jTextField2.getText() + jMenuItem.getText());
                                                    jTextField2.requestFocusInWindow();
                                                } else {
                                                    int a = 0;
                                                    while (!jMenuItem.getText().equals(francuskieLitery[a] + "")) {
                                                        a++;
                                                    }
                                                    jTextField2.setText(jTextField2.getText() + capsLock[a]);
                                                    jTextField2.requestFocusInWindow();

                                                }
                                            }
                                        });

                                    }
                                    menu.show((JComponent) e.getSource(), e.getX(), e.getY());
                                    menu.setVisible(true);
                                }

                                @Override
                                public void mouseReleased(MouseEvent e) {

                                }

                                @Override
                                public void mouseEntered(MouseEvent e) {

                                }

                                @Override
                                public void mouseExited(MouseEvent e) {

                                }

                            });

                            JButton jButton= new JButton("Zmień");
                            jFrame.add(jButton);
                            jFrame.pack();
                            jFrame.setVisible(true);

                            jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                            jButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    slowo.fore=jTextField2.getText();
                                    slowo.pol=jTextField1.getText();
                                    orginal.pol=slowo.pol;
                                    orginal.fore=slowo.fore;
                                    aktualny_plik.zapis_zmian();
                                    //z.zaktualizujZmiany();
                                    jFrame.dispose();


                                }
                            }



                            );

                        }
                    });
                    JMenuItem usun= new JMenuItem("Usuń");
                    menuSlowka.add(usun);
                    usun.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ex) {
                            int n=jList.locationToIndex(e.getPoint());
                            defaultLista.removeElementAt(n);
                            //aktualny_plik.lista.remove(n);
                            aktualny_plik.removeSlowko(n);
                            //int a=jList.locationToIndex(e.getPoint());



                           aktualny_plik.zapis_zmian();
                        }
                    });
                    JMenuItem obroc= new JMenuItem("Obróć");
                    menuSlowka.add(obroc);
                    obroc.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String tmp= orginal.fore;
                            orginal.fore=orginal.pol;
                            orginal.pol=tmp;
                            slowo.pol=orginal.pol;
                            slowo.fore=orginal.fore;
                            //z.zaktualizujZmiany();
                            aktualny_plik.zapis_zmian();
                        }
                    });
                    JMenuItem przenies= new JMenuItem("Przenieś do archiwum");
                    menuSlowka.add(przenies);
                    defaultLista.getElementAt(jList.locationToIndex(e.getPoint()));


                    if(aktualny_plik.get_nazwa_pliku().contains("Archiwum"))
                    {
                        JMenuItem przywroc= new JMenuItem("Przywróć");
                        menuSlowka.add(przywroc);
                        przywroc.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ex) {
                                Plik plik=z.zwrocPlikPoNumerze(ObsługaNazwowa.getNumerSlowoArchiwum(orginal.powrotArchiwum));
                                orginal.powrotArchiwum="";
                                plik.lista.add(orginal);
                                int n=jList.locationToIndex(e.getPoint());
                                defaultLista.removeElementAt(n);
                                aktualny_plik.removeSlowko(n);

                            }
                        });
                    }
                    menuSlowka.show(panelRodzajuPracy,e.getX(),e.getY());


                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        jList.setFont(new Font("Dialog", Font.BOLD,18));
        JScrollPane jScrollPane= new JScrollPane(jList);
        //panel.add(new JScrollPane(jScrollPane));
        //jScrollPane.setFont(new Font("Dialog", Font.PLAIN,18));





        return jScrollPane;
    }

    public void createTest1() {
        final JPanel panel= panelRodzajuPracy;
       // panel.add(new Gbutton(1,2,"ol"));
        //DrawPanel drawPanel=new DrawPanel();
       // drawPanel.setSize(30,50);
      //  panel.add(drawPanel);
        panel.setBackground(null);
        panel.setLayout(new MigLayout());
        JLabel labelPolskie =new JLabel("Polskie");
        labelPolskie.setFont(new Font("Dialog", Font.PLAIN,30));

        final JTextField pol= new JTextField();
        pol.setFont(new Font("Dialog", BOLD,25));
        pol.setBorder(standardBorder);
       // pol.setBackground(new Color(204,153,102));


        JLabel labelAngielskie= new JLabel("Angielskie");

        labelAngielskie.setFont(new Font("Dialog", Font.PLAIN,30));
        final JTextField ang= new JTextField();
        ang.setFont(new Font("Dialog", Font.PLAIN,25));

        setFont(new Font("Dialog", BOLD,30));
        JButton sprawdz= new JButton("Sprawdź");

        sprawdz.setBackground(new Color(204,153,102));
        sprawdz.setBorder(standardBorder);
       // sprawdz.setBackground(new Color(0,0,0,0));
        final JTextArea odpowiedz= new JTextArea();
        odpowiedz.setBorder(new LineBorder(Color.black,4));
        odpowiedz.setFont(new Font("Dialog", BOLD,20));
        odpowiedz.setBackground(new Color(207,207,207));
        JButton francuskieLiterki= new JButton("Literki");
        francuskieLiterki.setFont(new Font("Dialog", Font.BOLD,16));
        francuskieLiterki.setBackground(standardJButtonColor);
       francuskieLiterki.setBorder(standardBorder);
       // francuskieLiterki.setBackground(new Color(0,0,0,0));
        final char[] francuskieLitery={'à','â','ç','é','è','ê','ë','î','ï','ô','û','ù','ü','ÿ'};
        final char[] capsLock={'À','Â', 'Ç', 'É', 'È','Ê','Ë' , 'Î','Ï', 'Ô', 'Û' ,'Ù', 'Ü', 'Ÿ'};

        final JButton capsLk= new JButton("CapsLk");
        final JButton  kierunek=new JButton("pol-ang");
        kierunek.setFont(new Font("Dialog", Font.BOLD,16));
        if(aktualny_plik.ifDoArchiwum())kierunek.setBorder(new LineBorder(Color.red,3));
        kierunek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(kierunek.getText().equals("pol-ang"))
                {
                    kierunek.setText("ang-pol");
                    if(aktualny_plik.ifDoReArchiwum())kierunek.setBorder(new LineBorder(Color.ORANGE,3));
                    else kierunek.setBorder(null);
                    aktualny_plik.polang=false;
                    pol.setText("");
                    ang.setText("");
                    odpowiedz.setText("");
                }
                else if(kierunek.getText().equals("ang-pol"))
                {

                    if(aktualny_plik.ifDoArchiwum())kierunek.setBorder(new LineBorder(Color.red,3));
                    else kierunek.setBorder(null);
                    kierunek.setText("pol-ang");
                    aktualny_plik.polang=true;
                    pol.setText("");
                    ang.setText("");
                    odpowiedz.setText("");
                }
            }
        });
        //capsLk.setBackground(new Color(0,0,0,0));
        final int[] flagaCapsLka = {0};



        capsLk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flagaCapsLka[0] ==0) {
                    flagaCapsLka[0] = 1;
                    capsLk.setBackground(Color.GREEN);
                }
                else {
                    flagaCapsLka[0] = 0;
                    capsLk.setBackground(Color.cyan);
                }
            }
        });

        francuskieLiterki.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                JPopupMenu menu= new JPopupMenu();




                ListaSepcyficznychLiterek l=null;
                for(ListaSepcyficznychLiterek li:zbiornikLiter)
                {
                    if(aktualny_plik.statyplik.obcy.toLowerCase().equals(li.jezyk))
                    {
                        l=li;
                    }
                }

                if(l!=null){

                    for(int i=0;i<l.list.size();i++)
                    {



                    final JMenuItem jMenuItem= new JMenuItem(l.list.get(i).mala);
                        if(l.list.get(i).mala.equals("ẞ")){jMenuItem.setText("ß");
                        }
                    jMenuItem.setFont(new Font("Dialog", Font.BOLD,24));
                    menu.add(jMenuItem);
                    jMenuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (flagaCapsLka[0] == 0) {
                                ang.setText(ang.getText() + jMenuItem.getText());
                                ang.requestFocusInWindow();
                            } else {
                                int a = 0;
                                while (!jMenuItem.getText().equals(francuskieLitery[a] + "")) {
                                    a++;
                                }
                                ang.setText(ang.getText() + capsLock[a]);
                                ang.requestFocusInWindow();

                            }
                        }
                    });

                }}
                menu.show((JComponent) e.getSource(), e.getX(), e.getY());
                menu.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });
        //gaptop 150,gapbefore 120,
        panel.add(labelPolskie,"gaptop 150,gapbefore 120,cell 0 0");
        panel.add(pol,"w 150:300:350,h 40:50:60,cell 1 0");
        panel.add(capsLk,"cell 2 0");
        panel.add(labelAngielskie,"gapbefore 120,cell 0 1");
        panel.add(ang,"w 150:300:350,h 40:50:60,cell 1 1");
        panel.add(francuskieLiterki,"cell 2 1");
        francuskieLiterki.setPreferredSize(new Dimension(65,20));
        francuskieLiterki.setBorder(new LineBorder(Color.black,1));
        //francuskieLiterki.setBorder(standardBorder);
       // panel.add(sprawdz,"wrap,gapbefore 100,cell 1 2");
        panel.add(odpowiedz,"cell 0 2 3 2,w 400:500:600,h 150:200:300,ali,gapbefore 120");
       // panel.add(odpowiedz,"cell 2 0 2 3,w 400:500:600,h 150:200:300,dock south,ali,gapbefore 120");
        panel.add(kierunek,"cell 3 0");
        final JTextField wielkoscGrupy= new JTextField();
        final Checkbox grupowanie= new Checkbox();
        wielkoscGrupy.setFont(new Font("Dialog",BOLD,20));
        panel.add(wielkoscGrupy,"cell 3 1,height 30!,width 40!");

        panel.add(grupowanie,"cell 3 1");
        final CheckBoxList[] checkboxList = new CheckBoxList[1];

        if(aktualny_plik.getEnumeracje()!=0){
             checkboxList[0] = new CheckBoxList();
            MouseListener[] ml = (MouseListener[])checkboxList[0].getListeners(MouseListener.class);

            for (int j = 0; j < ml.length; j++)
                checkboxList[0].removeMouseListener( ml[j] );
            InputMap im = checkboxList[0].getInputMap();
            im.put(KeyStroke.getKeyStroke("SPACE"), "none");
            im.put(KeyStroke.getKeyStroke("released SPACE"), "none");
            grupowanie.setState(true);
            wielkoscGrupy.setText(aktualny_plik.getEnumeracje()+"");

            checkboxList[0].setOpaque(false);

            //checkboxList[0].setContentAreaFilled(false);
            //checkboxList[0].setBorderPainted(false);

            //checkboxList[0].setBorder(null);

            //checkboxList[0].setBackground(null);
        //LinkedList<Checkbox> checkboxLinkedList= new LinkedList<>();
            int liczbaCheckBoxow=(int)((float)aktualny_plik.getLiczbaSlowek()/(float)aktualny_plik.getEnumeracje()+0.99);
            Vector<Boolean> boolek=aktualny_plik.rozbicieEnumeracyjne();

        for(int i=0;i<liczbaCheckBoxow;i++)
        {


            JCheckBox checkbox=new JCheckBox(i+"",false);
            checkbox.setOpaque(false);

            checkbox.setSize(40,40);



            checkbox.setSelected(boolek.get(i));
            checkboxList[0].addCheckbox(checkbox);
            //checkboxLinkedList.add(checkbox);
            checkboxList[0].setOpaque(false);
        }

        panel.add(checkboxList[0],"cell 3 2");

        }
        wielkoscGrupy.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                grupowanie.setState(false);
            }
        });
        wielkoscGrupy.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //grupowanie.setState(false);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                grupowanie.setState(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                grupowanie.setState(false);
            }
        });
        grupowanie.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (grupowanie.getState())
                {
                    if(!wielkoscGrupy.getText().isEmpty()) {

                        int w = Integer.parseInt(wielkoscGrupy.getText());
                        if(w==0)
                        {
                            aktualny_plik.setEnumeracje(w);
                            z.zapisPlikow();
                            if (checkboxList[0] != null) {
                                panel.remove(checkboxList[0]);
                                panelRodzajuPracy.revalidate();
                                panelRodzajuPracy.repaint();

                            }
                        }
                        else
                        {
                        aktualny_plik.setEnumeracje(w);
                        z.zapisPlikow();
                        if (checkboxList[0] != null) {
                            panel.remove(checkboxList[0]);
                            panelRodzajuPracy.revalidate();

                        }
                        if (checkboxList[0] != null) checkboxList[0].removeAll();
                        checkboxList[0] = new CheckBoxList();
                            MouseListener[] ml = (MouseListener[])checkboxList[0].getListeners(MouseListener.class);

                            for (int j = 0; j < ml.length; j++)
                                checkboxList[0].removeMouseListener( ml[j] );
                            //InputMap im = checkboxList[0].getInputMap();
                          //  im.put(KeyStroke.getKeyStroke("SPACE"), "none");
                          //  im.put(KeyStroke.getKeyStroke("released SPACE"), "none");
                        checkboxList[0].setOpaque(false);
                        //LinkedList<Checkbox> checkboxLinkedList= new LinkedList<>();
                        int liczbaCheckBoxow = (int) ((float) aktualny_plik.getLiczbaSlowek() / (float) aktualny_plik.getEnumeracje() + 0.999);



                        for (int i = 0; i < liczbaCheckBoxow; i++) {
                            JCheckBox checkbox = new JCheckBox(i+1 + "", false);
                            checkbox.setSize(40, 40);
                            checkbox.setEnabled(false);
                            checkboxList[0].addCheckbox(checkbox);
                            checkbox.setOpaque(false);

                        }

                        panel.add(checkboxList[0], "cell 3 2");
                        // panelRodzajuPracy.removeAll();
                        //createTest1();
                        // panelRodzajuPracy.removeAll();
                        //checkboxList[0].revalidate();                //  checkboxList[0].repaint();
                        panelRodzajuPracy.revalidate();
                        panelRodzajuPracy.repaint();


                        // panel.revalidate();
                        //panel.repaint();

                    }

                    }
                }
            }
        });








        /*sprawdz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    aktualny_plik.testGUI(pol, ang, odpowiedz);
                } catch (Exception e1) {
                    // e1.printStackTrace();
                }

            }
        });*/

        Action wczytajSlowo= new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object object=tree.getLastSelectedPathComponent();
                try {
                    aktualny_plik.testGUI(pol, ang, odpowiedz,podsumowanie,getArchiwumForFile(aktualny_plik,z),tree);

                }
                catch(GratulacjeException ge)
                {
                  //  DefaultMutableTreeNode node =(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                    DefaultMutableTreeNode node =(DefaultMutableTreeNode) object;

                  //  String test=(String)node.getUserObject();

                    ObsługaNazwowa obsługaNazwowa= new ObsługaNazwowa();
                    String nazwea=obsługaNazwowa.zmienNaTeraz(aktualny_plik.get_nazwa_pliku());
                    System.out.println("Zmien na teraz "+aktualny_plik.get_nazwa_pliku());

                    if(!((String) node.getUserObject()).contains("Archiwum")){
                    node.setUserObject(nazwea);
                    z.zmienPobrane(aktualny_plik.get_nazwa_pliku(),nazwea,aktualny_plik.numer);
                    z.zakualizuj_zbiornik();}
                   /*

                    z.zmienPobrane(aktualny_plik.get_nazwa_pliku(),nazwea,aktualny_plik.numer);


                    z.wypisz_zbiornik();
                    z.zakualizuj_zbiornik();
                  //  z.zapelnij_Zbiornik();
                  // z.laduj_Zbiornik();
                    //z.pelne_zaladowanie_zbiornika();
                    z.zapelnij_Zbiornik();
                    z.wstawienie_plikow();
                    z.wczytaj_pliki();

                    z.wypisz_zbiornik();

                    for(int i=0;i<z.pobrane.size();i++)

                  //  z.wypisz_zbiornik();
                  */


                   z.zmienNazwePakietuPoNumerze(aktualny_plik.numer,nazwea);
                   //z.zakutualizujPobrane();
                   z.wypisz_zbiornik();
                    tree.revalidate();
                    tree.repaint();
                    p=z.zbiornik;
                //    DefaultTreeModel model= (DefaultTreeModel) tree.getModel();
                 //   model.reload();
                }
                catch(GratulacjeExceptionNull exceptionNull)
                {

                }
               /* catch(Exception e1)
                {

                }*/


            }
        };

        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),"wczytaj Slowo");
        panel.getActionMap().put("wczytaj Slowo",wczytajSlowo);

        panel.setFocusable(true);
        panel.requestFocusInWindow();



    }

    public void createStatystyki()
    {
        this.panelRodzajuPracy.removeAll();

        JLabel tytuł= new JLabel(aktualny_plik.getNazwaBezDany());
        tytuł.setFont(new Font("Dialog", Font.BOLD,24));
        this.panelRodzajuPracy.add(tytuł,"wrap");
    //this.panelRodzajuPracy.setFont(new Font("Dialog", Font.BOLD,24));
        JLabel opanowaneSłówka=new JLabel("opanowane słówka");
        JLabel answer1= new JLabel(this.aktualny_plik.opanowaneSlowka()+"");
        this.panelRodzajuPracy.add(opanowaneSłówka);
        this.panelRodzajuPracy.add(answer1,"wrap");

        JLabel iloscSlowekDoNauczenia= new JLabel("Ilość słówek do nauczenia");
        JLabel answer2= new JLabel(this.aktualny_plik.iloscSlowekDonauczenia()+"");
        this.panelRodzajuPracy.add(iloscSlowekDoNauczenia);
        this.panelRodzajuPracy.add(answer2,"wrap");

        JLabel procentOpanowanychSłówek= new JLabel("Procent opanowanych słówek");
        JLabel answer3= new JLabel(this.aktualny_plik.procentOpanowanychSłówek()+"%");
        this.panelRodzajuPracy.add(procentOpanowanychSłówek);
        this.panelRodzajuPracy.add(answer3,"wrap");

        JLabel iloscUdzielonychOdpowiedzi= new JLabel("ilosc udzielonych odpowiedzi");
        JLabel answer4= new JLabel(this.aktualny_plik.iloscUdzielonychOdpowiedzi()+"");
        this.panelRodzajuPracy.add(iloscUdzielonychOdpowiedzi);
        this.panelRodzajuPracy.add(answer4,"wrap");

        JLabel iloscOdpowiedziDoZakonczeniaTematu= new JLabel("Ilość odpowiedzi do zakonczenia tematu");
        JLabel answer5= new JLabel(this.aktualny_plik.iloscOdpowiedziDoZakonczeniaTematu()+"");
        this.panelRodzajuPracy.add(iloscOdpowiedziDoZakonczeniaTematu);
        this.panelRodzajuPracy.add(answer5,"wrap");;

        JLabel najgorszeSlowko=new JLabel("Njagorsze słówko");
        JLabel answer6= new JLabel(this.aktualny_plik.najgorszeSlowko());
        this.panelRodzajuPracy.add(najgorszeSlowko);
        this.panelRodzajuPracy.add(answer6,"wrap");

        JLabel procentOdpowiedziDoUdzielenia= new JLabel("Procent odpowiedzi do udzielenia");
        JLabel answer7= new JLabel(this.aktualny_plik.procentOdpowiedziDoUdzielenia()+"%");
        this.panelRodzajuPracy.add( procentOdpowiedziDoUdzielenia);
        this.panelRodzajuPracy.add(answer7,"wrap");

        JButton generujKod=new JButton("generuj kod");
        this.panelRodzajuPracy.add(generujKod);
        generujKod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LinkedList<Strin> l= new LinkedList<>();
                Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
                pomocnik_plikowy.zczytywanie_z_pliku(aktualny_plik.nazwa_systemowa,'&',l,Strin.class);

                JFrame jFrame= new JFrame();
                JPanel jPanel= new JPanel();

                JTextArea jTextArea= new JTextArea();

                    jTextArea.setText(l.get(0).string);


                for(int i=1;i<l.size();i++)
                {if(i!=0)
                {
                    jTextArea.append("\n");
                }
                    jTextArea.append(l.get(i).string);
                }


                jPanel.setLayout(new MigLayout());
                jPanel.add(jTextArea);

                jTextArea.setFont(new Font("Serif", Font.ITALIC, 20));//ustalanie czcionki tekstu piosenki
                jTextArea.setBackground(new Color(204,255,204));//ustalanie koloru tła
                jTextArea.setMargin(new Insets(30,40,10,0));//ustalanie marginesów
                JScrollPane jScrollPane= new JScrollPane(jTextArea);
                jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                jPanel.add(jScrollPane,"w 400:500:600,h 500:600:700");
                jFrame.add(jScrollPane);
                jFrame.pack();
                jFrame.setVisible(true);
                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);






            }
        });




        this.panelRodzajuPracy.revalidate();
        this.panelRodzajuPracy.repaint();




    }
    public void createStatystykiOgolne()
    {

    }
    public void createUstawieniaGrupy(final Pakiet grupa)
    {
        this.panelRodzajuPracy.removeAll();
        this.panelRodzajuPracy.setLayout(new MigLayout("fillx"));

        JLabel jlabel= new JLabel(grupa.nazwa_pakietu);
        Font tytulowa=new Font("Dialog",Font.BOLD,35);
        jlabel.setFont(tytulowa);
        this.panelRodzajuPracy.add(jlabel,"align center,wrap");
        JLabel jLabel2=new JLabel("Zmień język");
       // JButton jButton= new JButton(this.z.zwrocJezykGrupy(grupa));
        JMenuBar jMenuBar= new JMenuBar();
        JMenu jMenu= new JMenu(this.z.zwrocJezykGrupy(grupa));

        for(final Jezyk jezyk:this.jezyki)
        {
            JMenuItem jMenuItem= new JMenuItem(jezyk.nazwaJezyka);
            jMenuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    z.zmienJezyk(grupa,jezyk);
                }
            });
            jMenu.add(jMenuItem);
        }

        jMenuBar.add(jMenu);
        this.panelRodzajuPracy.add(jLabel2);
        this.panelRodzajuPracy.add(jMenuBar);
        //this.panelRodzajuPracy.add(jButton);

        this.panelRodzajuPracy.revalidate();
        this.panelRodzajuPracy.repaint();
    }
    public void createUstawieniaCalosciowe()
    {
        this.panelRodzajuPracy.removeAll();
        this.panelRodzajuPracy.setLayout(new MigLayout("fillx"));
        JLabel jlabel= new JLabel("Aplikacja");
        Font tytulowa=new Font("Dialog",Font.BOLD,35);
        jlabel.setFont(tytulowa);
        this.panelRodzajuPracy.add(jlabel,"align center,wrap");

        JLabel dodajJezyk= new JLabel("Dodaj nowy język");
        final JTextField nazwaNowegoJezyka= new JTextField();
        JButton dodajWpisanyJezyk= new JButton("Dodaj");
        dodajWpisanyJezyk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sprawdzenie= nazwaNowegoJezyka.getText();

                int flaga=1;
                for(Jezyk j: jezyki)
                {
                    if(j.nazwaJezyka.equals(sprawdzenie)) flaga=0;
                }
                if(flaga==1)
                {

                Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();



                Jezyk j= new Jezyk();
                j.nazwaJezyka=nazwaNowegoJezyka.getText();
                String tmp=j.nazwaJezyka;
                jezyki.add(j);

                pomocnik_plikowy.zapisywanie_do_pliku(ustawieniaSystemowe.plikZJezykami,',',jezyki,Jezyk.class);
                nazwaNowegoJezyka.setText("");


                Vector<Integer> nowyNumer=z.getNumerNowegoJezyka();
                Pakiet nowyJezyk= new Pakiet(tmp,new LinkedList<Plik>(),(Vector)nowyNumer.clone());

                nowyNumer.add(1);
                Pakiet nauka= new Pakiet("Nauka",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
                StatyPlik statyPlik1= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
                nowyNumer.add(1);
                Plik plik1= new Plik("Pierwsza lekcja",(Vector)nowyNumer.clone(),statyPlik1);


                nauka.get_nazwy_plikow().add(plik1);
                nowyNumer.remove(2);
                nowyNumer.remove(1);

                nowyNumer.add(2);
                Pakiet piosenki= new Pakiet("Piosenki",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
                StatyPlik statyPlik2= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
                nowyNumer.add(1);
                Plik plik2= new Plik("Pierwsza piosenka",(Vector)nowyNumer.clone(),statyPlik2);
             //   pomocnik_plikowy.CreateFile(plik2.nazwa_systemowa);
                piosenki.get_nazwy_plikow().add(plik2);
                nowyNumer.remove(2);
                nowyNumer.remove(1);

                nowyNumer.add(3);
                Pakiet ksiazki= new Pakiet("Książki",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
                StatyPlik statyPlik3= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
                nowyNumer.add(1);
                Plik plik3= new Plik("Pierwsza ksiażka",(Vector)nowyNumer.clone(),statyPlik3);
               // pomocnik_plikowy.CreateFile(plik3.nazwa_systemowa);
                ksiazki.get_nazwy_plikow().add(plik3);
                nowyNumer.remove(2);
                nowyNumer.remove(1);

                nowyNumer.add(4);
                Pakiet seriale= new Pakiet("Seriale",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
                StatyPlik statyPlik4= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
                nowyNumer.add(1);
                Plik plik4= new Plik("Pierwszy serial",(Vector)nowyNumer.clone(),statyPlik4);
              //  pomocnik_plikowy.CreateFile(plik4.nazwa_systemowa);
                seriale.get_nazwy_plikow().add(plik4);
                nowyNumer.remove(2);
                nowyNumer.remove(1);

                nowyNumer.add(5);
                Pakiet filmy= new Pakiet("Filmy",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
                StatyPlik statyPlik5= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
                nowyNumer.add(1);
                Plik plik5= new Plik("Pierwszy film",(Vector)nowyNumer.clone(),statyPlik5);
               // pomocnik_plikowy.CreateFile(plik5.nazwa_systemowa);
                filmy.get_nazwy_plikow().add(plik5);
                nowyNumer.remove(2);
                nowyNumer.remove(1);

                nowyNumer.add(6);

                StatyPlik statyPlik6= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");

                Plik technikaDziecka= new Plik("Metoda ciekawskiego dziecka "+tmp,(Vector)nowyNumer.clone(),statyPlik6);
              //  pomocnik_plikowy.CreateFile(technikaDziecka.nazwa_systemowa);
                nowyNumer.remove(1);
                nowyJezyk.get_nazwy_plikow().add(technikaDziecka);

                nowyNumer.add(7);

                StatyPlik statyPlik7= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");

                Plik archiwum= new Plik("Archiwum",(Vector)nowyNumer.clone(),statyPlik7);
            //    pomocnik_plikowy.CreateFile(archiwum.nazwa_systemowa);
                nowyJezyk.get_nazwy_plikow().add(archiwum);

                z.zbiornik.add(nowyJezyk);
                z.zbiornik.add(nauka);
                z.zbiornik.add(piosenki);
                z.zbiornik.add(ksiazki);
                z.zbiornik.add(seriale);
                z.zbiornik.add(filmy);
                z.zaktualizujZmiany();

                DefaultMutableTreeNode aktualny=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                DefaultMutableTreeNode nowyNode=new DefaultMutableTreeNode(nowyJezyk.nazwa_pakietu);
                aktualny.add(nowyNode);
                DefaultMutableTreeNode nodeNauka=new DefaultMutableTreeNode(nauka.nazwa_pakietu);
                nowyNode.add(nodeNauka);

                    DefaultMutableTreeNode nodePierwszaLekcja=new DefaultMutableTreeNode(plik1.get_nazwa_pliku());
                    nodeNauka.add(nodePierwszaLekcja);


                DefaultMutableTreeNode nodePiosenki=new DefaultMutableTreeNode(piosenki.nazwa_pakietu);
                nowyNode.add(nodePiosenki);
                    DefaultMutableTreeNode nodePierwszaPiosenka=new DefaultMutableTreeNode(plik2.get_nazwa_pliku());
                    nodePiosenki.add(nodePierwszaPiosenka);


                    DefaultMutableTreeNode nodeKsiazki=new DefaultMutableTreeNode(ksiazki.nazwa_pakietu);
                nowyNode.add(nodeKsiazki);
                    DefaultMutableTreeNode nodePierwszaKsiazka=new DefaultMutableTreeNode(plik3.get_nazwa_pliku());
                    nodeKsiazki.add(nodePierwszaKsiazka);


                    DefaultMutableTreeNode nodeSeriale=new DefaultMutableTreeNode(seriale.nazwa_pakietu);
                nowyNode.add(nodeSeriale);
                    DefaultMutableTreeNode nodePierwszySerial=new DefaultMutableTreeNode(plik4.get_nazwa_pliku());
                    nodeSeriale.add(nodePierwszySerial);


                    DefaultMutableTreeNode nodeFilmy=new DefaultMutableTreeNode(filmy.nazwa_pakietu);
                nowyNode.add(nodeFilmy);
                    DefaultMutableTreeNode nodePierwszyFilm=new DefaultMutableTreeNode(plik5.get_nazwa_pliku());
                    nodeFilmy.add(nodePierwszyFilm);


                    DefaultMutableTreeNode nodeTechnikaDziecka=new DefaultMutableTreeNode(technikaDziecka.get_nazwa_pliku());
                nowyNode.add(nodeTechnikaDziecka);

                DefaultMutableTreeNode nodeArchiwum=new DefaultMutableTreeNode(archiwum.get_nazwa_pliku());
                nowyNode.add(nodeArchiwum);

                    ((DefaultTreeModel)tree.getModel()).reload();
                   ListaSepcyficznychLiterek l= new ListaSepcyficznychLiterek();
                    l.jezyk=nowyJezyk.nazwa_pakietu;
                    try {

                        //File file= new File(j.nazwaJezyka+".txt");
                        FileInputStream test;
                        test=new FileInputStream(j.nazwaJezyka+".txt");
                        test.close();
                        pomocnik_plikowy.zczytywanie_z_pliku(j.nazwaJezyka+".txt",',',l.list,SpecyficznaLiterka.class);
                        zbiornikLiter.add(l);

                    }
                    catch(Exception ex)
                    {

                            if(new File("Literki/"+j.nazwaJezyka.toLowerCase()+".txt").exists()) {
                               ListaSepcyficznychLiterek specyficznaLiterkaLinkedList = new ListaSepcyficznychLiterek();
                                Pomocnik_plikowy.zczytywanie_z_pliku("Literki/" + j.nazwaJezyka.toLowerCase() + ".txt", ',', specyficznaLiterkaLinkedList.list, SpecyficznaLiterka.class);
                                Pomocnik_plikowy.CreateFile(j.nazwaJezyka.toLowerCase() + ".txt");
                                Pomocnik_plikowy.zapisywanie_do_pliku(j.nazwaJezyka.toLowerCase() + ".txt", ',', specyficznaLiterkaLinkedList.list, SpecyficznaLiterka.class);
                                specyficznaLiterkaLinkedList.jezyk=j.nazwaJezyka;
                                zbiornikLiter.add(specyficznaLiterkaLinkedList);

                            }

                           else Pomocnik_plikowy.CreateFile(j.nazwaJezyka.toLowerCase()+".txt");


                    }
                  //







                }
            else
            {
                JFrame jFrame= new JFrame();
                JLabel jLabel= new JLabel("Taki język już istanieje!!");
                jFrame.add(jLabel);
                jFrame.pack();
                jFrame.setVisible(true);
                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            }}
        });

        this.panelRodzajuPracy.add(dodajJezyk);
        this.panelRodzajuPracy.add(nazwaNowegoJezyka,"w 100!, h 40!");
        this.panelRodzajuPracy.add(dodajWpisanyJezyk,"wrap");

        for (final Jezyk j: jezyki) {
            final JLabel jLabel= new JLabel(j.nazwaJezyka);
            final JButton jButton= new JButton("X");
            jButton.setBackground(Color.red);
            this.panelRodzajuPracy.add(jLabel);
            this.panelRodzajuPracy.add(jButton,"wrap");
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(Jezyk je: jezyki)
                    {
                        if(je.equals(j)) jezyki.remove(je);
                }
               // z.zaktualizujZmiany();
                    Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
                    pomocnik_plikowy.zapisywanie_do_pliku(ustawieniaSystemowe.plikZJezykami,',',jezyki,Jezyk.class);
                    panelRodzajuPracy.remove(jLabel);
                    panelRodzajuPracy.remove(jButton);
                    panelRodzajuPracy.revalidate();
                    panelRodzajuPracy.repaint();

                }
            });

        }



        this.panelRodzajuPracy.revalidate();
        this.panelRodzajuPracy.repaint();
    }

    public void loadArchiwumForFile()
    {
        String thisArchiwum=null;
        Pakiet grupowy=null;
        for(Pakiet p:z.zbiornik)
        {
            if(p.numer.size()==1&&p.numer.get(0).equals(aktualny_plik.numer.get(0))){grupowy=p;break;}
        }

        Plik tmp=null;
        for(Plik p: grupowy.get_nazwy_plikow())
        {
            if(p.nazwa_systemowa.contains("Archiwum"))tmp=p;
        }
        tmp.zczytywanie();

    }
    public String getArchiwumForFile(Plik aktualny_plik,Zbiornik z)
    {
        String thisArchiwum=null;
        Pakiet grupowy=null;
        for(Pakiet p:z.zbiornik)
        {
            //if(p.numer.size()==1&&p.numer.get(0).equals(aktualny_plik.numer.get(0))){grupowy=p;break;}
            if(p.numer.size()==1&&p.numer.get(0).equals(aktualny_plik.numer.get(0))){grupowy=p;break;}
        }

        for(Plik p: grupowy.get_nazwy_plikow())
        {
            if(p.nazwa_systemowa.contains("Archiwum"))thisArchiwum=p.nazwa_systemowa;
        }
        return thisArchiwum;
}
    public String getArchiwumForPackage(Pakiet pakiet,Zbiornik z)
    {
        String thisArchiwum=null;
        Pakiet grupowy=null;
        for(Pakiet p:z.zbiornik)
        {
            if(p.numer.size()==1&&p.numer.get(0).equals(pakiet.numer.get(0))){grupowy=p;break;}
        }

        for(Plik p: grupowy.get_nazwy_plikow())
        {
            if(p.nazwa_systemowa.contains("Archiwum"))thisArchiwum=p.nazwa_systemowa;
        }
        return thisArchiwum;
    }
    public void createPracaZTekstem()
    {
        final int[] beginning = new int[1];
        final int[] ending = new int[1];

        panelRodzajuPracy.removeAll();
        panelRodzajuPracy.setLayout(new MigLayout("fill"));
       final PlikZTekstem p=(PlikZTekstem)aktualny_plik;
        final Object[] highlightTag = {null};
        final JTextPane tekst= new JTextPane();
        final JTextPane odpowiedzSlownikowa= new JTextPane();
        panelRodzajuPracy.add(tekst,"w 50%,h 80%,cell 0 0 0 2");
        panelRodzajuPracy.add(odpowiedzSlownikowa,"w 30%,h 40%,cell 1 0, top");
       final JScrollPane jScrollWskaznik=createPanelListySlowek();
        panelRodzajuPracy.add(jScrollWskaznik,"cell 1 1,w 30%,h 50%,top");

        JViewport viewport = jScrollWskaznik.getViewport();
        final JList jList = (JList)viewport.getView();
        jList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Highlighter highlighter = tekst.getHighlighter();
                Highlighter.HighlightPainter painter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.yellow);
                Highlighter.HighlightPainter whiter =
                        new DefaultHighlighter.DefaultHighlightPainter(Color.white);

                        WordFromBook wordFromBook=(WordFromBook)defaultLista.getElementAt(jList.locationToIndex(e.getPoint()));
                try {
                   // highlighter.addHighlight(0,tekst.getText().length(),null);
                    if (highlightTag[0] != null) {
                        tekst.getHighlighter().removeHighlight(highlightTag[0]);
                    }
                    highlightTag[0] =highlighter.addHighlight(wordFromBook.linksBeginning(), wordFromBook.linksEnding(), painter );


                } catch (BadLocationException e1) {
                    e1.printStackTrace();
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        }
        );
        tekst.setText(p.getTekstFromFile());

        String slowo;
        JButton ustaw= new JButton("Ustaw text");
        ustaw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                p.setTekst(tekst.getText());
            }
        });
        panelRodzajuPracy.add(ustaw,"cell 0 2");
        panelRodzajuPracy.revalidate();
        panelRodzajuPracy.repaint();
        tekst.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {
                StyledDocument doc = tekst.getStyledDocument();
                SimpleAttributeSet keyWord = new SimpleAttributeSet();
                StyleConstants.setUnderline(keyWord, Boolean.FALSE );


                doc.setCharacterAttributes(0, tekst.getText().length()-1, keyWord, false);
                int x = me.getX();
                int y = me.getY();
           //     System.out.println("X : " + x);
           //     System.out.println("Y : " + y);
                int startOffset = tekst.viewToModel(new Point(x, y));

                String wybraneSlowko="";
                int poczatek=0;
                int koniec=0;
               for(int n=startOffset;n>=0&&n<tekst.getText().length()-1;n--)
                {

                        Character c=tekst.getText().toCharArray()[n];
                        if(Character.isLetter(c))
                        {
                            wybraneSlowko=c+wybraneSlowko;
                        }
                        else
                        {
                            poczatek=n;
                            beginning[0] =n;
                            break;
                        }

                    }


                for(int n=startOffset+1;n<tekst.getText().length()-1;n++) {
                    Character c = tekst.getText().toCharArray()[n];

                    if (Character.isLetter(c))

                    {
                        wybraneSlowko += c;
                    } else {
                        koniec=n;
                        ending[0] =n;
                        break;
                    }

                }
                doc = tekst.getStyledDocument();
                keyWord = new SimpleAttributeSet();
                StyleConstants.setUnderline(keyWord, Boolean.TRUE );
                doc.setCharacterAttributes(poczatek, koniec-poczatek, keyWord, false);

              //  tekst.getText();
               /* class JTextTrick extends JTextArea
                {
                    public Document getDocumentModel()
                    {
                        return this.createDefaultModel();
                    }
                }*/
               // Document doc= tekst.getDocument();

                 //   System.out.println("Start Offset : " + startOffset);
             Slownik wsk=null;
             for(Slownik s:slowniki)
             {

                 System.out.println(s.jezyk1.toLowerCase().equals(aktualny_plik.statyplik.nasz.toLowerCase())&&s.jezyk2.toLowerCase().equals(aktualny_plik.statyplik.obcy.toLowerCase()));
                 if(s.jezyk1.toLowerCase().equals(aktualny_plik.statyplik.nasz.toLowerCase())&&s.jezyk2.toLowerCase().equals(aktualny_plik.statyplik.obcy.toLowerCase()))
                 {
                     wsk=s;
                     break;
                 }
             }
             wsk.answerJTextService(wybraneSlowko,odpowiedzSlownikowa);

                 //   System.out.println("Search Location : " + searchLocation);
                //  if (searchLocation == startOffset)
                    //JOptionPane.showMessageDialog(Drzewo.this, wybraneSlowko);
                if(SwingUtilities.isRightMouseButton(me))
                {
                    final JFrame jFrame= new JFrame();
                    final JPanel jPanel= new JPanel();
                    jPanel.setLayout(new MigLayout());
                    jFrame.add(jPanel);
                    final JTextField slowo= new JTextField(wybraneSlowko);
                    final JTextField tlumaczenie= new JTextField();
                    jPanel.add(slowo,"w 100:150:200,h  20:30:40");
                    jPanel.add(tlumaczenie,"w 100:150:200,h  20:30:40");



                    Action wczytajSlowo= new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //System.out.println("łapie enter");

                            if(!slowo.getText().equals(null)&&!tlumaczenie.getText().equals(null))
                            {
                                WordFromBook wordFromBook= new WordFromBook(slowo.getText(),tlumaczenie.getText(),aktualny_plik.statyplik.obcy,1,beginning[0]+"-"+ending[0]);
                                aktualny_plik.lista.add(wordFromBook);
                                defaultLista.addElement(wordFromBook);
                                aktualny_plik.zapis_zmian();
                                jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
                            }
                            else
                            {
                                jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
                            }




                        }
                    };
                    jPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),"wczytaj Slowo");
                    jPanel.getActionMap().put("wczytaj Slowo",wczytajSlowo);
                    jFrame.pack();
                    jFrame.setVisible(true);
                    jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                }
            }
        });

        odpowiedzSlownikowa.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent me)
            {if(SwingUtilities.isRightMouseButton(me))
            {
                StyledDocument doc = odpowiedzSlownikowa.getStyledDocument();
                //SimpleAttributeSet keyWord = new SimpleAttributeSet();
              //  StyleConstants.setUnderline(keyWord, Boolean.FALSE );


                //doc.setCharacterAttributes(0, odpowiedzSlownikowa.getText().length()-1, keyWord, false);
                int x = me.getX();
                int y = me.getY();
                //     System.out.println("X : " + x);
                //     System.out.println("Y : " + y);
                int startOffset = odpowiedzSlownikowa.viewToModel(new Point(x, y));

                String wybraneSlowko="";
                int poczatek=0;
                int koniec=0;
                for(int n=startOffset;n>=0&&n<odpowiedzSlownikowa.getText().length()-1;n--)
                {

                    Character c=odpowiedzSlownikowa.getText().toCharArray()[n];
                    if(Character.isLetter(c))
                    {
                        wybraneSlowko=c+wybraneSlowko;
                    }
                    else
                    {
                        poczatek=n;
                        break;
                    }

                }


                for(int n=startOffset+1;n<odpowiedzSlownikowa.getText().length()-1;n++) {
                    Character c = odpowiedzSlownikowa.getText().toCharArray()[n];

                    if (Character.isLetter(c))

                    {
                        wybraneSlowko += c;
                    } else {
                        koniec=n;
                        break;
                    }

                }
                String pytanie="";
                int flaga2=-1;
                //wybrane słówko będzie naszym tłumaczeniem
                for(Character c:odpowiedzSlownikowa.getText().toCharArray())
                {
                    if(Character.isLetter(c))
                    {
                        pytanie+=c;
                    }
                    else if(c.equals('-'))
                    {
                        flaga2=1;
                        break;
                    }
                }

                if(flaga2==1&&!wybraneSlowko.equals(pytanie)) {
                    if(aktualny_plik.getClass().equals(Plik.class)) {
                        Slowo noweSlowo = new Slowo(wybraneSlowko, pytanie, aktualny_plik.statyplik.obcy, 1);
                        aktualny_plik.lista.add(noweSlowo);
                        defaultLista.addElement(noweSlowo);
                    }
                    else if(aktualny_plik.getClass().equals(PlikZTekstem.class))
                    {
                        WordFromBook noweSlowo = new WordFromBook(wybraneSlowko, pytanie, aktualny_plik.statyplik.obcy, 1,beginning[0]+"-"+ending[0]);
                        noweSlowo.link=beginning[0]+"-"+ending[0];
                        aktualny_plik.lista.add(noweSlowo);
                        defaultLista.addElement(noweSlowo);
                    }
                    aktualny_plik.zapis_zmian();

                    //panelRodzajuPracy.revalidate();
                   // panelRodzajuPracy.repaint();
                  //  panelRodzajuPracy.remove(jScrollWskaznik);

               //     panelRodzajuPracy.add(createPanelListySlowek(),"cell 1 1,w 30%,h 50%,top");
              //     // jScrollWskaznik.revalidate();
                   // jScrollWskaznik.repaint();
                    //drzewoReload(getExpansionState());
                }
               // doc = odpowiedzSlownikowa.getStyledDocument();
                //keyWord = new SimpleAttributeSet();
               // StyleConstants.setUnderline(keyWord, Boolean.TRUE );
                //doc.setCharacterAttributes(poczatek, koniec-poczatek, keyWord, false);



                //  tekst.getText();
               /* class JTextTrick extends JTextArea
                {
                    public Document getDocumentModel()
                    {
                        return this.createDefaultModel();
                    }
                }*/
                // Document doc= tekst.getDocument();

                //   System.out.println("Start Offset : " + startOffset);
                /*Slownik wsk=null;
                for(Slownik s:slowniki)
                {
                    if(s.jezyk1.toLowerCase().equals(aktualny_plik.statyplik.nasz.toLowerCase())&&s.jezyk2.toLowerCase().equals(aktualny_plik.statyplik.obcy.toLowerCase()))
                    {
                        wsk=s;
                        break;
                    }
                }*/
                //wsk.answerJTextService(wybraneSlowko,odpowiedzSlownikowa);

                //   System.out.println("Search Location : " + searchLocation);
                //  if (searchLocation == startOffset)
                //JOptionPane.showMessageDialog(Drzewo.this, wybraneSlowko);


            }}
        });



        //JPanel slownik= tlumaczenieSlownikowe(slowo);



    }
    public JPanel tlumaczenieSlownikowe(String slowo, String jezyk, String tlumaczenieNa)
    {
        return new JPanel();
    }

    public void drzewoReload(String pathe)
    {
        pocz.removeAllChildren();

        final Pakiet pakiet= new Pakiet();




        uzupelnij_dziecmi(p,pocz,pakiet);
       /* List<TreePath> expanded = new ArrayList<>();
        for (int i = 0; i < tree.getRowCount() - 1; i++) {
            TreePath currPath = tree.getPathForRow(i);
            TreePath nextPath = tree.getPathForRow(i + 1);
            if (currPath.isDescendant(nextPath)) {
                expanded.add(currPath);
            }
        }

        ((DefaultTreeModel)tree.getModel()).reload();

        for (TreePath path : expanded) {
            tree.expandPath(path);
        }*/
        ((DefaultTreeModel)tree.getModel()).reload();
        setExpansionState(pathe);

    }


    public void setExpansionState(String s){

        String[] indexes = s.split(",");

        for ( String st : indexes ){

            int row = Integer.parseInt(st);

            tree.expandRow(row);

        }

    }
    public String getExpansionState(){

        StringBuilder sb = new StringBuilder();

        for ( int i = 0; i < tree.getRowCount(); i++ ){

            if ( tree.isExpanded(i) ){

                sb.append(i).append(",");

            }

        }

        return sb.toString();

    }
    public void zczytywanieSlownikow(String sciezkaDoSlownikow)
    {

        List<Strin> listtmp= new LinkedList<>();
       Pomocnik_plikowy.zczytywanie_z_pliku(sciezkaDoSlownikow,',',listtmp,Strin.class);
       for(Strin str:listtmp)
       {
           Slownik slownik= new Slownik();
           slownik.nazwaSlownika=str.string.toString();
            slownik.jezyk1=ObsługaNazwowa.getJezyk1(str.string);
            slownik.jezyk2=ObsługaNazwowa.getJezyk2(str.string);
            Pomocnik_plikowy.zczytywanie_z_pliku(slownik.nazwaSlownika+".txt",'-',slownik.slowka,Word.class);
                this.slowniki.add(slownik);
       }
    }
    public void addNewLanguage(String nazwaNowegoJezyka)
    {

            String sprawdzenie= nazwaNowegoJezyka;

            int flaga=1;
            for(Jezyk j: jezyki)
            {
                if(j.nazwaJezyka.equals(sprawdzenie)) flaga=0;
            }
            if(flaga==1)
            {

                Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();



                Jezyk j= new Jezyk();
                j.nazwaJezyka=nazwaNowegoJezyka;
                String tmp=j.nazwaJezyka;
                jezyki.add(j);

                pomocnik_plikowy.zapisywanie_do_pliku(ustawieniaSystemowe.plikZJezykami,',',jezyki,Jezyk.class);



                Vector<Integer> nowyNumer=z.getNumerNowegoJezyka();
                Pakiet nowyJezyk= new Pakiet(tmp,new LinkedList<Plik>(),(Vector)nowyNumer.clone());

                nowyNumer.add(1);
                Pakiet nauka= new Pakiet("Nauka",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
                StatyPlik statyPlik1= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
                nowyNumer.add(1);
                Plik plik1= new Plik("Pierwsza lekcja",(Vector)nowyNumer.clone(),statyPlik1);


                nauka.get_nazwy_plikow().add(plik1);
                nowyNumer.remove(2);
                nowyNumer.remove(1);

                nowyNumer.add(2);
                Pakiet piosenki= new Pakiet("Piosenki",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
                StatyPlik statyPlik2= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
                nowyNumer.add(1);
                Plik plik2= new Plik("Pierwsza piosenka",(Vector)nowyNumer.clone(),statyPlik2);
                //   pomocnik_plikowy.CreateFile(plik2.nazwa_systemowa);
                piosenki.get_nazwy_plikow().add(plik2);
                nowyNumer.remove(2);
                nowyNumer.remove(1);

                nowyNumer.add(3);
                Pakiet ksiazki= new Pakiet("Książki",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
                StatyPlik statyPlik3= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
                nowyNumer.add(1);
                Plik plik3= new Plik("Pierwsza ksiażka",(Vector)nowyNumer.clone(),statyPlik3);
                // pomocnik_plikowy.CreateFile(plik3.nazwa_systemowa);
                ksiazki.get_nazwy_plikow().add(plik3);
                nowyNumer.remove(2);
                nowyNumer.remove(1);

                nowyNumer.add(4);
                Pakiet seriale= new Pakiet("Seriale",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
                StatyPlik statyPlik4= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
                nowyNumer.add(1);
                Plik plik4= new Plik("Pierwszy serial",(Vector)nowyNumer.clone(),statyPlik4);
                //  pomocnik_plikowy.CreateFile(plik4.nazwa_systemowa);
                seriale.get_nazwy_plikow().add(plik4);
                nowyNumer.remove(2);
                nowyNumer.remove(1);

                nowyNumer.add(5);
                Pakiet filmy= new Pakiet("Filmy",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
                StatyPlik statyPlik5= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
                nowyNumer.add(1);
                Plik plik5= new Plik("Pierwszy film",(Vector)nowyNumer.clone(),statyPlik5);
                // pomocnik_plikowy.CreateFile(plik5.nazwa_systemowa);
                filmy.get_nazwy_plikow().add(plik5);
                nowyNumer.remove(2);
                nowyNumer.remove(1);

                nowyNumer.add(6);

                StatyPlik statyPlik6= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");

                Plik technikaDziecka= new Plik("Metoda ciekawskiego dziecka "+tmp,(Vector)nowyNumer.clone(),statyPlik6);
                //  pomocnik_plikowy.CreateFile(technikaDziecka.nazwa_systemowa);
                nowyNumer.remove(1);
                nowyJezyk.get_nazwy_plikow().add(technikaDziecka);

                nowyNumer.add(7);

                StatyPlik statyPlik7= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");

                Plik archiwum= new Plik("Archiwum",(Vector)nowyNumer.clone(),statyPlik7);
                //    pomocnik_plikowy.CreateFile(archiwum.nazwa_systemowa);
                nowyJezyk.get_nazwy_plikow().add(archiwum);

                z.zbiornik.add(nowyJezyk);
                z.zbiornik.add(nauka);
                z.zbiornik.add(piosenki);
                z.zbiornik.add(ksiazki);
                z.zbiornik.add(seriale);
                z.zbiornik.add(filmy);
                z.zaktualizujZmiany();

                DefaultMutableTreeNode aktualny=(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                DefaultMutableTreeNode nowyNode=new DefaultMutableTreeNode(nowyJezyk.nazwa_pakietu);
                aktualny.add(nowyNode);
                DefaultMutableTreeNode nodeNauka=new DefaultMutableTreeNode(nauka.nazwa_pakietu);
                nowyNode.add(nodeNauka);

                DefaultMutableTreeNode nodePierwszaLekcja=new DefaultMutableTreeNode(plik1.get_nazwa_pliku());
                nodeNauka.add(nodePierwszaLekcja);


                DefaultMutableTreeNode nodePiosenki=new DefaultMutableTreeNode(piosenki.nazwa_pakietu);
                nowyNode.add(nodePiosenki);
                DefaultMutableTreeNode nodePierwszaPiosenka=new DefaultMutableTreeNode(plik2.get_nazwa_pliku());
                nodePiosenki.add(nodePierwszaPiosenka);


                DefaultMutableTreeNode nodeKsiazki=new DefaultMutableTreeNode(ksiazki.nazwa_pakietu);
                nowyNode.add(nodeKsiazki);
                DefaultMutableTreeNode nodePierwszaKsiazka=new DefaultMutableTreeNode(plik3.get_nazwa_pliku());
                nodeKsiazki.add(nodePierwszaKsiazka);


                DefaultMutableTreeNode nodeSeriale=new DefaultMutableTreeNode(seriale.nazwa_pakietu);
                nowyNode.add(nodeSeriale);
                DefaultMutableTreeNode nodePierwszySerial=new DefaultMutableTreeNode(plik4.get_nazwa_pliku());
                nodeSeriale.add(nodePierwszySerial);


                DefaultMutableTreeNode nodeFilmy=new DefaultMutableTreeNode(filmy.nazwa_pakietu);
                nowyNode.add(nodeFilmy);
                DefaultMutableTreeNode nodePierwszyFilm=new DefaultMutableTreeNode(plik5.get_nazwa_pliku());
                nodeFilmy.add(nodePierwszyFilm);


                DefaultMutableTreeNode nodeTechnikaDziecka=new DefaultMutableTreeNode(technikaDziecka.get_nazwa_pliku());
                nowyNode.add(nodeTechnikaDziecka);

                DefaultMutableTreeNode nodeArchiwum=new DefaultMutableTreeNode(archiwum.get_nazwa_pliku());
                nowyNode.add(nodeArchiwum);

                ((DefaultTreeModel)tree.getModel()).reload();
                ListaSepcyficznychLiterek l= new ListaSepcyficznychLiterek();
                l.jezyk=nowyJezyk.nazwa_pakietu;
                try {

                    //File file= new File(j.nazwaJezyka+".txt");
                    FileInputStream test;
                    test=new FileInputStream(j.nazwaJezyka+".txt");
                    test.close();
                    pomocnik_plikowy.zczytywanie_z_pliku(j.nazwaJezyka+".txt",',',l.list,SpecyficznaLiterka.class);


                }
                catch(Exception ex)
                {
                    pomocnik_plikowy.CreateFile(j.nazwaJezyka+".txt");
                }
                zbiornikLiter.add(l);







            }
            else
            {
                JFrame jFrame= new JFrame();
                JLabel jLabel= new JLabel("Taki język już istanieje!!");
                jFrame.add(jLabel);
                jFrame.pack();
                jFrame.setVisible(true);
                jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            }
    }
    public void addDefaultEnglish()
    {
        String nazwaNowegoJezyka="angielski";
        String sprawdzenie= nazwaNowegoJezyka;


            Jezyk j= new Jezyk();
            j.nazwaJezyka=nazwaNowegoJezyka;
            String tmp=j.nazwaJezyka;
            jezyki.add(j);

            Pomocnik_plikowy.zapisywanie_do_pliku(ustawieniaSystemowe.plikZJezykami,',',jezyki,Jezyk.class);



            Vector<Integer> nowyNumer=z.getNumerNowegoJezyka();
            Pakiet nowyJezyk= new Pakiet(tmp,new LinkedList<Plik>(),(Vector)nowyNumer.clone());

            nowyNumer.add(1);
            Pakiet nauka= new Pakiet("Nauka",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
            StatyPlik statyPlik1= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
            nowyNumer.add(1);
            Plik plik1= new Plik("Pierwsza lekcja",(Vector)nowyNumer.clone(),statyPlik1);


            nauka.get_nazwy_plikow().add(plik1);
            nowyNumer.remove(2);
            nowyNumer.remove(1);

            nowyNumer.add(2);
            Pakiet piosenki= new Pakiet("Piosenki",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
            StatyPlik statyPlik2= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
            nowyNumer.add(1);
            Plik plik2= new Plik("Pierwsza piosenka",(Vector)nowyNumer.clone(),statyPlik2);
            //   pomocnik_plikowy.CreateFile(plik2.nazwa_systemowa);
            piosenki.get_nazwy_plikow().add(plik2);
            nowyNumer.remove(2);
            nowyNumer.remove(1);

            nowyNumer.add(3);
            Pakiet ksiazki= new Pakiet("Książki",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
            StatyPlik statyPlik3= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
            nowyNumer.add(1);
            Plik plik3= new Plik("Pierwsza ksiażka",(Vector)nowyNumer.clone(),statyPlik3);
            // pomocnik_plikowy.CreateFile(plik3.nazwa_systemowa);
            ksiazki.get_nazwy_plikow().add(plik3);
            nowyNumer.remove(2);
            nowyNumer.remove(1);

            nowyNumer.add(4);
            Pakiet seriale= new Pakiet("Seriale",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
            StatyPlik statyPlik4= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
            nowyNumer.add(1);
            Plik plik4= new Plik("Pierwszy serial",(Vector)nowyNumer.clone(),statyPlik4);
            //  pomocnik_plikowy.CreateFile(plik4.nazwa_systemowa);
            seriale.get_nazwy_plikow().add(plik4);
            nowyNumer.remove(2);
            nowyNumer.remove(1);

            nowyNumer.add(5);
            Pakiet filmy= new Pakiet("Filmy",new LinkedList<Plik>(),(Vector)nowyNumer.clone());
            StatyPlik statyPlik5= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");
            nowyNumer.add(1);
            Plik plik5= new Plik("Pierwszy film",(Vector)nowyNumer.clone(),statyPlik5);
            // pomocnik_plikowy.CreateFile(plik5.nazwa_systemowa);
            filmy.get_nazwy_plikow().add(plik5);
            nowyNumer.remove(2);
            nowyNumer.remove(1);

            nowyNumer.add(6);

            StatyPlik statyPlik6= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");

            Plik technikaDziecka= new Plik("Metoda ciekawskiego dziecka "+tmp,(Vector)nowyNumer.clone(),statyPlik6);
            //  pomocnik_plikowy.CreateFile(technikaDziecka.nazwa_systemowa);
            nowyNumer.remove(1);
            nowyJezyk.get_nazwy_plikow().add(technikaDziecka);

            nowyNumer.add(7);

            StatyPlik statyPlik7= new StatyPlik("pol",tmp,0,"",0,0,0,0,"");

            Plik archiwum= new Plik("Archiwum",(Vector)nowyNumer.clone(),statyPlik7);
            //    pomocnik_plikowy.CreateFile(archiwum.nazwa_systemowa);
            nowyJezyk.get_nazwy_plikow().add(archiwum);

            z.zbiornik.add(nowyJezyk);
            z.zbiornik.add(nauka);
            z.zbiornik.add(piosenki);
            z.zbiornik.add(ksiazki);
            z.zbiornik.add(seriale);
            z.zbiornik.add(filmy);
            z.zaktualizujZmiany();



            ListaSepcyficznychLiterek l= new ListaSepcyficznychLiterek();
            l.jezyk=nowyJezyk.nazwa_pakietu;
            try {

                //File file= new File(j.nazwaJezyka+".txt");
                FileInputStream test;
                test=new FileInputStream(j.nazwaJezyka+".txt");
                test.close();
                Pomocnik_plikowy.zczytywanie_z_pliku(j.nazwaJezyka+".txt",',',l.list,SpecyficznaLiterka.class);


            }
            catch(Exception ex)
            {

                    if(new File("Literki/"+j.nazwaJezyka.toLowerCase()+".txt").exists())
                    {
                       ListaSepcyficznychLiterek specyficznaLiterkaLinkedList= new ListaSepcyficznychLiterek();
                        //"src/Pictures/Eiffla.jpg"
                        Pomocnik_plikowy.zczytywanie_z_pliku("Literki/"+j.nazwaJezyka.toLowerCase()+".txt",',',specyficznaLiterkaLinkedList.list,SpecyficznaLiterka.class);
                        Pomocnik_plikowy.CreateFile(j.nazwaJezyka+".txt");
                        Pomocnik_plikowy.zapisywanie_do_pliku(j.nazwaJezyka+".txt",',',specyficznaLiterkaLinkedList.list,SpecyficznaLiterka.class);
                        if(zbiornikLiter==null) zbiornikLiter= new LinkedList<>();
                        zbiornikLiter.add(specyficznaLiterkaLinkedList);
                    }

                else  Pomocnik_plikowy.CreateFile(j.nazwaJezyka+".txt");



            }
        zbiornikLiter = new LinkedList<>();
            zbiornikLiter.add(l);

    }
}

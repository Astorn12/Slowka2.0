import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import static java.awt.Font.BOLD;

/**
 * Created by osiza on 01.10.2018.
 */
public class ZagadnienieGramatyczne extends Grama {

List<IssueTable> tabelki;
String opis;

//HeadlineGrama headlineGrama;

//można jeszcze dodać coś z tagami
ZagadnienieGramatyczne()
{
    tabelki= new LinkedList<>();
    opis="";
    this.statyplik=new HeadlineGrama("nic|nic|0");


}
ZagadnienieGramatyczne(Plik plik)
{
    this.tabelki= new LinkedList<>();
    this.opis="";
    this.juzZmienione=plik.juzZmienione;
    this.nazwa_pliku=plik.nazwa_pliku;
    this.nazwa_systemowa=plik.nazwa_systemowa;
}
ZagadnienieGramatyczne(String opis,List<IssueTable> tabelki)
    {
        this.tabelki=tabelki;
        this.opis=opis;

    }
    @Override
    public void createShowingJPanel(JPanel panelRodzajuPracy) {
        panelRodzajuPracy.removeAll();
        panelRodzajuPracy.setLayout(new MigLayout());
ObsługaNazwowa obsługaNazwowa= new ObsługaNazwowa();
        String  tmpp="";
        tmpp=obsługaNazwowa.oddzielNazeOdDaty(this.nazwa_pliku).nazwa;
        tmpp=(tmpp.substring(0,1).equals("!")) ?tmpp.substring(1,tmpp.length()-1):tmpp;
        JLabel jLabel= new JLabel(tmpp);
        panelRodzajuPracy.add(jLabel,"wrap");
        JTextArea jTextArea= new JTextArea();
        jTextArea.setText(this.opis);
        panelRodzajuPracy.add("wrap,align 50% 50%,w 50%,h 30%",jTextArea);
       // LinkedList<Strin> firstRow=tabelka.getFirst();
      /*  LinkedList<LinkedList<Strin>>  tmp= new LinkedList<>();
        for(LinkedList<Strin> list:tabelka) {
            if (list.equals(tabelka.getFirst())) {
            } else {
            tmp.add(list);
            }
        }*/
      for(IssueTable tabelka:tabelki) {
          JLabel jLabel1= new JLabel(tabelka.name);
          panelRodzajuPracy.add(jLabel1,"wrap,align 50% 50%");
          Vector<String> firstRow = new Vector<>();
          if (tabelka.tabelka.size() > 0) {
              for (Strin strin : tabelka.tabelka.get(0)) {
                  firstRow.add(strin.string);
              }
              Vector<Vector<String>> tmp = new Vector<>();
              for (int i = 0; i < tabelka.tabelka.size(); i++) {
                  tmp.add(new Vector<String>());
                  for (int j = 0; j < tabelka.tabelka.get(i).size(); j++) {
                      tmp.get(i).add(tabelka.tabelka.get(i).get(j).string);
                  }
              }
              JTable jTable = new JTable(tmp, firstRow);
              panelRodzajuPracy.add(jTable, "wrap");
          }
      }




    }

        @Override
    public void zczytywanie() {
        LinkedList<Strin> strins= new LinkedList<>();
        Pomocnik_plikowy.zczytywanie_z_pliku(this.nazwa_systemowa,',',strins,Strin.class);
        kodowanieDoTabeli(strins);

    }

    @Override
    public void zapis_zmian() {
        LinkedList<Strin> strins=rozkodowywanieZTabeli();
        strins.add(0,new Strin(((HeadlineGrama)this.statyplik).generateLine()));
        Pomocnik_plikowy.zapisywanie_do_pliku(this.nazwa_systemowa,',',strins,Strin.class);
    }

    @Override
    public void wypisanie_listy() {

    }

    @Override
    public List get_lista() {
        return null;
    }

    @Override
    public Grama clone() {
        return null;
    }

    private void kodowanieDoTabeli(LinkedList<Strin> strins)
    {
            //Wybieranie Headline
        this.statyplik= new HeadlineGrama(strins.get(0).string);
        strins.remove(0);
        int i=0;



        for(Strin strin:strins)
        {
            if(strin.string.contains("#"))
            {
                IssueTable issueTable= new IssueTable(strin.string);
                this.tabelki.add(issueTable);
                i=0;
            }
            else if(strin.string.contains("|"))
            {
               getLatestIssueTable().tabelka.add(new LinkedList<Strin>());
                char[] ctab=strin.string.toCharArray();
                String tmp="";
                for(char c:ctab)
                {
                    if(c=='|'&&tmp!=null)
                    {
                        getLatestIssueTable().tabelka.get(i).add(new Strin(tmp));
                       tmp="";
                    }
                    else if(c=='|'&&tmp==null)
                    {
                        getLatestIssueTable().tabelka.get(i).add(new Strin(tmp));
                        tmp="";
                    }
                    /*else if(strins.indexOf(c)==strins.size()-1)
                    {
                        tmp+=c;
                        tabelka.get(i).add(new Stirn(tmp);
                        tmp="";
                    }*/
                    else
                    {
                        tmp+=c;
                    }

                }
                i++;
            }
            else
            {
                this.opis+=strin.string+"\n";
                i=0;
            }
        }


    }
    private LinkedList rozkodowywanieZTabeli()
    {
        LinkedList<Strin> strins=new LinkedList<>();
        for(IssueTable issueTable:tabelki) {
            strins.add(new Strin(issueTable.getLineToSave()));

            for (LinkedList<Strin> l : issueTable.tabelka) {
                String string = "";
                for (Strin strin : l) {
                    string += strin.string + "|";
                }
                strins.add(new Strin(string));

            }
        }
        return strins;
    }

    public void createCreatingJPanel(JPanel panelRodzajuPracy)
    {
       // if((this.tabelka==null&&this.opis==""&&((HeadlineGrama)this.statyplik)==null)) {

            panelRodzajuPracy.removeAll();
            panelRodzajuPracy.setLayout(new MigLayout());
            JScrollPane js= new JScrollPane();
            panelRodzajuPracy.add(js);
            js.setLayout(new MigLayout());
            JLabel title= new JLabel(this.nazwa_pliku);
            js.add(title,"wrap,align 50% 50%");
            JTextArea jTextArea = new JTextArea();
            panelRodzajuPracy.add(jTextArea, "w 50%,h 30%,align 50% 50%,wrap");
            JLabel kolumny = new JLabel("kolumny");
            JButton dodajKolumne = new JButton("+");
            JButton usunKolumne = new JButton("-");
            JLabel wiersze = new JLabel("wiersze");
            JButton dodajWiersz = new JButton("+");
            JButton usunWiersz = new JButton("-");
            js.add(kolumny, "split 3");
            js.add(usunKolumne);
           js.add(dodajKolumne, "wrap");
            js.add(wiersze, "split 3");
            js.add(usunWiersz);
            js.add(dodajWiersz, "wrap");
        if(tabelki.size()==0)
        {   IssueTable tmp= new IssueTable(2,2);
            tmp.name="#nowa";
            tmp.kind="non";
            tabelki.add(tmp);

        }




        for(IssueTable issueTable:tabelki) {
            JLabel jLabel= new JLabel(issueTable.name);
            jLabel.setFont(new Font("Dialog",BOLD,20));
            panelRodzajuPracy.add(jLabel,"align 50% 50%,wrap");
        final JTable jTable = new JTable(2, 2);
        panelRodzajuPracy.add(jTable, "wrap,align 50% 50%,w 40%:70%:70%,h 30%:40%");


        addKindCheckbox(panelRodzajuPracy,"cell 1 2",jTable,issueTable);

            panelRodzajuPracy.revalidate();
            panelRodzajuPracy.repaint();

            dodajKolumne.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DefaultTableModel dtm= (DefaultTableModel)jTable.getModel();
                    dtm.setColumnCount(jTable.getColumnCount()+1);
                }
            });
            usunKolumne.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    DefaultTableModel dtm= (DefaultTableModel)jTable.getModel();
                    dtm.setColumnCount(jTable.getColumnCount()-1);


                }
            });

        dodajWiersz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dtm= (DefaultTableModel)jTable.getModel();
                dtm.setRowCount(jTable.getRowCount()+1);
            }
        });
        usunWiersz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dtm= (DefaultTableModel)jTable.getModel();
                dtm.setRowCount(jTable.getRowCount()-1);


            }
        });}

    }

    private void addKindCheckbox(JPanel panelRodzajuPracy, String komórka, JTable jTable, final IssueTable tabelka) {
        final JCheckBox zagadnienie= new JCheckBox("Zagadienie");
        final JCheckBox slowaNieregularne= new JCheckBox("Słówka Nieregularne");
        final JCheckBox wypelnianki= new JCheckBox("Wypełnianaki");
        if(tabelka.kind.equals("Zagadnienie"))
        {
            zagadnienie.setSelected(true);
        }
        if(tabelka.kind.equals("SlowaNieregularne"))
        {
            slowaNieregularne.setSelected(true);
        }
        if(tabelka.kind.equals("Wypelnianki"))
        {
            wypelnianki.setSelected(true);
        }
        panelRodzajuPracy.add(zagadnienie,"split 3,flowy");
        panelRodzajuPracy.add(slowaNieregularne,"flowy");
        panelRodzajuPracy.add(wypelnianki,"wrap");
        zagadnienie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(zagadnienie.isSelected())
                {
                    tabelka.kind="Zagadnienie";
                    zapis_zmian();
                }
                else
                {
                    tabelka.kind="zaden";
                    zapis_zmian();
                }
            }
        });
        slowaNieregularne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(slowaNieregularne.isSelected())
                {
                    tabelka.kind="Zagadnienie";
                    zapis_zmian();
                }
                else
                {
                    tabelka.kind="zaden";
                    zapis_zmian();
                }
            }
        });
        wypelnianki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(wypelnianki.isSelected())
                {
                    tabelka.kind="Zagadnienie";
                    zapis_zmian();
                }
                else
                {
                    tabelka.kind="zaden";
                    zapis_zmian();
                }
            }
        });


    }
    private IssueTable getLatestIssueTable()
    {
        if(tabelki.size()>0){
        return this.tabelki.get(tabelki.size()-1);}
        else return null;
    }

}

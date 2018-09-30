import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.nio.file.Path;
import java.util.List;
import java.util.Vector;

/**
 * Created by osiza on 21.02.2018.
 */
public class PomocnikDrzewowy {
    public Vector<Integer> numer_po_sciezce(Path path, List<Pakiet> pakiety)
    {
        TreePath p= (TreePath) path;


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
                    if(pakiet.get_nazwy_plikow().get(a).get_nazwa_pliku().equals(p.getPathComponent(i).toString())&&pakiet.czy_moje_Dziecko(tmp))
                    {
                        pakiet=tmp;
                    }
                }
            }
            if(flaga==0){
                for(int j=0;j< pakiety.size();j++)
                {

                    if(pakiety.get(j).get_nazwa_pakietu().equals(p.getPathComponent(i).toString())&&pakiet.czy_moje_Dziecko(pakiety.get(j)))
                    {

                        pakiet=pakiety.get(j);

                        continue;
                    }

                }}
        }




      //  System.out.println("Wyjście: "+pakiet.numer);

        return pakiet.numer;
    }


    public Vector<Integer> numer_po_sciezce(TreeNode[] p, Vector<Pakiet> pakiety)
    {
       // TreePath p= (TreePath) path;


        Pakiet pakiet= new Pakiet();
        pakiet.numer= new Vector<>();
        int flaga=0;
        for(int i=0;i<p.length;i++)
        {

            if(!pakiet.get_nazwy_plikow().isEmpty())
            {
                for(int a=0;a<pakiet.get_nazwy_plikow().size();a++)
                {
                    Pakiet tmp= new Pakiet();
                    tmp.numer=pakiet.get_nazwy_plikow().get(a).numer;

                    if(pakiet.get_nazwy_plikow().get(a).get_nazwa_pliku().equals(p[i].toString())&&pakiet.czy_moje_Dziecko(tmp))
                    {
                        pakiet=tmp;
                    }
                }
            }
            if(flaga==0){
                for(int j=0;j< pakiety.size();j++)
                {

                    if(pakiety.get(j).get_nazwa_pakietu().equals(p[i].toString())&&pakiet.czy_moje_Dziecko(pakiety.get(j)))
                    {

                        pakiet=pakiety.get(j);

                        continue;
                    }

                }}
        }




       // System.out.println("Wyjście: "+pakiet.numer);

        return pakiet.numer;
    }

    public Plik zwrocPlikPoSciezce(Path path,Vector<Pakiet> pakiety)
    {
        Plik plik= new Plik();
        Pakiet wsk=new Pakiet();
        int glebokosc;
        Vector<Integer> szukany=numer_po_sciezce(path,pakiety);
        Vector<Integer> pakietprzechowujacy= new Vector<>();
        for(int i=0;i<szukany.size()-1;i++)
        {
          pakietprzechowujacy.add(szukany.get(i));
        }


        Pakiet dziecko;
        for(int i=0;i<pakiety.size();i++)
        {
           if(pakiety.get(i).numer.equals(pakietprzechowujacy))
           {
            wsk=pakiety.get(i);

           }
        }

        for(int i=0;i<wsk.get_nazwy_plikow().size();i++)
        {
            if(wsk.get_nazwy_plikow().get(i).numer.equals(szukany))
            {
                plik=wsk.get_nazwy_plikow().get(i);
            }
        }


        return plik;
    }

    public Plik zwrocPlikPoSciezce(TreeNode[] path, Vector<Pakiet> pakiety)
    {
        Plik plik= new Plik();
        Pakiet wsk=new Pakiet();
        int glebokosc;
        Vector<Integer> szukany=numer_po_sciezce(path,pakiety);
        Vector<Integer> pakietprzechowujacy= new Vector<>();
        for(int i=0;i<szukany.size()-1;i++)
        {
            pakietprzechowujacy.add(szukany.get(i));
        }


        Pakiet dziecko;
        for(int i=0;i<pakiety.size();i++)
        {
            if(pakiety.get(i).numer.equals(pakietprzechowujacy))
            {
                wsk=pakiety.get(i);

            }
        }

        for(int i=0;i<wsk.get_nazwy_plikow().size();i++)
        {
            if(wsk.get_nazwy_plikow().get(i).numer.equals(szukany))
            {
                plik=wsk.get_nazwy_plikow().get(i);
            }
        }


        return plik;
    }
    public Pakiet zwrocPakietPoSciezce(TreeNode[] path, Vector<Pakiet> pakiety)
    {


        Pakiet wsk=new Pakiet();

        Vector<Integer> szukany=numer_po_sciezce(path,pakiety);

        for(Pakiet p:pakiety)
        {
            if(p.numer.equals(szukany))
            {
                wsk=p;
                break;
            }
        }





        return wsk;
    }

    public boolean czy_moje_Dziecko(Pakiet rodzic,Pakiet dziecko)
    {
        int flaga=0;
        //System.out.println(this.get_nazwa_pakietu());
        //System.out.println();
        if(dziecko.numer.size()!=rodzic.numer.size()+1)
        {
            flaga=1;
        }
        else
        {

            // System.out.println("Ojciec "+this.numer+"; "+"Dziecko"+dziecko.numer);
            for(int i=0;i<rodzic.numer.size();i++)
            {
                // System.out.println(this.numer.get(i)+"     "+dziecko.numer.get(i));
                if((int)rodzic.numer.get(i)!=(int)(dziecko.numer.get(i))) {flaga=1;
                    // System.out.println("to nie jest dzieko");
                }

            }
        }
        if(flaga==1) return false;
        else return true;
    }

   public Vector<Integer> numerPoNazwie(String nazwa)
    {
        int i=0;
        Vector<Integer> numer= new Vector<>();
        while(i<nazwa.length()&&nazwa.toCharArray()[i]!='$')
        {
            i++;
        }
        i++;
        for(int j=i;j<nazwa.length();j++)
        {
            numer.add(Integer.parseInt(String.valueOf(nazwa.toCharArray()[j])));
        }
        return numer;
    }

    public String nazwaPoNumerze(Vector<Integer> numer,Zbiornik z)
    {
        return z.poszukiwanie_pakietu(numer).get_nazwa_pakietu();
    }









}

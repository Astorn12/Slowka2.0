import javax.swing.event.TreeSelectionEvent;
import java.util.*;

/**
 * Created by osiza on 24.09.2017.
 */
public class Pakiet {
    String nazwa_pakietu;
    //private List <Pakiet> pakiet;
    private List <Plik> pliki;
    Vector<Integer> numer;
    public boolean juzZmienione;

    Pakiet()
    {
        this.nazwa_pakietu="";
        this.pliki=new LinkedList<>();
        this.numer= new Vector<>();
        //this.pakiet= new LinkedList<>();
        juzZmienione=false;
    }
    Pakiet(String nazwa_pakietu,List <Plik> pliki,Vector<Integer> numer)
    {
    this.nazwa_pakietu=nazwa_pakietu;
    this.pliki=pliki;
    this.numer=numer;
    juzZmienione=false;
    }



    /*class Strin{
            String string;
    }
        public void zczytywanie_pakietu()
        {
            Pomocnik_plikowy p= new Pomocnik_plikowy();
            p.zczytywanie_z_pliku(nazwa_pakietu,',',this.nazwy_plikow,Strin.class);
        }*/
    public void set_nazwa_pakietu(String nazwa_pakietu)
    {
        this.nazwa_pakietu=nazwa_pakietu;
    }
    public String get_nazwa_pakietu()
    {
        return this.nazwa_pakietu;
    }

    public List<Plik> get_nazwy_plikow()
    {
        return this.pliki;
    }

    public void laduj_pliki()
    {
        for(int i=0;i<this.pliki.size();i++)
        {
            this.pliki.get(i).zczytywanie();
        }
    }

    public Pakiet clone()
    {
        Pakiet pakiet= new Pakiet();
        pakiet.nazwa_pakietu=this.nazwa_pakietu;
        for(int i=0;i<this.pliki.size();i++)
        {

        }
        return pakiet;
    }

    public boolean czy_moje_Dziecko(Pakiet dziecko)
    {
        int flaga=0;

            if(dziecko.numer.size()!=this.numer.size()+1)
            {
                flaga=1;
            }
            else
            {


                for(int i=0;i<this.numer.size();i++)
                {

                    if((int)this.numer.get(i)!=(int)(dziecko.numer.get(i))) {flaga=1;

                        }

                }
            }
        if(flaga==1) return false;
            else return true;
    }
    public Plik zwrocPakietPoNumerze(Vector<Integer> vector)
    {
        Plik plik= new Plik();
        Vector <Integer> nowy= new Vector<>();

        for(int i=0;i<this.pliki.size();i++)
        {
            nowy=this.pliki.get(i).rozkodowywanie_numeru(this.pliki.get(i).nazwa_systemowa);

            if(nowy.size()==vector.size())
            {
                for(int j=0;j<nowy.size();j++)
                {
                    //if()
                }
            }

        }
        return plik;
    }

    public void pliksSort()
    {
        for(int i=0;i<this.pliki.size();i++)
        {  int staryNumer= this.pliki.get(i).numer.get(numer.size()-1);

            String staraNazwa="";
            String nowaNazwa="";
            staraNazwa+=this.pliki.get(i).get_nazwa_pliku()+"$";
            nowaNazwa+=this.pliki.get(i).get_nazwa_pliku()+"$";
            for(int j=0;j<this.pliki.get(i).numer.size()-1;j++)
            {
                staraNazwa+=this.pliki.get(i).numer.get(j)+",";
                nowaNazwa+=this.pliki.get(i).numer.get(j)+",";
            }

            staraNazwa+=this.pliki.get(i).numer.get(this.pliki.get(i).numer.size()-1)+",";
            int tmp=i+1;
            nowaNazwa+=tmp+",";
            staraNazwa+=".txt";
            nowaNazwa+=".txt";
            List<Slowo> listaDoPrzepisywania=new LinkedList<>();
            Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
            pomocnik_plikowy.zczytywanie_z_pliku(staraNazwa,',',listaDoPrzepisywania,Slowo.class);

            pomocnik_plikowy.DeleteFile(staraNazwa);
            pomocnik_plikowy.CreateFile(nowaNazwa);
            pomocnik_plikowy.zapisywanie_do_pliku(nowaNazwa,',',listaDoPrzepisywania,Slowo.class);

            this.pliki.get(i).nazwa_systemowa=nowaNazwa;

            this.pliki.get(i).numer.remove(this.pliki.get(i).numer.size()-1);
            this.pliki.get(i).numer.add(i+1);

        }
    }

    public void zapisPlikow()
    {
        for(int i=0;i<this.get_nazwy_plikow().size();i++)
        {

            this.get_nazwy_plikow().get(i).zapis_zmian();
        }
    }

    public Plik getPlik(Vector<Integer> vector)
    {
        Plik plik= null;
        for(Plik p: this.get_nazwy_plikow())
        {
            if(p.numer.equals(vector))
            {
                plik=p;
                break;
            }

        }
        return plik;
    }
    public void wGore(int poziom)
    {
    if(this.numer.get(poziom)>1)
    {
        this.numer.set(poziom,this.numer.get(poziom)-1);
    }
    juzZmienione=true;
    }
    public void wDol(int poziom)
    {

            this.numer.set(poziom,this.numer.get(poziom)+1);
            juzZmienione=true;

    }
    public void poZmianie()
    {
        this.juzZmienione=false;
        for(Plik p:this.get_nazwy_plikow())
        {
            p.juzZmienione=false;
        }
    }



























    public void  filesBubbleSort()
    {



        Collections.sort(this.get_nazwy_plikow(),new Comparator<Plik>()
        {
            @Override
            public int compare(Plik p1,Plik p2)
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
    }


}

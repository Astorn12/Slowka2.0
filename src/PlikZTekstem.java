import javax.management.Attribute;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * Created by osiza on 09.09.2018.
 */
public class PlikZTekstem extends Plik {
    //private String nazwa_pliku;

    //public  List<WordFromBook> lista;
public PlikZTekstem()
{
    this.nazwa_systemowa = null;
    this.nazwa_pliku = null;
    this.lista = new LinkedList<>();
    this.iloscUdzielonychOdpowiedzi = 0;
    this.date = null;
    polang = true;
    juzZmienione = false;
}
    public PlikZTekstem(String nazwaPliku)
    {
        this.nazwa_pliku=nazwaPliku;
        Pomocnik_plikowy.CreateFile("Tekst()@"+this.nazwa_pliku+".txt");
    }
    PlikZTekstem(String nazwa_pliku, Vector<Integer> numer, StatyPlik statyPlik)
    {
        super(nazwa_pliku,numer,statyPlik);

    }
    PlikZTekstem(Plik plik)
    {
        super(plik.nazwa_pliku,plik.numer,plik.statyplik);

    }
    private String tekst;

    public String getNazwaPlikuZTekstem()
    {
        ObsługaNazwowa obsługaNazwowa= new ObsługaNazwowa();
        return"Tekst()"+obsługaNazwowa.oddzielNazeOdDaty(this.get_nazwa_pliku()).nazwa+".txt";
    }
    public void setTekst(String tekst)
    {
        Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
        LinkedList<Strin> l= new LinkedList<>();
        Strin strin= new Strin(tekst);
        l.add(strin);
        pomocnik_plikowy.zapisywanie_do_pliku(getNazwaPlikuZTekstem(),'&',l,Strin.class);
    }
    public String getTekstFromFile()
    {
        Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
        LinkedList<Strin> l= new LinkedList<>();
        pomocnik_plikowy.zczytywanie_z_pliku(getNazwaPlikuZTekstem(),'&',l,Strin.class);
        String piosenka="";

        for (int i = 0; i < l.size(); i++) {
            if (i != 0) {
               // piosenka.append("\n");
                piosenka+="\n";
            }
          //  piosenka.append(list.get(i).string);
            piosenka+=l.get(i).string;
        }


        return piosenka;


    }
    @Override
    public void set_nazwa_pliku(String nazwa_pliku)
    {
        if (this.nazwa_pliku == null) {
            this.nazwa_pliku = nazwa_pliku;




            // setTekst(getTekstFromFile());
            Pomocnik_plikowy.CreateFile("Tekst()" + this.nazwa_pliku + ".txt");
        }

    }
    public String getTekst()
    {
        return this.tekst;
    }
    @Override
    public void zapis_zmian() {

        Pomocnik_plikowy p = new Pomocnik_plikowy();


        WordFromBook slowo = new WordFromBook(this.statyplik);


        this.wypisanie_listy();
        lista.add(0, slowo);
        List<WordFromBook> wl= new LinkedList<>();
        for(int i=0;i<this.lista.size();i++)
        {
            wl.add((WordFromBook)lista.get(i));
        }
        /*Slowo slowo= new Slowo();
        slowo.pol="pol";
        slowo.fore="ang";
        slowo.priority=0;
        slowo.enumeracja=0;
        slowo.repriority=0;
        slowo.language="";
        this.lista.add(0,slowo);*/

       /* for (Slowo s: lista
             ) {


        }*/

        /*for(Slowo s:lista)
        {
           s.powrotArchiwum="n,";
        }*/
        p.zapisywanie_do_pliku(this.nazwa_systemowa, ',', wl, WordFromBook.class);
        //  p.zapisywanie_do_pliku("^Metoda ciekawiaka%25018$2,5,1,.txt",',',this.lista,Slowo.class);
        lista.remove(0);
        System.out.println("Zapis zmian");
    }

    @Override
    public void zczytywanie() {
        Pomocnik_plikowy p = new Pomocnik_plikowy();
       /* Slowo slowo= new Slowo();
        slowo.pol="pol";
        slowo.fore="ang";
        slowo.priority=0;
        slowo.enumeracja=0;
        slowo.repriority=0;
        slowo.language="";
        this.lista.add(slowo);*/
        lista.clear();
        p.zczytywanie_z_pliku(this.nazwa_systemowa, ',', this.lista, WordFromBook.class);

        this.statyplik = new StatyPlik(lista.get(0));
        lista.remove(0);

        //if(lista.size()>0)


    }
    @Override
    public List get_lista()
    {
        return this.lista;
    }



}

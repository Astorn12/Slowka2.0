import javax.swing.*;
import java.util.Scanner;

/**
 * Created by osiza on 24.09.2017.
 */
public class Slowo  {
    public String pol;
    public String fore;
    public int priority;
    public String language;//służy do określenia koloru słówka w liście do uczenia, skrót słówka nie może zawierać literk i chyba
    public int archiwumLicznik;
    public int repriority;
    public int enumeracja;
    public int reArchiwum;
    public String jezyk;
    public String powrotArchiwum;
    Slowo()
    {
        this.pol=null;
        this.fore=null;
        this.priority= 1;
        this.language=null;
        this.enumeracja=0;
        this.reArchiwum=0;
        this.jezyk=null;
        this.powrotArchiwum="n";
    }//

    Slowo(String pol,String fore,String language,int priority)
    {
     this.pol=pol;
     this.fore=fore;
     this.language=language;
     this.priority=priority;

     this.jezyk="angielski";
     this.powrotArchiwum="n";
    }
    Slowo(StatyPlik statyPlik)
    {
        /*this.nasz=slowo.pol;
        this.obcy=slowo.fore;
        this.iloscOdpowiedzi=slowo.priority;
        this.nic=slowo.language;
        this.iloscZlychOdpowiedzi=slowo.archiwumLicznik;
        this.iloscDobrychOdpowiedzi=slowo.repriority;
        this.enumeracja=slowo.enumeracja;*/

        this.pol=statyPlik.nasz;
        this.fore=statyPlik.obcy;
        this.priority=statyPlik.iloscOdpowiedzi;
        this.language=statyPlik.nic;
        this.archiwumLicznik=statyPlik.iloscZlychOdpowiedzi;
        this.repriority=statyPlik.iloscDobrychOdpowiedzi;
        this.enumeracja=statyPlik.enumeracja;
        this.reArchiwum=statyPlik.dodatkowy;
        this.jezyk=statyPlik.extranull;
        this.powrotArchiwum="n";
    }
    Slowo(Word wd)
    {
        this.pol=wd.slowo1;
        this.fore=wd.slowo2;
        this.priority= 1;
        this.language="";
        this.enumeracja=0;
        this.reArchiwum=0;
        this.jezyk="";
        this.powrotArchiwum="n";
    }

    public void set_pol(String pol)
    {
        if(this.pol.equals(null)) this.pol=pol;
    }
    public void set_fore(String fore)
    {
        if(this.fore.equals(null)) this.fore=fore;
    }
    public void set_priority(int priority)
    {
        this.priority = priority;
    }
    public void set_language(String language)
    {
        if(this.language.equals(null)) this.language=language;
    }

    public void change_pol(String pol)
    {
        if(!(this.pol.equals(null))) this.pol=pol;
    }
    public void change_fore(String fore)
    {
        if(!(this.fore.equals(null))) this.fore=fore;
    }
    public void change_priority(int priority)
    {
if(!(this.priority==Integer.parseInt(null))) this.priority=priority;
    }
    public void change_language(String language)
    {
        if(!(this.language.equals(null))) this.language=language;
    }

    public String get_pol()
    {
        return this.pol;
    }
    public String get_fore()
    {
        return this.fore;
    }
    public int get_priority()
    {
        return this.priority;
    }
    public String get_language()
    {
        return this.language;
    }
    public void zapytanie() throws EndException
    {
        System.out.println(this.pol);
        System.out.println("");
        Scanner scanner= new Scanner(System.in);
        String odpowiedz=scanner.nextLine().toString();
        if (odpowiedz.equals("123")) throw  new EndException();
        if(odpowiedz.equals(this.fore)) {
            this.set_priority(this.get_priority() - 1);
            System.out.println("DOBRA ODPOWIEDŹ");
        }
        else

        {
            this.set_priority(this.get_priority() + 1);
            System.out.println("ZŁA ODPOWIEDŹ");
            System.out.println("Poprawna odpowiedź: "+this.fore);
        }

    }
public String toString()
{
    return this.get_pol();
}

public Slowo clone()
{
    Slowo slowo= new Slowo();
    slowo.fore = this.fore;
    slowo.pol=this.pol;
    slowo.priority=this.priority;
    slowo.language= this.language;
    return slowo;

}
public static Slowo toSlowo(Object o)
{
    return (Slowo) o;
}



}


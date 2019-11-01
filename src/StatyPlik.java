import java.util.Vector;

/**
 * Created by osiza on 07.05.2018.
 */
public class StatyPlik {
    public String nasz;
    public String obcy;
    public int iloscOdpowiedzi;
    public String nic;
    public int iloscZlychOdpowiedzi;
    public int iloscDobrychOdpowiedzi;
    public int enumeracja;
    public int dodatkowy;
    public String extranull;
    public String powrotArchiwum;
    public String extraPronunciation;

    StatyPlik(Slowo slowo) {
        if (slowo.pol.equals("pol")) {
            this.nasz = "polski";
        } else this.nasz = slowo.pol;
        this.obcy = slowo.fore;
        this.iloscOdpowiedzi = slowo.priority;
        this.nic = slowo.language;
        this.iloscZlychOdpowiedzi = slowo.archiwumLicznik;
        this.iloscDobrychOdpowiedzi = slowo.repriority;
        this.enumeracja = slowo.enumeracja;
        this.dodatkowy = slowo.reArchiwum;
        this.extranull = slowo.jezyk;
        this.powrotArchiwum = slowo.powrotArchiwum;


    }

    StatyPlik(String nasz, String obcy, int iloscOdpowiedzi, String nic, int iloscZlychOdpowiedzi, int iloscDobrychOdpowiedzi, int enumeracja, int dodatkowy, String extranull) {
        this.nasz = nasz;
        this.obcy = obcy;
        this.iloscOdpowiedzi = iloscOdpowiedzi;
        this.nic = nic;
        this.iloscZlychOdpowiedzi = iloscZlychOdpowiedzi;
        this.iloscDobrychOdpowiedzi = iloscDobrychOdpowiedzi;
        this.enumeracja = enumeracja;
        this.dodatkowy = dodatkowy;
        this.extranull = extranull;
        this.powrotArchiwum = " ";
        this.extraPronunciation="";
    }

    StatyPlik(String linia) {

    }

    StatyPlik() {

    }
}


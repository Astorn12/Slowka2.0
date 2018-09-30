import java.util.LinkedList;

/**
 * Created by osiza on 17.02.2018.
 */
public class InterfacePodsumowania {
    String sciezkaDoPliku;

    LinkedList<Podsumowanie> podsumowanie;
    InterfacePodsumowania(String sciezkaDoPliku)
    {
        this.sciezkaDoPliku=sciezkaDoPliku;
        podsumowanie= new LinkedList<>();
        Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
        pomocnik_plikowy.zczytywanie_z_pliku(sciezkaDoPliku,',',podsumowanie,Podsumowanie.class);
    }
    public void zapis()
    {
        Pomocnik_plikowy pomocnik_plikowy= new Pomocnik_plikowy();
        pomocnik_plikowy.zapisywanie_do_pliku(sciezkaDoPliku,',',podsumowanie,Podsumowanie.class);
    }
    public void odczyt()
    {

        podsumowanie= new LinkedList<>();
        Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
        pomocnik_plikowy.zczytywanie_z_pliku(sciezkaDoPliku,',',podsumowanie,Podsumowanie.class);
    }
}

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.miginfocom.swing.MigLayout;
import org.omg.CORBA.ARG_OUT;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Created by osiza on 24.09.2017.
 */
public class Plik {
    public String nazwa_systemowa;//
    protected String nazwa_pliku;//
    public List<Slowo> lista;
    public Vector<Integer> numer;//
    public int aktualnePytanie;
    protected int iloscUdzielonychOdpowiedzi;
    public boolean polang;

    public String date;
    public StatyPlik statyplik;
    public boolean juzZmienione;//
    // public enum Rodzaj{piosenka,tekst, archiwum,technikaDziecka;}

    Plik() {
        this.nazwa_systemowa = null;
        this.nazwa_pliku = null;
        this.lista = new LinkedList<>();
        this.iloscUdzielonychOdpowiedzi = 0;
        this.date = null;
        polang = true;
        juzZmienione = false;
    }

    Plik(Vector<Integer> numer, String nazwa_pliku, String date) {
        this.numer = numer;
        this.nazwa_pliku = nazwa_pliku;
        this.lista = new LinkedList<>();
        String systemowa;
        systemowa = nazwa_pliku;
        systemowa += date;
        systemowa += "$";
        for (int i = 0; i < this.numer.size(); i++) {
            systemowa += numer.get(i) + ",";
        }
        polang = true;
        juzZmienione = false;
    }

    Plik(String nazwa_pliku, Vector<Integer> numer, StatyPlik statyPlik) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DDDyy");
        this.date = simpleDateFormat.format(calendar.getTime());
        this.numer = numer;
        this.nazwa_pliku = nazwa_pliku + "%" + date;
        this.lista = new LinkedList<>();
        String systemowa;
        systemowa = this.nazwa_pliku;

        //systemowa+=date;
        systemowa += "$";
        for (int i = 0; i < this.numer.size(); i++) {
            systemowa += numer.get(i) + ",";
        }
        systemowa += ".txt";
        this.nazwa_systemowa = systemowa;
        polang = true;
        this.statyplik = statyPlik;
        Slowo slowo = new Slowo(statyPlik);
        LinkedList<Slowo> slowa = new LinkedList<>();
        slowa.add(slowo);
        Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
        pomocnik_plikowy.CreateFile(this.nazwa_systemowa);
        pomocnik_plikowy.zapisywanie_do_pliku(this.nazwa_systemowa, ',', slowa, Slowo.class);
        juzZmienione = false;
    }

    public void zczytywanie() {
        Pomocnik_plikowy p = new Pomocnik_plikowy();
        lista.clear();
        p.zczytywanie_z_pliku(this.nazwa_systemowa, ',', this.lista, Slowo.class);

        this.statyplik = new StatyPlik(lista.get(0));
        lista.remove(0);
    }

    public void zapis_zmian() {
        Pomocnik_plikowy p = new Pomocnik_plikowy();


        Slowo slowo = new Slowo(this.statyplik);


        this.wypisanie_listy();
        lista.add(0, slowo);
        if(this.nazwa_systemowa.equals("Lekcja16%04219$2,1,2,14,.txt")){
                int x=2;
        }

        p.zapisywanie_do_pliku(this.nazwa_systemowa, ',', this.lista, Slowo.class);

        lista.remove(0);
        System.out.println("Zapis zmian");
    }

    public void wypisanie_listy() {
        for (int i = 0; i < this.lista.size(); i++) {
        }
    }

    public void set_nazwa_pliku(String nazwa_pliku) {

        if (this.nazwa_pliku == null) {
            this.nazwa_pliku = nazwa_pliku;


        }
    }

    public void change_nazwa_pliku(String nazwa_pliku) {
        if (!(this.nazwa_pliku.equals(null))) this.nazwa_pliku = nazwa_pliku;

        String systemowa = nazwa_pliku + "$";
        for (int i = 0; i < this.numer.size(); i++) {
            systemowa += this.numer.get(i) + ",";

        }
        systemowa += ".txt";
        this.nazwa_systemowa = systemowa;
    }

    public String get_nazwa_pliku() {
        return this.nazwa_pliku;
    }

    public List get_lista() {
        return this.lista;
    }



    public Plik clone() {
        Plik plik = new Plik();
        plik.nazwa_systemowa = this.nazwa_systemowa;
        plik.nazwa_pliku = this.nazwa_pliku;
        plik.numer = this.numer;
        for (int i = 0; i < this.lista.size(); i++) {
            plik.lista.set(i, this.lista.get(i));
        }
        return plik;
    }

    public static Vector<Integer> rozkodowywanie_numeru(String kod) {
        Vector<Integer> wyjscie = new Vector();
        int licznik = 0;
        char[] chars = kod.toCharArray();

        char nowy = '$';

        while (chars[licznik] != '$') {
            licznik++;
        }
        licznik++;

        //  for(int i=licznik;i<chars.length;i++)
        while (licznik < chars.length) {
            Integer integer;
            String liczba = "";
            while (chars[licznik] != ',') {

                liczba += chars[licznik];
                licznik++;
            }
            licznik++;
            wyjscie.add(new Integer(Integer.valueOf(liczba)));
        }


        return wyjscie;
    }

    public void testGUI(JTextField pol, JTextField fore, JTextArea odpowiedz, InterfacePodsumowania podsumowania, String archiwum,JTree jtree) throws GratulacjeException, GratulacjeExceptionNull {

        String podpowiedz = "";
        if (this.polang) {
            if (pol.getText().isEmpty()) {
                List<Slowo> test = new LinkedList();


                if (getEnumeracje() == 0) {
                    for (int i = 0; i < lista.size(); i++) {
                        for (int j = 0; j < this.lista.get(i).get_priority(); j++) {
                            test.add(this.lista.get(i));
                        }
                    }
                } else {


                    for (int i = dolnyZakres(); i < gornyZakres(); i++) {
                        for (int j = 0; j < this.lista.get(i).get_priority(); j++) {
                            test.add(this.lista.get(i));
                        }
                    }
                }
                if (!test.isEmpty()) {
                    if (getEnumeracje() == 0) {
                        if (test.size() == 0) throw new GratulacjeExceptionNull();
                    } else if (wszystkieZdane()) throw new GratulacjeExceptionNull();
                    Plik plik = new Plik();
                    plik.lista = test;


                    Random random = new Random();
                    aktualnePytanie = random.nextInt(test.size());
                    //aktualnePytanie = okreslZakres();
                    Slowo wybraneSlowko = test.get(aktualnePytanie);
                    Vector<String> tesameslowka = new Vector<>();
                    for (Slowo s : this.lista) {
                        if (s.pol.equals(wybraneSlowko.pol) && !s.fore.equals(test.get(aktualnePytanie).fore)) {
                            tesameslowka.add(s.fore);
                        }
                    }

                    Vector<Character> podpowiedź = new Vector<>();
                    if (tesameslowka.size() > 0) {
                        for (int i = 0; i < wybraneSlowko.fore.length(); i++) {
                            podpowiedź.add('_');
                        }
                    }
                    for (String s : tesameslowka) {
                        char[] tochar = s.toCharArray();
                        char[] wybranetochar = wybraneSlowko.fore.toCharArray();

                        int flaga = 1;
                        for (int i = 0; i < podpowiedź.size(); i++) {
                            if (!podpowiedź.get(i).equals('_')) {
                                if (tochar.equals(podpowiedź.get(i))) {
                                    flaga = 1;
                                } else {
                                    flaga = 0;
                                    break;
                                }
                            }
                        }

                        if (flaga == 1) {
                            for (int i = 0; i < wybranetochar.length; i++) {
                                if (wybranetochar[i] == tochar[i] && podpowiedź.get(i).equals('_')) {

                                } else {
                                    podpowiedź.set(i, wybranetochar[i]);
                                    break;
                                }
                            }
                        }
                    }
                    podpowiedz = "";

                    if (podpowiedź.size() > 0) {
                        podpowiedz += "{";
                        for (int i = 0; i < podpowiedź.size(); i++) {
                            if (podpowiedź.get(i).equals('_') && i != 0) {
                                podpowiedz += " ";
                            }
                            podpowiedz += podpowiedź.get(i);
                        }
                        podpowiedz += "}";
                    }

                    pol.setText(test.get(aktualnePytanie).pol + podpowiedz);
                    pol.repaint();
                } else {

                    odpowiedz.setText("GRATULACJE!!! Umiesz już wszystkie słówka");

                    if (getEnumeracje() == 0) throw new GratulacjeExceptionNull();
                    else if (wszystkieZdane()) throw new GratulacjeExceptionNull();
                }
            } else {
                try {
                    // while (true) {
                    List<Slowo> test = new LinkedList();

                    if (getEnumeracje() == 0) {
                        for (int i = 0; i < lista.size(); i++) {
                            for (int j = 0; j < this.lista.get(i).get_priority(); j++) {
                                test.add(this.lista.get(i));
                            }
                        }
                    } else {
                        for (int i = dolnyZakres(); i < gornyZakres(); i++) {
                            for (int j = 0; j < this.lista.get(i).get_priority(); j++) {
                                test.add(this.lista.get(i));
                            }
                        }
                    }
                    if (getEnumeracje() == 0) {
                        if (test.size() == 0) throw new GratulacjeException();
                    } else if (wszystkieZdane()) throw new GratulacjeException();
                    Plik plik = new Plik();
                    plik.lista = test;

                    if (fore.getText().equals("123")) {
                        pol.setText("");
                        fore.setText("");
                        throw new EndException();

                    }



                    if (!manipulatorOdpowiedzi(fore.getText().toString(), test.get(aktualnePytanie).fore)) {

                        Speaker speaker= new Speaker();
                        try {
                            speaker.speak(test.get(aktualnePytanie).fore);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String answer="Źle: \n" + "Twoja odpowiedź:\n " + fore.getText() + "\nPoprawna odpowiedź:\n " + test.get(aktualnePytanie).pol + "- " + test.get(aktualnePytanie).fore;

                        odpowiedz.setText(answer);

                        if (test.get(aktualnePytanie).priority < Priority.priority) {
                            test.get(aktualnePytanie).priority++;
                            test.get(aktualnePytanie).archiwumLicznik = 0;
                            this.statyplik.iloscZlychOdpowiedzi+=1;
                            podsumowania.podsumowanie.get(0).iloscOdpowiedzi++;
                            podsumowania.podsumowanie.get(0).iloscNiepoprawnych++;
                            podsumowania.zapis();
                            test.add(test.get(aktualnePytanie));
                        }

                    } else {


                        Speaker reader= new Speaker();

                        try {
                            reader.speak(test.get(aktualnePytanie).fore);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        String answer="Dobrze: " + test.get(aktualnePytanie).pol + "- " + test.get(aktualnePytanie).fore;

                        odpowiedz.setText(answer);
                        test.get(aktualnePytanie).priority--;
                        this.statyplik.iloscDobrychOdpowiedzi++;
                        if (test.get(aktualnePytanie).priority == 0) {

                            TreePath p=jtree.getSelectionPath();

                           String s= getExpansionState(jtree);
                            ((DefaultTreeModel)jtree.getModel()).reload();

                            setExpansionState(s,jtree);
                            test.get(aktualnePytanie).archiwumLicznik--;
                        }

                        if (test.get(aktualnePytanie).archiwumLicznik < -4 && test.get(aktualnePytanie).reArchiwum < -2) {

                            //tutaj trzeba usunąć słówko z tego pliku i przenieść do archiwum
                            LinkedList<Slowo> l = new LinkedList<>();
                            //Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();

                            Pomocnik_plikowy.zczytywanie_z_pliku(archiwum, ',', l, Slowo.class);
                            String s = "";
                            for (Integer i : this.numer) {
                                s += i + ".";
                            }
                            test.get(aktualnePytanie).powrotArchiwum = s;
                            l.add(test.get(aktualnePytanie));


                            Pomocnik_plikowy.zapisywanie_do_pliku(archiwum, ',', l, Slowo.class);
                            this.usunSlowoZListy(test.get(aktualnePytanie));
                            this.zapis_zmian();

                        }
                        //test.get(aktualnePytanie)
                        podsumowania.podsumowanie.get(0).iloscOdpowiedzi++;
                        podsumowania.podsumowanie.get(0).iloscPoprawnych++;
                        podsumowania.zapis();
                        test.remove(aktualnePytanie);

                        if (getEnumeracje() == 0 && test.size() == 0) throw new GratulacjeException();
                        if (test.size() == 0 && wszystkieZdane()) throw new GratulacjeException();
                        if (test.size() == 0 && !wszystkieZdane()) {
                            for (int i = dolnyZakres(); i < gornyZakres(); i++) {
                                for (int j = 0; j < this.lista.get(i).get_priority(); j++) {
                                    test.add(this.lista.get(i));
                                }
                            }

                        }
                    }

                    fore.setText("");
                    test= new LinkedList<>();
                    if (getEnumeracje() == 0) {
                        for (int i = 0; i < lista.size(); i++) {
                            for (int j = 0; j < this.lista.get(i).get_priority(); j++) {
                                test.add(this.lista.get(i));
                            }
                        }
                    } else {
                        for (int i = dolnyZakres(); i < gornyZakres(); i++) {
                            for (int j = 0; j < this.lista.get(i).get_priority(); j++) {
                                test.add(this.lista.get(i));
                            }
                        }
                    }
                    if (test.size() > 1) aktualnePytanie = new Random().nextInt(test.size());
                    else aktualnePytanie = 0;

                    Slowo wybraneSlowko = test.get(aktualnePytanie);


                    Vector<String> tesameslowka = new Vector<>();
                    for (Slowo s : this.lista) {
                        if (s.pol.equals(wybraneSlowko.pol) && !s.fore.equals(test.get(aktualnePytanie).fore)) {
                            tesameslowka.add(s.fore);
                        }
                    }

                    Vector<Character> podpowiedź = new Vector<>();
                    if (tesameslowka.size() > 0) {
                        for (int i = 0; i < wybraneSlowko.fore.length(); i++) {
                            podpowiedź.add('_');
                        }
                    }
                    for (String s : tesameslowka) {
                        char[] tochar = s.toCharArray();
                        char[] wybranetochar = wybraneSlowko.fore.toCharArray();

                        int flaga = 1;
                        for (int i = 0; i < podpowiedź.size(); i++) {
                            if (!podpowiedź.get(i).equals('_')) {
                                if (tochar.equals(podpowiedź.get(i))) {
                                    flaga = 1;
                                } else {
                                    flaga = 0;
                                    break;
                                }
                            }
                        }

                        if (flaga == 1) {
                            for (int i = 0; i < wybranetochar.length; i++) {
                                if (wybranetochar[i] == tochar[i] && podpowiedź.get(i).equals('_')) {

                                } else {
                                    podpowiedź.set(i, wybranetochar[i]);
                                    break;
                                }
                            }
                        }
                    }
                    podpowiedz = "";
                    if (podpowiedź.size() > 0) {
                        podpowiedz += "{";
                        for (int i = 0; i < podpowiedź.size(); i++) {
                            if (podpowiedź.get(i).equals('_') && i != 0) {
                                podpowiedz += " ";
                            }
                            podpowiedz += podpowiedź.get(i);
                        }
                        podpowiedz += "}";
                    }


                    pol.setText(manipulatorZapytan(test.get(aktualnePytanie).pol) + podpowiedz);

                    fore.repaint();
                    //fore.setText();
                    //}
                } catch (EndException e) {
                    this.zapis_zmian();
                } catch (GratulacjeException e) {

                    odpowiedz.setText("GRATULACJE!!! Umiesz już wszystkie słówka");
                    this.zapis_zmian();
                    throw new GratulacjeException();
                }
                this.zapis_zmian();

            }
        } else { //==================================tutaj zaczyna się obł=sługa testu z obcego na polski=======================================
            if (pol.getText().isEmpty()) {

                List<Slowo> test = new LinkedList();


                if (getEnumeracje() == 0) {
                    for (int i = 0; i < lista.size(); i++) {
                        for (int j = 0; j < this.lista.get(i).repriority; j++) {
                            test.add(this.lista.get(i));
                        }
                    }
                } else {


                    for (int i = dolnyZakresRE(); i < gornyZakresRE(); i++) {
                        for (int j = 0; j < this.lista.get(i).repriority; j++) {

                            test.add(this.lista.get(i));
                        }
                    }
                }
                if (!test.isEmpty()) {
                    if (getEnumeracje() == 0) {
                        if (test.size() == 0) throw new GratulacjeExceptionNull();
                    } else if (wszystkieZdaneRE()) throw new GratulacjeExceptionNull();
                    Plik plik = new Plik();
                    plik.lista = test;
                    // plik.wypisanie_listy();

                    Random random = new Random();
                    aktualnePytanie = random.nextInt(test.size());
                    //aktualnePytanie = okreslZakres();
                    ////////////////////////////////
                    Slowo wybraneSlowko = test.get(aktualnePytanie);
                    Vector<String> tesameslowka = new Vector<>();
                    for (Slowo s : test) {
                        if (s.fore.equals(wybraneSlowko.fore) && !s.pol.equals(test.get(aktualnePytanie).pol)) {
                            tesameslowka.add(s.pol);
                        }
                    }

                    Vector<Character> podpowiedź = new Vector<>();
                    if (tesameslowka.size() > 0) {
                        for (int i = 0; i < wybraneSlowko.pol.length(); i++) {
                            podpowiedź.add('_');
                        }
                    }
                    for (String s : tesameslowka) {
                        char[] tochar = s.toCharArray();
                        char[] wybranetochar = wybraneSlowko.pol.toCharArray();

                        int flaga = 1;
                        for (int i = 0; i < podpowiedź.size(); i++) {
                            if (!podpowiedź.get(i).equals('_')) {
                                if (tochar.equals(podpowiedź.get(i))) {
                                    flaga = 1;
                                } else {
                                    flaga = 0;
                                    break;
                                }
                            }
                        }

                        if (flaga == 1) {
                            for (int i = 0; i < wybranetochar.length; i++) {
                                if (wybranetochar[i] == tochar[i] && podpowiedź.get(i).equals('_')) {

                                } else {
                                    podpowiedź.set(i, wybranetochar[i]);
                                    break;
                                }
                            }
                        }
                    }
                    podpowiedz = "";

                    if (podpowiedź.size() > 0) {
                        podpowiedz += "{";
                        for (int i = 0; i < podpowiedź.size(); i++) {
                            if (podpowiedź.get(i).equals('_') && i != 0) {
                                podpowiedz += " ";
                            }
                            podpowiedz += podpowiedź.get(i);
                        }
                        podpowiedz += "}";
                    }

                    ///////////////////////////////
                    pol.setText(test.get(aktualnePytanie).fore + podpowiedz);
                    pol.repaint();
                } else {

                    odpowiedz.setText("GRATULACJE!!! Umiesz już wszystkie słówka");

                    if (getEnumeracje() == 0) throw new GratulacjeExceptionNull();
                    else if (wszystkieZdaneRE()) throw new GratulacjeExceptionNull();
                }
            } else {
                try {
                    // while (true) {
                    List<Slowo> test = new LinkedList();

                    if (getEnumeracje() == 0) {
                        for (int i = 0; i < lista.size(); i++) {
                            for (int j = 0; j < this.lista.get(i).repriority; j++) {
                                test.add(this.lista.get(i));
                            }
                        }
                    } else {
                        for (int i = dolnyZakresRE(); i < gornyZakresRE(); i++) {
                            for (int j = 0; j < this.lista.get(i).repriority; j++) {
                                test.add(this.lista.get(i));
                            }
                        }
                    }
                    if (getEnumeracje() == 0) {
                        if (test.size() == 0) throw new GratulacjeException();
                    } else if (wszystkieZdaneRE()) throw new GratulacjeException();
                    Plik plik = new Plik();
                    plik.lista = test;
                    //plik.wypisanie_listy();

                   /* if (!pol.getText().equals(test.get(aktualnePytanie).fore) && !(pol.getText()).equals(test.get(aktualnePytanie).fore + extractPodpowiedz(pol.getText()))) {
                        System.out.println("To może być przyczyną błędu");
                        aktualnePytanie++;
                    }*/
                    //Random random = new Random();
                    // int a = random.nextInt(test.size());
                    if (fore.getText().equals("123")) {
                        pol.setText("");
                        fore.setText("");
                        throw new EndException();

                    }
                    // if (!((test.get(aktualnePytanie).pol).equals(fore.getText().toString()))) {
                    if (!manipulatorOdpowiedzi(fore.getText().toString(), test.get(aktualnePytanie).pol)) {

                        String answer="Źle: \n" + "Twoja odpowiedź:\n " + fore.getText() + "\nPoprawna odpowiedź:\n " + test.get(aktualnePytanie).fore + "- " + test.get(aktualnePytanie).pol;
                      /*  if(!(test.get(aktualnePytanie).pronunciation.equals("")|| test.get(aktualnePytanie).pronunciation.equals("pronunciation"))){
                            answer+=" <"+test.get(aktualnePytanie).pronunciation+">";
                        }*/

                        odpowiedz.setText(answer);

                        if (test.get(aktualnePytanie).repriority < 2) {
                            test.get(aktualnePytanie).repriority++;
                            this.statyplik.iloscZlychOdpowiedzi++;
                            test.get(aktualnePytanie).reArchiwum = 0;

                            podsumowania.podsumowanie.get(0).iloscOdpowiedzi++;
                            podsumowania.podsumowanie.get(0).iloscNiepoprawnych++;
                            podsumowania.zapis();
                            test.add(test.get(aktualnePytanie));
                        }
                        // test.add(test.get(aktualnePytanie));
                        //test.add(new Slowo());
                    } else {
                        odpowiedz.setText("Dobrze");
                        test.get(aktualnePytanie).repriority--;
                        this.statyplik.iloscDobrychOdpowiedzi++;
                        if (test.get(aktualnePytanie).repriority == 0) {
                            test.get(aktualnePytanie).reArchiwum--;
                        }

                        if (test.get(aktualnePytanie).archiwumLicznik < -4 && test.get(aktualnePytanie).reArchiwum < -2) {
                            //tutaj trzeba usunąć słówko z tego pliku i przenieść do archiwum
                           /* LinkedList<Slowo> l = new LinkedList<>();
                            Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
                            Pomocnik_plikowy.zczytywanie_z_pliku(archiwum, ',', l, Slowo.class);
                            l.add(test.get(aktualnePytanie));
                            Pomocnik_plikowy.zapisywanie_do_pliku(archiwum, ',', l, Slowo.class);
                            this.usunSlowoZListy(test.get(aktualnePytanie));
                            this.zapis_zmian();*/

                        }
                        //test.get(aktualnePytanie)
                        podsumowania.podsumowanie.get(0).iloscOdpowiedzi++;
                        podsumowania.podsumowanie.get(0).iloscPoprawnych++;
                        podsumowania.zapis();
                        test.remove(aktualnePytanie);

                        if (getEnumeracje() == 0 && test.size() == 0) throw new GratulacjeException();
                        if (test.size() == 0 && wszystkieZdaneRE()) throw new GratulacjeException();
                        if (test.size() == 0 && !wszystkieZdaneRE()) {
                            int d = dolnyZakresRE();
                            int g = gornyZakresRE();
                            for (int i = dolnyZakresRE(); i < gornyZakresRE(); i++) {
                                for (int j = 0; j < this.lista.get(i).repriority; j++) {
                                    test.add(this.lista.get(i));
                                }
                            }
                            //aktualnePytanie = random.nextInt(test.size());
                        }
                    }

                    fore.setText("");
                    test= new LinkedList<>();
                    if (getEnumeracje() == 0) {
                        for (int i = 0; i < lista.size(); i++) {
                            for (int j = 0; j < this.lista.get(i).get_priority(); j++) {
                                test.add(this.lista.get(i));
                            }
                        }
                    } else {
                        for (int i = dolnyZakres(); i < gornyZakres(); i++) {
                            for (int j = 0; j < this.lista.get(i).get_priority(); j++) {
                                test.add(this.lista.get(i));
                            }
                        }
                    }
                    if (test.size() > 1) aktualnePytanie = new Random().nextInt(test.size());
                    else aktualnePytanie = 0;

                    ////////////////////////////////
                    Slowo wybraneSlowko = test.get(aktualnePytanie);
                    Vector<String> tesameslowka = new Vector<>();
                    for (Slowo s : test) {
                        if (s.fore.equals(wybraneSlowko.fore) && !s.pol.equals(test.get(aktualnePytanie).pol)) {
                            tesameslowka.add(s.pol);
                        }
                    }

                    Vector<Character> podpowiedź = new Vector<>();
                    if (tesameslowka.size() > 0) {
                        for (int i = 0; i < wybraneSlowko.pol.length(); i++) {
                            podpowiedź.add('_');
                        }
                    }
                    for (String s : tesameslowka) {
                        char[] tochar = s.toCharArray();
                        char[] wybranetochar = wybraneSlowko.pol.toCharArray();

                        int flaga = 1;
                        for (int i = 0; i < podpowiedź.size(); i++) {
                            if (!podpowiedź.get(i).equals('_')) {
                                if (tochar.equals(podpowiedź.get(i))) {
                                    flaga = 1;
                                } else {
                                    flaga = 0;
                                    break;
                                }
                            }
                        }

                        if (flaga == 1) {
                            for (int i = 0; i < wybranetochar.length; i++) {
                                if ((i < tochar.length && wybranetochar[i] == tochar[i]) && podpowiedź.get(i).equals('_')) {

                                } else {
                                    podpowiedź.set(i, wybranetochar[i]);
                                    break;
                                }
                            }
                        }
                    }
                    podpowiedz = "";
                    if (podpowiedź.size() > 0) {
                        podpowiedz += "{";
                        for (int i = 0; i < podpowiedź.size(); i++) {
                            if (podpowiedź.get(i).equals('_') && i != 0) {
                                podpowiedz += " ";
                            }
                            podpowiedz += podpowiedź.get(i);
                        }
                        podpowiedz += "}";
                    }

                    ///////////////////////////////
                    pol.setText(test.get(aktualnePytanie).fore + podpowiedz);

                    fore.repaint();
                    //fore.setText();
                    //}
                } catch (EndException e) {
                    this.zapis_zmian();
                } catch (GratulacjeException e) {

                    odpowiedz.setText("GRATULACJE!!! Umiesz już wszystkie słówka");
                    this.zapis_zmian();
                    throw new GratulacjeException();
                }
                this.zapis_zmian();

            }

        }

    }



    private int okreslZakres() {
        Random random = new Random();
        int a = 0;
        int b = 0;
        int enu = this.getEnumeracje();
        int liczbaEnumeracji = (int) ((float) this.getLiczbaSlowek() / (float) this.getEnumeracje() + 0.99);
        return random.nextInt(a) + b;

    }

    private int dolnyZakres() {
        int enu = getEnumeracje();
        int liczbaEnumeracji = (int) ((float) this.getLiczbaSlowek() / (float) this.getEnumeracje() + 0.99);
        int i = 0;
        while (i < lista.size() && lista.get(i).priority == 0) {
            i++;
        }

        int ktoraEnumeracja = (int) ((float) i / (float) enu);
        return ktoraEnumeracja * enu;


    }

    private int gornyZakres() {
        int enu = getEnumeracje();
        int liczbaEnumeracji = (int) ((float) this.getLiczbaSlowek() / (float) this.getEnumeracje() + 0.99);
        int i = 0;
        while (i < lista.size() && lista.get(i).priority == 0) {
            i++;
        }


        int ktoraEnumeracja = (int) ((float) i / (float) enu + 0.99);
        /*if(ktoraEnumeracja==liczbaEnumeracji)
        {

         return lista.size();
        }*/
        if (dolnyZakres() + getEnumeracje() > lista.size()) {
            return lista.size();
        } else {

            return dolnyZakres() + getEnumeracje();
        }
    }

    private int dolnyZakresRE() {
        int enu = getEnumeracje();
        int liczbaEnumeracji = (int) ((float) this.getLiczbaSlowek() / (float) this.getEnumeracje() + 0.99);
        int i = 0;
        while (i < lista.size() && lista.get(i).repriority == 0) {
            i++;
        }

        int ktoraEnumeracja = (int) ((float) i / (float) enu);
        return ktoraEnumeracja * enu;
    }

    private int gornyZakresRE() {
        int enu = getEnumeracje();
        int liczbaEnumeracji = (int) ((float) this.getLiczbaSlowek() / (float) this.getEnumeracje() + 0.99);
        int i = 0;
        while (i < lista.size() && lista.get(i).repriority == 0) {
            i++;
        }


        int ktoraEnumeracja = (int) ((float) i / (float) enu + 0.99);
        /*if(ktoraEnumeracja==liczbaEnumeracji)
        {

         return lista.size();
        }*/
        if (dolnyZakresRE() + getEnumeracje() > lista.size()) {
            return lista.size();
        } else {

            return dolnyZakresRE() + getEnumeracje();
        }
    }


    public int opanowaneSlowka() {
        int n = 0;
        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).priority == 0) {
                n++;
            }
        }
        return n;
    }
    public int getWhite()
    {
        int n = 0;
        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).repriority == 0) {
                n++;
            }
        }
        return n;
    }
    public int getBlack()
    {
        int n = 0;
        for (int i = 0; i < this.lista.size(); i++) {
            if (this.lista.get(i).repriority != 0) {
                n++;
            }
        }
        return n;
    }

    public float procentOpanowanychSłówek() {
        float f = ((float) opanowaneSlowka() / (float) this.lista.size()) * 100;
        return f;
    }

    public int iloscUdzielonychOdpowiedzi() {
        return this.iloscUdzielonychOdpowiedzi;
    }

    public int iloscOdpowiedziDoZakonczeniaTematu() {
        int n = 0;
        for (int i = 0; i < this.lista.size(); i++) {
            n += this.lista.get(i).priority;
        }
        return n;
    }

    public String najgorszeSlowko() {
        String najgorszeSlowko = "";
        int aktualnyPiorytet;
        if (!lista.isEmpty()) {

            najgorszeSlowko = this.lista.get(0).pol + "-" + this.lista.get(0).fore;
            aktualnyPiorytet = this.lista.get(0).priority;
            for (int i = 0; i < this.lista.size(); i++) {
                if (this.lista.get(i).priority > aktualnyPiorytet) {

                    aktualnyPiorytet = this.lista.get(i).priority;
                    najgorszeSlowko = this.lista.get(i).pol + "-" + this.lista.get(i).fore;

                }
            }
        }
        aktualnyPiorytet = 0;
        return najgorszeSlowko;
    }

    public float procentOdpowiedziDoUdzielenia() {
        float f = (((float) this.iloscOdpowiedziDoZakonczeniaTematu()) / ((float) this.lista.size() * 3)) * 100;
        return f;
    }

    public int iloscSlowekDonauczenia() {
        return this.lista.size() - this.opanowaneSlowka();
    }

    public void aktualizujNazweSystemowa() {
        String nowaNazwaSystemowa;
        nowaNazwaSystemowa = this.nazwa_pliku;
        //nowaNazwaSystemowa+=this.date;
        nowaNazwaSystemowa += "$";
        for (int i = 0; i < this.numer.size(); i++) {
            nowaNazwaSystemowa += numer.get(i) + ",";
        }
        nowaNazwaSystemowa += ".txt";
        this.nazwa_systemowa = nowaNazwaSystemowa;
    }

    public void usunSlowoZListy(Slowo slowo) {
        for (int i = 0; i < this.lista.size(); i++) {
            if (lista.get(i).equals(slowo)) {
                lista.remove(i);
            }
        }

    }

    public int getLiczbaSlowek() {
        return this.lista.size();
    }

    public int getEnumeracje() {
        if (!lista.isEmpty()) {
            if (this.lista.get(0).enumeracja == 0) {
                return 0;
            } else {
                int checkelement;

                if (!lista.isEmpty() && lista.size() > 0) {
                    checkelement = this.lista.get(0).enumeracja;
                    if (lista.size() > 1) {
                        int i = 1;
                        while (i < lista.size() && checkelement == this.lista.get(i).enumeracja) {
                            i++;
                        }
                        return i;
                    } else {
                        return 1;
                    }

                } else {
                    return 0;
                }

            }
        } else {
            return 0;
        }
    }

    public void setEnumeracje(int liczba) {

        if (liczba == 0) {
            for (int i = 0; i < this.lista.size(); i++) {
                this.lista.get(i).enumeracja = 0;
            }
        } else {
            int licznik = 1;
            for (int i = 0; i < this.lista.size(); i++) {
                if (i % liczba == 0 && i != 0) licznik++;

                this.lista.get(i).enumeracja = licznik;
            }
        }

    }

    public Vector<Boolean> rozbicieEnumeracyjne() {
        Vector<Boolean> boolek = new Vector<>();
        int liczbaEnumeracji = (int) ((float) this.getLiczbaSlowek() / (float) this.getEnumeracje() + 0.99);
        int enumeracja = this.getEnumeracje();
        boolean zdanieEnumeracji = true;
        for (int i = 0; i < liczbaEnumeracji; i++) {
            zdanieEnumeracji = true;
            for (int j = 0; j < enumeracja; j++) {
                if (i * enumeracja + j < this.lista.size()) {
                    if (this.lista.get(i * enumeracja + j).priority == 0) {

                    } else {
                        zdanieEnumeracji = false;
                    }
                }

            }
            boolek.add(zdanieEnumeracji);
        }


        return boolek;
    }

    public boolean wszystkieZdane() {
        boolean t = true;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).priority > 0) t = false;
        }
        return t;
    }

    public boolean wszystkieZdaneRE() {
        boolean t = true;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).repriority > 0) t = false;
        }
        return t;
    }

    public String getNazwaBezDany() {
        int i = 0;

        char[] chars = this.nazwa_pliku.toCharArray();
        String nazwa = "";
        while (chars[i] != '%') {
            nazwa += chars[i];
            i++;
        }
        return nazwa;
    }

    private String extractPodpowiedz(String slowo) {
        String podpowiedz = "";
        int flaga = 0;
        char[] podpowiedzChar = slowo.toCharArray();
        for (char c : podpowiedzChar) {
            if ((flaga == 1 || c == '{') || c == '}') {
                if (c == '{') flaga = 1;
                if (c == '}') flaga = 0;
                podpowiedz += c;
            }
        }
        return podpowiedz;

    }

    private String manipulatorZapytan(String zapytanie) {

        if (zapytanie.contains(";") || zapytanie.contains("/")) {
           // String odpowiedz = "";
            char[] tabchar = zapytanie.toCharArray();
            Vector<String> vector = new Vector<>();
            String tmp = "";
            for (char c : tabchar) {
                if (c == ';' || c == '/') {

                    vector.add(tmp);
                    tmp = "";
                } else {
                    tmp += c;
                }
            }
            Random random = new Random();
            //int mtp=random.nextInt(vector.size()-1);
            //int z=random.nextInt(vector.size())>1 ? random.nextInt(vector.size())-1:random.nextInt(vector.size());
            if(vector.size()==1)return vector.get(0);
            else return vector.get(random.nextInt(vector.size()-1));
            //return "jeden";
        } else {
            return zapytanie;
        }
        //else return"dwa";


    }

    private boolean manipulatorOdpowiedzi(String odpowiedzUzytkownika, String odpowiedzWBazie) {
        int flaga = 0;
        String odpowiedz = odpowiedzWBazie;

        if (odpowiedz.contains("(") && odpowiedz.contains(")")) {
            String tmpS = "";
            char[] chatTab = odpowiedz.toCharArray();
            int flagaS = 1;
            for (char c : chatTab) {
                if (c == '(') {
                    flagaS = 0;
                } else if (c == ')') {
                    flagaS = 1;
                } else if (flagaS == 1) tmpS += c;
            }
            odpowiedz = tmpS;

        }
        if (odpowiedz.contains("[") & odpowiedz.contains("]")) {
            String tmpS = "";
            char[] chatTab = odpowiedz.toCharArray();
            int flagaS = 1;
            for (char c : chatTab) {
                if (c == '[') {
                    flagaS = 0;
                } else if (c == ']') {
                    flagaS = 1;
                } else if (flagaS == 1) tmpS += c;
            }
            odpowiedz = tmpS;
        }

        if (odpowiedz.contains("|")) {
            String tmpS = "";
            char[] chatTab = odpowiedz.toCharArray();
            int flagaS = 1;
            for (char c : chatTab) {
                if (c == '|') {
                    flagaS = 0;
                } else if (c == '|') {
                    flagaS = 1;
                } else if (flagaS == 1) tmpS += c;
            }
            odpowiedz = tmpS;
        }
        if (odpowiedz.contains(";") || odpowiedz.contains("/")) {
            Vector<String> vector = new Vector<>();
            char[] tabChar = odpowiedz.toCharArray();
            String tmpS = "";
            for (char c : tabChar) {
                if (c == ';' || c == '/') {
                    //vector.add(tmpS);
                    if (tmpS.equals(odpowiedzUzytkownika)) flaga = 1;
                    tmpS = "";
                } else tmpS += c;
            }
            if (tmpS.equals(odpowiedzUzytkownika)) flaga = 1;

        }
        /*if(odpowiedz.contains("/"))
        {
    //        Vector<String> vector= new Vector<>();
            char[] tabChar=odpowiedz.toCharArray();
            String tmpS="";
            for(char c:tabChar)
            {
                if(c==';')
                {
                    //vector.add(tmpS);
                    if(tmpS.equals(odpowiedzUzytkownika))flaga=1;
                    tmpS="";
                }
                else tmpS+=c;
            }

        }*/
        if (odpowiedzUzytkownika.equals(odpowiedz)) flaga = 1;
        if (flaga == 0) return false;
        else return true;
    }

    public String getJezyk() {
        return this.statyplik.nasz;
    }


    public void wGore(int poziom, String archiwum) {
        if (this.numer.get(poziom) > 1) {
            this.numer.set(poziom, this.numer.get(poziom) - 1);
        }
        aktualizujNumerPlikuZProgramuNaPliki();
        juzZmienione = true;
        aktualizacjaArchiwumWGore(archiwum);
    }

    public void wDol(int poziom, String archiwum) {

        this.numer.set(poziom, this.numer.get(poziom) + 1);
        aktualizujNumerPlikuZProgramuNaPliki();
        juzZmienione = true;
        aktualizacjaArchiwumWDol(archiwum);

    }

    public void wGore(String archiwum) {
        int poziom = this.numer.size() - 1;
        if (this.numer.get(poziom) > 1) {
            this.numer.set(poziom, this.numer.get(poziom) - 1);
        }
        aktualizujNumerPlikuZProgramuNaPliki();
        juzZmienione = true;
        aktualizacjaArchiwumWGore(archiwum);
    }

    public void wDol(String archiwum) {
        int poziom = this.numer.size() - 1;
        this.numer.set(poziom, this.numer.get(poziom) + 1);
        aktualizujNumerPlikuZProgramuNaPliki();
        juzZmienione = true;
        aktualizacjaArchiwumWDol(archiwum);
    }


    public void zmienNumerPliku(Vector<Integer> nowyNumer) {
        this.numer = nowyNumer;
        String nowaNazwaSystemowa = this.nazwa_pliku + "$";
        for (int i : nowyNumer) {
            nowaNazwaSystemowa += i + ",";
        }
        nowaNazwaSystemowa += ".txt";
        Pomocnik_plikowy.DeleteFile(this.nazwa_systemowa);

        this.nazwa_systemowa = nowaNazwaSystemowa;
        Pomocnik_plikowy.CreateFile(this.nazwa_systemowa);

        this.zapis_zmian();

    }

    public void aktualizujNumerPlikuZProgramuNaPliki() {
        zmienNumerPliku(this.numer);
    }

    public void removeSlowko(int n) {
        if (this.get_nazwa_pliku().contains("^")) {
            Pomocnik_plikowy pomocnik_plikowy = new Pomocnik_plikowy();
            LinkedList<Word> words = new LinkedList<>();
            ObsługaNazwowa ob = new ObsługaNazwowa();
            pomocnik_plikowy.zczytywanie_z_pliku("Skrót" + ob.oddzielNazeOdDaty(this.get_nazwa_pliku()).nazwa + ".txt", ',', words, Word.class);
            for (Word w : words) {
                if ((w.slowo1.equals(this.lista.get(n).pol) && w.slowo2.equals(this.lista.get(n).fore)) || ((w.slowo2.equals(this.lista.get(n).pol) && w.slowo1.equals(this.lista.get(n).fore)))) {
                    words.remove(w);
                    break;
                }
            }
            pomocnik_plikowy.zapisywanie_do_pliku("Skrót" + ob.oddzielNazeOdDaty(this.get_nazwa_pliku()).nazwa + ".txt", ',', words, Word.class);

        }
        this.lista.remove(n);

        this.zapis_zmian();

    }

    public void aktualizacjaArchiwumWGore(String archiwum) {
        LinkedList<Slowo> l = new LinkedList<>();
        Pomocnik_plikowy.zczytywanie_z_pliku(archiwum, ',', l, Slowo.class);
        String szukany = "";
        String wynikowy = "";

        for (Integer i : this.numer) {
            szukany += i + ".";
            if (i.equals(this.numer.get(this.numer.size() - 1))) wynikowy += (i + 1) + ".";
            else wynikowy += i + ".";
        }
        Vector<Integer> wgore = new Vector<>();
        Vector<Integer> wdol = new Vector<>();

        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).powrotArchiwum.equals(szukany)) {
                wgore.add(i);
            }
            if (l.get(i).powrotArchiwum.equals(wynikowy)) {
                wdol.add(i);
            }
        }
        for (Integer i : wgore) {
            l.get(i).powrotArchiwum = wynikowy;
        }
        for (Integer i : wdol) {
            l.get(i).powrotArchiwum = szukany;
        }


        Pomocnik_plikowy.zapisywanie_do_pliku(archiwum, ',', l, Slowo.class);

        this.zapis_zmian();
    }

    public void aktualizacjaArchiwumWDol(String archiwum) {
        LinkedList<Slowo> l = new LinkedList<>();
        Pomocnik_plikowy.zczytywanie_z_pliku(archiwum, ',', l, Slowo.class);
        String szukany = "";
        String wynikowy = "";

        for (Integer i : this.numer) {
            szukany += i + ".";
            if (i.equals(this.numer.get(this.numer.size() - 1))) wynikowy += (i - 1) + ".";
            else wynikowy += i + ".";
        }
        Vector<Integer> wgore = new Vector<>();
        Vector<Integer> wdol = new Vector<>();

        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).powrotArchiwum.equals(szukany)) {
                wgore.add(i);
            }
            if (l.get(i).powrotArchiwum.equals(wynikowy)) {
                wdol.add(i);
            }
        }
        for (Integer i : wgore) {
            l.get(i).powrotArchiwum = wynikowy;
        }
        for (Integer i : wdol) {
            l.get(i).powrotArchiwum = szukany;
        }


        Pomocnik_plikowy.zapisywanie_do_pliku(archiwum, ',', l, Slowo.class);

        this.zapis_zmian();
    }

    public void aktualizacjaArchiwumUsunięcie(String archiwum) {
        LinkedList<Slowo> l = new LinkedList<>();
        Pomocnik_plikowy.zczytywanie_z_pliku(archiwum, ',', l, Slowo.class);
        String szukany = "";


        for (Integer i : this.numer) {
            szukany += i + ".";
        }
        for (Slowo s : l) {
            if (s.powrotArchiwum.equals(szukany)) {
                s.powrotArchiwum = "n/n";

            }
        }


        Pomocnik_plikowy.zapisywanie_do_pliku(archiwum, ',', l, Slowo.class);

        this.zapis_zmian();
    }

    public boolean ifDoReArchiwum() {
        boolean flaga = false;
        for (Slowo s : this.lista) {
            if (s.reArchiwum < -1) {
                flaga = true;
                break;
            }
        }
        return flaga;
    }
    public boolean ifDoArchiwum() {
        boolean flaga = false;
        for (Slowo s : this.lista) {
            if (s.archiwumLicznik < -3) {
                flaga = true;
                break;
            }
        }
        return flaga;
    }
    public void setExpansionState(String s,JTree tree){

        String[] indexes = s.split(",");

        for ( String st : indexes ){

            int row = Integer.parseInt(st);

            tree.expandRow(row);

        }

    }
    public String getExpansionState(JTree tree){

        StringBuilder sb = new StringBuilder();

        for ( int i = 0; i < tree.getRowCount(); i++ ){

            if ( tree.isExpanded(i) ){

                sb.append(i).append(",");

            }

        }

        return sb.toString();

    }


    public void saveToFolderTree(String path)
    {
        try {

            Files.copy(new File(this.nazwa_systemowa).toPath(),new File(path+"\\"+this.getNazwaBezDany()+".txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   }
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

/**
 * Created by osiza on 04.03.2018.
 */
public class ObsługaNazwowa {

    class NazwaPlusData
    {
        public String nazwa;
        public String date;
    }

    public  NazwaPlusData oddzielNazeOdDaty(String nazwaZData)
    {
        NazwaPlusData nazwaPlusData= new NazwaPlusData();
        nazwaPlusData.nazwa="";
        nazwaPlusData.date ="";
        int dlugoscNazwy=0;
        while(dlugoscNazwy<nazwaZData.length()&&nazwaZData.toCharArray()[dlugoscNazwy]!='%')
        {

            nazwaPlusData.nazwa+=nazwaZData.toCharArray()[dlugoscNazwy];
            dlugoscNazwy++;
        }
        dlugoscNazwy++;
        for(int i=dlugoscNazwy;i<nazwaZData.toCharArray().length;i++)
        {
            nazwaPlusData.date+=nazwaZData.toCharArray()[i];
        }
        return nazwaPlusData;
    }

    public int roznicaDni(String teraz, String przedtem) {
        int roznica;

        if(teraz.length()==5&& przedtem.length()==5) {

            int nowDays = Integer.parseInt(teraz.substring(0, 3));
            int nowYear = Integer.parseInt(teraz.substring(3));
            int pastDays = Integer.parseInt(przedtem.substring(0, 3));
            int pastYear = Integer.parseInt(przedtem.substring(3));
            //System.out.println(teraz);
            //  System.out.println("ROZNICA: "+nowDays);
            // System.out.println("365*"+nowYear+"+"+nowDays+"-365*"+pastYear+"-"+pastDays);

            roznica = 365 * nowYear + nowDays - 365 * pastYear - pastDays;
        }
        else
        {
          roznica=365;
        }
        return roznica;
    }


    public String zmienNaTeraz(String nazwaDoZmiany)
    {
        Calendar calendar= Calendar.getInstance();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("DDDyy");

        String teraz=simpleDateFormat.format(calendar.getTime());
        String nazwa=this.oddzielNazeOdDaty(nazwaDoZmiany).nazwa;
        if(nazwa.contains("Technika dziecka")||nazwa.substring(0,1).contains("^"))
        {
            nazwa+="%";
        }
        else  nazwa+="%"+teraz;
        return nazwa;
    }

    public static Vector<Integer> getNumerSlowoArchiwum(String s)
    {
        Vector<Integer> vector= new Vector<>();
        char[] ca= s.toCharArray();
        for(char c:ca)
        {
            if(c!='.')
            {
                vector.add(Integer.parseInt(String.valueOf(c)));
            }
        }
        return vector;
    }

    public static String getJezyk1(String string)// służy do wyjęcia z nazw wsłowników typu: "Słownik-Polski-Angielski" => "Pol"
    {
        char[] tabs=string.toCharArray();
        boolean flaga=false;
        String ret="";
        for(char c:tabs)
        {
            if(c=='-' &&flaga==false)
            {
                flaga=true;
            }
            else if(flaga==true && c=='-')
            {
                flaga=false;
                break;
            }
            else if(flaga==true)
            {
                ret+=c;
            }
        }
        return ret;
    }

    public static String getJezyk2(String string)// służy do wyjęcia z nazw wsłowników typu: "Słownik-Polski-Angielski" => "Angielski"
    {
        char[] tabs=string.toCharArray();
        int flaga=0;
        String ret="";
        for(char c:tabs)
        {
            if(c=='-' &&flaga==0)
            {
                flaga=1;
            }
            else if(flaga==1 && c=='-')
            {
                flaga=2;

            }
            else if(flaga==2)
            {
                ret+=c;
            }
        }
        return ret;
    }

}

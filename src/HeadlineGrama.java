import java.util.Vector;

/**
 * Created by osiza on 01.10.2018.
 */
public class HeadlineGrama extends StatyPlik {


    public HeadlineGrama(String linia)
    {
        super(linia);
        char[] ctab=linia.toCharArray();
        Vector<String> vector= new Vector();
        String tmp="";
        for(char c:ctab)
        {
            if((!Character.isLetter(c)&&!Character.isDigit(c))&&!tmp.equals(null))
            {
                vector.add(tmp);
                tmp="";
            }
            else if(Character.isLetter(c)||Character.isDigit(c))
            {
                tmp+=c;
            }
        }
        if(vector.size()==4)
        {
            this.nasz=vector.get(0);
            this.obcy=vector.get(1);
            this.iloscOdpowiedzi=Integer.parseInt(vector.get(2));

        }
        this.iloscDobrychOdpowiedzi=0;
        this.iloscZlychOdpowiedzi=0;
        this.dodatkowy=0;
        this.enumeracja=0;
        this.powrotArchiwum="";
        this.nic="";
        this.extranull="";


    }

    public String generateLine()
    {
        String ret=this.nasz+"|"+this.obcy+"|"+this.iloscOdpowiedzi+"|";
        return ret;
    }



}

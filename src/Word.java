/**
 * Created by osiza on 02.09.2018.
 */
public class Word {
    String slowo1;
    String slowo2;
    Word(String slowo1,String slowo2)
    {
        this.slowo1=slowo1;
        this.slowo2=slowo2;
    }

    Word(Slowo slowo)
    {
        this.slowo1=slowo.pol;
        this.slowo2=slowo.fore;
    }
    Word(StatyPlik statyPlik)
    {
        this.slowo1=statyPlik.nasz;
        this.slowo2=statyPlik.obcy;
    }
    Word()
    {
        slowo1="";
        slowo2="";
    }
}

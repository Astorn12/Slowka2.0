/**
 * Created by osiza on 23.09.2018.
 */
public class WordFromBook extends Slowo {
  /* public String pol;
    public String fore;
    public int priority;
    public String language;//służy do określenia koloru słówka w liście do uczenia, skrót słówka nie może zawierać literk i chyba
    public int archiwumLicznik;
    public int repriority;
    public int enumeracja;
    public int reArchiwum;
    public String jezyk;
    public String powrotArchiwum;*/
        String link;
        public WordFromBook(StatyPlik staty)
        {
            super(staty);
            this.link="";
        }
        public WordFromBook()
        {
            super();
            this.link="";
        }
    WordFromBook(String pol,String fore,String language,int priority)
    {
        super(pol,fore,language,priority);
        link="";
    }
    WordFromBook(String pol,String fore,String language,int priority,String link)
    {
        super(pol,fore,language,priority);
        this.link=link;
    }
        public int linksBeginning()
        {
            String tmp="";
            for(Character c:this.link.toCharArray())
            {
                if(Character.isLetter(c)||Character.isDigit(c))
                {
                    tmp+=c;
                }
                else break;
            }
            return Integer.parseInt(tmp);
        }
        public int linksEnding()
        {
            String tmp="";
            boolean flaga=false;
            for(Character c:this.link.toCharArray())
            {
                if(!(Character.isLetter(c)||Character.isDigit(c)))
                {
                    flaga=true;


                }
                else if((Character.isLetter(c)||Character.isDigit(c))&&flaga)
                {
                    tmp+=c;
                }

            }
            return Integer.parseInt(tmp);
        }

   /* public Slowo clone1()
    {

        // WordFromBook slowo= new WordFromBook();
        Slowo slowo= new Slowo();
        slowo.fore = this.fore;
        slowo.pol=this.pol;
        slowo.priority=this.priority;
        slowo.language= this.language;
       // slowo.link="";
        return slowo;

    }*/
    @Override
    public WordFromBook clone()
    {

        // WordFromBook slowo= new WordFromBook();
        WordFromBook slowo= new WordFromBook();
        slowo.fore = this.fore;
        slowo.pol=this.pol;
        slowo.priority=this.priority;
        slowo.language= this.language;
        slowo.link=this.link;
        return slowo;

    }
}

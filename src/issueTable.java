import java.util.LinkedList;

/**
 * Created by osiza on 07.10.2018.
 */
public class IssueTable {
    public String name;
    public String kind;
    public LinkedList<LinkedList<Strin>>  tabelka;
    public int number;
    public IssueTable(String name,String rodzaj,LinkedList<LinkedList<Strin>> tabelka)
    {
        this.name=name;
        this.kind=rodzaj;
        this.tabelka=tabelka;
    }
    public IssueTable()
    {
        this.name="non";
        this.kind="non";
        this.tabelka= new LinkedList<>();
    }
    public IssueTable(String string)
    {
        char tab[]= string.toCharArray();
        String nazwa="";
        String rodzaj="";
        boolean flaga=false;
        for(char c:tab)
        {
            if(c!='#'&&c!='|'&&flaga==false)
            {
                nazwa+=c;
            }
            else if(c!='#'&&c!='|'&&flaga==true)
            {
                rodzaj+=c;
            }
            else if(c=='|')
            {
                flaga=true;
            }
        }
        this.name=nazwa;
        this.kind=rodzaj;
        this.tabelka= new LinkedList<>();

    }

    public String getLineToSave()
    {
        return "#"+this.name+"|"+this.kind+"|";
    }
    public IssueTable(int k,int r)
    {this.tabelka= new LinkedList<>();
        for(int i=0;i<r;i++)
        {
            LinkedList<Strin> tmp= new LinkedList<>();
            tabelka.add(tmp);
            for(int j=0;j<k;j++)
            {
                tmp.add(new Strin(""));
            }

        }
    }



}

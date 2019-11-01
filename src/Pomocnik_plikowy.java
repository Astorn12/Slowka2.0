import java.io.*;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by osiza on 24.09.2017.
 */
public class Pomocnik_plikowy {
    public static void zczytywanie_z_pliku(String plik, char odstep, List lista, Class clasa) {
        File file= new File(plik);
        FileInputStream test=null;
        FileInputStream istream=null;
        Scanner intest=null;
        Scanner input=null;

      //  Class<?> klasy=null;
        //System.out.println("Plik który próbujemy otworzyć:"+plik+"}");
            int error = 0;
            // FileInputStream test;
            try {

                //  FileInputStream istream= new FileInputStream("\""+plik+"\"");
               // System.out.println(file.canRead());
                char[] tmpt=plik.toCharArray();
                //System.out.println(tmpt[0]+"   zerowa wartosc");

              //  System.out.println(plik.substring(1,plik.length()-1));
                //System.out.println(plik+ "  "+plik.length());
                if(!Character.isLetter(tmpt[0])&&!file.exists())
                {plik=plik.substring(1,plik.length());//jest tutaj beka z niewidzialnym pierwszym znakiem pobranym z początkiem pliku w txt
                    file=new File(plik);

                }

                test = new FileInputStream(plik);
                istream = new FileInputStream(plik);
                intest = new Scanner(test,"UTF-8");
                input = new Scanner(istream,"UTF-8");
                String linia;
                char[] line;
                int licznik_fieldow = 0;
                int licznik_tmp = 0;
                char[] tmp_tab;
                String tmp = "";
                int flaga_przecinka;
                int licznik = 0;


                Class<?> clazz = clasa;
                //  Constructor<?> ctor=clazz.getConstructor();
                Field[] fields;
                 fields=getFields(clasa);



             // if(clazz.equals(WordFromBook.class))
            //  {
              //fields = clazz.getDeclaredFields();
                do {
                    linia = intest.nextLine();
                    line = linia.toCharArray();
                    for (int i = 0; i < line.length; i++) {
                        if (line[i] == odstep) licznik_fieldow++;
                    }

                    if ((licznik_fieldow + 1) != fields.length) error = -1;
                    licznik_fieldow = 0;
                } while (intest.hasNextLine());
                test.close();
                //////////////////////////////////////


                do {

                    Object karta = clasa.newInstance();
                    linia = input.nextLine();

                    line = linia.toCharArray();
                    licznik = 0;


                    for (int i = 0; i < fields.length; i++) {


                        while (licznik < line.length && line[licznik] != odstep) {
                            if(line[licznik]!='\uFEFF') {
                                tmp = tmp + line[licznik];
                            }
                           // System.out.println("Pomocnik: "+tmp);
                            licznik++;
                        }
                       // System.out.println("Pomocnik: "+linia);
                        if (fields[i].getGenericType().equals(String.class)) {

                            fields[i].set(karta, tmp);
                        } else if (fields[i].getGenericType() == int.class) {

                            fields[i].setInt(karta, Integer.parseInt(tmp));
                        } else if (fields[i].getGenericType() == int.class) {

                            fields[i].setDouble(karta, Double.parseDouble(tmp));
                        }
                        tmp = "";
                        licznik++;
                    }
                    lista.add(karta);
                    licznik = 0;


                } while (input.hasNextLine());

                test.close();
                input.close();
                intest.close();
                istream.close();


///////////////////////////////////////////////
            } catch (Exception e) {
               // System.out.println(klasy.toString());

                System.out.println("tutaj" + e+" "+plik);
                e.printStackTrace();
                //test.close();
                error = -1;
                try{
                test.close();
                input.close();
                intest.close();
                istream.close();
            }

            catch(Exception et)
            {
            }
            }


    }
    public static void zapisywanie_do_pliku(String plik, char odstep, List lista, Class clasa) {
        DeleteFile(plik);
        CreateFile(plik);
        Class<?> clazz = clasa;
        Field[] fields;
        fields = getFields(clasa);
        //FileWriter fileWriter;
        Writer fileWriter;
        try {
            //fileWriter = new FileWriter(new File(plik), true);
            fileWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(plik), "UTF-8"));


            for (int i = 0; i < lista.size(); i++) {
                if (i != 0) fileWriter.write(System.getProperty("line.separator"));
                for (int j = 0; j < fields.length; j++) {


                    if (fields[j].getGenericType().equals(String.class)) {
                        fileWriter.write((String) (fields[j].get(lista.get(i))));


                    } else if (fields[j].getGenericType() == int.class) {

                        fileWriter.write(((Integer) fields[j].get(lista.get(i))).toString());
                    } else if (fields[j].getGenericType() == double.class) {

                        fileWriter.write(String.valueOf((Double) fields[j].get(lista.get(i))));
                    }
                    if (j < fields.length - 1) fileWriter.write(",");
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Błąd przy zapisie do pliku: "+ plik);
            System.out.println(e);
        }

    }
    public static void CreateFile(String path) {
        try {

            File file = new File(path);

            if (file.createNewFile()) {

            } else {

            }

        } catch (IOException e) {
            e.printStackTrace();//tutaj jest opcja żeby wyrzucać wyjątek na konsolę
        }
    }
    public static void DeleteFile(String path) {
        try {

            File file = new File(path);
            if (file.delete()) {
                System.out.println("udało się usunąć plik");

            } else {
                System.out.println(" nie udało się usunąć pliku");

            }

        } catch (Exception e) {
            System.out.println("Nie udało się usunąć");
            e.printStackTrace();

        }
    }

    private static Field[] getFields(Class clas)
    {
         List<Field> fields=new LinkedList<>();
         for(Field f:clas.getDeclaredFields())
         {
             fields.add(f);
         }

        if(clas.getSuperclass().getPackage()==null)
        {
            getFieldsHelper(clas.getSuperclass(),fields);
        }
        Field[] fieldsTab=new Field[fields.size()];
         for(int i=0;i<fieldsTab.length;i++)
         {
             fieldsTab[i]=fields.get(i);
         }

        return fieldsTab;
    }
    private static void getFieldsHelper(Class clas, List<Field> fields)
    {
        int i=0;
        for(Field f:clas.getDeclaredFields())
        {

            fields.add(i,f);
        i++;
        }
        if(clas.getSuperclass().getPackage()==null)
        {
            getFieldsHelper(clas.getSuperclass(),fields);
        }
    }

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}

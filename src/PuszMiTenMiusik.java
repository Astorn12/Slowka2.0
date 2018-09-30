import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by osiza on 02.03.2018.
 */
public class PuszMiTenMiusik extends Thread {
    private volatile boolean running=true;
    private String nazwaKlipu;
    Player player;
    PuszMiTenMiusik(String nazwaKlipu)
    {
        this.nazwaKlipu=nazwaKlipu;
    }
    @Override
    public void run() {
        while(running){
            try (FileInputStream fis = new FileInputStream(this.nazwaKlipu)) {
                //new Player(fis).play();
                player=new Player(fis);
                player.play();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }

            player.close();
            running = false;
        }
    }

    public void terminate() {

        player.close();
        running = false;

    }



}


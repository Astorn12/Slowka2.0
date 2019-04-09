import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.List;

/**
 * Created by osiza on 30.09.2018.
 */
public abstract class Grama extends Plik {




    abstract public void createShowingJPanel(JPanel panelRodzajuPracy);
    abstract public void createCreatingJPanel(JPanel panelRodzajuPracy);
    @Override
    public abstract void zczytywanie();
    @Override
    public abstract void zapis_zmian();
    @Override
    public abstract void wypisanie_listy();
    @Override
    public abstract List get_lista();
    @Override
    public abstract Grama clone();

}

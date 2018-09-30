import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by osiza on 22.10.2017.
 */
public class PopClickPlikListener extends MouseAdapter {


    public void mousePressed(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    public void mouseReleased(MouseEvent e){
        if (e.isPopupTrigger())
            doPop(e);
    }

    private void doPop(MouseEvent e){
        RozwijaneMenuPliku menu = new RozwijaneMenuPliku();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
}

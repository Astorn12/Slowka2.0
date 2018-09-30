import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by osiza on 22.10.2017.
 */
public class RozwijaneMenuPliku extends JPopupMenu {
    JMenuItem listaSlowek;
    JMenuItem test;
    JMenuItem wpisywanie;

    RozwijaneMenuPliku()
    {

        listaSlowek= new JMenuItem("Lista słówek");
        test= new JMenuItem("Test");
        wpisywanie= new JMenuItem("Wpisywanie");
        listaSlowek.addMouseListener(new MouseListener() {
                                         @Override
                                         public void mouseClicked(MouseEvent e) {

                                         }

                                         @Override
                                         public void mousePressed(MouseEvent e) {
                                             System.out.println("jestem tutaj");
                                         }

                                         @Override
                                         public void mouseReleased(MouseEvent e) {

                                         }

                                         @Override
                                         public void mouseEntered(MouseEvent e) {

                                         }

                                         @Override
                                         public void mouseExited(MouseEvent e) {

                                         }
                                     }
        );
        add(listaSlowek);
        add(wpisywanie);
        add(test);
        listaSlowek.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        wpisywanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
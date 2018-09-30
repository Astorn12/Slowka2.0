import javax.swing.*;
import java.awt.*;

/**
 * Created by osiza on 20.02.2018.
 */

    public class DrawPanel extends JPanel {

        /**
         *
         */
        private static final long serialVersionUID = 4949248244138855737L;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.RED);
            g.drawRect(10, 10, getWidth()/2, getHeight()/2);
            g.setColor(Color.YELLOW);
            g.fillRect(11, 11, getWidth()/2-1, getHeight()/2-1);

            g.draw3DRect(10, getHeight()/2+20, 600, 280, true);

        }


    }


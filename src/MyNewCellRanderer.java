import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.regex.Pattern;

/**
 * Created by osiza on 21.11.2017.
 */
public class MyNewCellRanderer extends DefaultTreeCellRenderer {


    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, exp, leaf, row, hasFocus);

        // Assuming you have a tree of Strings
        String node = (String) ((DefaultMutableTreeNode) value).getUserObject();

        // If the node is a leaf and ends with "xxx"
        //if () {
            // Paint the node in blue

        Rectangle rectangle= new Rectangle();
        JButton jButton= new JButton("Robić");
        jButton.setBackground(Color.GREEN);
        return jButton;
    }
}

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TreeBackground extends JTree
{
    public TreeBackground(DefaultMutableTreeNode defaultMutableTreeNode)
    {
        JTree tree = new JTree(defaultMutableTreeNode)
        {
            BufferedImage image = loadImage();

            protected void paintComponent(Graphics g)
            {
                int w = getWidth();
                int h = getHeight();
                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();
                int x = (w - imageWidth)/2;      // to center image
                int y = (h - imageHeight)/2;     //   in background
                g.drawImage(image, x, y, this);  // render image
                super.paintComponent(g);         // render the tree
            }
        };
        tree.setOpaque(false);
        Color bgColor = UIManager.getColor("Panel.background");
        DefaultTreeCellRenderer renderer =
                (DefaultTreeCellRenderer)tree.getCellRenderer();
        renderer.setBackgroundNonSelectionColor(bgColor);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(tree);
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }

    private DefaultTreeModel getTreeModel()
    {
        String[] birds = {
                // branches |<-- child leaf nodes -->|
                "hawks", "gray", "red-tailed", "rough-legged",
                "falcons", "harrier", "kestrel", "kite",
                "owls", "barred", "saw-whet", "snowy"
        };
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("birds");
        DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[birds.length];
        for(int j = 0; j < nodes.length; j++)
            nodes[j] = new DefaultMutableTreeNode(birds[j]);
        for(int j = 0; j < 9; j += 4)
        {
            root.insert(nodes[j], j % 3);
            for(int k = j + 1; k < j + 4; k++)
                nodes[j].insert(nodes[k], k - j - 1);
        }
        return new DefaultTreeModel(root);
    }

    private BufferedImage loadImage()
    {
        String fileName = "Pictures/BigBen.jpg";
        BufferedImage image = null;
        try
        {
            URL url = getClass().getResource(fileName);
            image = ImageIO.read(url);
        }
        catch(MalformedURLException mue)
        {
            System.err.println("url: " + mue.getMessage());
        }
        catch(IOException ioe)
        {
            System.err.println("read: " + ioe.getMessage());
        }
        return image;
    }

   /* public static void main(String[] args)
    {
        new TreeBackground();
    }*/
}
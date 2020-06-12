package Cricket;
import java.awt.BorderLayout;

import javax.swing.*;

public class CricketImage extends JFrame {
	
    
       public CricketImage()
      {
       

         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         setTitle("Cricket the Gentlemen's game");

         ImageIcon image = new ImageIcon("//Users/xbbl4xf/Pictures/Cricket.jpeg");
         JLabel label = new JLabel(image);
         JScrollPane scrollPane = new JScrollPane(label);
         scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         add(scrollPane, BorderLayout.CENTER);
         pack();
      }

    
    }


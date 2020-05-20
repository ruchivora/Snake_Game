
package snake_game;

import java.awt.Color;
import javax.swing.JFrame;

public class Snake_Game {

    public static void main(String[] args) {
      
        JFrame frame = new JFrame() ;
        GamePlay gameplay = new GamePlay() ;
        frame.setBounds(10,10,905,700);
        
        frame.setResizable(false);
        //frame.setLocation(350,50);
        frame.setVisible(true);
        frame.setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(gameplay) ;
        
    }
    
}

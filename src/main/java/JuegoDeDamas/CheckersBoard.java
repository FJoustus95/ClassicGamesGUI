
package JuegoDeDamas;


import java.awt.*;
import javax.swing.*;

public class CheckersBoard extends JFrame {
    
 
     public CheckersBoard() {
         
         
     }
 
         
    
    
    public static void main(String [] args) {
      JFrame frame1 = new JFrame("Bienvenido a Shovel Damas"); 
      JFrame frame2 = new JFrame();
      
      frame1.setBounds(10, 10, 790, 537);
     
      JPanel panel1 = new JPanel(){
        
       
          
        @Override
        public void paint(Graphics g) {
  
            
        boolean yellow = true;
        
        Color goldShovel = new Color(254,238,204);
        Color cyanShovel = new Color(0,116,160);
        
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (yellow) {
                   g.setColor(goldShovel);
                          
                }else{
                   g.setColor(cyanShovel);
                    
                }
                g.fillRect(x*59, y*58, 59, 58);
                    yellow=!yellow;
            }
            yellow =!yellow;
            
        }
        
        
    }
      
   
    
    
    };
      frame1.add(panel1);
      frame1.setDefaultCloseOperation(3);
      
      frame1.setVisible(true);
    
}

   
}

package JuegoDeDamas;

import javax.swing.*;


//ejemplo para poner un jpanel con un background (imagen)
public class exampleBackground extends JFrame {
    
    JPanel jPanel = new JPanel();
    JLabel jLabel = new JLabel();
    
    public exampleBackground() {
        
        setTitle("exampleBackground");
        setVisible(true);
        setSize(790, 537);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        jLabel.setIcon(new ImageIcon("C:\\Users\\U\\Documents\\NetBeansProjects\\JuegosClasicos\\src\\main\\resources\\shovelBackground.png"));
        jPanel.add(jLabel);
        add(jPanel);
        
        validate();
    }
    public static void main(String[] args) {
        exampleBackground t = new exampleBackground();
        t.setVisible(true);
        
        JOptionPane.showInputDialog(null, "This is a example" , "don´t forget to say him the truth", 1);
    }
}

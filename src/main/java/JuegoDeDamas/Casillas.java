
package JuegoDeDamas;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Dimension;



public  abstract class Casillas extends JButton {
    
    private int posX, posY;

    public Casillas(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
 
    public Casillas() {}
    
    
    public void updatedPosition(int x, int y) {
        this.preferredSize(new Dimension(59,58));
        this.setBounds(x, y, 58, 59);
        
        
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public abstract void setNewColor(Color color);
    public abstract void updateColor();
    
}

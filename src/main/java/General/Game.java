
package General;
import java.io.*;

public class Game implements Serializable {
    private String nameOfGame;

    public Game(String nameOfGame) {
        this.nameOfGame = nameOfGame;
    }

    public boolean equals (Game game) {
        return (this.nameOfGame.equals(game.nameOfGame));
            
            
        
        
    }
    
    public String getNameOfGame() {
        return nameOfGame;
    }

    public void setNameOfGame(String nameOfGame) {
        this.nameOfGame = nameOfGame;
    }
    



}

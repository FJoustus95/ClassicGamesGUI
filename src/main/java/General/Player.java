
package General;

import java.io.*;

public class Player implements Serializable {
    private String name;
    private String surname;
    private Game game;
    private int numerOfPlayer;

    public Player(String name, String surname, Game game, int numerOfPlayer) {
        this.name = name;
        this.surname = surname;
        this.game = game;
        this.numerOfPlayer = numerOfPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getNumerOfPlayer() {
        return numerOfPlayer;
    }

    public void setNumerOfPlayer(int numerOfPlayer) {
        this.numerOfPlayer = numerOfPlayer;
    }
    
    
    
    
    
    
    
}


package JuegoDeDamas;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public final class Damas extends JPanel implements ActionListener, MouseListener {
	private static final long VersionSerial = 1L; 
        
        //parámetros cuadrados. Optimizado para cualquier resolución 
	public static int ancho = 450, alto = 550; 
        
        //8 fichas para tablero de damas
	public static final int tamañoMosaico = ancho/8; 
	public static final int numTilesPerRow = ancho/tamañoMosaico;
        
        //tablero de 8 x 8
	public static int[][] baseGameData = new int[numTilesPerRow][numTilesPerRow]; 
        
        //almacenamiento de la piezas en 8 x 8
	public static int[][] gameData = new int[numTilesPerRow][numTilesPerRow];
        
        //Valores para los datos del juego
        
	public static final int EMPTY = 0, RED = 1, RED_KING = 2, BLACK = 3, BLACK_KING = 4; 
        public static JFrame background;
	public boolean gameInProgress = true;
	public int currentPlayer = RED;
	
        //proceso de funcion de movimiento
        public boolean inPlay = false; 
	
        //Almacena jugadas disponibles en 8 x 8
        public static int[][] availablePlays = new int[numTilesPerRow][numTilesPerRow];
	public int storedRow, storedCol;
	public boolean isJump = false;
	static BufferedImage crownImage = null;

	public static void main(String[] args){
		
            
            try {
			crownImage = ImageIO.read(new File("crown.png"));
		} catch (IOException e) {
                    // Bloqueo autogenerado

		}
		new Damas();
	}
	
	public Damas(){
            
		window(ancho, alto, this);
		initializeBoard();
                // Esto está incluido en la JVM. Ejecuta pintar
		repaint(); 
	}
	
        //Envoltura para el gameOver Interno
	public boolean gameOver(){ 
		return gameOverInternal(0, 0, 0, 0);
	}
	
        //practica recursiva
	public boolean gameOverInternal(int col, int row, int cyan, int white){ 
		if(gameData[col][row] == RED || gameData[col][row] == RED_KING)
			cyan += 1;
		if(gameData[col][row] == BLACK || gameData[col][row] == BLACK_KING)
			white += 1;
		if(col == numTilesPerRow-1 && row == numTilesPerRow-1){
			if(cyan  == 0 || white == 0)
				return true;
			else return false;
		}
		if(col == numTilesPerRow-1){
			row += 1;
			col = -1;
		}
		return gameOverInternal(col+1, row, cyan, white);
	}
        public class exampleBackground extends JFrame {
    
    JPanel jPanel = new JPanel();
    JLabel jLabel = new JLabel();
    
   
    
        
        
}
            
        
	//Dibujar el JFrame y agregar la funcion de Salida
	public void window(int width, int height, Damas game){ 
		
                JFrame background = new JFrame();
                
                background.setTitle("Juego de shovel Damas");
                background.setVisible(true);
		background.setSize(790, 537);
		
		background.setLocationRelativeTo(null);
		background.pack();
		Insets insets = background.getInsets();
		int frameLeftBorder = insets.left;
		int frameRightBorder = insets.right;
		int frameTopBorder = insets.top;
		int frameBottomBorder = insets.bottom;
		background.setPreferredSize(new Dimension(790 + frameLeftBorder + frameRightBorder, 537 + frameBottomBorder + frameTopBorder));
		background.setMaximumSize(new Dimension(790 + frameLeftBorder + frameRightBorder, 537 + frameBottomBorder + frameTopBorder));
		background.setMinimumSize(new Dimension(790 + frameLeftBorder + frameRightBorder, 537 + frameBottomBorder + frameTopBorder));
		
		background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                
		background.addMouseListener(this);
		background.requestFocus();
		
	
                background.add(game);
                background.setLocationRelativeTo((Component)null);
               
        }
	
       
	public void initializeBoard(){
		//Actualizar las posiciones de Inicio
				for(int col=0; col < (numTilesPerRow); col+=2){
					gameData[col][5] = RED;
					gameData[col][7] = RED;
				}
				for(int col=1; col < (numTilesPerRow); col+=2)
					gameData[col][6] = RED;
				for(int col=1; col < (numTilesPerRow); col+=2){
					gameData[col][0] = BLACK;
					gameData[col][2] = BLACK;
				}	
				for(int col=0; col < (numTilesPerRow); col+=2)
					gameData[col][1] = BLACK;
	}
	//metodo que dibuja las piezas
	public static void drawPiece(int col, int row, Graphics g, Color color){
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setColor(color);
		
                // Estos valores 2 y 4 son valores arbitrarios que compensan un tamaño de pieza más pequeño
		g.fillOval((col*tamañoMosaico)+2, (row*tamañoMosaico)+2, tamañoMosaico-4, tamañoMosaico-4);
	}
	
	public void paint(Graphics g){ // Metodo que pinta el tablero
            
		//Imprimir el tablero y las piezas
		super.paintComponent(g);
                
                Color goldShovel = new Color(254,238,204);
                Color cyanShovel = new Color(0,116,160);
		
                for(int row = 0; row < numTilesPerRow; row++){
			for(int col = 0; col < numTilesPerRow; col++){
				if((row%2 == 0 && col%2 == 0) || (row%2 != 0 && col%2 != 0)){ // Esto asigna el patrón del tablero
					baseGameData[col][row] = 0;
					g.setColor(goldShovel);
					g.fillRect(col*tamañoMosaico, row*tamañoMosaico, tamañoMosaico, tamañoMosaico);
				}
				else{
					baseGameData[col][row] = 1;
					g.setColor(cyanShovel);
					g.fillRect(col*tamañoMosaico, row*tamañoMosaico, tamañoMosaico, tamañoMosaico);
				}
				if(checkTeamPiece(col, row) ==  true){			
					g.setColor(cyanShovel);
					g.fillRect(col*tamañoMosaico, row*tamañoMosaico, tamañoMosaico, tamañoMosaico);
				}
				if(availablePlays[col][row] == 1){
		 		g.setColor(Color.MAGENTA.darker());
					g.fillRect(col*tamañoMosaico, row*tamañoMosaico, tamañoMosaico, tamañoMosaico);
				}
				if(gameData[col][row] == BLACK)
					drawPiece(col, row, g, Color.black);
				else if(gameData[col][row] == BLACK_KING){
					drawPiece(col, row, g, Color.black);
					g.drawImage(crownImage, (col*tamañoMosaico)+6, (row*tamañoMosaico)+6, tamañoMosaico-12, tamañoMosaico-12, null);
				}
				else if(gameData[col][row] == RED)
					drawPiece(col, row, g, Color.red);
				else if(gameData[col][row] == RED_KING){
					drawPiece(col, row, g, Color.red);
				g.drawImage(crownImage, (col*tamañoMosaico)+6, (row*tamañoMosaico)+6, tamañoMosaico-12, tamañoMosaico-12, null);
				}
			}
		}
		if(gameOver() == true)
		
                    
                    this.gameOverDisplay(g);
        
        }
            
            
        
        //Lanza el mensaje de game Over
	public void gameOverDisplay(Graphics g) { 
		 String msg = "Game Over";
	     Font small = new Font("Helvetica", Font.BOLD, 40);
	     FontMetrics metr = getFontMetrics(small);
	     g.setColor(Color.black);
	     g.setFont(small);
	     g.drawString(msg, (ancho - metr.stringWidth(msg)) / 2, ancho / 2);
	}
	//gameOverDisplay(g);
        //metodo para resetear el Juego
	public void resetPlay(){
		storedCol = 0;
		storedRow = 0;
		inPlay = false;
		isJump = false;
		for(int row = 0; row < numTilesPerRow; row++){
			for(int col = 0; col < numTilesPerRow; col++){
				availablePlays[col][row] = 0;
			}
		}
		repaint();
	}
	//metodo que detecta el click del mouse
        @Override
	public void mousePressed(java.awt.event.MouseEvent evt) {
    	
            // 8 es la longitud del marco izquierdo
            int col = (evt.getX()-8) / tamañoMosaico; 
        
            // 30 es la longitud del marco superior
            int row = (evt.getY()-30) / tamañoMosaico; 
		if(inPlay == false && gameData[col][row] != 0 || inPlay == true && checkTeamPiece(col, row) == true){
			resetPlay();
			storedCol = col;
			
                        //Establece el clic actual en variables de instancia que se utilizarán en otros lugares
                        storedRow = row;
			getAvailablePlays(col, row);
		}
		else if(inPlay == true && availablePlays[col][row] == 1){
			hacerMovimiento(col, row, storedCol, storedRow);
		}
		else if(inPlay == true && availablePlays[col][row] == 0){
			resetPlay();
		}
	}
	//funcion que nos permite cambiar entre el jugador 1 y el jugador 2
	public void intercambiarPlayer(){
		if(currentPlayer == RED)
			currentPlayer = BLACK;
		else currentPlayer = RED;
	}
	
        //funcion que emula el movimiento de las fichas
	public void hacerMovimiento(int col, int row, int storedCol, int storedRow){
		
                //cambia las piezas para un nueva teja
                int x = gameData[storedCol][storedRow]; 
		gameData[col][row] = x;
		
                //cambia la localizacion de la pieza antigua a un espacio vacio
                gameData[storedCol][storedRow] = EMPTY; 
		checkKing(col, row);
		if(isJump == true)
			removePiece(col, row, storedCol, storedRow);
		resetPlay();
		intercambiarPlayer();
	}
	
        //funcion que detecta cuando una ficha se convierte en una ficha reina
	public boolean isKing(int col, int row){
		if(gameData[col][row] == RED_KING || gameData[col][row] == BLACK_KING){
			return true;
		}
		else return false;
	}
	
	public int checkOpponent(int col, int row){
		if(gameData[col][row] == RED || gameData[col][row] ==  RED_KING)
			return BLACK;
		else
			return RED;
	}
	
	public void checkExtraJumps(int col, int row){
		int opponent = checkOpponent(col, row);
		int opponentKing = checkOpponent(col, row) + 1;
		if(gameData[col-1][row-1] == opponent || gameData[col-1][row-1] == opponentKing){
			availablePlays[col-1][row-1] = 1;
		}
		else if(gameData[col+1][row-1] == opponent || gameData[col+1][row-1] == opponentKing){
			availablePlays[col+1][row-1] = 1;
		}
		else if(gameData[col-1][row+1] == opponent || gameData[col-1][row+1] == opponentKing){
			availablePlays[col-1][row+1] = 1;
		}
		else if(gameData[col+1][row+1] == opponent || gameData[col+1][row+1] == opponentKing){
			availablePlays[col+1][row+1] = 1;
		}
		repaint();
	}
	
	public void checkKing(int col, int row){
		if(gameData[col][row] == RED && row == 0)
			gameData[col][row] = RED_KING;
		else if(gameData[col][row] == BLACK && row == numTilesPerRow-1)
			gameData[col][row] = BLACK_KING;
		else return;
	}
	
	public void removePiece(int col, int row, int storedCol, int storedRow){ //might be a better way to do this, but detects position of opponent piece based on destination and original position
		int pieceRow = -1; 
		int pieceCol = -1;
		if(col > storedCol && row > storedRow){
			pieceRow = row-1;
			pieceCol = col-1;
		}
		if(col > storedCol && row < storedRow){
			pieceRow = row+1;
			pieceCol = col-1;
		}
		if(col < storedCol && row > storedRow){
			pieceRow = row-1;
			pieceCol = col+1;
		}
		if(col < storedCol && row < storedRow){
			pieceRow = row+1;
			pieceCol = col+1;
		}
		gameData[pieceCol][pieceRow] = EMPTY;
	}//TODO REWRITE
	
	public void getAvailablePlays(int col, int row){
		inPlay = true;
		if((checkTeamPiece(col, row) == true)){ //checks if the piece is assigned to the current player
			if(gameData[col][row] == RED){  // only goes north, checks the row above it's own
				getUp(col, row);
			}
			if(gameData[col][row] == BLACK){ // only goes south, checks the row below it's own
				getDown(col, row);
			}
			if(gameData[col][row] == RED_KING || gameData[col][row] == BLACK_KING){ // Goes up OR down 1 row below it's own
				getUp(col, row);
			  //getUp(col, row);
				getDown(col, row); // GET UP GET UP AND GET DOWN
			}
		repaint();
		}
	}
	
	public void getUp(int col, int row){ // Get Up availability
		int rowUp = row-1;
		if(col == 0 && row != 0){ //X=0, Y is not 0
			for(int i = col; i < col+2; i++){ //check to right
				if(gameData[col][row] != 0 && gameData[i][rowUp] != 0){
					if(canJump(col, row, i, rowUp) == true){
						int jumpCol = getJumpPos(col, row, i, rowUp)[0];
						int jumpRow = getJumpPos(col, row, i, rowUp)[1];
						availablePlays[jumpCol][jumpRow] = 1;
					}
				}
				else if(baseGameData[i][rowUp] == 1 && gameData[i][rowUp] == 0)
					availablePlays[i][rowUp] = 1;
			}
		}
		else if(col == (numTilesPerRow - 1) && row != 0){ //X=max, Y is not 0
				if(gameData[col][row] != 0 && gameData[col-1][rowUp] != 0){
					if(canJump(col, row, col-1, rowUp) == true){
						int jumpCol = getJumpPos(col, row, col-1, rowUp)[0];
						int jumpRow = getJumpPos(col, row, col-1, rowUp)[1];
						availablePlays[jumpCol][jumpRow] = 1;
					}
				}
				else if(baseGameData[col-1][rowUp] == 1 && gameData[col-1][rowUp] == 0)
					availablePlays[col-1][rowUp] = 1;
		}
		else if(col != numTilesPerRow - 1 && col != 0 && row != 0){
			for(int i = col-1; i <= col+1; i++){
				if(gameData[col][row] != 0 && gameData[i][rowUp] != 0){
					if(canJump(col, row, i, rowUp) == true){
						int jumpCol = getJumpPos(col, row, i, rowUp)[0];
						int jumpRow = getJumpPos(col, row, i, rowUp)[1];
						availablePlays[jumpCol][jumpRow] = 1;
					}
				}
				else if(baseGameData[i][rowUp] == 1 && gameData[i][rowUp] == 0)
					availablePlays[i][rowUp] = 1;
			}
		}
	}
	
	public void getDown(int col, int row){
		int rowDown = row+1;
		if(col == 0 && row != numTilesPerRow-1){
				if(gameData[col][row] != 0 && gameData[col+1][rowDown] != 0){
					if(canJump(col, row, col+1, rowDown) == true){
						int jumpCol = getJumpPos(col, row, col+1, rowDown)[0];
						int jumpRow = getJumpPos(col, row, col+1, rowDown)[1];
						availablePlays[jumpCol][jumpRow] = 1;
					}
				}
				else if(baseGameData[col+1][rowDown] == 1 && gameData[col+1][rowDown] == 0)
					availablePlays[col+1][rowDown] = 1;
		}
		else if(col == numTilesPerRow - 1 && row != numTilesPerRow-1){
				if(gameData[col][row] != 0 && gameData[col-1][rowDown] != 0){
					if(canJump(col, row, col-1, rowDown) == true){
						int jumpCol = getJumpPos(col, row, col-1, rowDown)[0];
						int jumpRow = getJumpPos(col, row, col-1, rowDown)[1];
						availablePlays[jumpCol][jumpRow] = 1;
					}
				}
				else if(baseGameData[col-1][rowDown] == 1 && gameData[col-1][rowDown] == 0)
					availablePlays[col-1][rowDown] = 1;
		}
		else if(col != numTilesPerRow-1 && col != 0 && row != numTilesPerRow-1){
			for(int i = col-1; i <= col+1; i++){
				if(gameData[col][row] != 0 && gameData[i][rowDown] != 0){
					if(canJump(col, row, i, rowDown) == true){
						int jumpCol = getJumpPos(col, row, i, rowDown)[0];
						int jumpRow = getJumpPos(col, row, i, rowDown)[1];
						availablePlays[jumpCol][jumpRow] = 1;
					}
				}
				else if(baseGameData[i][rowDown] == 1 && gameData[i][rowDown] == 0)
					availablePlays[i][rowDown] = 1;
			}
		}
	}
	
	public boolean checkTeamPiece(int col, int row){
		if(currentPlayer == RED && (gameData[col][row] == RED || gameData[col][row] == RED_KING)) //bottom
			return true;
		if(currentPlayer == BLACK && (gameData[col][row] == BLACK || gameData[col][row] == BLACK_KING)) //top
			return true;
		else
			return false;
	}
	
	public boolean isLegalPos(int col, int row){
		if(row < 0 || row >= numTilesPerRow || col < 0 || col >= numTilesPerRow)
			return false;
		else return true;
	}
	
	public boolean canJump(int col, int row, int opponentCol, int opponentRow){
		//Steps for checking if canJump is true: determine piece within movement. Then check if its an opponent piece, then if the space behind it is empty
		//and in bounds
		// 4 conditions based on column and row relations to the other piece
		if(((gameData[col][row] == BLACK || gameData[col][row] == BLACK_KING) && (gameData[opponentCol][opponentRow] == RED || gameData[opponentCol][opponentRow] == RED_KING)) || (gameData[col][row] == RED || gameData[col][row] == RED_KING) && (gameData[opponentCol][opponentRow] == BLACK || gameData[opponentCol][opponentRow] == BLACK_KING)){ 
			//If the piece is white/red and opponent piece is opposite TODO fix this if. It's so ugly
			if(opponentCol == 0 || opponentCol == numTilesPerRow-1 || opponentRow == 0 || opponentRow == numTilesPerRow-1)
				return false;
			int[] toData = getJumpPos(col, row, opponentCol, opponentRow);
		    if(isLegalPos(toData[0],toData[1]) == false) //check board outofbounds
		        return false;
		    if(gameData[toData[0]][toData[1]] == 0){
		    	isJump = true;
		    	return true;
		    }
		}
		return false;
	}
	
	public int[] getJumpPos(int col, int row, int opponentCol, int opponentRow){
		if(col > opponentCol && row > opponentRow && gameData[col-2][row-2] == 0)
			return new int[] {col-2, row-2};
		else if(col > opponentCol && row < opponentRow && gameData[col-2][row+2] == 0)
			return new int[] {col-2, row+2};
		else if(col < opponentCol && row > opponentRow && gameData[col+2][row-2] == 0)
			return new int[] {col+2, row-2};
		else
			return new int[] {col+2, row+2};
	}

	// Methods that must be included for some reason? WHY
        @Override
	public void mouseClicked(MouseEvent e) {}
        @Override
	public void mouseReleased(MouseEvent e) {}
        @Override
	public void mouseEntered(MouseEvent e) {}
        @Override
	public void mouseExited(MouseEvent e) {}
        @Override
	public void actionPerformed(ActionEvent e) {}
}

package logic;

import characterlogic.*;
import java.util.Random;

public class EscapeGameLogic {

    private int size;
    private Cell board[][];
    public Player player;
    public Hunter hunter1, hunter2;
    public Mine mine;
    private boolean isPlayerDead = false;
    private int numberOfHunters = 2;
    private Random r;
    private final int maxMine = 6;
    private final int minMine = 3;
    private int numberOfMines;

    public EscapeGameLogic(int size) {
        this.size = size;
        board = new Cell[size][size];
        r = new Random();
        numberOfMines = r.nextInt(maxMine - minMine + 1) + minMine;
        newGame();
    }

    private void newGame() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                board[i][j] = new Cell(i, j);
                board[i][j].setGameCharacter(null);
            }
        }

        player = new Player(0, Math.round(size / 2));
        hunter1 = new Hunter(size - 1, 0);
        hunter2 = new Hunter(size - 1, size - 1);

        board[0][Math.round(size / 2)].setGameCharacter(player);
        board[size - 1][0].setGameCharacter(hunter1);
        board[size - 1][size - 1].setGameCharacter(hunter2);

        for (int i = 1; i <= numberOfMines; i++) {
            boolean used = true;
            while (used) {
                int x = r.nextInt(size);
                int y = r.nextInt(size);
                if (board[x][y].getGameCharacter() == null) {
                    used = false;
                    mine = new Mine(x, y);
                    board[x][y].setGameCharacter(mine);
                }
            }
        }
    }

    public void move(int xPos, int yPos) {
        if (player.canMove(xPos, yPos, size)) {
            board[player.getXPos()][player.getYPos()].setGameCharacter(null);
            if ((board[xPos][yPos].getGameCharacter() instanceof Hunter) || (board[xPos][yPos].getGameCharacter() instanceof Mine)) {
                isPlayerDead = true;

            } else {
                board[xPos][yPos].setGameCharacter(player);
                player.move(xPos, yPos);


                if (hunter1.getIsActive()) {
                    board[hunter1.getXPos()][hunter1.getYPos()].setGameCharacter(null);
                    hunter1.move(xPos, yPos);
                    if (board[hunter1.getXPos()][hunter1.getYPos()].getGameCharacter() instanceof Player) {
                        isPlayerDead = true;
                        board[hunter1.getXPos()][hunter1.getYPos()].setGameCharacter(hunter1);
                    } else if (board[hunter1.getXPos()][hunter1.getYPos()].getGameCharacter() instanceof Mine) {
                        board[hunter1.getXPos()][hunter1.getYPos()].setGameCharacter(null);
                        hunter1.deactivate();
                        numberOfHunters--;
                    } else {
                        board[hunter1.getXPos()][hunter1.getYPos()].setGameCharacter(hunter1);
                    }
                }
                
                if (hunter2.getIsActive()) {
                    board[hunter2.getXPos()][hunter2.getYPos()].setGameCharacter(null);
                    hunter2.move(xPos, yPos);
                    if (board[hunter2.getXPos()][hunter2.getYPos()].getGameCharacter() instanceof Player) {
                        isPlayerDead = true;
                        board[hunter2.getXPos()][hunter2.getYPos()].setGameCharacter(hunter2);
                    } else if (board[hunter2.getXPos()][hunter2.getYPos()].getGameCharacter() instanceof Mine) {
                        board[hunter2.getXPos()][hunter2.getYPos()].setGameCharacter(null);
                        hunter2.deactivate();
                        numberOfHunters--;
                    } else {
                        board[hunter2.getXPos()][hunter2.getYPos()].setGameCharacter(hunter2);
                    }
                }





            }
        }

    }

    public boolean getIsPlayerDead() {
        return isPlayerDead;
    }

    public Cell getBoardAt(int xPos, int yPos) {
        return board[xPos][yPos];
    }
    
    public boolean getAreHuntersDead() {
        return (numberOfHunters==0);
    }
}

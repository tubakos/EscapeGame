/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;


import characterlogic.*;

public class Cell {
    private GameCharacter character;
    private final int xPos;
    private final int yPos;
    
    public Cell(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        character = null;
    }
    
    public void setGameCharacter(GameCharacter character) {
        this.character = character;
    }
    
    public GameCharacter getGameCharacter() {
        return character;
    }
}

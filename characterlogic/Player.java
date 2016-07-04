/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package characterlogic;


public class Player extends GameCharacter{
    public Player (int XPos, int YPos) {
        super(XPos, YPos);
    }
    
    public boolean canMove(int XPos, int YPos, int size) {
        if (XPos>=0 && XPos<size && YPos>=0 && YPos<size) {
            return true;
        }
        else return false;
    }
    
    
      public void move(int xPos, int yPos) {
        setXPos(xPos);
        setYPos(yPos);
    }
}

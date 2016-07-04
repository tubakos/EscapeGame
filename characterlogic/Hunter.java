/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package characterlogic;


public class Hunter extends GameCharacter {
    
    private boolean isActive = true;
    
    public Hunter(int xPos, int yPos) {
        super(xPos, yPos);
    }
    
    public void deactivate() {
        isActive = false;
    }

    public boolean getIsActive() {
        return isActive;
    }
    
    public void move(int playerXPos, int playerYPos) {
        if (Math.abs(getXPos() - playerXPos) >= Math.abs(getYPos() - playerYPos)) {
            if (playerXPos < getXPos()) {
                setXPos(getXPos() - 1);
            } else {
                setXPos(getXPos() + 1);
            }
        } else if (Math.abs(getXPos() - playerXPos) < Math.abs(getYPos() - playerYPos)) {
            if (playerYPos < getYPos()) {
                setYPos(getYPos() - 1);
            } else {
                setYPos(getYPos() + 1);
            }
        }
    }
}

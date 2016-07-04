
package characterlogic;


public abstract class GameCharacter {
    private int xPos;
    private int yPos;
    
    public GameCharacter(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
   
    
    public int getXPos() {
        return xPos;
    }
    
    public int getYPos() {
        return yPos;
    }
    
    public void setXPos(int xPos) {
        this.xPos = xPos; 
    }
    
    public void setYPos(int yPos) {
        this.yPos = yPos; 
    }
}

package frame;

import javax.swing.JButton;

class GameButton extends JButton {

    private final int xPos, yPos;
   
    public GameButton(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}


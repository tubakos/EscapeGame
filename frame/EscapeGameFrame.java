/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import logic.EscapeGameLogic;
import characterlogic.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;


public class EscapeGameFrame extends JFrame {

    private EscapeGameLogic logic;
    private int size;
    private JPanel buttonPanel; 

    public EscapeGameFrame() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Menekülj!");
        this.setLayout(new BorderLayout());
        this.setLocation(500, 50);
        this.setResizable(false);
        size = 10;
        logic = new EscapeGameLogic(size);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);
        JMenu newGameMenu = new JMenu("Új játék");
        menubar.add(newGameMenu);
        newGameMenu.add(new5);
        newGameMenu.add(new10);
        newGameMenu.add(new15);
        
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.CENTER);

        newGame();
        this.pack();
    }

    private void newGame() {
        logic = new EscapeGameLogic(size);
        addButtons();
        redraw();
    }

    private void addButtons() {
        buttonPanel.removeAll();
        buttonPanel.setLayout(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GameButton button = new GameButton(i, j);
                if (logic.getBoardAt(i, j).getGameCharacter() instanceof Player) {
                    button.setBackground(Color.red);
                }
                if (logic.getBoardAt(i, j).getGameCharacter() instanceof Hunter) {
                    button.setBackground(Color.blue);
                }
                if (logic.getBoardAt(i, j).getGameCharacter() instanceof Mine) {
                    button.setBackground(Color.black);
                }
                button.setPreferredSize(new Dimension(40, 40));
                button.setRolloverEnabled(false);
                button.addKeyListener(keyListener);
                buttonPanel.add(button);
            }
        }
        this.pack();
    }

    private void redraw() {
        GameButton button;
        int i = 0, j = 0;
        for (Component component : buttonPanel.getComponents()) {
            if (j == size) {
                j = 0;
                i++;
            }
            button = (GameButton) component;
            if (logic.getBoardAt(i, j).getGameCharacter() instanceof Player) {
                button.setBackground(Color.red);
            } else if (logic.getBoardAt(i, j).getGameCharacter() instanceof Hunter) {
                button.setBackground(Color.blue);
            } else if (logic.getBoardAt(i, j).getGameCharacter() instanceof Mine) {
                button.setBackground(Color.black);
            } else {
                button.setBackground(null);
            }
            j++;
        }

        if (logic.getIsPlayerDead()) {
            JOptionPane.showMessageDialog(this, "A vadászok nyertek!");
            newGame();
        }
        if (logic.getAreHuntersDead()) {
            JOptionPane.showMessageDialog(this, "A játékos nyert!");
            newGame();
        }
    }
    private KeyListener keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            int toX = logic.player.getXPos();
            int toY = logic.player.getYPos();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    toX--;
                    break;
                case KeyEvent.VK_DOWN:
                    toX++;
                    break;
                case KeyEvent.VK_LEFT:
                    toY--;
                    break;
                case KeyEvent.VK_RIGHT:
                    toY++;
                    break;

            }
            logic.move(toX, toY);
            redraw();
        }
    };
    private final Action new5 = new AbstractAction("5x5") {
        @Override
        public void actionPerformed(ActionEvent e) {
            size = 5;
            newGame();
        }
    };
    private final Action new10 = new AbstractAction("10x10") {
        @Override
        public void actionPerformed(ActionEvent e) {
            size = 10;
            newGame();
        }
    };
    private final Action new15 = new AbstractAction("15x15") {
        @Override
        public void actionPerformed(ActionEvent e) {
            size = 15;
            newGame();
        }
    };
}

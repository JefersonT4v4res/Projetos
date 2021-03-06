import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.naming.LinkLoopException;

import javax.swing.JPanel;

public class Game extends JPanel {

    Player p;
    faseOne fOne;
    shot s;

    boolean k_up = false;
    boolean k_down = false;
    boolean k_left = false;
    boolean k_right = false;
    boolean k_shift = false;
    boolean k_shot = false;

    public Game() {

        p = new Player();
        fOne = new faseOne();
        s = new shot();

        setFocusable(true);
        setLayout(null);

        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        k_up = true; break;
                    case KeyEvent.VK_S:
                        k_down = true; break;
                    case KeyEvent.VK_D:
                        k_right = true; break;
                    case KeyEvent.VK_A:
                        k_left = true; break;
                    case KeyEvent.VK_SHIFT:
                        k_shift = true; break;
                    case KeyEvent.VK_K: 
                        k_shot = true; break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        k_up = false; break;
                    case KeyEvent.VK_S:
                        k_down = false; break;
                    case KeyEvent.VK_D:
                        k_right = false; break;
                    case KeyEvent.VK_A:
                        k_left = false; break;
                    case KeyEvent.VK_SHIFT:
                        k_shift = false; break;
                    case KeyEvent.VK_K: 
                        k_shot = false; break;   
                }
            }

        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                gameloop();
            }
        }).start();

    }

    public void gameloop() {
        while (true) {
            handleEvent();
            update();
            render();

            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleEvent() {
        p.handleEvent(k_up, k_down, k_left, k_right, k_shift);
        fOne.handleEvent(k_left, k_right);
        s.handleEvent(k_shot);
    }

    private void update() {
        p.update();
        fOne.update();
        s.update(k_shot);
    }

    private void render() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g2){
        super.paintComponent(g2);
        Graphics2D g = (Graphics2D) g2.create();

        setBackground(Color.BLACK);
        
        fOne.render(g);
        s.render(g);
        p.render(g);

    }

}

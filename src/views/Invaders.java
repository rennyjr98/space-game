package views;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import control.Character;
import control.Enemies;
import control.Bullet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Invaders extends JFrame {
    public Invaders(){
        this.setTitle("Invaders");
        Lienzo lienzo = new Lienzo();
        this.getContentPane().add(lienzo);
        this.addKeyListener(lienzo);
        
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new Invaders();
    }
}


class Lienzo extends JPanel implements Runnable, KeyListener{
    public Lienzo(){
        this.setPreferredSize(new Dimension(600,500));
        this.h = new Thread(this);
        this.ship = new Character(300-(64/2));
        
        for(int i=0;i<enemiesF1.length;i++){
            this.enemiesF1[i] = new Enemies(44*i,20);
            this.enemiesF2[i] = new Enemies(44*i,20+36);
            this.enemiesF3[i] = new Enemies(44*i,20+(36*2));
        }
        
        h.start();
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0,0,600,500);
        
        ship.draw(g2d, this.getHeight());
        
        paintEnemy(enemiesF1,1,g2d);
        paintEnemy(enemiesF2,-1,g2d);
        paintEnemy(enemiesF3,1,g2d);
        
        for(int i=0;i<indexBullet;i++){
            if(bullets[i]!=null){
                bullets[i].move();
                bullets[i].draw(g2d);
                
                if(bullets[i].getY()<=-24){
                    bullets[i] = null;
                }
                
                killEnemy(enemiesF1);
                killEnemy(enemiesF2);
                killEnemy(enemiesF3);
            }
        }
        
        if(timeEnemy>=4) timeEnemy = 0;
    }
    
    public void paintEnemy(Enemies [] enemies, int dir, Graphics2D g2d){
        for(Enemies e: enemies){
            if(e!=null){
                e.move(dir);
                e.draw(g2d);
            }
        }
    }
    
    public void killEnemy(Enemies [] enemies){
        int cb = 0;
        for(int i=0;i<enemies.length;i++){
            for(int j=0;j<bullets.length;j++){
                if(bullets[j]!=null){
                    if(enemies[i]!=null){
                        if(bullets[j].kill(enemies[i].getX(), enemies[i].getY())){
                            bullets[j] = null;
                            enemies[i] = null;
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void run() {
        while(on){
            try{
                timeEnemy++;
                repaint();
                Thread.sleep(20);
            }catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch(ke.getKeyCode()){
            case KeyEvent.VK_LEFT:
                ship.move(-1);
                break;
            case KeyEvent.VK_RIGHT:
                ship.move(1);
                break;
            case KeyEvent.VK_SPACE:
                if(indexBullet<bullets.length)
                    bullets[indexBullet++] = new Bullet(ship.getX()+(32/2)+3,436);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    private boolean on = true;
    private Thread h;
    private Character ship;
    private int timeEnemy = 0;
    private int indexBullet = 0;
    private Bullet []bullets = new Bullet[100];
    private Enemies []enemiesF1 = new Enemies[14];
    private Enemies []enemiesF2 = new Enemies[14];
    private Enemies []enemiesF3 = new Enemies[14];
}
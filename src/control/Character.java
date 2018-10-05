package control;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Character {
    public Character(int x){
        this.x = x;
        this.health = 100;
        
        try{
            ship = ImageIO.read(getClass().getResourceAsStream("/control/space-ship.png"));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void move(int dir){
        if(dir>0 && x+64<= 600 || dir<0 && x>=0)
            this.x += dir*8;
    }
    
    public boolean damage(){
        health -= (health/3);
        return (health<=0);
    }
    
    public void draw(Graphics2D g2d, int height){
        g2d.drawImage(ship, x, height-64, 64, 64, null);
    }
    
    public int getX(){
        return this.x;
    }
    
    private int x;
    private int health;
    private BufferedImage ship;
}

package control;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemies {
    public Enemies(int x, int y){
        this.x = x;
        this.y = y;
        
        try{
            enemy = ImageIO.read(getClass().getResourceAsStream("/control/skull.png"));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void move(int dir){
        x += dir*4;
        if(dir>0 && (x>=600))
            x = -32;
        else if(dir<0 && (x<-32))
            x = 600;
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(enemy, x, y, 32, 32, null);
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    private int x, y;
    private BufferedImage enemy;
}

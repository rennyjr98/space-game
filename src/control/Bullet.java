package control;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bullet {
    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        
        try{
            bullet = ImageIO.read(getClass().getResourceAsStream("/control/bullet.png"));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(bullet, x, y, 24, 24, null);
    }
    
    public void move(){
        y -= 4;
    }
    
    public boolean kill(int xEnemy, int yEnemy){
        if(this.y<=yEnemy && (this.x>=xEnemy && this.x<=xEnemy+32)) return true;
        return false;
    }
    
    public int getY(){
        return this.y;
    }
    
    private int x, y;
    private BufferedImage bullet;
}

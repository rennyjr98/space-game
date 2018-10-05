# Space-Game
Principles of gaming on Java and Graphics2D - Spac-Game

Very simple code to make a game with Java, Graphics2D, Threads and Events.

---

# Graphics on Java

You have to know the basics principles of Graphics on Java to understand what is happening and can use the libraries like: JFrame, JPanel and methods and attributes of this components.

--- 

# Threads

On this case, I used interfaces for make a Thread with 

```
class Lienzo extends JPanel implements Runnable
```
and controlling the draw components with method run where i repaint everything what is on the method paint on the JPanel who is on the JFrame every 20 miliseconds

```
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
```
This is not a tutorial but, you have to have a object Thread on your code for make this works. This only show you how i create this mini-game

---

# Events

To know when user interacts with the application we need Listeners and Events, more specific to get what key on the keyword he type i use KeyEvent with KeyListener is a interfaze and we have to implements

```
class Lienzo extends JPanel implements Runnable, KeyListener {
```

and then access to the information to make fun thinks

```
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
```

# Characters

Use some and easy mathematics to move it to the right or to the left, just think that we have a Cartesian Plane and move somthing with positive number for right and negative number for left. Why? because if the x is 40 and we have to move to de left then add -8 pixels and ta da... x is on 32 now.

```
public void move(int dir){
        if(dir>0 && x+64<= 600 || dir<0 && x>=0)
            this.x += dir*8;
    }
```

The if sentence is to control that the character is not move out of the screen.

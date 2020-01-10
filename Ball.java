import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Math;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Ball{
    boolean pocket;
    Pair position;
    Pair velocity;
    double radius;
    double dampening;
    Color color;
    int number;
    public Ball(double x,double y,Color color,int number,double radius){
	Random rand = new Random(); 
	position = new Pair(x, y);
	velocity = new Pair(0.0,0.0);
	dampening = 1.03;
	this.color = color;
	this.radius=radius;
	this.number=number;
	pocket=false;
    }
    public void update(World w, double time){
	position = position.add(velocity.times(time));
	bounce(w);
    } 
     
    public void draw(Graphics g){
	Color c = this.color;
	g.setColor(color);
	g.fillOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
    }
    // handles the Balls bouncing off of walls or going in between the walls into the pockets
    public void bounce(World w){
	Boolean bounced = false;
	
	//Left Wall
	if(this.position.x-this.radius<30){
	    if (this.position.y>45 && this.position.y<w.boardh+15){
		    this.velocity.flipX();
		    position.x=30+this.radius;
		    bounced=true;
	    }
	}

        //Right Wall
	if(this.position.x+this.radius>w.boardw+30){
	    if (this.position.y>45 && this.position.y<w.boardh+15){
		    this.velocity.flipX();
		    position.x=w.boardw +30 - this.radius;
		    bounced=true;
	    }
	}

	//Top Left Wall
	if (this.position.y-this.radius<30){
	    if (this.position.x>45 && this.position.x<(w.width/2)){
		this.velocity.flipY();
		position.y=30+this.radius;
		bounced=true;	
	    }
	}
	
	//Top Right Wall
	if (this.position.y-this.radius<30){
	    if (this.position.x>(w.width/2)+30 && this.position.x<w.boardw+15){
		this.velocity.flipY();
		position.y=30+this.radius;
		bounced=true;
	    }
	}
	
	//Bottom Right Wall
	if(this.position.y+this.radius>w.boardh+30){
	    if (this.position.x>(w.width/2)+30 && this.position.x<w.boardw+15){
		this.velocity.flipY();
		position.y=w.boardh + 30-this.radius;
		bounced=true;
	    }
	}

	//Bottom Left Wall
	if (this.position.y+this.radius>w.boardh+30){
	    if (this.position.x>45 && this.position.x<(w.width/2)){
		this.velocity.flipY();
		position.y=w.boardh + 30-this.radius;
		bounced=true;
	    }
	}
	velocity = velocity.divide(dampening);
    }
    
    public boolean collisionTest(Pair q){
	return (distance(q)<radius*2)&&(distance(q)!=0);
    }
    public double distance(Pair q){
	return  Math.sqrt((position.x - q.x)*(position.x - q.x) + (position.y-q.y)*(position.y-q.y));
    }

    // checks if a ball is pocketed
    public boolean pocketed(World w){
	Pair a= new Pair(30,30);
	Pair b= new Pair(30+w.boardw, 30+w.boardh);
	Pair c= new Pair(30+w.boardw, 30);
	Pair d= new Pair(w.width/2+15, 20);
	Pair e= new Pair(w.width/2+15,w.boardh+40);
	Pair f= new Pair(30,w.boardh+30);
	if (this.distance(a)<=15)
	    return true;
	if (this.distance(b)<=15)
	    return true;
	if (this.distance(c)<=15)
	    return true;
	if (this.distance(d)<=15)
	    return true;
	if (this.distance(e)<=15)
	    return true;
	if (this.distance(f)<=15)
	    return true;
	return false;
    }
	
    public void shoot(){}
    public void aimUp(){}
    public void aimDown(){}
    public void powerUp(){}
    public void powerDown(){}
}

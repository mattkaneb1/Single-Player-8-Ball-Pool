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

public class cueBall extends Ball{
    double xAim=0;
    double yAim=1;
    double power=50;
    double theta=0;
    public cueBall(double x,double y,Color color,int number,double radius){
	super(x,y,color,number,radius);
	pocket=false;
    }

    public void draw(Graphics g){
	Color c = Color.WHITE;
	g.setColor(c);
	g.fillOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));

	// draws the aimLine(Only when cueBall is still)
	if (Math.abs(velocity.x)<.01 && Math.abs(velocity.y)<.01)
	    g.drawLine((int)(position.x+xAim), (int)(position.y+yAim),(int) position.x, (int) position.y);
    }
    
    public void shoot(){
	this.velocity.x=xAim*5;
	this.velocity.y=yAim*5;
    }

    public void aimUp(){
	theta+=.02;
	xAim=Math.cos(theta)*power;
	yAim=Math.sin(theta)*power;
    }

    public void aimDown(){
	theta-=.02;
	xAim=Math.cos(theta)*power;
	yAim=Math.sin(theta)*power;
    }
    public void powerUp(){
	if (power<400)
	    power+=5;
	xAim=Math.cos(theta)*power;
	yAim=Math.sin(theta)*power;
    }
    public void powerDown(){
	if (power>0){
	    power-=5;
	    xAim=Math.cos(theta)*power;
	    yAim=Math.sin(theta)*power;
	}	    
    }
    public void bounce(World w){
	super.bounce(w);
	if (pocketed(w)){
	    w.cue=false;
	    w.l.delete(this);
	}   
    }   
}

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

public class stripeBall extends Ball{
    public stripeBall(double x,double y,Color color,int number,double radius){
	super(x,y,color,number,radius);
    }

    public void draw(Graphics g){
	Color c = this.color;
	g.setColor(c);
	g.fillOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
        g.setColor(Color.WHITE);
	g.fillOval((int)(position.x - (0.5*radius)), (int)(position.y - (0.5*radius)), (int)(radius), (int)(radius));
	g.setColor(Color.BLACK);
	String num= "" + number;
	g.setFont(new Font("TimesRoman", Font.PLAIN,7)); 
	g.drawString(num,(int)(position.x)-3 ,(int)(position.y)+3);
    }
    
    public void bounce(World w){
        super.bounce(w);
	if (pocketed(w)){
	    w.ballsRemaining--;
	    System.out.println(w.ballsRemaining);
	    w.l.delete(this);
	}	
    }
}

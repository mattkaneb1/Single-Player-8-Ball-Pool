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

public class blackBall extends Ball{
    boolean endofgame;
    boolean win;
    public blackBall(double x,double y,Color color,int number,double radius){
	super(x,y,color,number,radius);
	win=false;
	endofgame=false;
    }
    
    public void draw(Graphics g){
	//determines if user won or not and displays it when the game is over
	if (endofgame){
	    if (win){
		String game="YOU WIN";
		g.setFont(new Font("TimesRoman", Font.PLAIN,40)); 
		g.drawString(game,240,190);
	    }else{
		String game="YOU LOSE";
		g.setFont(new Font("TimesRoman", Font.PLAIN,40)); 
		g.drawString(game,240,190);
	    }
	    //if game isnt over it behaves normally
	}else{
	    Color c = this.color;
	    g.setColor(c);
	    g.fillOval((int)(position.x - radius), (int)(position.y - radius), (int)(2*radius), (int)(2*radius));
	}
    }

    // if pocketed the game is over
    public void bounce(World w){
	super.bounce(w);
	if (pocketed(w)){
	    if (w.ballsRemaining==0){
		endofgame=true;
		win=true;
	    }
	    else{
		endofgame=true;
		win=false;
	    }
	} 
    }
}

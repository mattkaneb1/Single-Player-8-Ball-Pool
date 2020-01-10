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

public class World{
    int height;
    int width;
    int boardw;
    int boardh;
    List l;
    boolean cue;

    public static int ballsRemaining = 15;
    
    public World(int initWidth, int initHeight,int boardw, int boardh){
	width = initWidth;
	height = initHeight;
	this.boardw=boardw;
	this.boardh=boardh;
	l=new List();
	l.setup(initWidth,initHeight);
	this.cue=true;
    }
 
    public void drawBalls(Graphics g){
	Node n=l.end;
	while(n != null){
	    n.b.draw(g);
	    n=n.next;
	} 
    }
 
    public void updateBalls(double time){
	Node n=l.end;
	while(n != null){
	    n.b.update(this,time);
	    collisions();
	    n=n.next;
	}
    }

    //given 2 balls this method determines resulting velocity and applies it
    private void collide(Ball a,Ball b){
	Pair delta = (a.position.subtract(b.position));
	double length = Math.sqrt((delta.x*delta.x)+(delta.y*delta.y));
	double x=((a.radius + b.radius)-length)/length;
	Pair mtd =delta.times(x);
	
	Pair m=mtd.times(0.5);
        a.position =a.position.add(m);
	b.position =b.position.subtract(m);
	
	Pair v = (a.velocity.subtract(b.velocity));
	double ll= Math.sqrt((mtd.x*mtd.x)+(mtd.y*mtd.y));
	Pair n = v.mult(mtd.divide(ll));
	Double vn= n.x + n.y;

	if (vn > 0.0) return;

	double i = (-1.85 * vn)/(2);
	mtd=mtd.divide(ll);
	Pair impulse =mtd.times(i);
	
	a.velocity = a.velocity.add(impulse);
        b.velocity = b.velocity.subtract(impulse);	
    }
	
    public void collisions(){
	Node nn=l.end;
	while(nn != null){
	    Node n=l.end;
	    Ball a= nn.b;
	    while(n != null){
	        Ball b=n.b;
		if(a.collisionTest(b.position)){
		    collide(a,b);
		}
		n=n.next;
	    }
	    nn=nn.next;
	}
    }

    // throws a new Ball onto the table
    public void addBall(){
	Random rand=new Random();
	ballsRemaining++;
	Color c = new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255));
	Pair p=new Pair(rand.nextInt(300)-150,rand.nextInt(300)-150);
	
	Ball b = new stripeBall(rand.nextInt(boardw-30)+30,rand.nextInt(boardh-30)+30,c,0,width/60);
	b.velocity=p;
	Node n=l.end.next.next;
	Node a= new Node(b);
	l.end.next.next=a;
	a.next=n; 
    }   
}

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
public class List{
    Node end;
    public static int ballNumber=1;
    
    public List(){
	end = null;
    }	
    public void append(Ball b){
	Node n= new Node(b);
	n.next=end;
	end=n;
    }
    public void delete(Ball p){
        Node temp = end;
	Node prev=null;
  
        if (temp != null && temp.b == p){ 
            end = temp.next;
            return; 
        } 
 
        while (temp != null && temp.b != p) { 
            prev = temp; 
            temp = temp.next; 
        }     
  
        if (temp == null) return; 
        prev.next = temp.next; 
    }	
    public void setup(int width, int height){
	double breakX=.75*width;
	double breakY=0.5*height;
	int r=width/60;
	Ball b;
	Color c;
	Random rand=new Random();
	Pair[] pairs=new Pair[15];
	int x =0;
	//create an array of the starting positions of the Balls
	for (int i=1;i<6;i++){
	    breakY=(height/2)-((i-1)*r);
	    breakX=(0.75*width)+((i-1)*1.75*r);
	    for (int j=0;j<i;j++){
		pairs[x]=new Pair(breakX,breakY);
		x++;
		breakY+=2*r;	    
	    }
	}
	//shuffles the starting positions before assigning them to Balls
	int rr;
	Pair p;
	for (int k=0;k<100;k++){
	    rr=rand.nextInt(15);
	    p=pairs[rr];
	    pairs[rr]=pairs[0];
	    pairs[0]=p;
	}
	for(int j=0;j<15;j++){
	    if(ballNumber==8){
		c= Color.BLACK;
		b = new blackBall(pairs[ballNumber-1].x,pairs[ballNumber-1].y,c,ballNumber,width/60);
		this.append(b);
		ballNumber++;
	    }
	    else{
		c = new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255));
		b = new stripeBall(pairs[ballNumber-1].x,pairs[ballNumber-1].y,c,ballNumber,width/60);
		this.append(b);
		ballNumber++;	    
	    }
	}
	//makes the cueBall
	breakX=.25*width;
	breakY=0.5*height;
	c=Color.WHITE;
	b = new cueBall(breakX,breakY,c,ballNumber,width/60);
	this.append(b);
    }
}


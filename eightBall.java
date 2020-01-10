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

public class eightBall extends JPanel implements KeyListener,MouseListener{
    public static final int WIDTH = 660;
    public static final int HEIGHT = 360;
    public static final int BOARDW = 600;
    public static final int BOARDH = 300;
    public static final int FPS = 60;
    World world;
    
    class Runner implements Runnable{
	public void run(){
	    while(true){
		world.updateBalls(1.0 / (double)FPS);
		repaint();
		try{
		    Thread.sleep(1000/FPS);
		}
		catch(InterruptedException e){}
	    }
	}
    }

    public void keyPressed(KeyEvent e){
	char c = e.getKeyChar();
	if (c=='a' || c=='A')
	    world.l.end.b.aimUp();
	if (c=='d' || c=='D')
	    world.l.end.b.aimDown();
	if (c=='w' || c=='W')
	    world.l.end.b.powerUp();
	if (c=='s' || c=='S')
	    world.l.end.b.powerDown();
	if (c==' '){
	    boolean still=true;
	    Node n=world.l.end;
	    while (n.next!=null){
		if (Math.abs(n.b.velocity.x)>.1 && Math.abs(n.b.velocity.y)>.1)
		    still=false;
		n=n.next;
	    }
	    if (still==true){
		// 1in8 chance a ball is thrown in everytime the player shoots
		Random rand=new Random();
		int r= rand.nextInt(8);
		if (r==3)
		    world.addBall();
		world.l.end.b.shoot();
	    }
	}
    }
    public void keyTyped(KeyEvent e){}
    public void keyReleased(KeyEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    public void mouseClicked(MouseEvent e) {
	if (world.cue==false)
	    if ( e.getX()>30 && e.getX()<BOARDW && e.getY()>30 && e.getY()<BOARDH){
		world.addBall();
		world.addBall();
		world.addBall();
		Ball b = new cueBall(e.getX(),e.getY(),Color.WHITE,1,world.width/60);
		world.l.append(b);
		world.cue=true;
	    }
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
 
    public eightBall(){
	world = new World(WIDTH, HEIGHT,BOARDW,BOARDH);
        addKeyListener(this);
	addMouseListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	Thread mainThread = new Thread(new Runner());
	mainThread.start();
    }
     
    public static void main(String[] args){
        JFrame frame = new JFrame("8 Ball Pool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	eightBall mainInstance = new eightBall();
        frame.setContentPane(mainInstance);
        frame.pack();
	frame.setVisible(true);
     }
    //draws table and Balls
    public void paintComponent(Graphics g) {
	super.paintComponent(g);        
 
	g.setColor(Color.GRAY);
	g.fillRect(0, 0, WIDTH, HEIGHT);
	g.setColor(Color.GREEN);
	g.fillRect(30, 30, BOARDW, BOARDH);

	//TOPLEFT
	int[] a={50,40,40};
	int[] b={30,20,30};
	g.fillPolygon(a,b,3);
	int[] c={30,30,20};
	int[] d={40,50,40};
	g.fillPolygon(c,d,3);
	g.setColor(Color.BLACK);
	g.fillOval(15, 15, 30, 30);

	//BOTTOMRIGHT
	g.setColor(Color.GREEN);
	int[] e={40+BOARDW,30+BOARDW,30+BOARDW};
	int[] f={20+BOARDH,10+BOARDH,20+BOARDH};
	g.fillPolygon(e,f,3);
	int[] gg={20+BOARDW,20+BOARDW,10+BOARDW};
	int[] h={30+BOARDH,40+BOARDH,30+BOARDH};
	g.fillPolygon(gg,h,3);
	g.setColor(Color.BLACK);
	g.fillOval(15+BOARDW, 15+BOARDH, 30, 30);

	//TOPRIGHT
	g.setColor(Color.GREEN);
	int[] i={BOARDW+10,BOARDW+20,BOARDW+20};
	int[] j={30,30,20};
	g.fillPolygon(i,j,3);
	int[] k={BOARDW+30,BOARDW+30,BOARDW+40};
	int[] l={40,50,40};
	g.fillPolygon(k,l,3);
	g.setColor(Color.BLACK);
	g.fillOval(15+BOARDW, 15, 30, 30);

	//TOPMIDDLE
	g.setColor(Color.GREEN);
	int[] m={35+(WIDTH/2),25+(WIDTH/2),25+(WIDTH/2)};
	int[] n={30,10,30};
	g.fillPolygon(m,n,3);
	int[] o={(WIDTH/2)-5,(WIDTH/2)+5,(WIDTH/2)+5};
	int[] p={30,10,30};
	g.fillPolygon(o,p,3);
	g.setColor(Color.BLACK);
	g.fillOval(WIDTH/2, 5, 30, 30);

	//BOTTOMMIDDLE
	g.setColor(Color.GREEN);
	int[] q={35+(WIDTH/2),25+(WIDTH/2),25+(WIDTH/2)};
	int[] r={BOARDH+30,BOARDH+50,BOARDH+30};
	g.fillPolygon(q,r,3);
	int[] s={(WIDTH/2)-5,(WIDTH/2)+5,(WIDTH/2)+5};
	int[] t={BOARDH+30,BOARDH+50,BOARDH+30};
	g.fillPolygon(s,t,3);
	g.setColor(Color.BLACK);
	g.fillOval(WIDTH/2,BOARDH+25, 30, 30);

	//BOTTOMLEFT
        g.setColor(Color.GREEN);
	int[] u={40,40,50};
	int[] v={BOARDH+30,BOARDH+40,BOARDH+30};
	g.fillPolygon(u,v,3);
	g.setColor(Color.GREEN);
	int[] w={20,30,30};
	int[] x={BOARDH+20,BOARDH+10,BOARDH+20};
	g.fillPolygon(w,x,3);
	g.setColor(Color.BLACK);
	g.fillOval(15,BOARDH+15,30,30);
	
	world.drawBalls(g); 
    }    
}

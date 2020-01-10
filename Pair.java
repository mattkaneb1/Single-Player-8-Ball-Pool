public class Pair{
    public double x;
    public double y;
     
    public Pair(double initX, double initY){
	x = initX;
	y = initY;
    }
 
    public Pair add(Pair toAdd){
	return new Pair(x + toAdd.x, y + toAdd.y);
    }
    public Pair subtract(Pair toSub){
	return new Pair(x-toSub.x, y - toSub.y);
    }
 
    public Pair divide(double denom){
	return new Pair(x / denom, y / denom);
    }
 
    public Pair times(double val){
	return new Pair(x * val, y * val);
    }

    public Pair mult(Pair p){
	return new Pair(x*p.x,y*p.y);
    }
    public void flipX(){
	x = -x;
    }
     
    public void flipY(){
	y = -y;
    }
}
 

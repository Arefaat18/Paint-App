package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import static java.awt.geom.Point2D.distance;



public class MyLine extends MyShape
{  
    
    public MyLine()
    {
        super();
    }
    
    
    public MyLine( int x1, int y1, int x2, int y2, Color color )
    {
        super(x1, y1, x2, y2, color);
    } 
     
   
    @Override
    public void draw( Graphics g )
    {
        g.setColor( getColor() ); //sets the color
        g.drawLine( getX1(), getY1(), getX2(), getY2() ); //draws the line
    } 

    @Override
    public boolean Contain(int x, int y) {
      Point a = new Point(x,y);
       Point b = new Point(x2,y2);
       Point c=new Point(x1,y1);
      
      return c.distance(b) == (b.distance(a) + c.distance(a)) ;
      
       
       
    }

    @Override
    public void moveBy(int x, int y) {
       this.x1=x1+x;
        this.y1=y1+y;
        this.x2=x2+x;
        this.y2=y2+y;
    }

    @Override
    public void resize(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MyShape Copy(MyShape w) {
         MyShape q=new MyLine(x1+5, y1+5, x2+5, y2+5, color);
        
        return q;
    }

    @Override
    public MyShape fill(MyShape w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}

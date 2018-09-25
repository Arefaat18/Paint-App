package shapes;

import shapes.MyBoundedShape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;


public class MyTriangle extends MyBoundedShape
{ 
   
    public MyTriangle()
    {
        super();
    }
   
    
    public MyTriangle( int x1, int y1, int x2, int y2, Color color, boolean fill )
    {
 
        super(x1, y1, x2, y2, color,fill);
    } 
    
   
    @Override
    public void draw( Graphics g )
    {
        g.setColor( getColor() ); //sets the color
        int[] x = new int[3];
        int[] y = new int[3];
        x[0]=getX1(); x[1]=getX2(); x[2]=(getX2()-getX1())+getX2();
        y[0]=getY1(); y[1]=getY2(); y[2]=getY1();
        Polygon p = new Polygon(x, y, 3);
        if (getFill()) {
             g.fillPolygon(p);
        }
        else{
           g.drawPolygon(p);
        }
            
        
    } 

   
    @Override
    public MyShape fill(MyShape w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
   public boolean Contain(int x, int y) {int flag=0;int width;int x3=0,y3 = 0,x4=0,y4=0;
        if(x2>x1){
            if(y1>y2){
        y3=y2;
        x3=x1;
        y4=y1;
        x4=2*x2-x1;}else{
                
                x3=x1;
                y3=y1;
                y4=y2;
                x4=2*x2-x1;
                
            }}
        else{
            
          if(y1>y2){ x4=x1;
          y4=y1;
          x3=2*x2-x1;
          y3=y2;}else{
              y3=y1;
              x3=2*x2-x1;
              y4=y2;
              x4=x1;
          }
        }
       
       
          
            
        
        if( x>=x3 && x<x3+Math.abs(x4-x3) && y>=y3&&y<y3+Math.abs(y4-y3))
           return true;
       return false;
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
       this.x1=x1+x;
        this.y1=y1+y;
        this.y2=y2-y;
      
    }

    @Override
    public MyShape Copy(MyShape w) {
       MyShape q=new MyTriangle(x1+5, y1+5, x2+5, y2+5, color, fill);
        
        return q;
    }
    
}
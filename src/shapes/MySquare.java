package shapes;

import shapes.MyBoundedShape;
import java.awt.Color;
import java.awt.Graphics;


public class MySquare extends MyBoundedShape
{ 
   
    public MySquare()
    {
        super();
    }
   
    
    public MySquare( int x1, int y1, int x2, int y2, Color color, boolean fill )
    {
        super(x1, y1, x2, y2, color,fill);
    } 
    
   
    @Override
    public void draw( Graphics g )
    {
        g.setColor( getColor() ); //sets the color
        if (getFill()) //determines whether fill is true or false
            g.fillRect( getUpperLeftX(), getUpperLeftY(), getWidth(), getWidth() ); //draws a filled rectangle
        else
            g.drawRect( getUpperLeftX(), getUpperLeftY(), getWidth(), getWidth() ); //draws a regular rectangle
        
    } 

    @Override
    public boolean Contain(int x, int y) {
     if(y1>y2){int temp=x1;
            x1=x2;
            x2 = temp;
             temp=y1;
            y1=y2;
            y2 = temp;
            
        }
        if(x1>x2){
            int temp=x1;
            x1=x2;
            x2 = temp;
        }
            
        
       if( x>=x1 && x<x1+Math.abs(x2-x1) && y>=y1&&y<y1+Math.abs(y2-y1))
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
      
    }

    @Override
    public MyShape fill(MyShape w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MyShape Copy(MyShape w) {
        MyShape q=new MySquare(x1+5, y1+5, x2+5, y2+5, color, fill);
        
        return q;
    }
    
}


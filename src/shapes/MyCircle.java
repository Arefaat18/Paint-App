package shapes;
        
import java.awt.Color;
import java.awt.Graphics;

/**
 * This class inherits from MyBoundedShape and is responsible for drawing a oval.
 */
public class MyCircle extends MyBoundedShape
{ 
    /**
     * No parameter constructor which calls the no parameter constructor in MyBoundedShape.
     */
    public MyCircle()
    {
        super();
    }
    
    /** 
     * Overloaded constructor that takes coordinates, color and fill. 
     * It passes them into MyBoundedShape's constructor.
     */
    public MyCircle( int x1, int y1, int x2, int y2, Color color, boolean fill )
    {
        super(x1, y1, x2, y2, color,fill);
    }
     
    /**
     * Overrides the draw method in MyBoundedShape. It sets the gets the color from MyBoundedShape
     * to set the color and the values it needs to draw from MyBoundedShape as well.
     */
    @Override
    public void draw( Graphics g )
    {
        
        g.setColor( getColor() ); //sets the color
        if (getFill()) //determines whether fill is true or false
            g.fillOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getWidth() ); //draws a filled oval
        else
            g.drawOval( getUpperLeftX(), getUpperLeftY(), getWidth(), getWidth() ); //draws a regular oval
        
    }

   
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
    public MyShape fill(MyShape w) {MyShape q;
        if(fill==true){
       q=new MyCircle(x1, y1, x2, y2, color, false);
        }
        else
            q=new MyCircle(x1, y1, x2, y2, color, true);
        
      return q;
    }

    @Override
    public MyShape Copy(MyShape w) {
         MyShape q=new MyCircle(x1+5, y1+5, x2+5, y2+5, color, fill);
        
        return q;
    }
    
} // end class MyOval
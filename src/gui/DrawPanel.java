package gui;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.Node;
import shapes.MyCircle;
import shapes.MyLine;
import shapes.MyOval;
import shapes.MyRectangle;
import shapes.MySquare;
import shapes.MyShape;
import shapes.MyTriangle;

/**
 * This class handles mouse events and uses them to draw shapes. It contains a
 * dynamic stack myShapes which is the shapes drawn on the panel. It contains a
 * dynamic stack clearedShape which is the shapes cleared from the panel. It has
 * many variables for the current shape [type, variable to store shape object,
 * color, fill]. It contains a JLabel called statusLabel for the mouse
 * coordinates It has mutator methods for currentShapeType, currentShapeColor
 * and currentShapeFilled. It has methods for undoing, redoing and clearing
 * shapes. It has a private inner class MouseHandler which extends MouseAdapter
 * and handles mouse and mouse motion events used for drawing the current shape.
 */
public class DrawPanel extends JPanel {

    private LinkedList<MyShape> myShapes; //dynamic stack of shapes
    private LinkedList<MyShape> clearedShapes; //dynamic stack of cleared shapes from undo
     private ArrayList<Node> list = null;
    public String commander = null;
    //current Shape variables
    private int currentShapeType; //0 for line, 1 for rect, 2 for oval
    private MyShape currentShapeObject; //stores the current shape object
    private Color currentShapeColor; //current shape color
    private boolean currentShapeFilled; //determine whether shape is filled or not
    private int prevX;
    private int prevY;
    JLabel statusLabel; //status label for mouse coordinates
    private MyShape temp;
    private MyShape another;

    /**
     * This constructor initializes the dynamic stack for myShapes and
     * clearedShapes. It sets the current shape variables to default values. It
     * initalizes statusLabel from JLabel passed in. Sets up the panel and adds
     * event handling for mouse events.
     */
    public DrawPanel(JLabel statusLabel) {

        myShapes = new LinkedList<MyShape>(); //initialize myShapes dynamic stack
        clearedShapes = new LinkedList<MyShape>(); //initialize clearedShapes dynamic stack

        currentShapeType = 0;
        currentShapeObject = null;
        currentShapeColor = Color.BLACK;
        currentShapeFilled = false;

        this.statusLabel = statusLabel; //Initialize statusLabel

        setLayout(new BorderLayout()); //sets layout to border layout; default is flow layout
        setBackground(Color.WHITE); //sets background color of panel to white
        add(statusLabel, BorderLayout.SOUTH);  //adds a statuslabel to the south border

        // event handling for mouse and mouse motion events
        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
    }

    /**
     * Calls the draw method for the existing shapes.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the shapes
        List<MyShape> shapeArray = new ArrayList<MyShape>(myShapes);
        for (int counter = shapeArray.size() - 1; counter >= 0; counter--) {
            shapeArray.get(counter).draw(g);
        }

        //draws the current Shape Object if it is not null
        if (currentShapeObject != null) {
            currentShapeObject.draw(g);
        }
    }

    public void setCurrentShapeType(int type) {
        currentShapeType = type;
    }

    public void setCurrentShapeColor(Color color) {
        currentShapeColor = color;
    }

    public void setCurrentShapeFilled(boolean filled) {
        currentShapeFilled = filled;
    }
    
   public void clearLastShape() {
        if (!myShapes.isEmpty()) {
            clearedShapes.push(myShapes.removeFirst());
            repaint();
        }
    }

    public void redoLastShape() {
        if (!clearedShapes.isEmpty()) {
            myShapes.push(clearedShapes.removeFirst());
            repaint();
        }
    }

    /**
     * Remove all shapes in current drawing. Also makes clearedShapes empty
     * since you cannot redo after clear. It called repaint() to redraw the
     * panel.
     */
    public void clearDrawing() {
        myShapes.removeAll(myShapes);
        clearedShapes.removeAll(myShapes);
        repaint();
    }
    


    /**
     * Private inner class that implements MouseAdapter and does event handling
     * for mouse events.
     */
    private class MouseHandler extends MouseAdapter {

        /**
         * When mouse is pressed draw a shape object based on type, color and
         * filled. X1,Y1 & X2,Y2 coordinate for the drawn shape are both set to
         * the same X & Y mouse position.
         */
        public void mousePressed(MouseEvent event) {
            if ("Draw".equals(commander)) {
                switch (currentShapeType) //0 for line, 1 for rect, 2 for oval
                {
                    case 0:
                        currentShapeObject = new MyLine(event.getX(), event.getY(),
                                event.getX(), event.getY(), currentShapeColor);
                        break;
                    case 1:
                        currentShapeObject = new MyRectangle(event.getX(), event.getY(),
                                event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                        break;
                    case 2:
                        currentShapeObject = new MySquare(event.getX(), event.getY(),
                                event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                        break;
                    case 3:
                        currentShapeObject = new MyOval(event.getX(), event.getY(),
                                event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                        break;
                    case 4:
                        currentShapeObject = new MyCircle(event.getX(), event.getY(),
                                event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                        break;
                    case 5:
                        currentShapeObject = new MyTriangle(event.getX(), event.getY(),
                                event.getX(), event.getY(), currentShapeColor, currentShapeFilled);
                        break;

                }// end switch case
            }
            if ("Move".equals(commander)) {

                 List<MyShape> shapeArray = new ArrayList<MyShape>(myShapes);
                Point p = new Point(event.getX(), event.getY());
                System.out.println(p.x + " " + p.y);
                for (int i = 0; i < shapeArray.size(); i++) {

                    MyShape s = shapeArray.get(i);

                    if (s.Contain(p.x, p.y)) {
                        prevX = p.x;
                        prevY = p.y;
                        temp = s;
                        System.out.println("Here");
                    }

                }

            }
            if ("Resize".equals(commander)) {

                 List<MyShape> shapeArray = new ArrayList<MyShape>(myShapes);
                Point p = new Point(event.getX(), event.getY());
                System.out.println(p.x + " " + p.y);
                for (int i = 0; i < shapeArray.size(); i++) {

                    MyShape s = shapeArray.get(i);

                    if (s.Contain(p.x, p.y)) {
                        prevX = p.x;
                        prevY = p.y;
                        temp = s;

                        System.out.println("Here");
                    }

                }

            }
            if ("Copy".equals(commander)) {

             List<MyShape> shapeArray = new ArrayList<MyShape>(myShapes);
                Point p = new Point(event.getX(), event.getY());
                System.out.println(p.x + " " + p.y);
                for (int i = 0; i < shapeArray.size(); i++) {

                    MyShape s = shapeArray.get(i);

                    if (s.Contain(p.x, p.y)) {
                        prevX = p.x;
                        prevY = p.y;
                        temp = s;
                        another = temp.Copy(temp);
                        myShapes.push(another);
                        repaint();
                        System.out.println("Here");
                    }

                }

            }
            if ("Fill".equals(commander)) { 
                List<MyShape> shapeArray = new ArrayList<MyShape>(myShapes);
                Point p = new Point(event.getX(), event.getY());
                System.out.println(p.x + " " + p.y);
                for (int i = 0; i < shapeArray.size(); i++) {

                    MyShape s = shapeArray.get(i);

                    if (s.Contain(p.x, p.y)) {
                        prevX = p.x;
                        prevY = p.y;
                        temp = s;
                      another= temp.fill(temp);
                        myShapes.remove(temp);
                       
                        myShapes.push(another);
                        repaint();
                        System.out.println("Here");
                    }

                }

                    
            }
            if ("Delete".equals(commander)) {

                 List<MyShape> shapeArray = new ArrayList<MyShape>(myShapes);
                Point p = new Point(event.getX(), event.getY());
                System.out.println(p.x + " " + p.y);
                for (int i = 0; i < shapeArray.size(); i++) {

                    MyShape s = shapeArray.get(i);

                    if (s.Contain(p.x, p.y)) {
                        myShapes.remove(s);
                        repaint();
                        System.out.println("Here");break;
                    }

                }

            }

        } // end method mousePressed

        /**
         * When mouse is released set currentShapeObject's x2 & y2 to mouse pos.
         * Then addFront currentShapeObject onto the myShapes dynamic Stack and
         * set currentShapeObject to null [clearing current shape object since
         * it has been drawn]. Lastly, it clears all shape objects in
         * clearedShapes [because you cannot redo after a new drawing] and calls
         * repaint() to redraw panel.
         */
        public void mouseReleased(MouseEvent event) {
            //sets currentShapeObject x2 & Y2
            if ("Draw".equals(commander)) {
                currentShapeObject.setX2(event.getX());
                currentShapeObject.setY2(event.getY());

                myShapes.push(currentShapeObject); //addFront currentShapeObject onto myShapes

                currentShapeObject = null; //sets currentShapeObject to null
                clearedShapes.removeAll(myShapes); //clears clearedShapes
                repaint();
            }
            if ("Move".equals(commander)) {

                temp = null;
                currentShapeObject = null;

            }

            if ("Resize".equals(commander)) {
                temp = null;
                currentShapeObject = null;
            }
            if ("Copy".equals(commander)) {
                temp = null;
                currentShapeObject = null;
            }

        } // end method mouseReleased

        /**
         * This method gets the mouse pos when it is moving and sets it to
         * statusLabel.
         */
        public void mouseMoved(MouseEvent event) {
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));
        } // end method mouseMoved

        /**
         * This method gets the mouse position when it is dragging and sets x2 &
         * y2 of current shape to the mouse pos It also gets the mouse position
         * when it is dragging and sets it to statusLabel Then it calls
         * repaint() to redraw the panel
         */
        public void mouseDragged(MouseEvent event) {
            //sets currentShapeObject x2 & Y2
            if ("Draw".equals(commander)) {
                currentShapeObject.setX2(event.getX());
                currentShapeObject.setY2(event.getY());

                //sets statusLabel to current mouse position
                statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));

                repaint();
            }
            if ("Move".equals(commander)) {
                Point p = new Point(event.getX(), event.getY());
                int x = event.getX();

                int y = event.getY();

                if (temp != null) {

                    temp.moveBy(x - prevX, y - prevY);

                    prevX = x;

                    prevY = y;
                    repaint();

                }
            }
            if ("Resize".equals(commander)) {
                Point p = new Point(event.getX(), event.getY());
                int x = event.getX();

                int y = event.getY();

                if (temp != null) {

                    temp.resize(x - prevX, y - prevY);

                    prevX = x;

                    prevY = y;
                    repaint();

                }
            }
            if ("Copy".equals(commander)) {
                Point p = new Point(event.getX(), event.getY());
                int x = event.getX();

                int y = event.getY();

                if (temp != null) {

                    temp.moveBy(x - prevX, y - prevY);

                    prevX = x;

                    prevY = y;

                    repaint();

                }
            }

        } // end method mouseDragged

    }// end MouseHandler

} // end class DrawPanel
/*public class DrawPanel extends javax.swing.JFrame {

   
    public DrawPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DrawPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}*/

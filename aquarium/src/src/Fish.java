
package src;

import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Alberto Fernandez Saucedo
 */
public class Fish {
    Component tank;
    Image image1, 
          image2;
    Point location,
          velocity;
    Rectangle edges;
    Random random;
    
    public Fish(Image img1, Image img2, Rectangle edges, Component tank)
    {
        random = new Random(System.currentTimeMillis());
        this.tank = tank;
        this.image1 = img1;
        this.image2 = img2;
        this.edges = edges;
        this.location = new Point(
            100 + (Math.abs(random.nextInt()) % 300),
            100 + (Math.abs(100 + random.nextInt()) % 100));
        this.velocity = new Point(random.nextInt() % 8, random.nextInt());
    }//end constructor
    
}

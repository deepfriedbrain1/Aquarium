package src;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static src.Sprite.getSprite;

/**
 *
 * @author Alberto Fernandez Saucedo
 */
public class Aquarium extends Frame 
{
    //Images for application
    Image aquariumBackground;
    Image [] fishImages = new Image[2];
    //Alerts the user if there is an issue loading images
    MediaTracker tracker;
    
    Aquarium()
    {
        setTitle("The Aquarium");
        
        tracker = new MediaTracker(this);
        
        fishImages[0] = Toolkit.getDefaultToolkit().getImage("./resources/fish1.gif");
        tracker.addImage(fishImages[0], 0);
        
        fishImages[1] = Toolkit.getDefaultToolkit().getImage("./resources/fish2.gif");
        tracker.addImage(fishImages[1], 0);
        
        aquariumBackground = getSprite("background");
        tracker.addImage(aquariumBackground, 0);
        
        try{
            tracker.waitForID(0);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        setSize(aquariumBackground.getWidth(this), aquariumBackground.getHeight(this));
   
        setResizable(false);
        
        setVisible(true);
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(
                    WindowEvent windowEvent){
                        System.exit(0);
            }
           
        }
        );
    }//end constructor 
    
    public static void main(String[] args)
    {
        new Aquarium();
    }
}

package src;
import java.awt.Frame;
import java.awt.MediaTracker;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *
 * @author Alberto Fernandez Saucedo
 */
public class Aquarium extends Frame 
{
    //Alerts the user if there is an issue loading images
    MediaTracker tracker;
    
    Aquarium()
    {
        setTitle("The Aquarium");
        
        tracker = new MediaTracker(this);
        
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

package src;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import static src.Sprite.getSprite;

/**
 *
 * @author Alberto Fernandez Saucedo
 */
public class Aquarium extends Frame implements Runnable
{
    //Images for application
    Image aquariumBackground, memoryImage;
    Image [] fishImages = new Image[2];
    
    //Used to draw memory image offscreen, prevent flickering
    Graphics memoryGraphics;
    
    //Alerts if there is an issue loading images
    MediaTracker tracker;
    
    //Thread
    Thread thread;
    
    int numberOfFish = 3;
    int sleepTime = 110;
    Vector<Fish> fishes = new Vector<>();
    boolean runOk = true;
    
    Aquarium()
    {
        setTitle("The Aquarium");
        
        tracker = new MediaTracker(this);
        
        fishImages[0] = getSprite("fish1");
        tracker.addImage(fishImages[0], 0);
        
        fishImages[1] = getSprite("fish2");
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
        
        memoryImage = createImage(getSize().width, getSize().height);
        memoryGraphics = memoryImage.getGraphics();
        
        thread = new Thread(this);
        thread.start();
        
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(
                    WindowEvent windowEvent){
                        runOk = false; //end the thread
                        System.exit(0); //exit system
            }
           
        }
        );
    }//end constructor 
    
    public void update(Graphics g)
    {
        memoryGraphics.drawImage(aquariumBackground, 0, 0, this);
        for(int i = 0; i < numberOfFish; i++){
            ((Fish)fishes.elementAt(i)).drawFishImage(memoryGraphics);
        }
        g.drawImage(memoryImage, 0, 0, this);
    }//end update
    
    public static void main(String[] args)
    {
        new Aquarium();
    }

    @Override
    public void run() {
        Rectangle edges = new Rectangle(0 + getInsets().left, 
        0 + getInsets().top, getSize().width - (getInsets().left 
                + getInsets().right), getSize().height - (getInsets().top
                        + getInsets().bottom));
        
        for(int i = 0; i < numberOfFish; i++){
            fishes.add(new Fish(fishImages[0], fishImages[1], edges, this));
            try{
                Thread.sleep(20);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        Fish fish;
        while(runOk){
            for(int i = 0; i < numberOfFish; i++){
                fish = (Fish)fishes.elementAt(i);
                fish.swim();
            }
            
            try{
                Thread.sleep(sleepTime);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
            repaint();
        }//end while
    }//end run
}//end Aquarium

package src;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static src.Sprite.getSprite;

/**
 * Multithreaded Aquarium 
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
    
    //Execution stream for fish drawing and movement
    Thread thread;
    
    int numberOfFish = 5;
    int sleepTime = 110;
    
    ArrayList<Fish> fishes = new ArrayList<>();
    boolean runOk = true;
    
    public Aquarium()
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
        }catch(InterruptedException e){
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
            @Override
            public void windowClosing(
                    WindowEvent windowEvent){
                        runOk = false; //end the thread
                        System.exit(0); //exit the program
            }
           
        }
        );
    }//end constructor 
    
    public void update(Graphics g)
    {
        memoryGraphics.drawImage(aquariumBackground, 0, 0, this);
        for(int i = 0; i < numberOfFish; i++){
            ((Fish)fishes.get(i)).drawFishImage(memoryGraphics);
        }
        g.drawImage(memoryImage, 0, 0, this);
    }//end update

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
                fish = (Fish)fishes.get(i);
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
    
    public static void main(String[] args)
    {
        new Aquarium();
    }
}//end Aquarium

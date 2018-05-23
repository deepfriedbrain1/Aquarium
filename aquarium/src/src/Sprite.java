package src;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * @author Alberto Fernandez Saucedo
 */

public abstract class Sprite {
        private static Image img;
        
     static public Image getSprite(String name)
    {   
        try{
         img = Toolkit.getDefaultToolkit().getImage(Sprite.class.getResource(name + ".gif"));
         return img;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }    
       
}

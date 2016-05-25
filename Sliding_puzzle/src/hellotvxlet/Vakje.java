/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import org.havi.ui.HStaticText;
import org.havi.ui.HVisible;

/**
 *
 * @author student
 */
public class Vakje extends HStaticText{
    int orgX;
    int orgY;
    public Vakje (int x, int y, String text)
    {
        super (text, x*100,y*100,100,100);
        this.setBordersEnabled(true);
        this.setBackgroundMode(HVisible.BACKGROUND_FILL);
        this.setBackground(Color.BLUE);
        
        orgX = x;
        orgY = y;
        
    }

 public void schuif(int x, int y)
 {
     this.setBounds(x*100,y*100,100,100);
     this.repaint();
 }
    
    
    
   

}

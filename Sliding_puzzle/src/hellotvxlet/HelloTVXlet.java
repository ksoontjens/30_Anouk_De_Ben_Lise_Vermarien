package hellotvxlet;

import java.awt.Color;
import java.awt.event.*;
import java.util.Random;
import javax.tv.xlet.*;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.*;
import org.havi.ui.event.HActionListener;





public class HelloTVXlet implements Xlet, UserEventListener, HActionListener
{
HScene scene = HSceneFactory.getInstance().getDefaultHScene();

private HTextButton startknop = new HTextButton("START");

Vakje v[][] = new Vakje[4][4];
int leegVakjeX = 3;
int leegVakjeY = 3;

    public void MeerdereVakjes()
    {
        int x = 4;
        int number=0;
      
        
        for(int j=0;j<x;j++)
        {            
            for(int i=0;i<4;i++)
            {
                if(i==3 && j==3)
                {
                v[i][j] = new Vakje(i,j,"");
                v[i][j].setBordersEnabled(true);
                v[i][j].setBackgroundMode(HVisible.BACKGROUND_FILL);
                v[i][j].setBackground(Color.BLACK);
                }
                else
                {
                number=number+1;
                String strNumber = Integer.toString(number);
                v[i][j] = new Vakje(i,j,strNumber);
                }
            scene.add(v[i][j]);
            }
        }
        
        scene.setVisible(true);
        scene.validate();
    }

    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
        
    }

    public void initXlet(XletContext ctx){
        MeerdereVakjes();
        UserEventRepository rep=new UserEventRepository("rep");
        rep.addAllArrowKeys();
        EventManager.getInstance().addUserEventListener(this, rep);
        
        HSceneTemplate sceneTemplate = new HSceneTemplate();
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_DIMENSION,
                new HScreenDimension(1.0f, 1.0f), HSceneTemplate.REQUIRED);
        
        sceneTemplate.setPreference(HSceneTemplate.SCENE_SCREEN_LOCATION, 
                new HScreenPoint(0.0f, 0.0f), HSceneTemplate.REQUIRED);
        
        //scene = HSceneFactory.getInstance().getBestScene(sceneTemplate);
        scene.setBackground(DVBColor.DARK_GRAY);
        scene.setBackgroundMode(1);
        
        
        startknop.setSize(200,50);
        startknop.setLocation(450,100);
        startknop.setBackground(DVBColor.LIGHT_GRAY);
        startknop.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(startknop);
        startknop.requestFocus();
        startknop.setActionCommand("startknop");
        startknop.addHActionListener((HActionListener) this);
       
        
        
    }

    public void pauseXlet() {
        
    }

    public void startXlet() throws XletStateChangeException {
     System.out.println("start Xlet");    
     scene.validate();
     scene.setVisible(true);
     System.out.println("started Xlet"); 
    }
    
    public void move(int x) {
       switch(x){
            //left
           case 1:
              // maak een backup van het leeg vakje:
              Vakje tempL=v[leegVakjeX][leegVakjeY];
              if (leegVakjeX==0) break;
              //checken of vakje naar links mag
              if(v[leegVakjeX][leegVakjeY] != v[0][leegVakjeY]){

              // verplaats het vakje naar het lege vakje:
              v[leegVakjeX][leegVakjeY]=v[leegVakjeX-1][leegVakjeY];
              v[leegVakjeX][leegVakjeY].schuif(leegVakjeX, leegVakjeY);                  

               // verplaats het lege vakje naar de oude positie van het verschoven vakje:
               v[leegVakjeX-1][leegVakjeY]=tempL;     
               v[leegVakjeX-1][leegVakjeY].schuif(leegVakjeX-1,leegVakjeY);
               leegVakjeX--;    
               break;
              }
           
          //right
           case 2:
             // maak een backup van het leeg vakje:
             Vakje tempR=v[leegVakjeX][leegVakjeY];
             if(leegVakjeX==3) break;
             //checken of vakje naar rechts mag
             if(v[leegVakjeX][leegVakjeY] != v[3][leegVakjeY]){
                 
             // verplaats het vakje naar het lege vakje:
             v[leegVakjeX][leegVakjeY]=v[leegVakjeX+1][leegVakjeY];
             v[leegVakjeX][leegVakjeY].schuif(leegVakjeX, leegVakjeY);

            // verplaats het lege vakje naar de oude positie van het verschoven vakje:
            v[leegVakjeX+1][leegVakjeY]=tempR;
            v[leegVakjeX+1][leegVakjeY].schuif(leegVakjeX+1,leegVakjeY);
            leegVakjeX++;
            break;
            }
            
          //up
           case 3:
            Vakje tempU=v[leegVakjeX][leegVakjeY];
            if(leegVakjeY==0) break;
            //checken of vakje naar boven mag
            if(v[leegVakjeX][leegVakjeY] != v[leegVakjeX][0]){
                
           // verplaats het vakje naar het lege vakje:
           v[leegVakjeX][leegVakjeY]=v[leegVakjeX][leegVakjeY-1];
           v[leegVakjeX][leegVakjeY].schuif(leegVakjeX, leegVakjeY);

           // verplaats het lege vakje naar de oude positie van het verschoven vakje:
           v[leegVakjeX][leegVakjeY-1]=tempU;
           v[leegVakjeX][leegVakjeY-1].schuif(leegVakjeX,leegVakjeY-1);
           leegVakjeY--;
           break;
            }
         //down
           case 4:
             // maak een backup van het leeg vakje:
             Vakje tempD=v[leegVakjeX][leegVakjeY];
             if(leegVakjeY==3) break;
             //checken of vakje naar boven mag
            if(v[leegVakjeX][leegVakjeY] != v[leegVakjeX][3]){
                
             // verplaats het vakje naar het lege vakje:
             v[leegVakjeX][leegVakjeY]=v[leegVakjeX][leegVakjeY+1];
             v[leegVakjeX][leegVakjeY].schuif(leegVakjeX, leegVakjeY);

             // verplaats het lege vakje naar de oude positie van het verschoven vakje:
             v[leegVakjeX][leegVakjeY+1]=tempD;
             v[leegVakjeX][leegVakjeY+1].schuif(leegVakjeX,leegVakjeY+1);
             leegVakjeY++;
             break;
            }    
               
                    
           
        }
    }

    public void userEventReceived(UserEvent e) {
        CheckPosition();
        if (e.getType()==KeyEvent.KEY_PRESSED) // enkel ingedrukte toetsen
        {
            switch(e.getCode())
            {
                case HRcEvent.VK_LEFT:
                    System.out.println("LEFT!!!"); 
                    
                   move(1);
                    break;
                    
                        
                case HRcEvent.VK_RIGHT:
                    System.out.println("RIGHT!!!");
                    move(2);
                    
                    break;
                    
                    
                 case HRcEvent.VK_UP:
                    System.out.println("UP!!!");
                    move(3);
                    
                    break;
                  
                case HRcEvent.VK_DOWN:
                    System.out.println("DOWN!!!");
                    move(4);
                    
                    break;
            }
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("startknop"))
        {
            //code als ge op uw knop duwt (met enterknop)
            System.out.println("BUTTON");
            randomMoves();
            
        }
    }    
    public void randomMoves() {
        
        
       int randomMoves = 1000;
       Random rndmMove = new Random();
       for(int i=0; i < randomMoves; i++) {
            
            int nummer = 1 + rndmMove.nextInt(4);
            System.out.println(nummer);
            move(nummer);
              
       }
       scene.repaint();
    }
    
    public void CheckPosition()
    {
        boolean OK=true;
        int x,y;
        for (x=0;x<4;x++)
            for (y=0;y<4;y++)
            {
                System.out.print("Vakje op pos("+x);
                System.out.print(","+y+") komt van "); 
                
                System.out.println("("+v[x][y].orgX+","+v[x][y].orgY+")");
                
 
              
                if (!(x==leegVakjeX && y==leegVakjeY)) 
                if (!((v[x][y].orgX==x) && v[x][y].orgY==y))
                {
                    OK=false;
                }
            }
        if (OK) { System.out.println ("puzzel opgelost!!!!"); }
        
    }


    
}
/* [DisplayGrid.java]
 * A Small program for Display a 2D String Array graphically
 * @author Mangat
 */

// Graphics Imports
import javax.swing.*;
import java.awt.*;



class DisplayGrid { 
  
  private JFrame frame;
  private int maxX,maxY, GridToScreenRatio;
  private Living[][] world;
  private Image grass, plant, adultMaleSheep, adultFemaleSheep, childMaleSheep, childFemaleSheep;
  private Image adultMaleWolf,childMaleWolf,childFemaleWolf,adultFemaleWolf;
  
  DisplayGrid(Living[][] w) { 
    this.world = w;
    
    //All the images used to draw the living things
    grass= Toolkit.getDefaultToolkit().getImage("grass.png");
    plant = Toolkit.getDefaultToolkit().getImage("plant.png");
    
    
    adultMaleSheep = Toolkit.getDefaultToolkit().getImage("adultMaleSheep.png");
    adultFemaleSheep = Toolkit.getDefaultToolkit().getImage("adultFemaleSheep.png");
    childMaleSheep = Toolkit.getDefaultToolkit().getImage("childMaleSheep.png");
    childFemaleSheep = Toolkit.getDefaultToolkit().getImage("childFemaleSheep.png");
    
    
    
    adultMaleWolf = Toolkit.getDefaultToolkit().getImage("adultMaleWolf.png");
    adultFemaleWolf = Toolkit.getDefaultToolkit().getImage("adultFemaleWolf.png");
    childMaleWolf = Toolkit.getDefaultToolkit().getImage("childMaleWolf.png");
    
    childFemaleWolf = Toolkit.getDefaultToolkit().getImage("childFemaleWolf.png");
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = maxY / (world.length+1);  //ratio to fit in screen as square map
    
    System.out.println("Map size: "+world.length+" by "+world[0].length + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);
    
    this.frame = new JFrame("Map of World");
    
    GridAreaPanel worldPanel = new GridAreaPanel();
    
    frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    frame.setVisible(true);
  }
  
  public void refresh() { 
    frame.repaint();
  }
  
  class GridAreaPanel extends JPanel {
    public void paintComponent(Graphics g) {        
      //super.repaint();
      
      //Counter of living things
      int sheeps=0;
      int wolfs=0;
      int plants=0;
      
      setDoubleBuffered(true); 
      g.setColor(Color.BLACK);
      
      for(int i = 0; i<world[0].length;i=i+1){
        for(int j = 0; j<world.length;j=j+1){
          
          if (world[i][j] instanceof Sheep){     //This code draws the sheep by sorting from gender then age  
            sheeps+=1;
            if(world[i][j].getGender()){         
              if(world[i][j].getAge()){
                g.drawImage(childMaleSheep,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }else{
                g.drawImage(adultMaleSheep,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }
            }else{
              if(world[i][j].getAge()){
                g.drawImage(childFemaleSheep,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }else{
                g.drawImage(adultFemaleSheep,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }   
            }
          }
          
          else if (world[i][j] instanceof Wolf) {
            wolfs+=1;
            if(world[i][j].getGender()){         //This code draws the wolf by sorting from gender then age
              if(world[i][j].getAge()){
                g.drawImage(childMaleWolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }else{
                g.drawImage(adultMaleWolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }
            }else{
              if(world[i][j].getAge()){
                g.drawImage(childFemaleWolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }else{ 
                g.drawImage(adultFemaleWolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }            
            }
          }
          
          else if (world[i][j] instanceof Plant){ 
            plants+=1;
            g.drawImage(plant,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else{
            g.drawImage(grass,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }
        }
      }
      
      // Draw the counters beside the grid
      int fontLit = world[0].length * GridToScreenRatio + 20;
      
      g.drawString("Number of Wolves : " + wolfs, fontLit, 40);
      g.drawString("Number of Sheep : " + sheeps, fontLit, 60);
      g.drawString("Number of Plants : " + plants, fontLit, 80);    
    }
  }//end of GridAreaPanel
} //end of DisplayGrid

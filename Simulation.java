/* [Simulation.java]
 * A object orientated program to simulate an ecosystem with wolves,sheep and plants.
 * Author: Naymul Mohammed
 * May 4, 2017
 */



import java.util.Scanner;
class Simulation { 
  public static void main(String[] args) { 
    
    double wolfSpawn;
    double sheepSpawn;
    double plantSpawn;
    double plantRespawn;
    
    int wolfCounter=1;
    int sheepCounter=1;
    int plantCounter=1;
    int gridLength;
    int gridWidth;
    int timer=0;
    
    Scanner input=new Scanner(System.in);
    
    System.out.println("Enter the spawn rates a percent in decimal form.");
    System.out.println("The decimals should always add up to less than 1 as there needs to be a chance for nothing to spawn");
    System.out.println("The recommended and most stable setting is: 20x20 grid, 0.01 wolves, 0.3 plants, 0.35 sheep, and 0.11 plants respawn");
    System.out.println();
    //Input rates from user
    System.out.println("Enter the grid length");
    gridLength=input.nextInt();
    
    System.out.println("Enter the grid width");
    gridWidth=input.nextInt();
    
    System.out.println("Enter the initial wolf spawn");
    wolfSpawn=input.nextDouble();
    
    System.out.println("Enter the initial plant spawn");
    plantSpawn=input.nextDouble();
    
    System.out.println("Enter the initial Sheep spawn");
    sheepSpawn=input.nextDouble();
    
    System.out.println("Enter the plant respawn");
    plantRespawn=input.nextDouble();
    
    input.close();
    Living map[][] = new Living[gridLength][gridWidth];
    
    DisplayGrid grid = new DisplayGrid(map);         
    makeHabitat(map,sheepSpawn,wolfSpawn,plantSpawn);   //Makes the array of animals
    
    grid.refresh();
    
    //While none of the species are extinct the program will run
    while(wolfCounter>0 && sheepCounter>0 &&plantCounter>0){ 
      
      wolfCounter=0;
      sheepCounter=0;
      plantCounter=0;
      try{ Thread.sleep(1000); }catch(Exception e) {};
      
      for(int h=0;h<gridWidth;h++){
        for(int q=0;q<gridLength;q++){
          
          //If there is an animal let it attempt movement
          if(!(map[h][q]==null)){
            map[h][q].animalMove(h,q,map,gridLength,gridWidth);
          }
        }
      }
      
      for(int k=0;k<gridWidth;k++){
        for(int m=0;m<gridLength;m++){
          
          //All animals will lose 1 health per turn and gain age by 1 tick
          if(!(map[k][m]==null)){
            map[k][m].setMovement(true);
            map[k][m].lowerHealth(1);
            map[k][m].gainTimer(1);
            
            //Animals with more than 2 turns of age are adults
            if(map[k][m].getTimer()>2){
              map[k][m].setAge(false);     
            }
            
            //If an animal's age is 0, or a plants nutrition(received by an overiding getHealth method) value is over 100 it will die
            if(map[k][m].getHealth()<=0  || (map[k][m] instanceof Plant && map[k][m].getHealth()>=100 )){
              map[k][m]=null; 
            }
          }else{ 
            //If its a null, there is a chance a plant will respawn 
            if(Math.random()<=plantRespawn){
              map[k][m]=new Plant(1,true,1); 
            } 
          }
          
          //Following code counts the number of living things
          if(map[k][m] instanceof Sheep){
            sheepCounter=sheepCounter+1; 
          }
          
          if(map[k][m] instanceof Wolf){
            wolfCounter=wolfCounter+1; 
          } 
          if(map[k][m] instanceof Plant){
            plantCounter=plantCounter+1; 
          } 
        }
        grid.refresh();
      }
      
      timer+=1;
      System.out.println("Number of turns: " +timer);             //Output the number of turns and living things
      System.out.println("Number of wolves: " +wolfCounter);  
      System.out.println("Number of sheeps: " +sheepCounter);
      System.out.println("Number of plants: " +plantCounter);
      System.out.println();
    } 
  }
  
  /**
   * makeHabitat
   * This method accepts a 2D array of living things, and their spawn rates that are saved as double variables. It 
   * fills the spots of the array with specific living things in each spot.
   * @param A 2D array of animal objects, a double value of the sheep spawn, a double value of the wolves spawn,
   * and a double value of the plants spawn, 
   * @return It doesn't return anything as the array is changed within the method
   */
  public static void makeHabitat(Living[][] map,double sheepSpawn, double wolfSpawn, double plantSpawn) { 
    double random;
    boolean made;
    
    for(int i = 0; i<map[0].length;i++){        
      for(int j = 0; j<map.length;j++){ 
        random=Math.random();             //At every spot a random number is generated
        made=true;                       //Made is a boolean that becomes false when something is made on the array
        
        //Following code creates living things based off of rates entered into the method
        if(random<sheepSpawn&& made){
          
          map[i][j]=new Sheep ((int) (Math.random()*10 +100),0,true,false,(Math.random()>0.5));    //Gender is 50% chance of either male or female
          made=false;
        }
        if(random>sheepSpawn && random<=sheepSpawn+wolfSpawn && made){
          map[i][j]=new Wolf ((int) (Math.random()*10 +25),5,true,false,(Math.random()>0.5));
          made=false;
        }
        if(random>sheepSpawn+wolfSpawn && random<=sheepSpawn+wolfSpawn+plantSpawn&& made){
          
          map[i][j]=new Plant (1,true,1); 
          made=false;
        }
      }
    }
  }
}
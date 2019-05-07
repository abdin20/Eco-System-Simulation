/* [Wolf.java]
 * A class that is extended from Living to represent wolves in the ecosystem.
 * Author: Naymul Mohammed
 * May 4, 2017
 */

class Wolf extends Living{
  
  /**
   * Wolf
   * This method is a constructor for the wolf object. It takes in various properties of the wolf
   * to be used later on in the program.
   * @param An integer value for its health, and its age in turns. Boolean values for its current movement(if it moved before in a turn),
   * if its a child, and its gender
   * @return It doesnt return anything as it is a constructor
   */
  Wolf(int health,int timer,boolean movement,boolean child,boolean gender){
    super(health,timer,movement,child,gender);
  }
  
  
  /**
   * animalMove
   * This method takes in the array of living things,the array's demenstions, and the objects position in the array. 
   * It checks the best possible move for the wolf to make with its priorities being : Sheep>Wolves>Everything else
   * @param A 2D array of living objects, 2 integer values for the array's dimenstions and 2 other
   * integer values representing the objects position in the array
   * @return It doesn't return anything as the array is changed within the method
   */
  void animalMove(int x, int y, Living[][] map,int gridLength, int gridWidth){
    
    boolean age=map[x][y].getAge();
    boolean gender=map[x][y].getGender();
    boolean checkRight=false;
    boolean checkLeft=false;
    boolean checkUp=false;
    boolean checkDown=false;
    boolean up=false;
    boolean down=false;
    boolean right=false;
    boolean left=false;
    boolean baby=false;
    boolean turn=map[x][y].getMovement();
    
    double random=Math.random();
    
    //Check to see if it moved before in the array
    
    
    //Checks to see if it is possible to even see to the left first
    if(y-1>=0){
      left=true;
      
      if(turn && (map[x][y-1] instanceof Sheep) ){             //Check to see if there is sheep and goes to sheep
        map[x][y].gainHealth((map[x][y-1].getHealth()));
        map[x][y].setMovement(false);  
        map[x][y-1]=map[x][y];
        map[x][y]=null;
        turn=false;  
      }
      
      if(turn && (map[x][y-1] instanceof Wolf) && (age==map[x][y-1].getAge()) ){     //Wolves only of same age can fight/reproduce
        
        //If the ages match they must be false to be adults and both be opposite genders
        if(turn && (!age) && (!(gender==map[x][y-1].getGender())) && (map[x][y].getHealth()>=20) && (map[x][y-1].getHealth()>=20) ){
          
          //Success rate of conceiving
          if( (Math.random()<=0.30) ){ 
            if( (x+1<gridLength) && (map[x+1][y]==null) && !(baby) ){           //Following ifs check to see if a baby can be made surrounding the intitiating wolf
              map[x+1][y]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
              baby=true;  
            }
            
            if( (x-1>=0) && (map[x-1][y]==null) && !(baby) ){
              map[x-1][y]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
              baby=true;
            }
            if( (y+1<gridLength) && (map[x][y+1]==null) && !(baby) ){
              map[x][y+1]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
              baby=true;
            }
            if(baby){                     //If baby is made then wolves will lose health and turn is therefore over                   
              map[x][y].lowerHealth(10);
              map[x][y-1].lowerHealth(10);
              map[x][y].setMovement(false);
              map[x][y-1].setMovement(false);
              turn=false;
              baby=false;
            }
          }
        }           
        //Wolves of same age only fight and only if they are ready to fight(health over 19) also 50%chance of this occuring as wolves are loyal
        if(turn && (Math.random()<=0.50) && (map[x][y].getHealth()>19) && (map[x][y].compareTo(map[x][y-1])>=0) ){
          System.out.println("attack");
          map[x][y-1].lowerHealth(40);
          map[x][y].setMovement(false);
          map[x][y-1].setMovement(false);
          turn=false;
        }
      }
      //If it can move to the left need to check if it can foresee more incase there wasn't any place for it to go
      if(y-2>=0){
        checkLeft=true;
      }
    }
    
    //Checks to see if it is possible to even see to the right first 
    if(y+1<gridWidth){
      right=true;
      
      //Check to move on towards sheep
      if(turn && (map[x][y+1] instanceof Sheep) ){
        map[x][y].gainHealth(map[x][y+1].getHealth()); 
        map[x][y].setMovement(false);  
        map[x][y+1]=map[x][y];
        map[x][y]=null;
        turn=false; 
      }
      //Wolves only of same age can fight/reproduce
      if(turn && (map[x][y+1] instanceof Wolf) && (age==map[x][y+1].getAge()) ){
        
        //If the ages match they must be false to be adults and both be opposite genders
        if(turn && !(age) && !(gender==map[x][y+1].getGender()) && (map[x][y].getHealth()>=20) && (map[x][y+1].getHealth()>=20) ){
          
          //Success rate of conceiving
          if( (Math.random()<=0.30) ){
            
            if( (x+1<gridLength) && (map[x+1][y]==null) && !(baby) ) {           //Following ifs check to see if a baby can be made surrounding the intitiating wolf
              
              map[x+1][y]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
              baby=true;  
            }
            
            if( (x-1>=0) && (map[x-1][y]==null) && !(baby) ){
              map[x-1][y]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
              baby=true;
            }
            if( (y-1>=0) && (map[x][y-1]==null) && !(baby) ){
              map[x][y-1]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
              baby=true;
            }
            if(baby){                     //If baby is made then wolves will lose health and turn is therefore over                   
              map[x][y].lowerHealth(10);
              map[x][y+1].lowerHealth(10);
              map[x][y].setMovement(false);
              map[x][y+1].setMovement(false);
              turn=false; 
              baby=false;
            }
          }
        }  
        
        //Wolves of same age only fight and only if they are ready to fight(health over 19) also 50%chance of this occuring as wolves are loyal
        if(turn && (Math.random()<=0.50) && (map[x][y].getHealth()>19) && (map[x][y].compareTo(map[x][y+1])>=0) ){
          System.out.println("attack");
          map[x][y+1].lowerHealth(40);
          map[x][y].setMovement(false);
          map[x][y+1].setMovement(false);
          turn=false;
        }
      }
      //If it can move to the right need to check if it can foresee more incase there wasn't any place for it to go
      if(y+2<gridWidth){  
        checkRight=true;
      }
    }  
    
    //Check to see if it can go up
    if(x-1>=0){
      up=true;
      
      //Check for sheep to go on to upwards 
      if(turn && (map[x-1][y] instanceof Sheep) ){
        map[x][y].gainHealth(map[x-1][y].getHealth()); 
        map[x][y].setMovement(false);  
        map[x-1][y]=map[x][y];
        map[x][y]=null;
        turn=false;
      }
      //Wolves only of same age can fight/reproduce
      if(turn && (map[x-1][y] instanceof Wolf) && (age==map[x-1][y].getAge()) ){
        
        // 
        //If the ages match age must be false to be adults and both be opposite genders and have more than 19 health
        if(turn && !(age) && !(gender==map[x-1][y].getGender()) && (map[x][y].getHealth()>19) && (map[x-1][y].getHealth()>19) ){
          
          //Success rate of conceiving
          if(Math.random()<=0.30){
            
            if( (y+1<gridLength) && (map[x][y+1]==null) && !(baby) ){           //Following ifs check to see if a baby can be made surrounding the intitiating wol
              
              map[x][y+1]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
              baby=true;  
            }
            if( (y-1>=0) && (map[x][y-1]==null) && !(baby) ){
              map[x][y-1]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
              baby=true;
            }
            
            if( (x+1<gridLength) && (map[x+1][y]==null) && !(baby) ){
              map[x+1][y]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
              baby=true;
            }
            
            if(baby){                     //If baby is made then wolves will lose health and turn is therefore over                   
              map[x][y].lowerHealth(10);
              map[x-1][y].lowerHealth(10);
              map[x][y].setMovement(false);
              map[x-1][y].setMovement(false);
              turn=false; 
              baby=false;
            }
          }
        }
        
        //Wolves of same age only fight and only if they are ready to fight(health over 19) also 50%chance of this occuring as wolves are loyal
        if(turn && (Math.random()<=0.50) && (map[x][y].getHealth()>19) && (map[x][y].compareTo(map[x-1][y])>=0) ){
          System.out.println("attack");
          map[x-1][y].lowerHealth(40);
          map[x][y].setMovement(false);
          map[x-1][y].setMovement(false);
          turn=false;
        }
      } 
      //If it can move to upwards need to check if it can foresee more incase there wasn't any place for it to go
      if(x-2>=0){
        checkUp=true; 
      }
    }   
    //Check to see where to go downwards
    if(x+1<gridLength){
      down=true; 
      
      //Check to see if sheep downwards
      if(turn && (map[x+1][y] instanceof Sheep) ){
        map[x][y].gainHealth(   (map[x+1][y].getHealth()));
        map[x][y].setMovement(false);
        map[x+1][y]=map[x][y];
        map[x][y]=null;
        turn=false;
      }    
      //Wolves only of same age can fight/reproduce
      if(turn && (map[x+1][y] instanceof Wolf) && (age==map[x+1][y].getAge()) ){
        
        if(turn && !(map[x][y].getAge()) && !(gender==map[x+1][y].getGender()) && (map[x][y].getHealth()>19) && (map[x+1][y].getHealth()>19) ){  
          
          //If the ages match they must be false to be adults and both be opposite genders
          if(turn && !(age) && !(gender==map[x+1][y].getGender()) && (map[x][y].getHealth()>=20) && (map[x+1][y].getHealth()>=20) ){
            
            //Success rate of conceiving
            if( (Math.random()<=0.30) ){
              if( (y+1<gridLength) && (map[x][y+1]==null)&& !(baby) ){           //Following ifs check to see if a baby can be made surrounding the intitiating wol                                                        
                map[x][y+1]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
                baby=true;  
              }
              if( (y-1>=0) && (map[x][y-1]==null) && !(baby) ){
                map[x][y-1]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
                baby=true;
              }
              if( (x-1>=0) && (map[x+1][y]==null) && !(baby) ){
                map[x-1][y]=new Wolf(10,0,true,true,(Math.random()>0.5)); 
                baby=true;
              }
              if(baby){                     //If baby is made then wolves will lose health and turn is therefore over                   
                map[x][y].lowerHealth(10);
                map[x+1][y].lowerHealth(10);
                map[x][y].setMovement(false);
                map[x+1][y].setMovement(false);
                turn=false;
                baby=false;
              }
            }
          }  
        }    
        //Wolves of same age only fight and only if they are ready to fight(health over 19) also 50%chance of this occuring as wolves are loyal
        if(turn && (Math.random()<=0.50) && (map[x][y].getHealth()>19) && (map[x][y].compareTo(map[x+1][y])>=0) ){
          System.out.println("attack");
          map[x+1][y].lowerHealth(40);
          map[x][y].setMovement(false);
          map[x+1][y].setMovement(false);
          turn=false;
        }
      }
    }
    if(x+2<gridLength){
      checkDown=true;
    }
    
    //Movement towards sheep only if no immediate sheep/wolves are there to move to
    if(turn && checkRight && (map[x][y+2] instanceof Sheep) ){
      if(map[x][y+1]==null){
        
        map[x][y+1]=map[x][y];
        map[x][y]=null;
        map[x][y+1].setMovement(false);
        turn=false;
      }
    }
    if(turn && checkDown && (map[x+2][y] instanceof Sheep) ){
      if(map[x+1][y]==null){
        map[x+1][y]=map[x][y];
        map[x][y]=null;
        map[x+1][y].setMovement(false);
        turn=false;
      }
    }  
    if(turn && checkUp && (map[x-2][y] instanceof Sheep) ){
      if(map[x-1][y]==null){
        
        map[x-1][y]=map[x][y];
        map[x][y]=null;
        map[x-1][y].setMovement(false);
        turn=false;
      }
    }
    
    if(turn && checkLeft && (map[x][y-2] instanceof Sheep) ){
      if(map[x][y-1]==null){
        map[x][y-1]=map[x][y];
        map[x][y]=null;
        map[x][y-1].setMovement(false);
        turn=false;
      }
    }
    
    //Random Movement if there are no sheep to move towards 
    if(turn && right && (random<=0.2) ){
      if(map[x][y+1]==null){
        
        map[x][y].setMovement(false);
        map[x][y+1]=map[x][y];
        map[x][y]=null;
        
      }
    }
    if(turn && left && (random>0.2) && (random<=0.4) ){
      if(map[x][y-1]==null){
        
        map[x][y].setMovement(false);
        map[x][y-1]=map[x][y];
        map[x][y]=null;
      }
    }
    if(turn && up && (random>0.4) && (random<=0.6) ){
      if(map[x-1][y]==null){
        
        map[x][y].setMovement(false);
        map[x-1][y]=map[x][y];
        map[x][y]=null;
      }
    }
    if(turn && down && (random>0.6) && (random<=0.8) ){
      if(map[x+1][y]==null){
        map[x][y].setMovement(false);
        map[x+1][y]=map[x][y];
        map[x][y]=null;
      }
    }
  }
}
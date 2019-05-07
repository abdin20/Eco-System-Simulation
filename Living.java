/* [Living.java]
 * A super class that is needed to connect all things that are living in the ecosystem.
 * Author: Naymul Mohammed
 * May 4, 2017
 */


abstract class Living implements Comparable<Living> {
  private int health;
  private boolean movement;
  private boolean child;
  private boolean gender;
  private int timer;
  
  /**
   * Living
   * This method constructs a Living object. It takes in health and a boolean to see if
   * it moved before.
   * @param It takes in an integer representing the object's health, and 
   * a boolean to see if it moved before in a turn before
   * @return Nothing because it's a constructor
   */
  Living(int health,boolean movement){
    this.health=health;
    this.movement=movement;
  }
  
  /**
   * Living
   * This method constructs a Living object. It takes in health, age in turns, a boolean to
   * see if it has moved, boolean representing if its a child and a boolean representing if its
   * a male/female
   * @param It takes in an integer representing the object's health, an integer of its age in turns,
   * a boolean to see if it moved before in a turn before, boolean representing if its a child 
   * and a boolean representing if its a male/female
   * @return Nothing because it's a constructor
   */ 
  Living(int health,int timer,boolean movement,boolean child,boolean gender){
    this.health=health;
    this.movement=movement;
    this.child=child;
    this.gender=gender;
    this.timer=timer;
  }
  
  /**
   * compareTo
   * This method compares two Living objects based on the integer value of their health.
   * @param An object to compare it to.
   * @return It returns an integer value representing the result of the comparison.
   */
  public int compareTo(Living other) {
    return Integer.compare(this.getHealth(), other.getHealth());
  }
  
  /**
   * getGender
   * This method gets a gender of an object.
   * @param Nothing because it is to receive information of an object.
   * @return It returns the gender of an object.
   */ 
  public boolean getGender(){
    return this.gender; 
  }
  
  /**
   * getAge
   * This method gets the age of an object.
   * @param Nothing because it is to receive information of an object.
   * @return It returns the integer value of the age of an object.
   */
  public boolean getAge(){
    return this.child;  
  }
  
  /**
   * setAge
   * This method set the age of an object.
   * @param A boolean value to represnting if its an adult or not
   * @return Nothing because it is setting something
   */ 
  public void setAge(boolean age){
    this.child=age;  
  }
  
  /**
   * getTimer
   * This method gets the time in turns of an object.
   * @param Nothing because it is to receive information of an object.
   * @return It returns an integer of time in turns of an object.
   */ 
  public int getTimer(){
    return this.timer;  
  }
  
  /**
   * gainTimer
   * This method increases the age of an object.
   * @param A integer value of how much in turns its age is increasing.
   * @return Nothing because it is setting something.
   */ 
  public void gainTimer(int add){
    this.timer=this.timer+add;   
  }
  
  /**
   * setMovement
   * This method set the movement of an object. This meaning if it changing the value
   * that represents if it moved in a turn already.
   * @param A boolean value to represnt if it moved or not
   * @return Nothing because it is setting something
   */   
  public void setMovement(boolean movement){
    this.movement=movement;
  }
  
  /**
   * getMovement
   * This method gets boolean value of movement to see if an object moved already.
   * @param Nothing because it is to receive information of an object.
   * @return It returns the boolean value of movement.
   */
  public boolean getMovement(){
    return this.movement;  
  }
  
  /**
   * getHealth
   * This method gets the health of an object.
   * @param Nothing because it is to receive information of an object.
   * @return It returns the integer value of health of an object.
   */
  public int getHealth(){
    return this.health;
  }
  
  /**
   * lowerHealth
   * This method lowers the health of an object.
   * @param A integer value of how much health is decreasing.
   * @return Nothing because it is setting something.
   */ 
  public void lowerHealth(int health){
    this.health=this.health-health;
  }
  
  /**
   * gainHealth
   * This method increases the health of an object.
   * @param A integer value of how much health is increasing.
   * @return Nothing because it is setting something.
   */ 
  public void gainHealth(int health){                         
    if(this.health<=50){                   //If an objects health is more than 50 then its health can no longer increase
      this.health=this.health+health;
    }
  }
  
  /**
   * animalMove
   * This method is abstract because every animal moves differently so each class needs its own movement method.
   * @param Integer values of its x and y position in the array, and the dimensions of the array. A 2D array of Living objects
   * this being the map of all the living things in the ecosystem.
   * @return Nothing because the array is changed within the method.
   */  
  abstract void animalMove(int x,int y, Living[][] map,int gridLength, int gridWidth); 
}
/* [Plant.java]
 * A class that is extended from Living to represent plants in the ecosystem.
 * Author: Naymul Mohammed
 * May 4, 2017
 */

class Plant extends Living{
  private int nutrition;
  
  /**
   * Plant
   * This method is a constructor for the plant object. It takes in various properties of the plant
   * to be used later on in the program.
   * @param An integer value for its health, a boolean value for its current move state(if it moved in a turn already)
   * and an integer value representing its nutrition value
   * @return It doesnt return anything as it is a constructor
   */
  Plant(int health,boolean movement,int nutrition){
    super(health,movement);
    this.nutrition=nutrition; 
  }
  
  /**
   * animalMove
   * This method takes in the array of living things,the array's demenstions, and the objects position in the array. 
   * It is here because the animalMove method is abstract and since plants cannot move it is blank.
   * @param A 2D array of living objects, 2 integer values for the array's dimenstions and 2 other
   * integer values representing the objects position in the array
   * @return It doesnt return anything because nothing occurs in the method
   */
  void animalMove(int x,int y, Living[][] map,int gridLength, int gridWidth){  
  }
  
  
  /**
   * lowerHealth
   * This method overrides the lowerHealth method in the super class Living. This is because
   * plants don't lose health but gain nutritional value after every turn.
   * @param An integer representing how much the nutritional value is going to increase by
   * @return It doesnt return anything it changes a class variable
   */
  public void lowerHealth(int gain){
    this.nutrition=this.nutrition + (gain*10); 
  }
  
  /**
   * getHealth
   * This method overrides the getHealth method in the super class Living. This is because
   * all plants have health of 1 and when animals eat it and use the getHealth method, it will
   * get its nutritional value
   * @param Nothing because it is for getting a value.
   * @return It returns the plant's nutritional value.
   */
  public int getHealth(){
    return nutrition;  
  }
}
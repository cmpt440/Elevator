/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Elevator;

/**
 *
 * @author fev
 */
public class Building 
{
    
    /**
     * Building contains the dimensions
     */
    int numb_of_floors;
    
    /**
     * Default constructor: Gives the building of 10 floors
     */
    public Building()
    {
        numb_of_floors=10;
    }
    
    /**
     * Sets the floors of the building
     * @param numb_of_floors 
     */
    public void set_building_floors(int numb_of_floors)
    {
        if(numb_of_floors<0)
        {
            System.err.print("Error: Number of floors need to be 0 or higher");
            System.exit(0);
        }
        else
        {
            this.numb_of_floors=numb_of_floors;
        }
    }
    
    /**
     * Returns the number of floors in the building
     * @return 
     */
    public int get_building_floors()
    {
        return numb_of_floors;
    }
}

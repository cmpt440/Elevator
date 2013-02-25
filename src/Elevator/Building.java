/*
 * Created by: Christopher Feveck
 * Purpose: To determine the dimensions of the building. This will be used
 * to help in velocity calculations.
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
    int height_of_floor;    
    int building_height;
    
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
    
    /**
     * Sets the height of the floor
     * Height will be used to determine velocity or speeds
     */
    public void set_floor_height(int height_of_floor)
    {        
        
        if(height_of_floor<10)
        {
            System.err.println("floor height must be 10 feet or higher but less than 15.");
            System.exit(0);
        }
        
        if(height_of_floor>15)
        {
            System.err.println("floor height must be 15 or less but higher than 10.");
            System.exit(0);
        }
        
        this.height_of_floor=height_of_floor;
        this.calc_building_height();
    }
    
    /**
     * Returns the height of the floor
     * @return 
     */
    public int get_floor_height()
    {
        return height_of_floor;
    }
    
    /**
     * Calculates the building height
     */
    private void calc_building_height()
    {
        building_height=height_of_floor*numb_of_floors;
    }
    
    /**
     * Returns the height of the building (used to determine velocity)
     * @return 
     */
    public int get_building_height()
    {
        return building_height;
    }
    
    /*
    public static void main(String[] args)
    {
        Building building=new Building();
        building.set_building_floors(10);
        //building.set_floor_height(17);
        building.set_floor_height(10);
        building.get_building_floors();
        System.out.println(building.get_building_height());
        
    }
    */
     
    
}

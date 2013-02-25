/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * This class creates other objects which is implemented in the queue
 */
package Elevator;

/**
 *
 * Nafy 8:04 pm
 */
public class Create_class implements Comparable
{
   
    /**
     * Tick: a variable which keeps the priority in the queue
     * class_type: Variable which holds a string and creates an object based
     * on what is in the string.
     */
    private int tick;
    String class_type;
    /**
     * Default constructor. 
     * Requires a string to set the class type
     * and an int to set the tick
     */
    public Create_class(String class_type, int tick)
    {
        this.tick=tick;
        this.class_type = class_type;        
    }
    /**
     * Default constructor
     */
    public Create_class()
    {
        tick=1;
        class_type="Elevator";
    }
    
    /**
     * Sets the tick of the class
     */
    public void set_tick(int tick)
    {
        this.tick=tick;
    }
    
    /**
     * returns the tick of the class
     */
    public int get_tick()
    {
        return tick;
    }
    
    /**
     * Sets the type of class needed
     */
    public void set_class_type(String class_type)
    {
        this.class_type=class_type;
    }
    
    /**
     * Returns the class type set
     */
    public String get_class_type()
    {
        return this.class_type;
    }
    
    /**
     * Implements the comparable interface and compares the object on the queue
     * (Needed for the priority queue 0 denotes equality -1 for <, 1 for >)
     * 0 is only the concern. Anything else is a fault.
     */
    public int compareTo(Object obj )
    {
        int result=-1;
        
            if (obj instanceof Elevator)
            {
                if(tick<((Elevator)obj).get_tick())
                {
                    result=-1;
                }
                else if(tick==((Elevator)obj).get_tick())
                {
                    result=0;
                }
                else
                {
                    result=1;
                }
                
            }
        else if (obj instanceof Create_class)
        {
            if(tick < ((Create_class)obj).get_tick())
            {
                result=-1;
            }
            else if(tick==((Create_class)obj).get_tick())
            {
                result=0;
            }
            else
            {
                result=1;
            }
            
        }
            else if (obj instanceof Person)
        {
            if(tick < ((Person)obj).get_tick())
            {
                result=-1;
            }
            else if(tick==((Person)obj).get_tick())
            {
                result=0;
            }
            else
            {
                result=1;
            }
            
        }
                                    else if (obj instanceof Request)
        {
            if(tick < ((Request)obj).get_tick())
            {
                result=-1;
            }
            else if(tick==((Request)obj).get_tick())
            {
                result=0;
            }
            else
            {
                result=1;
            }

        }

        
    return result;
    }

    
}




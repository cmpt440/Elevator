/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Elevator;

/**
 *
 * @author fev
 */
public class Person implements Comparable
{
    /**
     * tick: the time at which an event is triggered
     * destination_floor: the floor the person wants to go 
     * (randomize the floor choice after the class is fully functional)
     */
    private int tick;
    //private int destination_floor;
    private Request request;
    private int current_floor;
    /**
     * Instanciates the Person class: the person will know what floor they
     * what to go to and they will arrive in the building at a tick
     * @param tick
     * @param destination_floor 
     */
    
    //request methods
    //sends the request
    public void set_request(Request request)
    {
        this.request=request;
    }
    //receives the request
    public Request get_request()
    {
        return request;
    }
    //constructor
    public Person(int tick, int current_floor)
    {
        this.tick = tick;
        this.current_floor=current_floor;      
    }

    /**
     * Sets the tick of the Person
     * @param tick 
     */
    public void set_tick(int tick)
    {        
        this.tick=tick;
    }
    
    /**
     * Sets the desired destination floor of the Person
     * @param destination_floor 
     */    
   /* public void set_destination_floor(int destination_floor)
    {
        this.destination_floor=destination_floor;
    }
    */
    /**
     * Returns the destination floor of the Person
     * @return 
     */
    /*public int get_destination_floor()
    {
        return destination_floor;
    }
*/
    
       public void set_current_floor(int current_floor)
    {
        this.current_floor=current_floor;
    }
    
    /**
     * Returns the current floor of the Person
     * @return 
     */
    public int get_current_floor()
    {
        return current_floor;
    }
    
    /**
     * Returns the tick of the Person
     * @return 
     */
    public int get_tick()
    {        
        return tick;
    } 
    
    /**
     * Implements the comparable interface and compares the object on the queue
     * (Needed for the priority queue 0 denotes equality -1 for <, 1 for >)
     * 0 is only the concern. Anything else is a fault.
     */
    public int compareTo(Object obj )
    {
        int result=-1;
            /*
             * Compares the object 
             */
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

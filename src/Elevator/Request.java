/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Elevator;

/**
 *
 * @author fev
 */
public class Request implements Comparable
{
        //Time of the event. When it runs
    private int tick;
    private int from_floor;
    private int to_floor;
    private boolean direction;
    
    
    public Request(int tick)
    {
        this.tick = tick;
        from_floor=0;
        to_floor=0;
        direction=true;
    }  
    public void set_tick(int tick)
    {        
        this.tick=tick;
    }

    
    public int get_tick()
    {        
        return tick;
    }    
    /*
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
            //result = (tick < ((Create_class)obj).get_tick() ? -1 : (tick == ((Create_class)obj).get_tick() ? 0 : 1));
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
    
    public void set_to_floor_request(int to_floor)
    {
        this.to_floor=to_floor;
    }
    
    public int get_to_floor_request()
    {
        return to_floor;
    }
    
    
    public void set_from_floor_request(int from_floor)
    {
        this.from_floor=from_floor;
    }
    
    public int get_from_floor_request()
    {
        return from_floor;
    }
    
    
    public void set_direction_request(boolean direction)
    {
        this.direction=direction;
    }
    
    public boolean get_direction_request()
    {
        return direction;
    }
   
    
}

/*
 * Created by: Christopher Feveck
 * Purpose: To create the simulate the events of the elevator and the people
 * who get on the elevator as well as the movement.
 * 
 * Note: a priority queue will be used to create this event simulator. 
 * The events will be have numbers associated with the occurance of each event
 * when the event occurs the number associated with it will be added to the
 * event taking place: eg add_person-2 when that event is called two will be
 * added to the already existing number 2 so the add_person will be 
 * add_person-4
 */
package Elevator;
import java.util.*;
import java.util.Random;



public class Events 
{
    private PriorityQueue<Object> p_queue;
    private int sim_tick;
    private LinkedList[] building_floors;    
    
    //speed of elevator
    private int elevator_tick;
    //arrival of people
    private int people_tick;
    //number of floors
    private int floors;   
    private int tick;
    private String class_type;
    
    private int wait_time;
    private int divisor_for_average;
    private int onloaded_tick;
    private int unloaded_tick;
    private int divisor_for_intransit_tick;
    
    private int e_algorithm_type;
    private Infile infile;
    
    Random rand=new Random();
    private int dropofffloor;
    private int elevators;
    
    //The floor a person is delivered upon creation
    private int placeofbirth;
    //delay when dropping / pickup
    private int pick_up_delay;
    //Sets the idle location of the elevator
    //THIS IS AN INPUT VARIABLE tweak it to take input
    //Create an output message for the IDLE variable as it is an output 
    //parameter
    private int idle;
    
    private int num_of_ticks;
    
    public Events()
    {
        
    }
    
    public Events(String file_name, String num_of_ticks)
    {
        this.num_of_ticks=Integer.parseInt(num_of_ticks);
        
        infile=new Infile(file_name);
        infile.get_data();
        
        sim_tick = 0;
        e_algorithm_type = infile.get_algorithm();
        elevator_tick=infile.get_speed();
        building_floors = new LinkedList[infile.get_floors()];
        floors=infile.get_floors();
        
        //NEED TO EDIT
        dropofffloor= rand.nextInt(floors);
        
        wait_time=0;
        divisor_for_average=1;
        onloaded_tick=0;
        unloaded_tick=0;
        divisor_for_intransit_tick=1;
        
        people_tick=infile.get_birth_of_person();
        elevators=infile.get_elevators();
        placeofbirth=infile.get_place_of_birth()-1;
        pick_up_delay = infile.get_pic_up_delay();
        
        idle=infile.get_idle()-1;
        //ELEVATOR CAPACITY
        //PICK UP DELAY
        
        sim_tick = 0;
        
        p_queue=new PriorityQueue<Object>();
        this.tick=1;
        
        
        
        for(int x=0; x<floors;x++)
        {
            building_floors[x] = new LinkedList<Person>();
        }
        //this.class_type="elevator";
    }
    public String get_file_name()
    {
        return this.infile.get_file_name();
    }
    public int get_num_ticks()
    {
        return this.num_of_ticks;
    }
    public int get_average_intransit_tick()
    {
        return (this.unloaded_tick-this.onloaded_tick)/divisor_for_intransit_tick;
    }
    //object that creates a person object that moves in the elevator
    public void set_floor_person_list(int floor, Person obj)
    {
        building_floors[floor].add(obj);
    }
    
    public LinkedList get_floor_person_list(int floor)
    {
        return building_floors[floor];
    }
    
    public void set_class_type(String class_type)
    {
        this.class_type=class_type;
    }
    
    public String get_class_type()
    {
        return class_type;
    }
    
    public void set_elevator_tick(int elevator_tick)
    {
        this.elevator_tick=elevator_tick;
    }
    
    public int get_elevator_tick()
    {
        return elevator_tick;
    }
    
    //output stats
    public int get_Average_person_wait_time()
    {
        return Math.abs(wait_time/divisor_for_average);
    }
    
    private void collective_up_collective_down(Object obj)
    {
                    if(((Elevator)obj).Get_direction() == true)
                    {
                        if(((Elevator)obj).Increment() == false)
                        {
                            ((Elevator)obj).Decrement();
                        }
                    }
                    else if(((Elevator)obj).Get_direction() == false)
                    {
                        if(((Elevator)obj).Decrement() == false)
                        {
                            ((Elevator)obj).Increment();
                        }
                    }   
        
    }
    //need to modify
    private void collective_up_selective_down(Object obj)
    {
        //set the lowest floor to the highest floor
        int lowest = floors;
        
                    if(((Elevator)obj).Get_direction() == true)
                    {
                        if(((Elevator)obj).Increment() == false)
                        {
                            ((Elevator)obj).Decrement();
                        }
                    }
                    
                    
                    else if(((Elevator)obj).Get_direction() == false)
                    {
                        //get the requests list
                        Iterator ite = ((Elevator)obj).Get_RequestIterator();
                        if (((Elevator)obj).Get_RequestQueue().size() == 0)
                            lowest = infile.get_floors();
                        while(ite.hasNext())
                        {
                            Request req = (Request)ite.next();
                            //check for the lowest request
                            if(req.get_from_floor_request() < lowest)
                            {
                                //set the lowest request
                                lowest = req.get_from_floor_request();
                            }
                        }
                        
                        //keep going down until reach the lowest requested floor
                        if(((Elevator)obj).Get_CurrentFloor() > lowest)
                        {
                            //ensure that it does no go past the requested floor
                            if(((Elevator)obj).Get_CurrentFloor() == lowest)
                            {
                                //change direction to up
                                ((Elevator)obj).Set_Direction(true);
                            }
                            else if(((Elevator)obj).Decrement() == false)
                            {
                                ((Elevator)obj).Increment();
                            }
                            
                        }
                        else if(((Elevator)obj).Get_CurrentFloor() == lowest)
                        {
                            ((Elevator)obj).Set_Direction(true);
                        }
                            
                    }   
        
    }
    
    
    //method to generate random number
    int random_generator(int num_to_avoid)
    {
        Random ran = new Random();
        int random= ran.nextInt(building_floors.length-1)+1;
        
        if (random == num_to_avoid)
        {
            random_generator(num_to_avoid);
        }
        return random;
    }
    
    
    public void start()            
    {
        p_queue.add(new Elevator(elevator_tick,floors));
        p_queue.add(new Create_class("people", people_tick));
        Object obj;
        
        while(sim_tick<num_of_ticks)
        {
            obj=p_queue.poll();
            
            if(obj instanceof Elevator)
            {
                    sim_tick = ((Elevator)obj).get_tick();
                    
                    switch(e_algorithm_type)
                    {
                        case 0:
                            collective_up_collective_down(obj);
                            break;
                         case 1:
                            collective_up_selective_down(obj);
                            break;
                            
                        default:
                            collective_up_collective_down(obj);
                            break;
                    }
                    ((Elevator)obj).set_tick(((Elevator)obj).get_tick()+elevator_tick);
                    

//picks/drop the people                        
                    Iterator iter = ((Elevator)obj).Get_RequestIterator();
                    
                    //Request elevReqs;
                    //iterate the requests on the elevator
                    while(iter.hasNext())
                    {
                        Request elevReqs = null;
                        try
                        {
                            elevReqs = (Request)iter.next();
                        }
                        catch(Exception e){
                            break;
                        }
                        
                        //Collective up collective down
                        if(e_algorithm_type == 0)
                        {
                            //pickup
                            if(elevReqs.get_from_floor_request() == ((Elevator)obj).Get_CurrentFloor())
                            {
                                Iterator iterf;
                                iterf = this.building_floors[((Elevator)obj).Get_CurrentFloor()].iterator();

                                Person tmpPer;
                                //Request perReq;
                                

                                //iterate the persons on the floor
                                while(iterf.hasNext())
                                {
                                        tmpPer = (Person)iterf.next();
                                        if(tmpPer.get_request().get_from_floor_request() == ((Elevator)obj).Get_CurrentFloor())
                                        {
                                            
                                            ((Elevator)obj).Set_Persons(tmpPer);
                                            
                                            iterf.remove();
                                        }
                                        tmpPer = null;
                                    
                                }


                            }
                            
                            //drop person off
                            if((elevReqs.get_to_floor_request() == ((Elevator)obj).Get_CurrentFloor()) && (((Elevator)obj).Get_PersonsLinkedList().size() > 0))
                            {
                                Iterator iterf;
                                iterf = ((Elevator)obj).Get_PersonsIterator();

                                Person tmpPer;
                                //Request perReq;
                                

                                //iterate the persons on the floor
                                while(iterf.hasNext())
                                {
                                        tmpPer = (Person)iterf.next();
                                        if(tmpPer.get_request().get_to_floor_request() == ((Elevator)obj).Get_CurrentFloor())
                                        {
                                             this.building_floors[((Elevator)obj).Get_CurrentFloor()].add(tmpPer);
                                            iterf.remove();
                                        }
                                        wait_time+=(((Elevator)obj).get_tick()-tmpPer.get_request().get_tick());
                                        divisor_for_average++;
                                        //System.out.println("wait time algorithm 0:"+wait_time);
                                        tmpPer = null;
                                    
                                }


                            }
                            //System.out.println(wait_time);
                            //System.out.println("divisor"+divisor_for_average);
                            
                        }
                        
                        
                        //Collective up selective down
                        else if(e_algorithm_type == 1)
                        {
                            //check that the elevator is moving up
                            //if(((Elevator)obj).Get_direction() == true)
                            {
                                
                            /*
                             * check if any request is in the Priority Event Queue, 
                             * for the floor the elevator is currently on
                             */
                                Iterator ite = p_queue.iterator();
                                //going through all items in the priority Queue
                                while(ite.hasNext())
                                {
                                    //holds the cureent object of the iterator
                                    Object tmp = ite.next();
                                    if( tmp instanceof Request)
                                    {
                                        //check if request floor is the same as elevator current floor
                                        // and direction == false, but the next tick will move the elevator
                                        // to floor 1 and direction to true
                                        if (  (((Request)tmp).get_from_floor_request() == 0) && (((Elevator)obj).Get_CurrentFloor() == 0) 
                                                && (((Elevator)obj).Get_direction() == false )  )
                                        {
                                            //put the request on the elevator
                                            ((Elevator)obj).Set_Request( ((Request)tmp));
                                        }
                                        else if (  (((Request)tmp).get_from_floor_request() == (((Elevator)obj).Get_CurrentFloor())) 
                                                && (((Elevator)obj).Get_direction() == true )  )
                                        {
                                            //put the request on the elevator
                                            ((Elevator)obj).Set_Request( ((Request)tmp));
                                        }
                                    }
                                }
                                
                                //if request(s) are present, put in elevator request queue
                            }
                                

                            
                            //pickup
                            if(elevReqs.get_from_floor_request() == ((Elevator)obj).Get_CurrentFloor())
                            {
                                Iterator iterf;
                                iterf = this.building_floors[((Elevator)obj).Get_CurrentFloor()].iterator();

                                Person tmpPer;
                                //Request perReq;
                                

                                //iterate the persons on the floor
                                while(iterf.hasNext())
                                {
                                        tmpPer = (Person)iterf.next();
                                        if(tmpPer.get_request().get_from_floor_request() == ((Elevator)obj).Get_CurrentFloor())
                                        {
/*
 * iterate the priority queue and find the correcpond Request for 
 * each person as you bring them unto the elevator
 */ 

Iterator it1 = p_queue.iterator();
while(it1.hasNext())
{
    Object ob = it1.next();
    if(ob instanceof Request)
    {
        if (    
                 (tmpPer.get_request().get_from_floor_request() == ((Request)ob).get_from_floor_request()) &&
                (tmpPer.get_request().get_to_floor_request() == ((Request)ob).get_to_floor_request()) &&
                (tmpPer.get_request().get_direction_request() == ((Request)ob).get_direction_request()) )
        {
            ((Elevator)obj).Set_Request((Request)ob);
            it1.remove();
            
        }
    }
}

//System.out.println("Elevator arrived at: "+((Elevator)obj).get_tick());

//System.out.println("Request was made at: "+ tmpPer.get_request().get_tick());


wait_time+=(((Elevator)obj).get_tick()-tmpPer.get_request().get_tick());
divisor_for_average++;
//System.out.println("wait time algorithm 1:"+wait_time);
//System.out.println(wait_time);

((Elevator)obj).Set_Persons(tmpPer);
                                            iterf.remove();
                                        }
                                        tmpPer = null;
                                   
                                }


                            }
                //floor traffic          
                for(int i=0;i<floors;i++)
                    {
                      System.out.println("Traffic on floor "+ i+": "+building_floors[i].size());
                    }
                //elevator traffic
                //System.out.println("Elevator currently contains : "+((Elevator)obj).Get_PersonsLinkedList().size());
                
                            //drop person off
                            if((elevReqs.get_to_floor_request() == ((Elevator)obj).Get_CurrentFloor()) &&
                                    (((Elevator)obj).Get_PersonsLinkedList().size() > 0))
                            {
                                Iterator iterf;
                                iterf = ((Elevator)obj).Get_PersonsIterator();

                                Person tmpPer;
                                //Request perReq;
                                

                                //iterate the persons on the floor
                                while(iterf.hasNext())
                                {
                                        tmpPer = (Person)iterf.next();
                                        if(tmpPer.get_request().get_to_floor_request() == ((Elevator)obj).Get_CurrentFloor())
                                        {
                                             this.building_floors[((Elevator)obj).Get_CurrentFloor()].add(tmpPer);
                                            iterf.remove();
                                            ((Elevator)obj).Remove_Request();
                                        }
                                        tmpPer = null;
                                    
                                }


                            }
                            
                            
                        }
                    }
//*************************                            

                    

                    
                        
                        
                        
                    p_queue.add(obj);
                    //System.out.println("Elevator time:"+((Elevator)obj).get_tick()+", direction: "+((Elevator)obj).Get_direction() + ", Floor:"+((Elevator)obj).Get_CurrentFloor());                ;
            }
            if(obj instanceof Person)
            {
                    sim_tick = ((Person)obj).get_tick();
                    
                    Request personRequest= new Request( ((Person)obj).get_tick());
                    //set the person's destination floor
                    //int request_floor;
                    //randomly picks a number between 0 and the max num of floors
                    Random ran = new Random();
                    dropofffloor=ran.nextInt(building_floors.length-1); // * (building_floors.length -1));
                    personRequest.set_to_floor_request(dropofffloor);
                    //sets the person's spawning floor
                    
                    //placeofbirth=random_generator(dropofffloor);
                    
                    personRequest.set_from_floor_request(placeofbirth);
                    //sets the random floor location for spawning but now working
                    //personRequest.set_from_floor_request(start_floor);                    
                    //if the current floor less than destination floor this means that the direction is up
                    if(personRequest.get_from_floor_request() < personRequest.get_to_floor_request())
                    {
                        personRequest.set_direction_request(true);
                    }
                    //else if the current floor is more than the destination florr this means that the direction is down
                    else if(personRequest.get_from_floor_request() > personRequest.get_to_floor_request())
                    {
                        personRequest.set_direction_request(false);
                    }
                    
                    Request elevRequest = personRequest;
                    
                    ((Person)obj).set_request(personRequest);
                    p_queue.add(elevRequest);
                    //put person on the floor linklist
//building_floors[((Person)obj).get_destination_floor()].add(((Person)obj));

                    building_floors[((Person)obj).get_request().get_from_floor_request()].add(((Person)obj));//after request is added                                         
                    
                //System.out.println("People time:"+((Person)obj).get_tick());
                //System.out.println("destination: "+((Person)obj).get_request().get_to_floor_request() + ", Floor:"+((Person)obj).get_current_floor());                    
                    
                    
                    
                    
                    //after request is added                                         
                   // System.out.println("People time:"+((Person)obj).get_tick())                ;
            }
                                             
            else if(obj instanceof Create_class)
            {
                if(((Create_class)obj).get_class_type().compareTo("people") == 0)
                {
                    sim_tick = ((Create_class)obj).get_tick();
                    
                    p_queue.add(new Person(sim_tick,2));
                    ((Create_class)obj).set_tick(sim_tick+people_tick);
                    p_queue.add(obj);

                }
            }
            else if(obj instanceof Request)
            {                                
                    Iterator it = p_queue.iterator();
                    Object elevator = null;
                    while(it.hasNext())
                    {
                        elevator = it.next();
                        if(elevator instanceof Elevator)
                            break;
                    }
                    if(this.e_algorithm_type == 0)
                    {
                        if((Elevator)elevator instanceof Elevator)
                        {                                                      
                            ((Elevator)elevator).Set_Request( ((Request)obj));
                        }
                    }

                    else if(this.e_algorithm_type == 1)
                    {
                        if((Elevator)elevator instanceof Elevator)
                        {
                            if((((Elevator)elevator).Get_CurrentFloor() == ((Request)obj).get_from_floor_request() )
                                  && (((Elevator)elevator).Get_direction() == true)  )
                            {
                                ((Elevator)elevator).Set_Request( ((Request)obj));
                            }
                            else
                            {
                                ((Request)obj).set_tick(sim_tick+elevator_tick);
                                p_queue.add((Request)obj);
                            }
                        }
                    } 
                                     
            }
        }
        
    }
         

    public static void main(String[] args)
    {
        Events event=new Events(args[0], args[1]);
        System.out.println("\nParameters above taken from "+event.get_file_name());
        System.out.println("***************************************************");
        System.out.println("\nResults:");
        event.start();   
        System.out.println("Average wait time for elevator:\n"
                + " "+event.get_Average_person_wait_time()+" ticks.\n"+" Using:"+args[1]+" ticks.\n"
                + ""+"Onloaded tick: "+event.get_average_intransit_tick());
        
    }
    
    
}

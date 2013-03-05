/*
 * Created by: Christopher Feveck
 * Purpose: To read in from a file the parameters required to 
 * instanciate the variables of the elevator simulator
 * Date: 26/02/2013 12:17pm 
 */
package Elevator;
import java.util.*;
import java.io.*;

public class Infile 
{
    private String textfile="text.txt";
    private String file_data;
    private String [][] opt;
    
    private int elevator_capacity;
    private int floors;
    private int idle;
    private int pick_up_delay;
    private int speed;
    private int algorithm;
    private int elevators;
    private int birthofperson;
    private int placeofbirth;
    
    public Infile(String file_name)
    {
        textfile=file_name;
        read_file();
    }
    
    public void get_data()
    {
        System.out.println(file_data);
        String data[]=file_data.split("-");
        opt = new String[data.length][];
        
        int cnt=0;
        
        for(String s:data)
        {
            System.out.println(s);
            opt[cnt] = s.split(" ");
            
            cnt +=1;
        }
        
        for(int x=0;x<opt.length;x++)
            for(String s:opt[x])
            {
                if(s.compareToIgnoreCase("ElevatorCapacity") ==0 )
                {
                    elevator_capacity = Integer.parseInt(opt[x][1]);
                }
                else if(s.compareToIgnoreCase("Floors") ==0 )
                {
                    floors = Integer.parseInt(opt[x][1]);
                }
                else if(s.compareToIgnoreCase("Idle") ==0 )
                {
                    idle = Integer.parseInt(opt[x][1]);
                }
                else if(s.compareToIgnoreCase("PickupDelay") ==0 )
                {
                    pick_up_delay= Integer.parseInt(opt[x][1]);
                }
                else if(s.compareToIgnoreCase("Speed") ==0 )
                {
                 speed= Integer.parseInt(opt[x][1]);   
                }
                else if(s.compareToIgnoreCase("Algorithm") ==0 )
                {
                    algorithm= Integer.parseInt(opt[x][1]);
                }
                
                else if(s.compareToIgnoreCase("Elevators") ==0 )
                {
                    elevators= Integer.parseInt(opt[x][1]);
                }
                
                else if(s.compareToIgnoreCase("Birthofperson") ==0 )
                {
                    birthofperson= Integer.parseInt(opt[x][1]);
                }
                
                else if(s.compareToIgnoreCase("placeofbirth") ==0 )
                {
                    placeofbirth= Integer.parseInt(opt[x][1]);
                }
            }
        
    }
    
    public int get_place_of_birth()
    {
        return this.placeofbirth;
    }
    
    public int get_elev_cap()
    {
        return this.elevator_capacity;
    }
    
    public int get_floors()
    {
        return floors;
    }
    
    public int get_elevators()
    {
        return this.elevators;
    }
    
    public int get_birth_of_person()
    {
        return birthofperson;
    }
    
    
    
    public int get_idle()
    {
        return idle;
    }
    
    public int get_pic_up_delay()
    {
        return this.pick_up_delay;
    }
    
    public int get_speed()
    {
        return speed;
    }
    
    public int get_algorithm()
    {
        return this.algorithm;
    }
    
    public void read_file()
    {
        
        try
        {
	    //System.out.println(System.getProperty("user.dir")+"\\"+textfile);
            FileInputStream fis=new FileInputStream(textfile);
            DataInputStream dis=new DataInputStream(fis);
            BufferedReader br=new BufferedReader(new InputStreamReader(dis));
            
            file_data=br.readLine();
            
            br.close();
        }
        catch(Exception e)
        {
            
        }
                
        
    }
    /*
    public static void main(String[] args)
    {
        Infile tst =new Infile("textfile.txt");
        tst.get_data();
        
        System.out.println(tst.get_algorithm());
        System.out.println(tst.get_elev_cap());
        System.out.println(tst.get_floors());
        System.out.println(tst.get_idle());
        System.out.println(tst.get_pic_up_delay());
        System.out.println(tst.get_speed());
    }
    * 
    */
    
}

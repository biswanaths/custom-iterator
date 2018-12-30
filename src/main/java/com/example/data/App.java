package com.example.data;

import com.example.data.model.Employee;
import com.example.data.model.Address;

public class App 
{
    public static void main( String[] args )
    {
        DataReader dr = new DataReader(); 
        for(Employee e: dr) { 
            System.out.println(e);
            for(Address a:e.getAddresses()) { 
                System.out.println("\t" + a );
            }
        }
        System.out.println( "Done !" );
    }
}

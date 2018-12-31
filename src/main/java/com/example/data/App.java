package com.example.data;

import java.io.IOException; 

import com.example.data.model.Employee;
import com.example.data.model.Address;

public class App 
{

    final static String empFile = "employee.csv";
    final static String addFile = "address.csv";

    public static void main( String[] args ) throws IOException 
    {
        EmployeeDataReader dr = new EmployeeDataReader(empFile, addFile); 
        for(Employee e: dr) { 
            System.out.println(e);
            for(Address a:e.getAddresses()) { 
                System.out.println("\t" + a );
            }
        }
        System.out.println( "Done !" );
    }
}

package com.example.data;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


import com.example.data.model.Employee;
import com.example.data.model.Address;
import com.opencsv.CSVReader;


public class DataReader implements Iterable<Employee> 
{
    @Override
    public Iterator<Employee> iterator() { 
        // this is bad. Need to find a better way to 
        // float the exception up. 
        // may be move the initalization of the 
        // iterator to constructor. Then the whole point 
        // of releasing resource. May be need to use 
        // a builder class 
        try { 
            return new DataIterator();
        } 
        catch(Exception e) { 
            System.out.println(e);
            return null;
        }
    }

    public class DataIterator implements Iterator<Employee> { 

        Iterator<String[]> empIt, addIt;

        Address prevAdd;

        public DataIterator() throws IOException {
            empIt = new CSVReader(new FileReader("employee.csv")).iterator();
            addIt = new CSVReader(new FileReader("address.csv")).iterator();
            if(addIt.hasNext())
                prevAdd = toAddress(addIt.next());
        } 

        @Override 
        public boolean hasNext() { 
            return empIt.hasNext();
        }

        @Override 
        public Employee next() {
            String[] empRow = empIt.next();
            Employee emp = new Employee();
            emp.setId(empRow[0]);
            emp.setName(empRow[1]);
            while(prevAdd != null &&  prevAdd.getEmpId().equals(emp.getId()) ) { 
                emp.getAddresses().add(prevAdd);
                if(addIt.hasNext())
                    prevAdd  = toAddress(addIt.next());
                else 
                    prevAdd = null;
            }
            return emp;
        }

        public Address toAddress(String[] addRow) { 
            Address address = new Address();
            address.setId(addRow[0]);
            address.setName(addRow[1]);
            address.setEmpId(addRow[2]);
            return address;
        }

    }


}

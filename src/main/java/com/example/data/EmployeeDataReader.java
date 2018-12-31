package com.example.data;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


import com.example.data.model.Employee;
import com.example.data.model.Address;
import com.opencsv.CSVReader;


public class EmployeeDataReader implements Iterable<Employee> 
{

    CSVReader empReader, addReader;

    public EmployeeDataReader(String empFile, String addFile) throws IOException { 
        empReader  = new CSVReader(new FileReader(empFile));
        addReader  = new CSVReader(new FileReader(addFile));
    }

    @Override
    public Iterator<Employee> iterator() { 
        return new DataIterator(empReader, addReader);
    }

    public class DataIterator implements Iterator<Employee> { 

        Iterator<String[]> empIt, addIt;

        Address prevAdd;

        public DataIterator(CSVReader empReader, CSVReader addReader) {
            empIt = empReader.iterator();
            addIt = addReader.iterator();
            if(addIt.hasNext())
                prevAdd = convertToAddress(addIt.next());
        } 

        @Override 
        public boolean hasNext() { 
            return empIt.hasNext();
        }

        @Override 
        public Employee next() {
            Employee emp = covertToEmployee(empIt.next());
            while(prevAdd != null &&  prevAdd.getEmpId().equals(emp.getId()) ) { 
                emp.getAddresses().add(prevAdd);
                if(addIt.hasNext())
                    prevAdd  = convertToAddress(addIt.next());
                else 
                    prevAdd = null;
            }
            return emp;
        }

        public Address convertToAddress(String[] addRow) { 
            Address address = new Address();
            address.setId(addRow[0]);
            address.setName(addRow[1]);
            address.setEmpId(addRow[2]);
            return address;
        }

        public Employee covertToEmployee(String[] empRow) { 
            Employee emp = new Employee();
            emp.setId(empRow[0]);
            emp.setName(empRow[1]);
            return emp;
        }


    }


}

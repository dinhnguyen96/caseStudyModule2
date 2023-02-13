package controller;

import model.Customer;
import storage.CustomerReadWrite;

import java.util.List;

public class CustomerManager implements ApplicationManager<Customer>
{
    private CustomerReadWrite customerReadWrite = CustomerReadWrite.getInstance();

    public CustomerManager()
    {

    }

    @Override
    public List<Customer> readFile()
    {
       return customerReadWrite.readFile();
    }

    @Override
    public void writeFile(List<Customer> customerList)
    {
       customerReadWrite.writeFile(customerList);
    }
}

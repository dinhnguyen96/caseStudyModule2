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

    @Override
    public Customer get(String code) {
        return null;
    }

    @Override
    public boolean add(Customer customer) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public void remove(Customer customer) {

    }
}

package controller.customer;

import controller.manager.GeneralFunction;
import model.Customer;
import storage.CustomerReadWrite;
import storage.GetData;

import java.util.List;

public class CustomerManager implements GeneralFunction<Customer>
{
    private GetData<Customer> customerReadWrite;

    private List<Customer> customerList;

    public CustomerManager()
    {
        customerReadWrite = CustomerReadWrite.getInstance();

        customerList = customerReadWrite.readFile();

    }

    @Override
    public List<Customer> readFile()
    {
       return customerList;
    }

    @Override
    public void writeFile(List<Customer> customerList)
    {
       customerReadWrite.writeFile(customerList);
    }

    @Override
    public Customer get(String customerCodeEmailUser)
    {
        for (Customer customer:readFile())
        {
            if (customer.getCustomerCode().equalsIgnoreCase(customerCodeEmailUser) ||
                    customer.getUser().getUsername().equalsIgnoreCase(customerCodeEmailUser) ||
                    customer.getEmail().equalsIgnoreCase(customerCodeEmailUser) )
            {
                return customer;
            }
        }
        return null;
    }
    @Override
    public boolean add(Customer customer)
    {
        Customer customerCode = get(customer.getCustomerCode());
        Customer customerEmail = get(customer.getEmail());
        Customer customerUserName = get(customer.getUser().getUsername());
        if (customerCode != null || customerEmail != null || customerUserName != null)
        {
            return false;
        }
        List<Customer> updateCustomertList = readFile();
        updateCustomertList.add(customer);
        writeFile(updateCustomertList);
        return true;
    }
    @Override
    public boolean update(Customer customer)
    {
        Customer c = get(customer.getCustomerCode());
        if (c != null)
        {
            List<Customer> updateCustomerList = readFile();
            updateCustomerList.set(updateCustomerList.indexOf(c), c);
            writeFile(updateCustomerList);
            return true;
        }
        return false;
    }
    @Override
    public boolean remove(Customer customer)
    {
        Customer c = get(customer.getCustomerCode());
        if (c != null)
        {
            List<Customer> updateCustomerList = readFile();
            updateCustomerList.remove(c);
            writeFile(updateCustomerList);
            return true;
        }
        return false;
    }

}

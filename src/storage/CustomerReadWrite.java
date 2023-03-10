package storage;

import model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerReadWrite extends ReadWrite<Customer>
{

    private static ReadWrite<Customer> customerReadWrite;

    private CustomerReadWrite(String pathDataFile)
    {
        super(pathDataFile);
    }

    public static ReadWrite<Customer> getInstance()
    {
        if (customerReadWrite == null)
        {
           customerReadWrite = new CustomerReadWrite("src/storage/file/customer.dat");
        }
        return customerReadWrite;
    }

    @Override
    public List<Customer> readFile()
    {
        try(InputStream customerDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(customerDataFile) )
        {
           List<Customer> customerList  = (List<Customer>)objectInputStream.readObject();
           return customerList;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file customer !");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Không tìm thấy lớp hợp lệ!");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi đọc dữ liệu file customer");
        }
        return null;
    }

    @Override
    public void writeFile(List<Customer> customerList)
    {
        try(OutputStream customerFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(customerFile))
        {
            objectOutStream.writeObject(customerList);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file customer !");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi ghi file dữ liệu customer !");
        }

    }
}

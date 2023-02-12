package controller;

import model.Customer;
import model.Product;

import java.io.*;
import java.util.List;

public class CustomerManager extends ApplicationManager<Customer>
{
    public CustomerManager(String pathDataFile)
    {
        super(pathDataFile);
    }

    @Override
    public List<Customer> readFile()
    {
        try(InputStream customerDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(customerDataFile) )
        {
            List<Customer> customerList = (List<Customer>)objectInputStream.readObject();
            return customerList;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file !");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Không tìm thấy lớp hợp lệ!");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi đọc file dữ liệu !");
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
            System.out.println("Ghi thành công ! ");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file !");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi đọc file dữ liệu !");
        }
    }
}

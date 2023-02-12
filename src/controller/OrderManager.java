package controller;

import model.Customer;
import model.Order;

import java.io.*;
import java.util.List;

public class OrderManager extends ApplicationManager<Order>
{
    public OrderManager(String pathDataFile)
    {
        super(pathDataFile);
    }

    @Override
    public List<Order> readFile() {
        try(InputStream customerDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(customerDataFile) )
        {
            List<Order> orderList = (List<Order>)objectInputStream.readObject();
            return orderList;
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
    public void writeFile(List<Order> orderList)
    {
        try(OutputStream orderFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(orderFile))
        {
            objectOutStream.writeObject(orderList);
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

package storage;

import model.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderReadWrite extends ReadWrite<Order>
{
    private static ReadWrite<Order> orderReadWrite;

    private OrderReadWrite(String pathDataFile) {
        super(pathDataFile);
    }

    public static ReadWrite<Order> getInstance()
    {
        if (orderReadWrite == null)
        {
            orderReadWrite = new OrderReadWrite("src/storage/file/order.dat");
        }
        return orderReadWrite;
    }

    @Override
    public List<Order> readFile()
    {
        try(InputStream customerDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(customerDataFile) )
        {
            List<Order> orderList  = (List<Order>)objectInputStream.readObject();
            return  orderList;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file order !");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Không tìm thấy lớp hợp lệ!");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi đọc dữ liệu file order !");
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
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file order!");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi ghi file dữ liệu order!");
        }
    }
}

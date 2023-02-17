package storage;

import model.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderReadWrite extends GetData<Order>
{
    private static OrderReadWrite orderReadWrite;

    private OrderReadWrite(String pathDataFile) {
        super(pathDataFile);
    }

    public static OrderReadWrite getInstance()
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
        List<Order> orderList = null;
        try(InputStream customerDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(customerDataFile) )
        {
           orderList  = (List<Order>)objectInputStream.readObject();
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
            orderList = new ArrayList<>();
        }
        return orderList;
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

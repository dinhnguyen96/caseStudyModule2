package storage;

import model.OrderDetail;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailReadWrite extends ReadWrite<OrderDetail>
{

    private static OrderDetailReadWrite orderDetailReadWrite;

    private OrderDetailReadWrite(String pathDataFile)
    {
        super(pathDataFile);
    }

    public static OrderDetailReadWrite getInstance()
    {
        if (orderDetailReadWrite == null)
        {
            orderDetailReadWrite = new OrderDetailReadWrite("src/storage/file/orderdetail.dat");
        }
        return orderDetailReadWrite;
    }
    @Override
    public List<OrderDetail> readFile()
    {
        List<OrderDetail> orderDetailList = null;
        try(InputStream orderDetailDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(orderDetailDataFile) )
        {
            orderDetailList = (List<OrderDetail>)objectInputStream.readObject();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file order detail !");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Không tìm thấy lớp hợp lệ!");
        }
        catch (IOException e)
        {
            orderDetailList = new ArrayList<>();
        }
        return orderDetailList;
    }

    @Override
    public void writeFile(List<OrderDetail> orderDetailList)
    {
        try(OutputStream orderDetailFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(orderDetailFile))
        {
            objectOutStream.writeObject(orderDetailList);

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Không tìm thấy đường dẫn file order detail !");
        }
        catch (IOException e)
        {
            System.out.println("Lỗi ghi file dữ liệu order detail !");
        }
    }
}

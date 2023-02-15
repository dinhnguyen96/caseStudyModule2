package storage;

import model.Order;
import model.OrderDetail;

import java.io.*;
import java.util.List;

public class OrderDetailReadWrite extends GetData<OrderDetail>
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
        try(InputStream orderDetailDataFile = new FileInputStream(getPathDataFile());
            ObjectInputStream objectInputStream = new ObjectInputStream(orderDetailDataFile) )
        {
            List<OrderDetail> orderDetailList = (List<OrderDetail>)objectInputStream.readObject();
            return orderDetailList;
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
            System.out.println("Lỗi đọc file dữ liệu order detail !");
        }
        return null;
    }

    @Override
    public void writeFile(List<OrderDetail> orderDetailList)
    {
        try(OutputStream orderDetailFile = new FileOutputStream(getPathDataFile());
            ObjectOutputStream objectOutStream = new ObjectOutputStream(orderDetailFile))
        {
            objectOutStream.writeObject(orderDetailList);
            System.out.println("Ghi thành công ! ");

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

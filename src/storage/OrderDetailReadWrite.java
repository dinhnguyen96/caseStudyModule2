package storage;

import model.OrderDetail;

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
        return null;
    }

    @Override
    public void writeFile(List<OrderDetail> dataList)
    {

    }
}

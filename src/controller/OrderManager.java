package controller;

import model.Order;
import storage.OrderReadWrite;

import java.util.List;

public class OrderManager implements ApplicationManager<Order>
{
    private OrderReadWrite orderReadWrite = OrderReadWrite.getInstance();

    public OrderManager()
    {

    }

    @Override
    public List<Order> readFile()
    {
        return orderReadWrite.readFile();

    }

    @Override
    public void writeFile(List<Order> orderList)
    {
       orderReadWrite.writeFile(orderList);
    }
}

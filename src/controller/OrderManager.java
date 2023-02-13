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

    @Override
    public Order get(String code) {
        return null;
    }

    @Override
    public boolean add(Order order) {
        return false;
    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public void remove(Order order) {

    }
}

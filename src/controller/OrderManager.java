package controller;

import model.Order;
import storage.OrderReadWrite;

import java.util.List;

public class OrderManager implements ApplicationManager<Order>, AdditionalFunction<Order>
{
    private OrderReadWrite orderReadWrite;

    private List<Order> orderList;

    public OrderManager()
    {
       orderReadWrite  = OrderReadWrite.getInstance();

       orderList = orderReadWrite.readFile();
    }

    @Override
    public List<Order> readFile()
    {
        return orderList;

    }

    @Override
    public void writeFile(List<Order> orderList)
    {
       orderReadWrite.writeFile(orderList);
    }

    @Override
    public Order get(String code)
    {
        for (Order order:readFile())
        {
            if (order.getOrderCode().equalsIgnoreCase(code))
            {
                return order;
            }
        }
        return null;
    }

    @Override
    public boolean add(Order order)
    {
        Order o = get(order.getOrderCode());
        if (o != null)
        {
            return false;
        }
        o = order;
        List<Order> updateOrderList = readFile();
        updateOrderList.add(o);
        writeFile(updateOrderList);
        return true;
    }

    @Override
    public boolean update(Order order)
    {
        Order o = get(order.getOrderCode());
        if (o != null)
        {
            List<Order> updateOrderList = readFile();
            updateOrderList.set(updateOrderList.indexOf(o),  o);
            writeFile(updateOrderList);
            return true;
        }
        return false;

    }

    @Override
    public boolean remove(Order order)
    {
        Order o = get(order.getOrderCode());
        if (o != null)
        {
            List<Order> updateOrderList = readFile();
            updateOrderList.remove(o);
            writeFile(updateOrderList);
            return true;
        }
        return false;
    }
}

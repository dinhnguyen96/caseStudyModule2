package controller.order;

import controller.manager.GeneralFunction;
import model.Order;
import storage.ReadWrite;
import storage.OrderReadWrite;

import java.util.List;

public class OrderManager implements GeneralFunction<Order>
{
    private ReadWrite<Order> orderReadWrite;

    private List<Order> orderList;


    public static boolean orderDateCheck = false;

    public OrderManager()
    {
       orderReadWrite  = OrderReadWrite.getInstance();

       orderList = orderReadWrite.readFile();
    }

    @Override
    public List<Order> readFile()
    {
        if (orderDateCheck)
        {
            orderList = orderReadWrite.readFile();
        }
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
    public void update(Order order)
    {
        List<Order> updateOrderList = readFile();
        updateOrderList.set(updateOrderList.indexOf(order),  order);
        writeFile(updateOrderList);

    }

    @Override
    public void remove(Order order)
    {
        List<Order> updateOrderList = readFile();
        updateOrderList.remove(order);
        writeFile(updateOrderList);
    }

    @Override
    public List<Order> searchByName(String name) {
        return null;
    }
}

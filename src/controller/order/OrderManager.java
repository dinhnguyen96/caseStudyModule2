package controller.order;

import controller.manager.GeneralFunction;
import model.Order;
import storage.GetData;
import storage.OrderReadWrite;

import java.util.List;

public class OrderManager implements GeneralFunction<Order>
{
    private GetData<Order> orderReadWrite;

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
        List<Order> updateOrderList = readFile();
        updateOrderList.set(updateOrderList.indexOf(order),  order);
        writeFile(updateOrderList);
        return true;

    }

    @Override
    public boolean remove(Order order)
    {
        List<Order> updateOrderList = readFile();
        updateOrderList.remove(order);
        writeFile(updateOrderList);
        return true;
    }
}

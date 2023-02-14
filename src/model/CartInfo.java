package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartInfo implements Serializable
{
    private List<Item> cartList;

    private Customer customer;

    public CartInfo(Customer customer)
    {
        this.cartList = new ArrayList<>();
        this.customer = customer;
    }

    public List<Item> getCartList()
    {
        return cartList;
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }
}

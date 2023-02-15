package model;

import java.io.Serializable;

public class Order implements Serializable
{
    private Long id;

    private String orderCode;

    private String dateOfOrder;

    private Customer customer;

    public Order(Long id, String orderCode, String dateOfOrder, Customer customer)
    {
        this.id = id;
        this.orderCode = orderCode;
        this.dateOfOrder = dateOfOrder;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

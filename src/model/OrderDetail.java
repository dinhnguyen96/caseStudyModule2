package model;

import java.io.Serializable;

public class OrderDetail implements Serializable
{
    private Long id;

    private Order order;

    private Product product;


    public OrderDetail(Long id, Order order, Product product)
    {
        this.id = id;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

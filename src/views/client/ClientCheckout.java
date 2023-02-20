package views.client;

import controller.manager.GeneralFunction;
import controller.order.OrderManager;
import controller.shoppingcart.ApplicationShoppingCart;
import controller.shoppingcart.ShoppingCartManager;
import model.CartInfo;
import model.Item;
import model.Order;
import model.OrderDetail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class ClientCheckout {

    private static ApplicationShoppingCart applicationShoppingCart = new ShoppingCartManager();

    private static GeneralFunction<Order> orderGeneralFunction = new OrderManager();


    public static void checkOut()
    {
        CartInfo cartInfo = applicationShoppingCart.get();
        Scanner input = new Scanner(System.in);
        if (cartInfo.getCartList().size() > 0)
        {
            System.out.println("Thông tin thanh toán ");
            System.out.println("Name : " + cartInfo.getCustomer().getCustomerName());
            System.out.println("Email : " + cartInfo.getCustomer().getEmail());
            for (Item item : cartInfo.getCartList())
            {
                System.out.println("Product Code : " + item.getProduct().getProductCode());
                System.out.println("Product Name : " + item.getProduct().getProductName());
                System.out.println("Product Price : " + item.getProduct().getProductPrice());
                System.out.println("Product Quanity: " + item.getQuantity());
            }
            System.out.println("Total : " + cartInfo.getTotal());
            System.out.println("1. Tôi muốn thanh toán");
            System.out.println("2. Quay lại trang chủ");
            System.out.print("Bạn muốn thanh toán không ? : ");
            int answer = Integer.parseInt(input.nextLine());
            switch (answer)
            {
                case 1 -> {
                    createOrder();
                    System.out.println("Thanh toán thành công ! ");
                    ClientShoppingCart.removeAllItem();
                }
                case 2->{
                    ClientTemplate.clientTemplate();;
                }
            }
        }
        else
        {
            System.out.println("Không có sản phẩm nào để thanh toán ");
        }
    }

    public static void createOrder()
    {
        CartInfo cartInfo = applicationShoppingCart.get();
        List<Order> orderList = orderGeneralFunction.readFile();
        LocalDate localDate = LocalDate.now();
        Order order;

        DateTimeFormatter orderDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (orderList == null ||orderList.size() == 0)
        {
           order = new Order(1L, "1",orderDate.format(localDate),cartInfo.getCustomer());
        }
        else
        {
            order = new Order(orderList.get(orderList.size()-1).getId()+1,String.valueOf(orderList.get(orderList.size()-1).getId()+1),
                    orderDate.format(localDate), cartInfo.getCustomer());
        }
        orderGeneralFunction.add(order);
        OrderManager.orderDateCheck = true;
    }

}

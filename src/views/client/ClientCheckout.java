package views.client;

import controller.shoppingcart.ApplicationShoppingCart;
import controller.shoppingcart.ShoppingCartManager;
import model.CartInfo;
import model.Item;

import java.util.Scanner;

public class ClientCheckout {

    private static ApplicationShoppingCart applicationShoppingCart = new ShoppingCartManager();

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
                    ClientShoppingCart.removeAllItem();
                    System.out.println("Thanh toán thành công ! ");
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

}

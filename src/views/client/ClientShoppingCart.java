package views.client;

import controller.manager.GeneralFunction;
import controller.product.ProductManager;
import controller.shoppingcart.ApplicationShoppingCart;
import controller.shoppingcart.ShoppingCartManager;
import model.CartInfo;
import model.Item;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class ClientShoppingCart {


    private static GeneralFunction<Product> productGeneralFunction = new ProductManager();
    private static ApplicationShoppingCart applicationShoppingCart = new ShoppingCartManager();

    public static void cartDisplay()
    {
        CartInfo cartInfo = applicationShoppingCart.get();
        if (cartInfo == null || cartInfo.getCartList().size() == 0)
        {
            System.out.println("Giỏ hàng đang còn trống");
            return;
        }
        List<Item> itemList = cartInfo.getCartList();
        System.out.println("Danh sách giỏ hàng ");
        int position = 0;
        for (Item item : itemList)
        {
            System.out.println(++position);
            System.out.println("Product code : " + item.getProduct().getProductCode());
            System.out.println("Product name : " + item.getProduct().getProductName());
            System.out.println("Product Price : " + item.getProduct().getProductPrice());
            System.out.println("Product Quantity : " + item.getQuantity());
            System.out.println("Total : " + item.itemTotal());
        }

        System.out.println("All Product Total : " + cartInfo.getTotal());

    }
    public static void addItem()
    {
        boolean exixts = false;
        Scanner input = new Scanner(System.in);
        Product product ;
        do {
            System.out.print("Nhập mã sản phẩm  : ");
            String productCode =  input.nextLine();
            product = productGeneralFunction.get(productCode);
            if (product == null)
            {
                System.out.println("Sản phẩm không tồn tại !");
            }
            else
            {
                exixts = true;
            }
        }
        while (!exixts);
        Item item = new Item();
        item.setProduct(product);
        applicationShoppingCart.addItem(item);
        System.out.println("Thêm sản phẩm vào giỏ hàng thành công");
        ShoppingCartManager.shoppingCartDataChecked = true;
    }

    public static void removeItem()
    {
        CartInfo cartInfo = applicationShoppingCart.get();
        if (cartInfo.getCartList().size() > 0)
        {
            boolean exixts = false;
            Scanner input = new Scanner(System.in);
            Product product ;
            do {
                System.out.print("Nhập mã sản phẩm  : ");
                String productCode =  input.nextLine();
                product = productGeneralFunction.get(productCode);
                if (product == null)
                {
                    System.out.println("Sản phẩm không tồn tại !");
                }
                else
                {
                    exixts = true;
                }
            }
            while (!exixts);
            boolean removeStatus  = false;
            for (Item item: cartInfo.getCartList())
            {
                if (item.getProduct().getProductCode().equals(product.getProductCode()))
                {
                    applicationShoppingCart.removeItem(item.getProduct().getProductCode());
                    removeStatus = true;
                    break;
                }
            }
            if (removeStatus)
            {
                System.out.println("Xóa sản phẩm ra khỏi giỏ hàng thành công");
                ShoppingCartManager.shoppingCartDataChecked = true;
            }
            else
            {
                System.out.println("Sản phẩm không tồn tại trong giỏ hàng");
            }
        }
        else
        {
            System.out.println("Giỏ hàng không có sản phẩm để xóa");
        }
    }
    public static void removeAllItem()
    {
        CartInfo cartInfo = applicationShoppingCart.get();
        if (cartInfo.getCartList().size() > 0)
        {
            applicationShoppingCart.removeAllItem();
            System.out.println("Xóa thành công ");
            ShoppingCartManager.shoppingCartDataChecked = true;
        }
        else
        {
            System.out.println("Giỏ hàng không có sản phẩm để xóa");
        }

    }
}

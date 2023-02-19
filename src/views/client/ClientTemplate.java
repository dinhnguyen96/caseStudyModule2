package views.client;

import controller.login_logout.Signout;
import controller.product.ProductManager;
import controller.product.ProductSearch;
import controller.shoppingcart.ShoppingCartManager;
import model.Product;
import views.LoginTemplate;

import java.util.List;
import java.util.Scanner;

public class ClientTemplate
{
    private static ProductSearch productGeneralFunction = new ProductManager();


    public static void productDisplay()
    {
        try {
            List<Product> productList = productGeneralFunction.readFile();
            for (Product product : productList)
            {
                System.out.println("Product "+product.getId());
                System.out.println("Product Code : " + product.getProductCode());
                System.out.println("Product Name : " + product.getProductName());
                System.out.println("Product Price : " + product.getProductPrice());
                System.out.println("Product Describe : " + product.getProductDescribe());
                System.out.println("Product Categories : " + product.getCategories().getCategoriesName());
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Không có sản phẩm nào trong danh sách");
        }
    }

    public static void clientTemplate()
    {
        Scanner input = new Scanner(System.in);
        boolean exits = false;
        do {
            try
            {
                System.out.println("Menu");
                System.out.println("1.Hiển thị sản phẩm ");
                System.out.println("2.Thêm sản phẩm vào giỏ hàng ");
                System.out.println("3.Xem giỏ hàng ");
                System.out.println("4.Tìm kiếm sản phẩm ");
                System.out.println("5.Đăng xuất");
                System.out.println("6.Thoát");
                System.out.print("Mời bạn chọn chức năng : ");
                int functionCode = Integer.parseInt(input.nextLine());
                switch (functionCode) {
                    case 1 -> {
                       productDisplay();
                    }
                    case 2 -> {

                    }
                    case 3 -> {
//                        ClientShoppingCart.cartDisplay();
                    }
                    case 4 -> {
                        System.out.print("Nhập tên sảm phẩm tìm kiếm : ");
                        String productName = input.nextLine();
                        List<Product> productsSeacrh = productGeneralFunction.searchByName(productName);
                        if (productsSeacrh.size() == 0)
                        {
                            System.out.println("Không tìm thấy sản phẩm");
                        }
                        else
                        {
                            for (Product product : productsSeacrh)
                            {
                                System.out.println("Product "+product.getId());
                                System.out.println("Product Code : " + product.getProductCode());
                                System.out.println("Product Name : " + product.getProductName());
                                System.out.println("Product Price : " + product.getProductPrice());
                                System.out.println("Product Describe : " + product.getProductDescribe());
                            }
                        }
                    }
                    case 5 -> {
                        signOut();
                        exits = true;
                    }
                    case 6 ->{
                        System.exit(6);
                    }
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Nhập không hợp lệ");
            }
        }
        while(!exits) ;
    }

    public static void signOut()
    {
        Signout.getInstance().signOut();
        LoginTemplate.loginStatus();
    }
}

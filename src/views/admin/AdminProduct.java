package views.admin;

import controller.categories.CategoriesManager;
import controller.manager.GeneralFunction;
import controller.product.ProductManager;
import model.Categories;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class AdminProduct {


    public static GeneralFunction<Product> productGeneralFunction = new ProductManager();

    public  static GeneralFunction<Categories> categoriesGeneralFunction = new CategoriesManager();

    public static List<Product> productList = productGeneralFunction.readFile();


    public static void addProduct()
    {
        boolean checked = false;
        do {
            try
            {
                System.out.println("Thêm sản phẩm ");
                Scanner input = new Scanner(System.in);
                System.out.print("Product Name :");
                String productName = input.nextLine();
                System.out.print("Product Price :");
                double productPrice = Double.parseDouble(input.nextLine());
                System.out.print("Product Describe : ");
                String productDescribe = input.nextLine();
                System.out.print("Categories Type : ");
                String categorieType = input.nextLine();
                Categories categories = categoriesGeneralFunction.get(categorieType);
                Product product = new Product(productList.get(productList.size()-1).getId()+1,String.valueOf(productList.get(productList.size()-1).getId()+1),
                                    productName, productPrice, productDescribe, categories);
                checked =  productGeneralFunction.add(product);
            }
            catch (NumberFormatException | NullPointerException e)
            {
                System.out.println("Nhập không hợp lệ !");
            }
        }
        while (!checked);
        System.out.println("Thêm thành công ");
    }

    public static void updateProduct()
    {
        Scanner input = new Scanner(System.in);
        boolean checked = false;
        do {
            String productName="",  productDescribe="",  categorieType="";
            double productPrice=0.0;
            try
            {
                System.out.println("Cập nhật sản phẩm ");
                System.out.print("Product Code :");
                String productCode = input.nextLine();
                Product product = productGeneralFunction.get(productCode);
                if (product != null)
                {
                    System.out.print("Bạn muốn cập nhật thông tin nào : ");
                    int number = Integer.parseInt(input.nextLine());
                    switch (number)
                    {
                        case 1->{
                            System.out.print("Product Name :");
                            productName = input.nextLine();
                            product.setProductName(productName);
                        }
                        case 2->{
                            System.out.print("Product Price :");
                            productPrice = Double.parseDouble(input.nextLine());
                            product.setProductPrice(productPrice);
                        }
                        case 3->{
                            System.out.print("Product Describe : ");
                            productDescribe = input.nextLine();
                            product.setProductDescribe(productDescribe);
                        }
                        case 4->
                        {
                            System.out.print("Categories Type : ");
                            categorieType = input.nextLine();
                            Categories categories = categoriesGeneralFunction.get(categorieType);
                            if (categories != null)
                            {
                               product.setCategories(categories);
                            }

                        }
                   }
                   checked =  productGeneralFunction.update(product);
                    checked = true;
                }
            }
            catch (NumberFormatException | NullPointerException e)
            {
                System.out.println("Nhập không hợp lệ !");
            }
        }
        while (!checked);
        System.out.println("Cập nhật thành công ");
    }

    public static void removeProduct()
    {
        Scanner input = new Scanner(System.in);
        boolean checked = false;
        do {
            System.out.println("Xóa  sản phẩm ");
            System.out.print("Product Code :");
            String productCode = input.nextLine();
            Product product = productGeneralFunction.get(productCode);
            if (product != null)
            {
                productGeneralFunction.remove(product);
                checked = true;
            }
        }
        while (!checked);
        System.out.println("Xóa thành công ");
    }

    public static void functionSelection()
    {
        boolean checked = false;
        Scanner input = new Scanner(System.in);
        System.out.print("Mời lựa chọn chức năng sản phẩm : ");
        int function = input.nextInt();
        do{
            switch (function)
            {
                case 1->{
                    addProduct();
                }
                case 2 -> {
                   updateProduct();
                }
                case 3 ->{
                    removeProduct();
                }
                default -> {
                    checked = true;
                }
            }
        }
        while (!checked);

    }
}

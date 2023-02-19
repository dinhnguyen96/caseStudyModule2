package views.admin;

import controller.categories.CategoriesManager;
import controller.manager.GeneralFunction;
import controller.product.ProductManager;
import controller.product.ProductSearch;
import model.Categories;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdminProduct {

    private static ProductSearch productGeneralFunction = new ProductManager();

    private  static GeneralFunction<Categories> categoriesGeneralFunction = new CategoriesManager();

    private static List<Product> productList = productGeneralFunction.readFile();


    private static void addProduct()
    {
        boolean checked = false;
        Categories categories;
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
                categories = categoriesGeneralFunction.get(categorieType);
                if (categories == null)
                {
                    System.out.println("Danh mục này không tồn tại");
                }
                else
                {
                    Product product = new Product(productList.get(productList.size()-1).getId()+1,String.valueOf(productList.get(productList.size()-1).getId()+1),
                            productName, productPrice, productDescribe, categories);
                    checked =  productGeneralFunction.add(product);
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Nhập không hợp lệ !");
            }
        }
        while (!checked);
        System.out.println("Thêm thành công ");
    }

    private static void updateProduct()
    {
        Scanner input = new Scanner(System.in);
        boolean updateChecked = false;
        do {
            String productName,  productDescribe,  categorieType;
            double productPrice;
            try
            {
                System.out.println("Cập nhật sản phẩm ");
                System.out.print("Product Code :");
                String productCode = input.nextLine();
                Product product = productGeneralFunction.get(productCode);
                if (product != null)
                {
                    System.out.println("Menu");
                    System.out.println("1.Product Name");
                    System.out.println("2.Product Price");
                    System.out.println("3.Product Describe");
                    System.out.println("4.Categories Type");
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
                   productGeneralFunction.update(product);
                   updateChecked = true;
                }
                else
                {
                    System.out.println("Sản phẩm này không tồn tại!");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Nhập không hợp lệ !");
            }
        }
        while (!updateChecked);
        System.out.println("Cập nhật thành công !");
    }

    private static void removeProduct()
    {
        Scanner input = new Scanner(System.in);
        boolean removeChecked = false;
        do {
            System.out.println("Xóa  sản phẩm ");
            System.out.print("Product Code :");
            String productCode = input.nextLine();
            Product product = productGeneralFunction.get(productCode);
            if (product != null)
            {
                productGeneralFunction.remove(product);
                removeChecked = true;
            }
        }
        while (!removeChecked);
        System.out.println("Xóa thành công ");
    }
    public static void functionSelection()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1.Thêm sản phẩm ");
        System.out.println("2.Cập nhật sản phẩm  ");
        System.out.println("3.Xoá sản phẩm  ");
        System.out.println("4.Tìm kiếm sản phẩm theo tên ");
        System.out.println("5.Tìm kiếm sản phẩm theo danh mục ");
        System.out.println("6.Quay lại trang chủ");
        System.out.print("Lựa chọn chức năng sản phẩm: ");
        int function = Integer.parseInt(input.nextLine());
        switch (function)
        {
            case 1 -> {
                addProduct();
            }
            case 2 -> {
                updateProduct();
            }
            case 3 -> {
                removeProduct();
            }
            case 4 ->{
                System.out.print("Nhập tên sản phẩm tìm kiếm : ");
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
            case 5->
            {
                System.out.print("Nhập danh mục : ");
                String categoriesName = input.nextLine();
                List<Product> productsSeacrh = productGeneralFunction.productSearchbyCategories(categoriesName);
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
            case 6 ->{
                  AdminTemplate.adminTemplate();
            }
        }
    }
}

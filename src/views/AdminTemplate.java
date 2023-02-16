package views;

import controller.categories.CategoriesManager;
import controller.customer.CustomerManager;
import controller.employee.EmployeeManager;
import controller.login_logout.Signout;
import controller.manager.GeneralFunction;
import controller.order.OrderManager;
import controller.product.ProductManager;
import model.*;

import java.util.List;
import java.util.Scanner;

public class AdminTemplate
{

    public static GeneralFunction<Employee>  employeeGeneralFunction = new EmployeeManager();

    public static GeneralFunction<Customer>  customerGeneralFunction = new CustomerManager();

    public static GeneralFunction<Product> productGeneralFunction = new ProductManager();

    public static GeneralFunction<Order> orderGeneralFunction = new OrderManager();

    public static GeneralFunction<Categories> categoriesGeneralFunction = new CategoriesManager();

    public static void employeeManager()
    {
        try {
            List<Employee> employeeList = employeeGeneralFunction.readFile();
            for (Employee employee : employeeList) {
                System.out.println("Employee "+employee.getId());
                System.out.println("Employee Code : " + employee.getEmployeeCode());
                System.out.println("Employee Name : " + employee.getEmployeeName());
                System.out.println("Employee DateOfBirth : " + employee.getDateOfBirth());
                System.out.println("Employee PlaceOfBirth : " + employee.getPlaceOfBirth());
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Không có nhân viên nào trong danh sách");
        }

    }

    public static void customerManager()
    {
        try {
            List<Customer>  customerList = customerGeneralFunction.readFile();
            for (Customer customer : customerList) {
                System.out.println("Customer "+customer.getId());
                System.out.println("Customer Code : " + customer.getCustomerCode());
                System.out.println("Customer Name : " + customer.getCustomerName());
                System.out.println("Customer DateOfBirth : " + customer.getDateOfBirth());
                System.out.println("Customer PlaceOfBirth : " + customer.getPlaceOfBirth());
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Không có khách hàng nào trong danh sách");
        }
    }

    public static void productManager()
    {
        try {
            List<Product> productList = productGeneralFunction.readFile();
            for (Product product : productList)
            {
                System.out.println("Product "+product.getId());
                System.out.println("Product Code : " + product.getProductCode());
                System.out.println("Product Name : " + product.getProductName());
                System.out.println("Product Price : " + product.getProductPrice());
                System.out.println("Product Describe : " + product.getProductPrice());
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Không có sản phẩm nào trong danh sách");
        }
    }
    public static void orderManager()
    {
        try {
            List<Order> orderList = orderGeneralFunction.readFile();
            for (Order order : orderList) {
                System.out.println("Order "+order.getId());
                System.out.println("Order Code : " + order.getOrderCode());
                System.out.println("Order Date : " + order.getDateOfOrder());
                System.out.println("Customer : " + order.getCustomer().getCustomerName());
            }

        }
        catch (NullPointerException e)
        {
            System.out.println("Không có hóa đơn nào trong danh sách");
        }
    }

    public static void categoriesManager()
    {
        try {
            List<Categories> categoriesList = categoriesGeneralFunction.readFile();
            for (Categories categories : categoriesList) {
                System.out.println("Categories "+categories.getId());
                System.out.println("Categories Code : " + categories.getCategoriesCode());
                System.out.println("Categories Name : " + categories.getCategoriesName());
            }
        }
        catch (NullPointerException e)
        {
            System.out.println("Không có danh mục nào trong danh sách");
        }
    }

    public static void adminTemplate()
    {
        Scanner input = new Scanner(System.in);
        boolean exits = false;
        do {
            System.out.println("Menu quản lý ");
            System.out.println("1.Quản lý nhân viên ");
            System.out.println("2.Quản lý khách hàng ");
            System.out.println("3.Quản lý sản phẩm ");
            System.out.println("4.Quản lý danh mục ");
            System.out.println("5.Quản lý hóa đơn ");
            System.out.println("6.Đăng xuất");
            System.out.print("Mời bạn chọn chức năng : ");
            int functionCode = input.nextInt();
            switch (functionCode) {
                case 1 -> {
                    employeeManager();
                }
                case 2 -> {
                    customerManager();
                }
                case 3 -> {
                    productManager();
                }
                case 4 -> {
                    categoriesManager();
                }
                case 5 -> {
                    orderManager();
                }
                case 6 -> {
                    signOut();
                    exits = true;
                }
            }
        }
        while(!exits) ;
    }
    public static void signOut()
    {
        Signout.getInstance().signOut();
        LoginTemplate.signInOrSignUpSelect();
    }

}

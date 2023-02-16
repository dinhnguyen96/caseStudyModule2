package controller.login_signup;

import controller.customer.CustomerManager;
import controller.manager.GeneralFunction;
import controller.user.UserManager;
import model.Customer;
import model.Roles;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginSignup {

    private String username;

    private String password;

    private GeneralFunction<Customer> customerManager;

    private GeneralFunction<User> userManager;


    public LoginSignup()
    {
        this.username = "";
        this.password = "";
        customerManager = new CustomerManager();
        userManager = new UserManager();
    }

    public void signIn()
    {
        System.out.println("Đăng nhập tài khoản");
        Scanner input = new Scanner(System.in);
        System.out.print("Username : ");
        username = input.nextLine();
        System.out.print("Password : ");
        password = input.nextLine();

        List<Customer> customers = customerManager.readFile();

        Customer signInCustomer = null;

        for (Customer customer : customers)
        {
            if (customer.getUser().getUsername().equals(username) && customer.getUser().getPassword().equals(password))
            {
                signInCustomer = customer;
                break;
            }
        }

        if (signInCustomer == null)
        {
            System.out.println("Đăng nhập thất bại");
        }
        else
        {
           for (Roles roles: signInCustomer.getUser().getRolesList())
           {
               if (roles.getRoleName().equalsIgnoreCase("Admin"))
               {
                   break;
               }
           }
        }

    }
    public void signUp()
    {
        System.out.println("Đăng ký tài khoản");
        List<Customer> customerList = customerManager.readFile();

        List<User> userList = userManager.readFile();

        List<Roles> rolesList = new ArrayList<>();

        Roles rolesCustomer = new Roles(3L, "03","CUSTOMER");
        rolesList.add(rolesCustomer);

        User user;

        boolean result;
        do
        {
            Scanner input = new Scanner(System.in);
            System.out.print("Username : ");
            username = input.nextLine();
            System.out.print("Password : ");
            password = input.nextLine();
            System.out.print("Name :  ");
            String name = input.nextLine();
            System.out.print("Day of birth : ");
            String dayofBirth = input.nextLine();
            System.out.print("Place of birth : ");
            String placeOfBirth = input.nextLine();
            System.out.print("Email : ");
            String email = input.nextLine();
            user = new User(userList.get(userList.size()-1).getId()+1, String.valueOf(userList.get(userList.size()-1).getId()+1),
                    username, password, rolesList);
            Customer customer = new Customer(customerList.get(customerList.size()-1).getId()+1,String.valueOf(customerList.get(customerList.size()-1).getId()+1),
                    name,dayofBirth, placeOfBirth, email, user);
            result = customerManager.add(customer);

            if (!result)
            {
                System.out.println("Email hoặc Username đã tồn tại ");
            }

        }
        while (!result);

        userManager.add(user);

        System.out.println("Đăng ký thành công");

        signIn();
    }

}

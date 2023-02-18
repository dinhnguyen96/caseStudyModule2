package controller.login_logout;

import controller.customer.CustomerManager;
import controller.manager.GeneralFunction;
import controller.user.UserManager;
import model.Customer;
import model.Employee;
import model.Roles;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SigninSignup {

    private String username;

    private String password;

    private GeneralFunction<Customer> customerManager;

    private GeneralFunction<User> userManager;

    public static Customer signInCustomerInfo = null;


    public SigninSignup(String username, String password)
    {
        this.username = username;
        this.password = password;
        customerManager = new CustomerManager();
        userManager = new UserManager();
    }

    // Xử lý đăng nhập
    public Customer signIn()
    {
        List<Customer> customers = customerManager.readFile();

        for (Customer customer : customers)
        {
            if (customer.getUser().getUsername().equals(username) && customer.getUser().getPassword().equals(password))
            {
                signInCustomerInfo = customer;
                break;
            }
        }
        return signInCustomerInfo;// return null
    }
    // Xử lý Đăng ký
    public boolean signUp(String username,String password, String name,String dayOfBirth,
                          String placeOfBirth,String email)
    {
        List<Customer> customerList = customerManager.readFile();
        List<User> userList = userManager.readFile();
        List<Roles> rolesList = new ArrayList<>();
        Roles rolesCustomer = new Roles(3L, "03","CUSTOMER");
        rolesList.add(rolesCustomer);
        User user = new User(userList.get(userList.size()-1).getId()+1, String.valueOf(userList.get(userList.size()-1).getId()+1),
                username, password, rolesList);
        Customer customer = new Customer(customerList.get(customerList.size()-1).getId()+1,String.valueOf(customerList.get(customerList.size()-1).getId()+1),
                name,dayOfBirth, placeOfBirth, email, user);

        String regrex = "^(?=.*[A-Z]+)(?=.*[a-z]+)(?=.*[*$@!#]).{6,}$";

        Pattern p = Pattern.compile(regrex);

        Matcher m = p.matcher(password);

        if (m.matches())
        {
            boolean result = customerManager.add(customer);

            if (!result)
            {
                return false;
            }
            else
            {
                userManager.add(user);
                return true;
            }
        }
        return false;
    }

}

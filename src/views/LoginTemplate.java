package views;

import controller.login_logout.SigninSignup;
import model.Customer;
import model.Roles;
import views.admin.AdminTemplate;
import views.client.ClientTemplate;

import java.util.List;
import java.util.Scanner;

public class LoginTemplate
{

    // đăng nhập
    public static void signIn()
    {
        int signInCount = 0;
        Customer customer ;
        do {

            System.out.println("Đăng nhập tài khoản");
            Scanner input = new Scanner(System.in);
            System.out.print("Username : ");
            String username = input.nextLine();
            System.out.print("Password : ");
            String password = input.nextLine();
            SigninSignup logSign = new SigninSignup(username, password);
            customer = logSign.signIn();

            if (customer == null)
            {
                signInCount++;
                System.out.printf("Bạn đã nhập sai %d lần \n", signInCount) ;
            }
        }
        while (customer == null && signInCount < 3) ;

        if (signInCount == 3)
        {
            System.out.println("Bạn đã bị khóa tài khoản !");
            return;
        }
        System.out.println("Đăng nhập thành công !");
    }

    // đăng ký
    public static void signUp()
    {
        boolean result;
        do {
            System.out.println("Đăng ký tài khoản");
            Scanner input = new Scanner(System.in);
            System.out.print("Username : ");
            String username = input.nextLine();
            System.out.print("Password : ");
            String password = input.nextLine();
            System.out.print("Name :  ");
            String name = input.nextLine();
            System.out.print("Day of birth : ");
            String dayofBirth = input.nextLine();
            System.out.print("Place of birth : ");
            String placeOfBirth = input.nextLine();
            System.out.print("Email : ");
            String email = input.nextLine();
            SigninSignup logSign = new SigninSignup(username, password);
            result = logSign.signUp(name,dayofBirth,placeOfBirth,email);
            if (!result)
            {
                System.out.println("Usename hoặc email đã tồn tại hoặc mật khẩu không hợp lệ !");
            }
        }
        while (!result);
        signIn();
    }

    // lựa chọn đăng ký hoặc đăng nhập
    public static void signInOrSignupHandling(int loginSignup)
    {
        boolean result = false;
        do {
            switch (loginSignup)
            {
                case 1 -> {
                    signIn();
                    result = true;
                }
                case 2 -> {
                    signUp();
                    result = true;
                }
            }
        }
        while (!result);
    }

    public static void signInOrSignUpSelect()
    {
        boolean result = false;
        do {
            try
            {
                Scanner input = new Scanner(System.in);
                System.out.println("Mời bạn lựa chọn đăng nhập hoặc đăng ký ");
                System.out.println("1. Đăng nhập");
                System.out.println("2. Đăng ký");
                System.out.print("Lựa chọn : ");
                int sigInOrSignUp = Integer.parseInt(input.nextLine());
                signInOrSignupHandling(sigInOrSignUp);
                result = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Nhập dữ liệu không hợp lê!");
            }
        }
        while (!result);
    }
    // Trạng thái đăng nhập
    public static void loginStatus()
    {
        signInOrSignUpSelect();
        boolean adminRoleName = false;
        List<Roles> roles = SigninSignup.signInCustomerInfo.getUser().getRolesList();
        for (Roles roleName : roles)
        {
            if (roleName.getRoleName().equalsIgnoreCase("Admin"))
            {
                adminRoleName = true;
                break;
            }
        }
        if (adminRoleName)
        {
            templateAccessSelect();
        }
        else
        {
            ClientTemplate.clientTemplate();
        }

    }
    // lựa chọn giao diện đối với admin
    public static void templateAccessSelect()
    {
        boolean result = false;
        Scanner input = new Scanner(System.in);
        do {
            try
            {
                System.out.println("1. Giao diện admin");
                System.out.println("2. Giao diện người dùng ");
                System.out.print("Mời bạn chọn giao diện để truy cập : ");
                int numberTemplate = Integer.parseInt(input.nextLine());
                switch (numberTemplate)
                {
                    case 1->
                    {
                        AdminTemplate.adminTemplate();
                    }
                    case 2->{
                        ClientTemplate.clientTemplate();

                    }
                }
                result = true;
            }
            catch (NumberFormatException e)
            {
                System.out.println("Nhập không hợp lệ!");
            }

        }
        while (!result);
    }
    public static void main(String[] args)
    {
       loginStatus();
    }
}

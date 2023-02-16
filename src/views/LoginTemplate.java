package views;

import controller.login_signup.LoginSignup;

import java.util.Scanner;

public class LoginTemplate
{
    public static void signInOrSignup(int loginSignup)
    {
        boolean result = false;
        LoginSignup logSign = new LoginSignup();
        do {
            switch (loginSignup)
            {
                case 1 -> {
                    logSign.signIn();
                    result = true;
                }
                case 2 -> {
                    logSign.signUp();
                    result = true;
                }
            }
        }
        while (!result);
    }
    public static void signInOrSignUpSelect()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Mời bạn lựa chọn đăng nhập hoặc đăng ký");
        int sigInOrSignUp = input.nextInt();
        signInOrSignup(sigInOrSignUp);
    }
    public static void main(String[] args)
    {
        signInOrSignUpSelect();

    }
}

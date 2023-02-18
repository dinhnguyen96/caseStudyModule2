package controller.login_logout;

public class Signout {


    private static Signout signout;

    private Signout()
    {

    }
    public static Signout getInstance()
    {
        if (signout == null)
        {
            signout = new Signout();
        }
        return signout;
    }
    public void signOut()
    {
        if (SigninSignup.signInCustomerInfo != null)
        {
            System.out.println("Đăng xuất thành công !");
            SigninSignup.signInCustomerInfo = null;
        }
    }
}

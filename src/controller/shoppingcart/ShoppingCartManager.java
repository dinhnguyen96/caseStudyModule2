package controller.shoppingcart;

import controller.login_logout.SigninSignup;
import controller.manager.ApplicationManager;
import controller.customer.CustomerManager;
import model.CartInfo;
import model.Customer;
import model.Item;
import storage.GetData;
import storage.ShoppingCartReadWrite;

import java.util.List;

public class ShoppingCartManager implements ApplicationShoppingCart
{
    private GetData<CartInfo> shoppingCartReadWrite;

    private List<CartInfo> cartInfoList;

    public ShoppingCartManager()
    {
       shoppingCartReadWrite = ShoppingCartReadWrite.getInstance();
       cartInfoList = shoppingCartReadWrite.readFile();
    }
    @Override
    public List<CartInfo> readFile()
    {
       return cartInfoList;
    }
    @Override
    public void writeFile(List<CartInfo> cartInfoList)
    {
        shoppingCartReadWrite.writeFile(cartInfoList);
    }
    // lay cartInfo theo user
    @Override
    public CartInfo get()
    {
        if (readFile() != null)
        {
            for (CartInfo cartInfo:readFile())
            {
                if (cartInfo.getCustomer().getUser().getUsername().equalsIgnoreCase(SigninSignup.signInCustomerInfo.getUser().getUsername()))
                {
                    return cartInfo;
                }
            }
        }
        return null;
    }
    // add item in cart of user
    @Override
    public void addItem(Item item)
    {
        boolean checkExits = false;
        CartInfo cartInfo = get();
        if(cartInfo == null)
        {
            Customer customer = SigninSignup.signInCustomerInfo;
            cartInfo = new CartInfo(customer);
            item.setQuantity(1);
            cartInfo.getCartList().add(item);
        }
        else
        {
            for (Item item1 : cartInfo.getCartList())
            {
                if (item1.getProduct().getProductCode().equalsIgnoreCase(item.getProduct().getProductCode()))
                {
                    item1.setQuantity(item1.getQuantity()+1);
                    checkExits = true;
                    break;
                }
            }
            if (!checkExits)
            {
                item.setQuantity(1);
                cartInfo.getCartList().add(item);
            }
        }
        cartInfoList.set(cartInfoList.indexOf(cartInfo), cartInfo);
        writeFile(cartInfoList);

    }
    // remove item in cart of user
    @Override
    public void removeItem(String productCode)
    {
        CartInfo cartInfo = get();

        for (Item item : cartInfo.getCartList())
        {
            if (item.getProduct().getProductCode().equalsIgnoreCase(productCode))
            {
                cartInfo.getCartList().remove(item);
                break;
            }
        }
        cartInfoList.set(cartInfoList.indexOf(cartInfo), cartInfo);
        writeFile(cartInfoList);
    }
    // clear item in cart of user
    @Override
    public void removeAllItem()
    {
        CartInfo cartInfo = get();
        List<CartInfo> updateCartInfoList = readFile();
        updateCartInfoList.remove(cartInfo);
        writeFile(updateCartInfoList);
    }
}

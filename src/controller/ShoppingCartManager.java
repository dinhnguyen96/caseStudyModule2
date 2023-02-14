package controller;

import model.CartInfo;
import model.Customer;
import model.Item;
import storage.GetData;
import storage.ShoppingCartReadWrite;

import java.util.List;

public class ShoppingCartManager implements ApplicationManager<CartInfo>
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
    public CartInfo get(String customerCode)
    {
        if (readFile() != null)
        {
            for (CartInfo cartInfo:readFile())
            {
                if (cartInfo.getCustomer().getCustomerCode().equalsIgnoreCase(customerCode))
                {
                    return cartInfo;
                }
            }
        }
        return null;
    }
    // add item in cart of user
    public void addItem(String customerCode,Item item)
    {
        boolean checkExits = false;
        CartInfo cartInfo = get(customerCode);
        if(cartInfo == null)
        {
            ApplicationManager<Customer> customerManager = new CustomerManager();
            Customer customer = customerManager.get(customerCode);
            cartInfo = new CartInfo(customer);
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
    // add item in cart of user
    public void removeItem(String customerCode, String productCode)
    {
        CartInfo cartInfo = get(customerCode);

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
    public void removeAllItem(String customerCode)
    {
        CartInfo cartInfo = get(customerCode);
        List<CartInfo> updateCartInfoList = readFile();
        updateCartInfoList.remove(cartInfo);
        writeFile(updateCartInfoList);
    }
}

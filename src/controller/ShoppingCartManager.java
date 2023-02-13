package controller;

import model.CartInfo;
import storage.ShoppingCartReadWrite;

import java.util.List;

public class ShoppingCartManager implements ApplicationManager<CartInfo>
{
    private ShoppingCartReadWrite shoppingCartReadWrite;

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

    @Override
    public CartInfo get(String code)
    {
        for (CartInfo cartInfo:readFile())
        {
            if (cartInfo.getCustomer().getCustomerCode().equalsIgnoreCase(code))
            {
                return cartInfo;
            }
        }
        return null;
    }

    @Override
    public boolean add(CartInfo cartInfo)
    {
        CartInfo cart = get(cartInfo.getCustomer().getCustomerCode());
        if (cart == null)
        {
            cart = new CartInfo();
        }
        List<CartInfo> updateCartInfoList = readFile();
        updateCartInfoList.add(cart);
        writeFile(updateCartInfoList);
        return true;
    }
    @Override
    public boolean update(CartInfo cartInfo)
    {
        CartInfo cart = get(cartInfo.getCustomer().getCustomerCode());
        if (cart != null)
        {
            List<CartInfo> updateCartInfoList = readFile();
            updateCartInfoList.set(updateCartInfoList.indexOf(cart), cart);
            writeFile(updateCartInfoList);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(CartInfo cartInfo)
    {
        CartInfo cart = get(cartInfo.getCustomer().getCustomerCode());
        if (cart != null)
        {
            List<CartInfo> updateCartInfoList = readFile();
            updateCartInfoList.remove(cart);
            writeFile(updateCartInfoList);
            return true;
        }
        return false;
    }
}

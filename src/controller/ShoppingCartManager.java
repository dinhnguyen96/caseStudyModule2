package controller;

import model.CartInfo;
import model.Item;
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
    // lay cartInfo theo user
    @Override
    public CartInfo get(String customerCode)
    {
        for (CartInfo cartInfo:readFile())
        {
            if (cartInfo.getCustomer().getCustomerCode().equalsIgnoreCase(customerCode))
            {
                return cartInfo;
            }
        }
        return null;
    }

    // them gio hang theo user
    @Override
    public boolean add(CartInfo cartInfo)
    {
        CartInfo cart = get(cartInfo.getCustomer().getCustomerCode());
        if (cart == null)
        {
            cart = new CartInfo(cartInfo.getCustomer());
        }
        List<CartInfo> updateCartInfoList = readFile();
        updateCartInfoList.add(cart);
        writeFile(updateCartInfoList);
        return true;
    }

    // cap nhat gio hang theo user
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


    // xoa  gio hang theo user
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


    // lay items in cart of user
    public Item getItem(String customerCode, Item item)
    {
        CartInfo cartInfo = get(customerCode);

        for (Item i: cartInfo.getCartList())
        {
             if (i.getProduct().getProductCode().equalsIgnoreCase(item.getProduct().getProductCode()))
             {
                 return i;
             }
        }
        return null;
    }

    // add item in cart of user
    public void addItem(String customerCode,Item item)
    {
        Item i = getItem(customerCode, item);

        CartInfo cartInfo = get(customerCode);

        if (i == null)
        {
            i = new Item();
            i.setQuantity(1);
            cartInfo.getCartList().add(i);
        }
        else
        {
            i.setQuantity(i.getQuantity()+1);
        }
        cartInfoList.set(cartInfoList.indexOf(cartInfo), cartInfo);
        writeFile(cartInfoList);
    }
    // add item in cart of user
    public void removeItem(String customerCode, Item item)
    {
        Item i = getItem(customerCode, item);

        CartInfo cartInfo = get(customerCode);

        if (i != null)
        {
            cartInfo.getCartList().remove(i);
        }
        cartInfoList.set(cartInfoList.indexOf(cartInfo), cartInfo);
        writeFile(cartInfoList);
    }
    // clear item in cart of user
    public void removeAllItem(String customerCode)
    {
        CartInfo cartInfo = get(customerCode);
        cartInfo.getCartList().clear();
        cartInfoList.set(cartInfoList.indexOf(cartInfo), cartInfo);
        writeFile(cartInfoList);
    }

}

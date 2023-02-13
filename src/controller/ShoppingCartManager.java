package controller;

import model.CartInfo;
import storage.ShoppingCartReadWrite;

import java.util.List;

public class ShoppingCartManager implements ApplicationManager<CartInfo>
{

    private ShoppingCartReadWrite shoppingCartReadWrite = ShoppingCartReadWrite.getInstance();

    public ShoppingCartManager()
    {
    }

    @Override
    public List<CartInfo> readFile()
    {
       return shoppingCartReadWrite.readFile();
    }
    @Override
    public void writeFile(List<CartInfo> cartInfoList)
    {
      shoppingCartReadWrite.writeFile(cartInfoList);
    }

    @Override
    public CartInfo get(String code) {
        return null;
    }

    @Override
    public boolean add(CartInfo cartInfo) {
        return false;
    }

    @Override
    public boolean update(CartInfo cartInfo) {
        return false;
    }

    @Override
    public void remove(CartInfo cartInfo) {

    }
}

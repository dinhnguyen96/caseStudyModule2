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
}

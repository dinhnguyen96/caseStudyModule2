package controller.shoppingcart;

import controller.manager.ApplicationManager;
import model.CartInfo;
import model.Item;

import java.util.List;

public interface ApplicationShoppingCart extends ApplicationManager<CartInfo>
{
    CartInfo get();
    void addItem(Item item);

    void removeItem(String productCode);

    void removeAllItem();

}

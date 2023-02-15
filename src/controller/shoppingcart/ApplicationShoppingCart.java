package controller.shoppingcart;

import controller.manager.ApplicationManager;
import model.CartInfo;
import model.Item;

public interface ApplicationShoppingCart extends ApplicationManager<CartInfo>
{
    void addItem(String customerCode, Item item);

    void removeItem(String customerCode, String productCode);

    void removeAllItem(String customerCode);

}

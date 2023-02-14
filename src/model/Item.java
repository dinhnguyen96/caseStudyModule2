package model;

import java.io.Serializable;

public class Item implements Serializable
{
      private Product product;
      private int quantity;

      public Item()
      {
          this.quantity = 0;
      }
      public Product getProduct()
      {
            return product;
      }
      public void setProduct(Product product)
      {
          this.product = product;
      }
      public int getQuantity()
      {
          return quantity;
      }
      public void setQuantity(int quantity)
      {
          this.quantity = quantity;
      }
}

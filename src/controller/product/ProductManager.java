package controller.product;

import controller.manager.GeneralFunction;
import model.Product;
import storage.ReadWrite;
import storage.ProductReadWrite;

import java.util.ArrayList;
import java.util.List;

public class ProductManager implements ProductSearch
{
    private ReadWrite<Product> productReadWrite;

    private List<Product> productList;

    public static boolean productDataChecked = false;


    public ProductManager()
    {
        productReadWrite = ProductReadWrite.getInstance();
        productList = productReadWrite.readFile();// doc file khi khoi tao doi tuong
    }
    @Override
    public List<Product> readFile()
    {
        if (productDataChecked)
        {
            productList = productReadWrite.readFile();
            productDataChecked = false;
        }
         return productList;
    }
    @Override
    public void writeFile(List<Product> productList)
    {
       productReadWrite.writeFile(productList);
    }

    @Override
    public Product get(String code)
    {
        for (Product product:readFile())
        {
            if (product.getProductCode().equalsIgnoreCase(code))
            {
                return product;
            }
        }
        return null;
    }

    @Override
    public boolean add(Product product)
    {
        Product p = get(product.getProductCode());
        if (p != null)
        {
            return false;
        }
        p = product;
        List<Product> updatePoductList = readFile();
        updatePoductList.add(p);
        writeFile(updatePoductList);
        return true;
    }
    @Override
    public void update(Product product)
    {
        List<Product> updatePoductList = readFile();
        updatePoductList.set(updatePoductList.indexOf(product), product);
        writeFile(updatePoductList);
    }

    @Override
    public void remove(Product product)
    {
        List<Product> updatePoductList = readFile();
        updatePoductList.remove(product);
        writeFile(updatePoductList);
    }

    @Override
    public List<Product> searchByName(String productName)
    {
        List<Product> searchList = new ArrayList<>();
        String regex = ".*"+productName+".*";

        for (Product product : productList)
        {
            if (product.getProductName().matches(regex))
            {
                searchList.add(product);
            }
        }
        return searchList;
    }

    @Override
    public List<Product> productSearchbyCategories(String categoriesName)
    {
        List<Product> searchList = new ArrayList<>();
        String regex = ".*"+categoriesName+".*";
        for (Product product : productList)
        {
            if (product.getCategories().getCategoriesName().matches(regex))
            {
                searchList.add(product);
            }
        }
        return searchList;
    }
}

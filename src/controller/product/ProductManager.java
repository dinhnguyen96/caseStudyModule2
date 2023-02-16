package controller.product;

import controller.manager.GeneralFunction;
import model.Product;
import storage.GetData;
import storage.ProductReadWrite;

import java.util.List;

public class ProductManager implements GeneralFunction<Product>
{
    private GetData<Product> productReadWrite;

    private List<Product> productList;

    public ProductManager()
    {
        productReadWrite = ProductReadWrite.getInstance();
        productList = productReadWrite.readFile();
    }
    @Override
    public List<Product> readFile()
    {
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
    public boolean update(Product product)
    {
        List<Product> updatePoductList = readFile();
        updatePoductList.set(updatePoductList.indexOf(product), product);
        writeFile(updatePoductList);
        return true;
    }

    @Override
    public boolean remove(Product product)
    {
        List<Product> updatePoductList = readFile();
        updatePoductList.remove(product);
        writeFile(updatePoductList);
        return true;
    }

}

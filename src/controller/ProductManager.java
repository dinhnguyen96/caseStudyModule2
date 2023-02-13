package controller;

import model.Product;
import storage.ProductReadWrite;

import java.util.List;

public class ProductManager implements ApplicationManager<Product>
{
    private ProductReadWrite productReadWrite = ProductReadWrite.getInstance();
    public ProductManager()
    {

    }

    @Override
    public List<Product> readFile()
    {
       return productReadWrite.readFile();
    }

    @Override
    public void writeFile(List<Product> productList)
    {
       productReadWrite.writeFile(productList);
    }

    @Override
    public Product get(String code) {
        return null;
    }

    @Override
    public boolean add(Product product) {
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public void remove(Product product) {

    }
}

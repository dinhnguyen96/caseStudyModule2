package views;

import controller.ApplicationManager;
import controller.CategoriesManager;
import controller.ProductManager;
import model.Categories;
import model.Product;

public class Main {

    public static void main(String[] args)
    {
        ApplicationManager<Categories> categoriesManager = new CategoriesManager("src/storage/categories.dat");
        ApplicationManager<Product> productManager = new ProductManager("src/storage/product.dat");


    }
}

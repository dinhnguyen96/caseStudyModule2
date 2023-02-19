package controller.product;

import controller.manager.GeneralFunction;
import model.Product;

import java.util.List;

public interface ProductSearch extends GeneralFunction<Product> {

    List<Product> productSearchbyCategories(String categoriesName);
}

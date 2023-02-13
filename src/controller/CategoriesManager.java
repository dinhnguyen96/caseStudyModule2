package controller;

import model.Categories;
import storage.CategoriesReadWrite;

import java.util.List;

public class CategoriesManager implements ApplicationManager<Categories>
{

    private CategoriesReadWrite categoriesReadWrite = CategoriesReadWrite.getInstance();

    public CategoriesManager()
    {

    }
    @Override
    public List<Categories> readFile()
    {
        return categoriesReadWrite.readFile();
    }

    @Override
    public void writeFile(List<Categories> categoriesList)
    {
        categoriesReadWrite.writeFile(categoriesList);
    }

}

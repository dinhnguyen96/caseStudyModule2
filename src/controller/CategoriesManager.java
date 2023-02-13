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

    @Override
    public Categories get(String code) {
        return null;
    }

    @Override
    public boolean add(Categories categories) {
        return false;
    }

    @Override
    public boolean update(Categories categories) {
        return false;
    }

    @Override
    public void remove(Categories categories) {

    }

}

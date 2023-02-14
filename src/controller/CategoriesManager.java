package controller;

import model.Categories;
import storage.CategoriesReadWrite;

import java.util.List;

public class CategoriesManager implements ApplicationManager<Categories>
{
    private CategoriesReadWrite categoriesReadWrite;

    private List<Categories> categoriesList;

    public CategoriesManager()
    {
       categoriesReadWrite = CategoriesReadWrite.getInstance();
       categoriesList = categoriesReadWrite.readFile();
    }
    @Override
    public List<Categories> readFile()
    {
        return categoriesList;
    }

    @Override
    public void writeFile(List<Categories> categoriesList)
    {
        categoriesReadWrite.writeFile(categoriesList);
    }

    @Override
    public Categories get(String code)
    {
        for (Categories categories:readFile())
        {
            if (categories.getCategoriesCode().equalsIgnoreCase(code))
            {
                return categories;
            }
        }
        return null;
    }

    @Override
    public boolean add(Categories categories)
    {
        Categories c = get(categories.getCategoriesCode());
        if (c != null)
        {
            return false;
        }
        c = categories;
        List<Categories> updateCategoriesList = readFile();
        updateCategoriesList.add(c);
        writeFile(updateCategoriesList);
        return true;
    }

    @Override
    public boolean update(Categories categories)
    {
        Categories c = get(categories.getCategoriesCode());
        if (c != null)
        {
            List<Categories> updateCategoriesList = readFile();
            updateCategoriesList.set(updateCategoriesList.indexOf(c), c);
            writeFile(updateCategoriesList);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Categories categories)
    {
        Categories c = get(categories.getCategoriesCode());
        if (c != null)
        {
            List<Categories> updateCategoriesList = readFile();
            updateCategoriesList.remove(c);
            writeFile(updateCategoriesList);
            return true;
        }
        return false;
    }

}

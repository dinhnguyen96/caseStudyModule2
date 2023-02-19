package controller.categories;

import controller.manager.GeneralFunction;
import model.Categories;
import storage.CategoriesReadWrite;
import storage.ReadWrite;

import java.util.ArrayList;
import java.util.List;

public class CategoriesManager implements GeneralFunction<Categories>
{
    private ReadWrite<Categories> categoriesReadWrite;

    private List<Categories> categoriesList;


    private boolean categoriesDataCheck;



    public CategoriesManager()
    {
        this.categoriesDataCheck = false;
        this. categoriesReadWrite = CategoriesReadWrite.getInstance();
        this.categoriesList = categoriesReadWrite.readFile();

    }
    @Override
    public List<Categories> readFile()
    {
        if (categoriesDataCheck)
        {
            categoriesList = categoriesReadWrite.readFile();
            categoriesDataCheck = false;
        }
        return categoriesList;
    }

    @Override
    public void writeFile(List<Categories> categoriesList)
    {
        categoriesReadWrite.writeFile(categoriesList);
    }

    @Override
    public Categories get(String categoriesCodeOrName)
    {
        for (Categories categories:readFile())
        {
            if (categories.getCategoriesCode().equalsIgnoreCase(categoriesCodeOrName) ||
               categories.getCategoriesName().equalsIgnoreCase(categoriesCodeOrName))
            {
                return categories;
            }
        }
        return null;
    }

    @Override
    public boolean add(Categories categories)
    {
        Categories categoriesByCode = get(categories.getCategoriesCode());
        Categories categoriesByName = get(categories.getCategoriesName());
        if (categoriesByCode != null || categoriesByName != null)
        {
            return false;
        }
        List<Categories> updateCategoriesList = readFile();
        updateCategoriesList.add(categories);
        writeFile(updateCategoriesList);
        categoriesDataCheck = true;
        return true;
    }

    @Override
    public void update(Categories categories)
    {
        List<Categories> updateCategoriesList = readFile();
        updateCategoriesList.set(updateCategoriesList.indexOf(categories), categories);
        writeFile(updateCategoriesList);
        categoriesDataCheck = true;
    }

    @Override
    public void remove(Categories categories)
    {
        List<Categories> updateCategoriesList = readFile();
        updateCategoriesList.remove(categories);
        writeFile(updateCategoriesList);
        categoriesDataCheck = true;
    }

    @Override
    public List<Categories> searchByName(String categoriesName)
    {
        List<Categories> searchList = new ArrayList<>();
        String regex = ".*"+categoriesName+".*";

        for (Categories categories : categoriesList)
        {
            if (categories.getCategoriesName().matches(regex))
            {
                searchList.add(categories);
            }
        }
        return searchList;
    }

}

package controller;

import model.Categories;

import java.util.List;

public class CategoriesManager extends ApplicationManager<Categories>
{
    public CategoriesManager(String pathDataFile)
    {
        super(pathDataFile);
    }

    @Override
    public List<Categories> readFile()
    {
        return null;
    }

    @Override
    public void writeFile(List<Categories> dataList)
    {


    }
}

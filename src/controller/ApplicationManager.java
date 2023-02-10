package controller;

import model.GetData;

public abstract class ApplicationManager<T>  extends GetData<T>
{
    protected ApplicationManager(String pathDataFile)
    {
        super(pathDataFile);
    }


}

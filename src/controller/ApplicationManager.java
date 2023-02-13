package controller;

import storage.GetData;

public abstract class ApplicationManager<T>  extends GetData<T>
{
    protected ApplicationManager(String pathDataFile)
    {
        super(pathDataFile);
    }



}

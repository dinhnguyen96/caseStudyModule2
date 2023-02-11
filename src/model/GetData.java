package model;

import java.util.List;

public abstract class GetData<T>
{
    private String pathDataFile;

    protected GetData(String pathDataFile)
    {
        this.pathDataFile = pathDataFile;
    }

    public String getPathDataFile()
    {
        return pathDataFile;
    }

    public void setPathDataFile(String pathDataFile)
    {
        this.pathDataFile = pathDataFile;
    }

    public abstract List<T> readFile();

    public abstract void writeFile(List<T> dataList);

}

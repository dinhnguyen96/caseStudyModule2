package storage;

import java.util.List;

public abstract class ReadWrite<T>
{
    private String pathDataFile;

    protected ReadWrite(String pathDataFile)
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

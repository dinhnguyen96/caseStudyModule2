package model;

import java.io.IOException;
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

    public abstract List<T> readFile() throws IOException;

    public abstract void writeFile(List<T> dataList) throws IOException;

}

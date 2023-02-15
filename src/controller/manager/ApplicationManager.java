package controller.manager;

import java.util.List;

public interface ApplicationManager<T>
{
    List<T> readFile();

    void writeFile(List<T> dataList);

    T get(String code);


}

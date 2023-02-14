package controller;

import java.util.List;

public interface ApplicationManager<T>
{
    List<T> readFile();

    void writeFile(List<T> dataList);


    T get(String code);
    boolean add(T t);

    boolean update(T t);

    boolean remove(T t);

}

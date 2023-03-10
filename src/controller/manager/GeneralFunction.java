package controller.manager;

import java.util.List;

public interface GeneralFunction<T> extends ApplicationManager<T> {

    T get(String code);
    boolean add(T t);

    void update(T t);

    void remove(T t);

    List<T> searchByName(String name);
}

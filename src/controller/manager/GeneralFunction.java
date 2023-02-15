package controller.manager;

public interface GeneralFunction<T> extends ApplicationManager<T> {

    boolean add(T t);

    boolean update(T t);

    boolean remove(T t);
}

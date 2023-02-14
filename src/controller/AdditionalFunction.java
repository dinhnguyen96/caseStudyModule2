package controller;

public interface AdditionalFunction<T> {

    boolean add(T t);

    boolean update(T t);

    boolean remove(T t);
}

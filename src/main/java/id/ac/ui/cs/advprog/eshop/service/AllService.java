package id.ac.ui.cs.advprog.eshop.service;
import java.util.List;

public interface AllService<T> {
    public T create(T object);
    public List<T> findAll();
    T findById(String objectId);
    public void update(String objectId, T object);
    public void deleteObjectById(String objectId);
}
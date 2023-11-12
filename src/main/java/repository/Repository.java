package repository;

import java.util.List;
import java.util.UUID;

public interface Repository<T> {
    T add(T entity);
    T getById(UUID id);
    void remove(UUID id);
    void update(T entity);
    long size();
    List<T> findAll();
}

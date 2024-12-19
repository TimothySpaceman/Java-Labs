package lab11;

import java.util.List;

public abstract class AbstractDAO<T> {
    public abstract void create(T entity);
    public abstract T read(int id);
    public abstract List<T> readAll();
    public abstract void update(T entity);
    public abstract void delete(int id);
}
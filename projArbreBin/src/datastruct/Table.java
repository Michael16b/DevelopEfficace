package datastruct;

public interface Table<E extends Comparable<E>, T> {
    public T select(E key);
    public boolean insert(E key, T data);
    public boolean delete (E key);
}

public interface Table<T,E extends Comparable<E> > {
    public T select(E key);
    public boolean insert(E key, T data);
    public boolean delete (E key);
}
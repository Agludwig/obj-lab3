public interface Storage<T> {

    boolean isEmpty();

    boolean isFull();

    int getCapacity();

    void load(T obj);

    T  unload();
}
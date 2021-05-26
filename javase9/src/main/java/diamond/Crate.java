package diamond;

public interface Crate<T> {

    void put(T content);

    void empty();

}

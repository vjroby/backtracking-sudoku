package ro.robertgabriel.backtrackingsudoku.controllers;


@FunctionalInterface
public interface TriConsumer<T, R, V> {

    /**
     * Accepts three parameters
     * @param t
     * @param r
     * @param v
     */
    void accept(T t, R r, V v);
}

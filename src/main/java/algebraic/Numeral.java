package algebraic;

/** @author Jinho D. Choi */
public interface Numeral<T extends Numeral<T>> {
    /**
     * Adds `n` to this numeral.
     * @param n the numeral to be added.
     */
    void add(T n);
}

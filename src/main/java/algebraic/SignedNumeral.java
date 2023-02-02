package algebraic;
public abstract class SignedNumeral<T extends SignedNumeral<T>> implements Numeral<T> {
    /**
     * The sign of this numeral.
     */
    protected Sign sign;

    /**
     * Creates a signed numeral.
     * the default sign is {@link Sign#POSITIVE}.
     */
    public SignedNumeral() {
        this(Sign.POSITIVE);
    }

    /**
     * Creates a signed numeral.
     *
     * @param sign the sign of this numeral.
     */
    public SignedNumeral(Sign sign) {
        this.sign = sign;
    }

    /**
     * @return true if this numeral is positive; otherwise, false.
     */
    public boolean isPositive() {
        return sign == Sign.POSITIVE;
    }

    /**
     * @return true if this numeral is negative; otherwise, false.
     */
    public boolean isNegative() {
        return sign == Sign.NEGATIVE;
    }

    /**
     * Flips the sign of this numeral.
     */
    public void flipSign() {
        sign = isPositive() ? Sign.NEGATIVE : Sign.POSITIVE;
    }

    /**
     * Subtracts `n` from this numeral.
     *
     * @param n the numeral to be subtracted.
     */
    public void subtract(T n) {
        n.flipSign();
        add(n);
        n.flipSign();
    }

    /**
     * Multiplies `n` to this numeral.
     *
     * @param n the numeral to be multiplied.
     */
    public abstract void multiply(T n);

}



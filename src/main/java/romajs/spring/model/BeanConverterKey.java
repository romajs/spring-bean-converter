package romajs.spring.model;

public class BeanConverterKey {

    private final Class fromClass;

    private final Class toClass;

    public BeanConverterKey(final Class fromClass, final Class toClass) {
        this.fromClass = fromClass;
        this.toClass = toClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeanConverterKey that = (BeanConverterKey) o;

        if (!fromClass.equals(that.fromClass)) return false;
        return toClass.equals(that.toClass);
    }

    @Override
    public int hashCode() {
        int result = fromClass.hashCode();
        result = 31 * result + toClass.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BeanConverterKey{" +
                "fromClass=" + fromClass +
                ", toClass=" + toClass +
                '}';
    }
}

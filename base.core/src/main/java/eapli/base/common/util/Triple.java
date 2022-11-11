package eapli.base.common.util;

/**
 * Data Structure which groups 3 elements instances of Classes of the programmers choosing, together;
 *
 * @param <K>
 * @param <E>
 * @param <V>
 *
 * @author Gonçalo Monteiro
 */
public class Triple<K, E, V> {
    private K firstElement;
    private E secondElement;
    private V thirdElement;

    public Triple(K firstElement, E secondElement, V thirdElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
        this.thirdElement = thirdElement;
    }

    public K getFirstElement() {
        return firstElement;
    }

    public E getSecondElement() {
        return secondElement;
    }

    public V getThirdElement() {
        return thirdElement;
    }
}

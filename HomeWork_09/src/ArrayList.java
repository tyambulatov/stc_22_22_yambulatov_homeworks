import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private final static int DEFAULT_ARRAY_SIZE = 10;

    private T[] elements;

    private int count;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_ARRAY_SIZE];
        this.count = 0;
    }

    public ArrayList(Collection<? extends T> array) {
        T[] a = (T[]) array.toArray();

        if ((count = a.length) != 0) {
            if (array.getClass() == java.util.ArrayList.class) {
                elements = a;
            } else {
                elements = (T[]) Arrays.copyOf(a, count, Object[].class);
            }
        } else {
            elements = (T[]) new Object[DEFAULT_ARRAY_SIZE];
        }
    }

    @Override
    public void remove(T element) {
        final Object[] es = elements;
        final int size = count;

        int index = 0;

        if (element == null) {
            for (; index < size; index++)
                if (es[index] == null) {
                    fastRemove(es, index);
                    break;
                }
        } else {
            for (; index < size; index++)
                if (element.equals(es[index])) {
                    fastRemove(es, index);
                    break;
                }
        }
    }


    @Override
    public void removeAt(int index) {
        Objects.checkIndex(index, count);
        final Object[] es = elements;
        fastRemove(es, index);
    }

    private void fastRemove(Object[] es, int i) {
        final int newCount = count - 1;

        if (newCount > i) {
            System.arraycopy(es, i + 1, es, i, newCount - i);
        }

        count = newCount;
        es[newCount] = null;
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOfRange(elements, 0, count);
    }

    @Override
    public void add(T element) {

        if (isFull()) {
            resize();
        }
        elements[count] = element;
        count++;
    }

    private void resize() {
        int currentLength = elements.length;
        int newLength = currentLength + currentLength / 2;
        T[] newElements = (T[]) new Object[newLength];

        if (count >= 0) System.arraycopy(elements, 0, newElements, 0, count);

        this.elements = newElements;
    }

    private boolean isFull() {
        return count == elements.length;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < count) {
            return elements[index];
        }
        return null;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < count; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int size() {
        return count;
    }


}

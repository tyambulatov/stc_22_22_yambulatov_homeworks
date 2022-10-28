import java.util.Collection;
import java.util.Objects;

public class LinkedList<T> implements List<T> {

    private Node<T> first;

    private Node<T> last;
    private int count;

    private static class Node<E> {
        E value;
        Node<E> next;

        Node<E> prev;

        public Node(E value) {
            this.value = value;
        }
    }

    public LinkedList() {
    }

    public LinkedList(Collection<? extends T> c) {
        this();
        addAll(c);
    }

    @Override
    public void remove(T element) {
        if (element == null) {
            for (Node<T> current = first; current != null; current = current.next) {
                if (current.value == null) {
                    unlink(current);
                }
            }
        } else {
            for (Node<T> current = first; current != null; current = current.next) {
                if (element.equals(current.value)) {
                    unlink(current);
                }
            }
        }

    }

    @Override
    public void removeAt(int index) {
        Objects.checkIndex(index, count);
        Node<T> current = this.first;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        unlink(current);
    }

    private void unlink(Node<T> node) {
        final Node<T> next = node.next;
        final Node<T> prev = node.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.value = null;
        count--;
    }

    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (count == 0) {
            this.first = newNode;
        } else {
            this.last.next = newNode;
            newNode.prev = this.last;
        }
        this.last = newNode;
        count++;
    }

    @Override
    public boolean contains(T element) {
        Node<T> current = this.first;
        while (current != null) {
            if (current.value.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }


    @Override
    public T get(int index) {
        if (0 <= index && index <= count) {
            Node<T> current = this.first;

            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current.value;
        }
        return null;

    }

    private void addAll(Collection<? extends T> c) {
        for (var element : c) {
            this.add(element);
        }
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[count];
        int i = 0;
        for (Node<T> x = first; x != null; x = x.next)
            result[i++] = x.value;
        return result;
    }
}

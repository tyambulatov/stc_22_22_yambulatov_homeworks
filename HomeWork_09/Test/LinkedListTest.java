import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class LinkedListTest {
    private LinkedList<String> linkedList;

    @Test
    void remove() {
        removeFromEmptyArray();
        removeFromSingleElementArray();
        removeWithoutDuplicates();
        removeWithDuplicates();
    }

    private void removeFromEmptyArray() {
        linkedList = new LinkedList<>();
        linkedList.remove(null);
        Assertions.assertEquals("[]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>();
        linkedList.remove("");
        Assertions.assertEquals("[]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>();
        linkedList.remove("a");
        Assertions.assertEquals("[]", Arrays.toString(linkedList.toArray()));
    }

    private void removeFromSingleElementArray() {

        linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.remove(null);
        Assertions.assertEquals("[]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a"));
        linkedList.remove(null);
        Assertions.assertEquals("[a]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a"));
        linkedList.remove("b");
        Assertions.assertEquals("[a]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a"));
        linkedList.remove("a");
        Assertions.assertEquals("[]", Arrays.toString(linkedList.toArray()));
    }

    private void removeWithoutDuplicates() {
        linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add(null);
        linkedList.remove(null);
        Assertions.assertEquals("[a]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.add("a");
        linkedList.remove(null);
        Assertions.assertEquals("[a]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add(null);
        linkedList.add("c");
        linkedList.add("d");
        linkedList.remove(null);
        Assertions.assertEquals("[a, c, d]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "b", "c", "d"));
        linkedList.remove(null);
        Assertions.assertEquals("[a, b, c, d]", Arrays.toString(linkedList.toArray()));


        linkedList = new LinkedList<>(Arrays.asList("a", "b"));
        linkedList.remove("a");
        Assertions.assertEquals("[b]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "b"));
        linkedList.remove("b");
        Assertions.assertEquals("[a]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "b", "c", "d"));
        linkedList.remove("z");
        Assertions.assertEquals("[a, b, c, d]", Arrays.toString(linkedList.toArray()));


        linkedList = new LinkedList<>(Arrays.asList("a", "b", "c", "d"));
        linkedList.remove("a");
        Assertions.assertEquals("[b, c, d]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "b", "c", "d"));
        linkedList.remove("c");
        Assertions.assertEquals("[a, b, d]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "b", "c", "d"));
        linkedList.remove("d");
        Assertions.assertEquals("[a, b, c]", Arrays.toString(linkedList.toArray()));
    }

    private void removeWithDuplicates() {
        linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.add(null);
        linkedList.remove(null);
        Assertions.assertEquals("[null]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add(null);
        linkedList.add("a");
        linkedList.add(null);
        linkedList.remove(null);
        Assertions.assertEquals("[a, a, null]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.add("a");
        linkedList.add(null);
        linkedList.add("a");
        linkedList.remove(null);
        Assertions.assertEquals("[a, null, a]", Arrays.toString(linkedList.toArray()));


        linkedList = new LinkedList<>(Arrays.asList("a", "a"));
        linkedList.remove("a");
        Assertions.assertEquals("[a]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "b", "a", "b"));
        linkedList.remove("a");
        Assertions.assertEquals("[b, a, b]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "b", "a", "b"));
        linkedList.remove("b");
        Assertions.assertEquals("[a, a, b]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "a", "a", "a"));
        linkedList.remove("a");
        Assertions.assertEquals("[a, a, a]", Arrays.toString(linkedList.toArray()));
    }


    @Test
    void removeAt() {
        removeAtFromEmptyArray();
        removeAtFromSingleElementArray();
        removeAtMultipleElementArray();
        removeAtIndexTest();
    }

    private void removeAtFromEmptyArray() {
        linkedList = new LinkedList<>();
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.removeAt(0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.removeAt(5));
    }

    private void removeAtFromSingleElementArray() {

        linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.removeAt(0);
        Assertions.assertEquals("[]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a"));
        linkedList.removeAt(0);
        Assertions.assertEquals("[]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a"));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.removeAt(1));
        Assertions.assertEquals("[a]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a"));
        linkedList.removeAt(0);
        Assertions.assertEquals("[]", Arrays.toString(linkedList.toArray()));
    }

    private void removeAtMultipleElementArray() {
        linkedList = new LinkedList<>();
        linkedList.add(null);
        linkedList.add("a");
        linkedList.removeAt(0);
        Assertions.assertEquals("[a]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "b"));
        linkedList.removeAt(0);
        Assertions.assertEquals("[b]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "b"));
        linkedList.removeAt(1);
        Assertions.assertEquals("[a]", Arrays.toString(linkedList.toArray()));

        linkedList = new LinkedList<>(Arrays.asList("a", "b", "c", "d"));
        linkedList.removeAt(3);
        Assertions.assertEquals("[a, b, c]", Arrays.toString(linkedList.toArray()));

    }

    private void removeAtIndexTest() {
        linkedList = new LinkedList<>(Arrays.asList("a", "b", "c"));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.removeAt(-1));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.removeAt(3));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> linkedList.removeAt(10));

        linkedList.removeAt(2);
        Assertions.assertEquals("[a, b]", Arrays.toString(linkedList.toArray()));

        linkedList.removeAt(1);
        Assertions.assertEquals("[a]", Arrays.toString(linkedList.toArray()));

        linkedList.removeAt(0);
        Assertions.assertEquals("[]", Arrays.toString(linkedList.toArray()));
    }
}
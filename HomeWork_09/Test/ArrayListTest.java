import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class ArrayListTest {

    private ArrayList<String> arrayList;

    @Test
    void remove() {
        removeFromEmptyArray();
        removeFromSingleElementArray();
        removeWithoutDuplicates();
        removeWithDuplicates();
    }

    private void removeFromEmptyArray() {
        arrayList = new ArrayList<>();
        arrayList.remove(null);
        Assertions.assertEquals("[]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>();
        arrayList.remove("");
        Assertions.assertEquals("[]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>();
        arrayList.remove("a");
        Assertions.assertEquals("[]", Arrays.toString(arrayList.toArray()));
    }
    private void removeFromSingleElementArray() {

        arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.remove(null);
        Assertions.assertEquals("[]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a"));
        arrayList.remove(null);
        Assertions.assertEquals("[a]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a"));
        arrayList.remove("b");
        Assertions.assertEquals("[a]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a"));
        arrayList.remove("a");
        Assertions.assertEquals("[]", Arrays.toString(arrayList.toArray()));
    }

    private void removeWithoutDuplicates() {
        arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add(null);
        arrayList.remove(null);
        Assertions.assertEquals("[a]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.add("a");
        arrayList.remove(null);
        Assertions.assertEquals("[a]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("c");
        arrayList.add("d");
        arrayList.remove(null);
        Assertions.assertEquals("[a, c, d]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        arrayList.remove(null);
        Assertions.assertEquals("[a, b, c, d]", Arrays.toString(arrayList.toArray()));


        arrayList = new ArrayList<>(Arrays.asList("a", "b"));
        arrayList.remove("a");
        Assertions.assertEquals("[b]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "b"));
        arrayList.remove("b");
        Assertions.assertEquals("[a]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        arrayList.remove("z");
        Assertions.assertEquals("[a, b, c, d]", Arrays.toString(arrayList.toArray()));


        arrayList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        arrayList.remove("a");
        Assertions.assertEquals("[b, c, d]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        arrayList.remove("c");
        Assertions.assertEquals("[a, b, d]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        arrayList.remove("d");
        Assertions.assertEquals("[a, b, c]", Arrays.toString(arrayList.toArray()));
    }

    private void removeWithDuplicates() {
        arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.add(null);
        arrayList.remove(null);
        Assertions.assertEquals("[null]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>();
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");
        arrayList.add(null);
        arrayList.remove(null);
        Assertions.assertEquals("[a, a, null]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");
        arrayList.remove(null);
        Assertions.assertEquals("[a, null, a]", Arrays.toString(arrayList.toArray()));


        arrayList = new ArrayList<>(Arrays.asList("a", "a"));
        arrayList.remove("a");
        Assertions.assertEquals("[a]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "b", "a", "b"));
        arrayList.remove("a");
        Assertions.assertEquals("[b, a, b]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "b", "a", "b"));
        arrayList.remove("b");
        Assertions.assertEquals("[a, a, b]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "a", "a", "a"));
        arrayList.remove("a");
        Assertions.assertEquals("[a, a, a]", Arrays.toString(arrayList.toArray()));
    }



    @Test
    void removeAt() {
        removeAtFromEmptyArray();
        removeAtFromSingleElementArray();
        removeAtMultipleElementArray();
        removeAtIndexTest();
    }


    private void removeAtFromEmptyArray() {
        arrayList = new ArrayList<>();
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.removeAt(0));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.removeAt(5));

    }

    private void removeAtFromSingleElementArray() {

        arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.removeAt(0);
        Assertions.assertEquals("[]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a"));
        arrayList.removeAt(0);
        Assertions.assertEquals("[]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a"));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.removeAt(1));
        Assertions.assertEquals("[a]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a"));
        arrayList.removeAt(0);
        Assertions.assertEquals("[]", Arrays.toString(arrayList.toArray()));
    }

    private void removeAtMultipleElementArray() {
        arrayList = new ArrayList<>();
        arrayList.add(null);
        arrayList.add("a");
        arrayList.removeAt(0);
        Assertions.assertEquals("[a]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "b"));
        arrayList.removeAt(0);
        Assertions.assertEquals("[b]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "b"));
        arrayList.removeAt(1);
        Assertions.assertEquals("[a]", Arrays.toString(arrayList.toArray()));

        arrayList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        arrayList.removeAt(3);
        Assertions.assertEquals("[a, b, c]", Arrays.toString(arrayList.toArray()));

    }

    private void removeAtIndexTest() {
        arrayList = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.removeAt(-1));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.removeAt(3));
        Assertions.assertThrowsExactly(IndexOutOfBoundsException.class, () -> arrayList.removeAt(10));

        arrayList.removeAt(2);
        Assertions.assertEquals("[a, b]", Arrays.toString(arrayList.toArray()));

        arrayList.removeAt(1);
        Assertions.assertEquals("[a]", Arrays.toString(arrayList.toArray()));

        arrayList.removeAt(0);
        Assertions.assertEquals("[]", Arrays.toString(arrayList.toArray()));
    }


}
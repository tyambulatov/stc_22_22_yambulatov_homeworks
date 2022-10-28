import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MostFrequentWordFinderTest {

    private String line;
    private String actual;

    @Test
    void getMostFrequentWordAndFrequency() {
        testNullPtrLine();
        testEmptyLine();
        testOnlyWhitespacesLine();
        testMultipleWhitespacesLine();
        testFrequency();
    }

    private void testNullPtrLine() {
        line = null;
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency());
    }

    private void testEmptyLine() {
        line = "";
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency());
    }

    private void testOnlyWhitespacesLine() {
        line = " ";
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency());

        line = "    ";
        Assertions.assertThrowsExactly(IllegalArgumentException.class,
                () -> new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency());
    }

    private void testMultipleWhitespacesLine() {
        line = "a  ";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[a, 1]", actual);

        line = "  a";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[a, 1]", actual);

        line = "a    b  a";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[a, 2]", actual);

    }

    private void testFrequency() {
        line = "a a";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[a, 2]", actual);

        line = "a a a";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[a, 3]", actual);

        line = "a b";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[a, 1]", actual);

        line = "a b b";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[b, 2]", actual);

        line = "a a b";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[a, 2]", actual);

        line = "a a b b";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[a, 2]", actual);

        line = "a b a b a b b a";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[b, 4]", actual);

        line = "Hello Hello bye Hello bye Inno";
        actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        Assertions.assertEquals("[Hello, 3]", actual);
    }
}
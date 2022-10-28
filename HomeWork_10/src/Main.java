public class Main {
    public static void main(String[] args) {
        String expected = "[Hello, 3]";
        String line = "Hello Hello bye Hello bye Inno";
        String actual = new MostFrequentWordFinder(line).getMostFrequentWordAndFrequency().toString();
        assert expected.equals(actual);
    }
}
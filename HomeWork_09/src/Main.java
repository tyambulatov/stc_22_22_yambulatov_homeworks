import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<>(Arrays.asList(8, 10, 11, 13, 11, 15, -5));
        linkedList.removeAt(2);
        linkedList.remove(11);
        assert "[8, 13, 11, 15, -5]".equals(Arrays.toString(linkedList.toArray()));

        List<Integer> arrayList = new ArrayList<>(Arrays.asList(8, 10, 11, 13, 11, 15, -5));
        arrayList.removeAt(2);
        arrayList.remove(11);
        assert "[8, 13, 11, 15, -5]".equals(Arrays.toString(arrayList.toArray()));
    }
}
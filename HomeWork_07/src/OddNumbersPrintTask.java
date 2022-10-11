import java.util.stream.IntStream;

public class OddNumbersPrintTask extends AbstractNumbersPrintTask {
    public OddNumbersPrintTask(int from, int to) {
        super(from, to);
    }

    @Override
    public int[] calc() {
        return IntStream.rangeClosed(from, to).filter(num -> num % 2 == 1).toArray();
    }
}

import java.util.stream.IntStream;

public class EvenNumbersPrintTask extends AbstractNumbersPrintTask {
    public EvenNumbersPrintTask(int from, int to) {
        super(from, to);
    }

    @Override
    public int[] calc() {
        return IntStream.rangeClosed(from, to).filter(num -> num % 2 == 0).toArray();
    }
}

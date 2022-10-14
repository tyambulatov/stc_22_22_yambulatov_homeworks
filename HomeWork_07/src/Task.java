import java.util.Arrays;

@FunctionalInterface
public interface Task {
    int[] calc();
    default void complete() {
        System.out.println(Arrays.toString(calc()));
    }
}

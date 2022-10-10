import java.util.Arrays;

public interface Task {
    int[] calc();
    default void complete() {
        System.out.println(Arrays.toString(calc()));
    }
}

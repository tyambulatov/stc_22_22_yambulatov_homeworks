import java.util.Arrays;

public class ArraysTasksResolver {
    public static void resolveTask(int[] array, ArrayTask task, int from, int to) {
        int result = task.resolve(array, from, to);

        System.out.println("'array': " + Arrays.toString(array));
        System.out.printf("'from': %d; 'to': %d\n", from, to);
        System.out.println("result: " + result);
    }

}

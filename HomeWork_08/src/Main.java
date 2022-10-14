import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ArrayTask arrayTask1 = (array, from, to) -> {
            ArrayTask.throwExceptionIncorrectArrayOrRange(array, from, to);

            return Arrays.stream(Arrays.copyOfRange(array, from, to + 1)).sum();
        };

        ArrayTask arrayTask2 = (array, from, to) -> {
            ArrayTask.throwExceptionIncorrectArrayOrRange(array, from, to);

            int maxNum = Arrays.stream(Arrays.copyOfRange(array, from, to + 1)).max().getAsInt();
            return String.valueOf(maxNum).chars().map(Character::getNumericValue).sum();
        };

        TestRunner.runTests(arrayTask1, arrayTask2);

        ArraysTasksResolver.resolveTask(new int[]{12, 62, 4, 2, 100, 40, 56}, arrayTask1, 1, 3);
        ArraysTasksResolver.resolveTask(new int[]{12, 62, 4, 2, 100, 40, 56}, arrayTask2, 1, 3);

    }

}
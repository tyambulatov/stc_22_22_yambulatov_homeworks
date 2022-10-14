@FunctionalInterface
public interface ArrayTask {
    int resolve(int[] array, int from, int to);

    static boolean checkIncorrectArrayOrRange(int[] array, int from, int to) {
        return (array.length == 0 || from > to || from < 0 || to > array.length - 1);
    }

    static void throwExceptionIncorrectArrayOrRange(int[] array, int from, int to) {
        if (checkIncorrectArrayOrRange(array, from, to)) {
            throw new IllegalArgumentException("Incorrect 'array' or array range");
        }
    }
}


public class TestRunner {
    public static void runTests(ArrayTask arrayTask1, ArrayTask arrayTask2) {
        testArrayTask1(arrayTask1);
        testArrayTask2(arrayTask2);
        testCheckCorrectArrayAndRange();
    }

    public static void testArrayTask1(ArrayTask arrayTask) {
        assert 0 == arrayTask.resolve(new int[]{0}, 0, 0);
        assert 0 == arrayTask.resolve(new int[]{0, 0, 0, 0}, 1, 2);

        assert 1 == arrayTask.resolve(new int[]{1, 2}, 0, 0);
        assert 3 == arrayTask.resolve(new int[]{1, 2}, 0, 1);
        assert 2 == arrayTask.resolve(new int[]{1, 2}, 1, 1);

        assert 1 == arrayTask.resolve(new int[]{1, 2, 4}, 0, 0);
        assert 3 == arrayTask.resolve(new int[]{1, 2, 4}, 0, 1);
        assert 7 == arrayTask.resolve(new int[]{1, 2, 4}, 0, 2);
        assert 2 == arrayTask.resolve(new int[]{1, 2, 4}, 1, 1);
        assert 6 == arrayTask.resolve(new int[]{1, 2, 4}, 1, 2);
        assert 4 == arrayTask.resolve(new int[]{1, 2, 4}, 2, 2);

        assert 6 == arrayTask.resolve(new int[]{1, 2, 4, 8}, 1, 2);
    }

    public static void testArrayTask2(ArrayTask arrayTask) {

        assert 0 == arrayTask.resolve(new int[]{0}, 0, 0);
        assert 0 == arrayTask.resolve(new int[]{0, 0, 0, 0}, 1, 2);

        assert 5 == arrayTask.resolve(new int[]{5}, 0, 0);
        assert 7 == arrayTask.resolve(new int[]{25}, 0, 0);
        assert 7 == arrayTask.resolve(new int[]{124}, 0, 0);

        assert 2 == arrayTask.resolve(new int[]{11}, 0, 0);
        assert 2 == arrayTask.resolve(new int[]{11, 11}, 0, 1);
        assert 2 == arrayTask.resolve(new int[]{11, 11, 11, 11}, 0, 3);

        assert 4 == arrayTask.resolve(new int[]{13, 12, 14, 11}, 0, 0);
        assert 4 == arrayTask.resolve(new int[]{13, 12, 14, 11}, 0, 1);
        assert 5 == arrayTask.resolve(new int[]{13, 12, 14, 11}, 0, 2);
        assert 5 == arrayTask.resolve(new int[]{13, 12, 14, 11}, 0, 3);

        assert 3 == arrayTask.resolve(new int[]{13, 12, 14, 11}, 1, 1);
        assert 5 == arrayTask.resolve(new int[]{13, 12, 14, 11}, 1, 2);
        assert 5 == arrayTask.resolve(new int[]{13, 12, 14, 11}, 1, 3);

        assert 5 == arrayTask.resolve(new int[]{13, 12, 14, 11}, 2, 2);
        assert 5 == arrayTask.resolve(new int[]{13, 12, 14, 11}, 2, 3);

        assert 2 == arrayTask.resolve(new int[]{13, 12, 14, 11}, 3, 3);


    }

    public static void testCheckCorrectArrayAndRange() {

        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{}, 0, 0);

        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 1, 0);
        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 2, 0);
        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 2, 1);

        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, -1, 0);
        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, -1, 1);
        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, -2, 1);

        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{1}, 0, 1);
        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{1}, 0, 2);
        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 0, 3);
        assert true == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 0, 4);


        assert false == ArrayTask.checkIncorrectArrayOrRange(new int[]{1}, 0, 0);

        assert false == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1}, 0, 0);
        assert false == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1}, 0, 1);
        assert false == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1}, 1, 1);

        assert false == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 0, 0);
        assert false == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 0, 1);
        assert false == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 0, 2);
        assert false == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 1, 1);
        assert false == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 1, 2);
        assert false == ArrayTask.checkIncorrectArrayOrRange(new int[]{1, 1, 1}, 2, 2);
    }
}

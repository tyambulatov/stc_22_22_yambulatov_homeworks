import java.util.Arrays;

public class TestRunner {
    public static void runTests() {
        testTask();
        testAbstractNumbersPrintTask();
        testEvenNumbersPrintTask();
        testOddNumbersPrintTask();

    }

    private static void testTask() {
        Task taskOdd = new OddNumbersPrintTask(1, 2);
        Task taskEven = new EvenNumbersPrintTask(1, 2);

        Task[] tasks = {taskOdd, taskEven};

        int[][] intendedResult = {{1}, {2}};
        int[][] output = new int[2][1];

        for (int i = 0; i < tasks.length; i++) {
            output[i] = tasks[i].calc();
        }
        assert Arrays.deepEquals(intendedResult, output);
    }

    public static void testAbstractNumbersPrintTask() {
        assert 1 == new OddNumbersPrintTask(1, 2).getFrom();
        assert 2 == new OddNumbersPrintTask(1, 2).getTo();

        for (var pair : new int[][]{{2, 1}, {1, 0}, {0, -1},
                                    {0, 0}, {0, 1}, {1, 0},
                                    {-1, -1}, {-1, 1}, {1, -1}}) {
            try {
                new OddNumbersPrintTask(pair[0], pair[1]);
                throw new Exception("Incorrect object created");

            } catch (Exception exception) {

                String expectedMessage1 = "Invalid tasks range:'from' is bigger than 'to': 'from': "
                        + pair[0] + " 'to': " + pair[1];
                String expectedMessage2 = "Invalid task number: 'from' or 'to' parameter is lower than 1: 'from': "
                        + pair[0] + " 'to': " + pair[1];
                String gotMessage = exception.getMessage();

                assert gotMessage.equals(expectedMessage1) || gotMessage.equals(expectedMessage2);
            }

        }

    }

    private static void testOddNumbersPrintTask() {
        assert Arrays.equals(new int[]{1}, new OddNumbersPrintTask(1, 1).calc());
        assert Arrays.equals(new int[]{}, new OddNumbersPrintTask(2, 2).calc());
        assert Arrays.equals(new int[]{9}, new OddNumbersPrintTask(9, 9).calc());
        assert Arrays.equals(new int[]{1}, new OddNumbersPrintTask(1, 2).calc());
        assert Arrays.equals(new int[]{1, 3}, new OddNumbersPrintTask(1, 3).calc());
        assert Arrays.equals(new int[]{1, 3}, new OddNumbersPrintTask(1, 4).calc());
        assert Arrays.equals(new int[]{1, 3, 5}, new OddNumbersPrintTask(1, 5).calc());
        assert Arrays.equals(new int[]{3}, new OddNumbersPrintTask(2, 4).calc());
        assert Arrays.equals(new int[]{3, 5}, new OddNumbersPrintTask(2, 5).calc());
    }

    private static void testEvenNumbersPrintTask() {
        assert Arrays.equals(new int[]{}, new EvenNumbersPrintTask(1, 1).calc());
        assert Arrays.equals(new int[]{2}, new EvenNumbersPrintTask(2, 2).calc());
        assert Arrays.equals(new int[]{10}, new EvenNumbersPrintTask(10, 10).calc());
        assert Arrays.equals(new int[]{2}, new EvenNumbersPrintTask(1, 2).calc());
        assert Arrays.equals(new int[]{2}, new EvenNumbersPrintTask(1, 3).calc());
        assert Arrays.equals(new int[]{2, 4}, new EvenNumbersPrintTask(1, 4).calc());
        assert Arrays.equals(new int[]{2, 4}, new EvenNumbersPrintTask(1, 5).calc());
        assert Arrays.equals(new int[]{2, 4}, new EvenNumbersPrintTask(2, 4).calc());
        assert Arrays.equals(new int[]{2, 4}, new EvenNumbersPrintTask(2, 5).calc());
    }


}

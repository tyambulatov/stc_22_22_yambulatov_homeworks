public class Main {
    public static void main(String[] args) {
        TestRunner.runTests();

        Task[] tasks = new Task[]{
                new EvenNumbersPrintTask(1, 1),
                new EvenNumbersPrintTask(2, 2),
                new EvenNumbersPrintTask(1, 5),
                new OddNumbersPrintTask(1, 1),
                new OddNumbersPrintTask(2, 2),
                new OddNumbersPrintTask(1, 5),
        };
        completeAllTasks(tasks);

    }

    public static void completeAllTasks(Task[] tasks) {
        for (var a : tasks) {
            a.complete();
        }
    }
}

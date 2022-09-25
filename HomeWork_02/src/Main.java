import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        while (a != b) {
            System.out.print(a + " ");

            if (a < b) {
                a++;
            } else {
                a--;
            }
        }

        System.out.print(b);

    }
}

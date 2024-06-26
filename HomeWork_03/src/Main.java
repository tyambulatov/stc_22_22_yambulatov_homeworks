import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        int numLocalMin = 0;

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                /* check if array got only one element
                 * or if array.length() > 1 and 
                 *    element satisfies localMin condition
                 */
                if (n == 1 || array[i] < array[i + 1]) {
                    numLocalMin++;
                }

            } else if (i == n - 1) {    /* check if element is the last element of an array */
                if (array[i] < array[i - 1]) {
                    numLocalMin++;
                }

            } else {
                if (array[i] < array[i - 1] && array[i] < array[i + 1]) {
                    numLocalMin++;
                }
            }
        }

        System.out.println(numLocalMin);

    }
}

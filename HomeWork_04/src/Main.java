import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // input of: array length, array elements, indexBeginSum, indexEndSum
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of an array:");
        int n = sc.nextInt();

        System.out.println("Enter elements of an array:");
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println("Enter index of first and last element:");
        int lhs = sc.nextInt();
        int rhs = sc.nextInt();
        System.out.println();

        // output of: arraySectionSum, arrayEvenNumbers, arrayToInt
        output(a, lhs, rhs);
    }

    public static void output(int[] a, int lhs, int rhs) {

        int sumArray = computeArraySectionSum(a, lhs, rhs);
        System.out.println((sumArray != -1) ? "Sum of array numbers from index " + lhs + " to index " + rhs + " is: " + sumArray : "Can't compute array section sum: incorrect first or last index");

        System.out.print("Array even numbers are: ");
        displayEvenArrayElements(a);

        System.out.println((toInt(a) != -1) ? "Array converted to int: " + toInt(a) : "Can't convert array to Int: array length is zero or some array element are not digits");
    }

    public static int computeArraySectionSum(int[] a, int lhs, int rhs) {
        if (lhs >= 0 && rhs < a.length && lhs <= rhs) {
            int res = 0;

            for (int i = lhs; i <= rhs; i++) {
                res += a[i];
            }

            return res;
        }
        return -1;
    }

    public static void displayEvenArrayElements(int[] a) {
        for (int elm : a) {
            if (elm % 2 == 0) {
                System.out.print(elm + " ");
            }
        }
        System.out.println();
    }

    public static int toInt(int[] a) {
        String s = "";
        for (int i : a) {
            if (i < 0 || i > 9) {
                return -1;
            }
            s += i;
        }
        return (s.length() > 0) ? Integer.parseInt(s) : -1;
    }
}
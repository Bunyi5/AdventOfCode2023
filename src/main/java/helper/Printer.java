package helper;

public class Printer {

    public static void printStringMatrix(String[][] array) {
        for (String[] strings : array) {
            for (String aString : strings) {
                System.out.print(aString + " ");
            }
            System.out.println();
        }
    }
}

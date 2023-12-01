import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Day1 day1 = new Day1();

        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the day number of the problem to be run(0 to exit): ");
            System.out.print("> ");
            try {
                int day = sc.nextInt();
                switch (day) {
                    case 0:
                        System.out.println("Exiting...");
                        System.exit(0);
                    case 1:
                        day1.run();
                        break;
                    default:
                        System.out.println("Invalid day number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }
            System.out.println();
        } while (true) ;

    }
}

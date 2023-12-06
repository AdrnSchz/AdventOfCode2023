import Days.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                        Day1 day1 = new Day1();
                        day1.run();
                        break;
                    case 2:
                        Day2 day2 = new Day2();
                        day2.run();
                        break;
                    case 3:
                        Day3 day3 = new Day3();
                        day3.run();
                        break;
                    case 4:
                        Day4 day4 = new Day4();
                        day4.run();
                        break;
                    case 5:
                        Day5 day5 = new Day5();
                        day5.run();
                        break;
                    case 6:
                        Day6 day6 = new Day6();
                        day6.run();
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

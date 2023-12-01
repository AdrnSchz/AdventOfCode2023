import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class Day1 {

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Datasets/day1.txt"));
            int total = 0;
            String[] words = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
            int[] found = new int[9];

            String line = reader.readLine();
            while (line != null) {
                Arrays.fill(found, 0);
                char first = 'a', last = 'a';
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) <= '9' && line.charAt(i) >= '0') {
                        if (first == 'a') {
                            first = line.charAt(i);
                        } else {
                            last = line.charAt(i);
                        }
                        Arrays.fill(found, 0);
                    }
                    else {
                        char c = line.charAt(i);
                        for (int k = 0; k < 9; k++) {
                            if (c == words[k].charAt(found[k])) {
                                found[k] += 1;
                                if (found[k] == words[k].length()) {
                                    if (first == 'a') {
                                        first = (char) ((k + 1) + '0');
                                    } else {
                                        last = (char) ((k + 1) + '0');
                                    }
                                    found[k] = 0;
                                }
                            }
                            else {
                                if (c == words[k].charAt(0)) {
                                    found[k] = 1;
                                } else {
                                    found[k] = 0;
                                }
                            }

                        }
                    }
                }

                int num;
                if (last == 'a') {
                    num = (first - '0') * 10 + (first - '0');
                } else {
                    num = (first - '0') * 10 + (last - '0');
                }

                total += num;
                //System.out.println(line);
                System.out.println(num);
                line = reader.readLine();
            }

            System.out.println("Total: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

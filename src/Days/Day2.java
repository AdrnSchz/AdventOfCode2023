package Days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day2 {

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Datasets/day2.txt"));
            int total = 0;
            String[] colors = {"red", "green", "blue"};
            int[] largest = {0, 0, 0};

            String line = reader.readLine();
            while (line != null) {
                Arrays.fill(largest, 0);
                String[] split1 = line.split(":");

                split1[1] = split1[1].replaceAll("\s","");
                String[] sets = split1[1].split(";");
                for (String s : sets) {
                    String[] set = s.split(",");
                    for (String value : set) {
                        int num = 0;
                        for (int k = 0; k < value.length(); k++) {
                            if (value.charAt(k) <= '9' && value.charAt(k) >= '0') {
                                num = num * 10 + (value.charAt(k) - '0');
                            } else {
                                break;
                            }
                        }
                        for (int k = 0; k < colors.length; k++) {
                            if (value.contains(colors[k])) {
                                if (num > largest[k]) {
                                    largest[k] = num;
                                }
                                break;
                            }
                        }
                    }
                }
                int power = largest[0] * largest[1] * largest[2];
                //System.out.println("red: " + largest[0] + ", green: " + largest[1] + ", blue: " + largest[2]);
                total += power;
                //System.out.println(power);
                line = reader.readLine();
            }
            System.out.println("Total: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

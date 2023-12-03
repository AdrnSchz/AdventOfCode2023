package Days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day3 {

        public void run() {
            List<String> data = new ArrayList<>();
            getData(data);
            part1(data);
            part2(data);

        }

        private void part1(List<String> data) {
            int num, total = 0, hasAdjacent;
            for (int i = 0; i < data.size(); i++) {
                num = 0;
                hasAdjacent = 0;
                for (int j = 0; j < data.get(i).length(); j++) {
                    if (data.get(i).charAt(j) >= '0' && data.get(i).charAt(j) <= '9') {
                        num = num * 10 + (data.get(i).charAt(j) - '0');
                        hasAdjacent += checkAdjacentSymbols(data, i, j);
                        if (j == data.get(i).length() - 1) {
                            if (hasAdjacent > 0) {
                                total += num;
                            }
                        }
                    }
                    else {
                        if (hasAdjacent > 0) {
                            total += num;
                        }
                        num = 0;
                        hasAdjacent = 0;
                    }
                }
            }

            System.out.println("Part 1: " + total);
        }
        private int checkAdjacentSymbols(List<String> data, int row, int column) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (i >= 0 && i < data.size() && j >= 0 && j < data.get(i).length()) {
                        if (data.get(i).charAt(j) != '.' && (data.get(i).charAt(j) < '0' || data.get(i).charAt(j) > '9')) {
                            return 1;
                        }
                    }
                }
            }
            return 0;
        }

        private void part2(List<String> data) {
            int total = 0;
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).length(); j++) {
                    if (data.get(i).charAt(j) == '*') {
                        total += checkAdjacentNums(data, i, j);
                    }
                }
            }
            System.out.println("Part 2: " + total);
        }

        private int checkAdjacentNums(List<String> data, int row, int column) {
            int total = 0, num1 = 0, num2 = 0, times = 0;

            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (i >= 0 && i < data.size() && j >= 0 && j < data.get(i).length()) {
                        if (data.get(i).charAt(j) >= '0' && data.get(i).charAt(j) <= '9') {
                            if (!(j + 1 <= column + 1 && data.get(i).charAt(j + 1) >= '0' && data.get(i).charAt(j + 1) <= '9')) {
                                if (times == 0) {
                                    num1 = getNum(data, i, j);
                                } else {
                                    num2 = getNum(data, i, j);
                                }
                                times++;
                            }
                        }
                    }
                }
            }

            if (times == 2) {
                total = num1 * num2;
            }

            return total;
        }

        private int getNum(List<String> data, int row, int column) {
            int num = 0, multiplier = 1;

            for (int i = column; i >= 0 ; i--) {
                if (data.get(row).charAt(i) >= '0' && data.get(row).charAt(i) <= '9') {
                    num += (data.get(row).charAt(i) - '0') * multiplier;
                    multiplier *= 10;
                }
                else {
                    break;
                }
            }
            for (int i = column + 1; i < data.get(row).length(); i++) {
                if (data.get(row).charAt(i) >= '0' && data.get(row).charAt(i) <= '9') {
                    num = num * 10 + (data.get(row).charAt(i) - '0');
                }
                else {
                    break;
                }
            }
            return num;
        }

        private void getData(List<String> data) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("Datasets/day3.txt"));
                String line = reader.readLine();

                while (line != null) {
                    data.add(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

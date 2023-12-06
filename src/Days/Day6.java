package Days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day6 {

    static class Race {
        long time;
        long distance;

        public Race(long time) {
            this.time = time;
        }

        public long getTime() {
            return time;
        }

        public long getDistance() {
            return distance;
        }

        public void setDistance(long distance) {
            this.distance = distance;
        }
    }
    public void run() {
        part1();
        part2();
    }

    private void part1() {
        List<Race> races = getData();

        int total = 1;
        for (Race race : races) {
            int ways = 0;
            for (int j = 1; j < race.getTime(); j++) {
                if (j * (race.getTime() - j) >= race.getDistance()) ways++;
            }
            total *= ways;
        }
        System.out.println("Part1: " + total);
    }

    private void part2() {
        Race race = getRace();

        int ways = 0;
        for (int i = 0; i < race.getTime(); i++) {
            if (i * (race.getTime() - i) >= race.getDistance()) ways++;
        }
        System.out.println("Part2: " + ways);
    }

    private ArrayList<Race> getData() {
        ArrayList<Race> races = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Datasets/day6.txt"));
            for (int i = 0; i < 2; i++) {
                String line = reader.readLine();
                String[] separate = line.split(":\\s+", 2);
                String[] numbers = separate[1].split("\\s+");

                for (int j = 0; j < numbers.length; j++) {
                    if (i == 0) races.add(new Race(Long.parseLong(numbers[j])));
                    else races.get(j).setDistance(Long.parseLong(numbers[j]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return races;
    }

    private Race getRace() {
        Race race = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Datasets/day6.txt"));
            for (int i = 0; i < 2; i++) {
                String line = reader.readLine();
                String[] separate = line.split(":\\s+", 2);
                String number = separate[1].replaceAll("\\s+", "");

                if (i == 0) race = new Race(Long.parseLong(number));
                else race.setDistance(Long.parseLong(number));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return race;
    }
}

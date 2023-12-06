package Days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day5 {
    static class Info {
        long seed;
        long range;
        long soil;
        long fertilizer;
        long water;
        long light;
        long temperature;
        long humidity;
        long location;

        public Info(long seed) {
            this.seed = seed;
            this.soil = -1;
            this.fertilizer = -1;
            this.water = -1;
            this.light = -1;
            this.temperature = -1;
            this.humidity = -1;
            this.location = -1;
        }

        public Info(long seed, long range) {
            this.seed = seed;
            this.range = range;
            this.soil = -1;
            this.fertilizer = -1;
            this.water = -1;
            this.light = -1;
            this.temperature = -1;
            this.humidity = -1;
            this.location = -1;
        }

        public void setSoil(long soilType) {this.soil = soilType;}
        public void setFertilizer(long fertilizer) {this.fertilizer = fertilizer;}
        public void setWater(long water) {this.water = water;}
        public void setLight(long light) {this.light = light;}
        public void setTemperature(long temperature) {this.temperature = temperature;}
        public void setHumidity(long humidity) {this.humidity = humidity;}
        public void setLocation(long location) {this.location = location;}
        public long getSeed() {return seed;}
        public long getRange() {return range;}
        public long getSoil() {return soil;}
        public long getFertilizer() {return fertilizer;}
        public long getWater() {return water;}
        public long getLight() {return light;}
        public long getTemperature() {return temperature;}
        public long getHumidity() {return humidity;}
        public long getLocation() {return location;}
    }
    public void run() {
        part1();
        part2();
    }

    private void part1() {
        List<Info> data = new ArrayList<>();
        getDataPart1(data);
        long lowestLocation = Long.MAX_VALUE;
        long seed = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getLocation() < lowestLocation) {
                lowestLocation = data.get(i).getLocation();
                seed = data.get(i).getSeed();
            }
            //printData(data.get(i));
        }
        System.out.println("Part 1: ");
        System.out.println("Lowest location: " + lowestLocation);
        System.out.println("Corresponding to seed: " + seed);
    }

    private void part2() {
        List<Info> data = new ArrayList<>();
        getDataPart2(data);
        long lowestLocation = Long.MAX_VALUE;
        long seed = 0;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getLocation() < lowestLocation) {
                lowestLocation = data.get(i).getLocation();
                seed = data.get(i).getSeed();
            }
            //printData(data.get(i));
        }
        System.out.println("Part 2: ");
        System.out.println("Lowest location: " + lowestLocation);
        System.out.println("Corresponding to seed: " + seed);
    }

    private void getDataPart1(List<Info> data) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Datasets/day5.txt"));
            String line = reader.readLine();
            String[] separate = line.split(":\\s", 2);
            String[] numbers = separate[1].split("\\s+");


            for (int i = 0; i < numbers.length; i++) {
                Info info = new Info(Long.parseLong(numbers[i]));
                data.add(info);
            }
            reader.readLine();
            line = reader.readLine();
            while (line != null) {
                long[] map;
                String category = line;

                line = reader.readLine();
                while (line != null && !line.equals("")) {
                    map = mapCategory(line);
                    for (int i = 0; i < data.size(); i++) {
                        long source = getSource(data, i, category);
                        if (source >= map[1] && source < map[1] + map[2]) {
                            setDestination(data, i, category, map[0] + (source - map[1]));
                        }
                    }
                    line = reader.readLine();
                }
                for (int i = 0; i < data.size(); i++) {
                    long destination = getDestination(data, i, category);
                    long source = getSource(data, i, category);
                    if (destination == -1) {
                        setDestination(data, i, category, source);
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getDataPart2(List<Info> data) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Datasets/day5.txt"));
            String line = reader.readLine();
            String[] separate = line.split(":\\s", 2);
            String[] numbers = separate[1].split("\\s+");


            for (int i = 0; i < numbers.length; i+=2) {
                Info info = new Info(Long.parseLong(numbers[i]), Long.parseLong(numbers[i + 1]));
                data.add(info);
            }
            reader.readLine();
            line = reader.readLine();
            while (line != null) {
                long[] map;
                String category = line;

                line = reader.readLine();
                while (line != null && !line.equals("")) {
                    map = mapCategory(line);

                    if (category.equals("seed-to-soil map:")) {
                        for (int i = 0; i < data.size(); i++) {
                            long source = data.get(i).getSeed();
                            long range = data.get(i).getRange();
                            if (source >= map[1] && source < map[1] + map[2]) {
                                setDestination(data, i, category, map[0] + (source - map[1]));
                            }
                            else if (source >= map[1] + map[2] && source < map[1] + map[2] + range) {
                                setDestination(data, i, category, map[0] + (source - map[1] - map[2]));
                            }
                            if (source >= map[1] && source < map[1] + map[2]) {
                                setDestination(data, i, category, map[0] + (source - map[1]));
                            }
                        }
                    }
                    else {
                        for (int i = 0; i < data.size(); i++) {
                            long source = getSource(data, i, category);
                            if (source >= map[1] && source < map[1] + map[2]) {
                                setDestination(data, i, category, map[0] + (source - map[1]));
                            }
                        }
                    }
                    line = reader.readLine();
                }
                for (int i = 0; i < data.size(); i++) {
                    long destination = getDestination(data, i, category);
                    long source = getSource(data, i, category);
                    if (destination == -1) {
                        setDestination(data, i, category, source);
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long[] mapCategory(String line) {
        String[] numbers = line.split("\\s+");

        long[] map = new long[numbers.length];
        for (int i = 0; i < map.length; i++) {
            try {
                map[i] =  Long.parseLong(numbers[i]);
            } catch (NumberFormatException e) {
                System.out.println("Failed: " + numbers[i] + " is not a number.");
            }
        }

        return map;
    }

    private long getSource(List<Info> data, int index, String category) {
        switch (category) {
            case "seed-to-soil map:" -> {
                return data.get(index).getSeed();
            }
            case "soil-to-fertilizer map:" -> {
                return data.get(index).getSoil();
            }
            case "fertilizer-to-water map:" -> {
                return data.get(index).getFertilizer();
            }
            case "water-to-light map:" -> {
                return data.get(index).getWater();
            }
            case "light-to-temperature map:" -> {
                return data.get(index).getLight();
            }
            case "temperature-to-humidity map:" -> {
                return data.get(index).getTemperature();
            }
            case "humidity-to-location map:" -> {
                return data.get(index).getHumidity();
            }
            default -> {
                System.out.println("Input file is not formatted correctly.");
                System.exit(0);
            }
        }
        return -1;
    }
    private long getDestination(List<Info> data, int index, String category) {
        switch (category) {
            case "seed-to-soil map:" -> {
                return data.get(index).getSoil();
            }
            case "soil-to-fertilizer map:" -> {
                return data.get(index).getFertilizer();
            }
            case "fertilizer-to-water map:" -> {
                return data.get(index).getWater();
            }
            case "water-to-light map:" -> {
                return data.get(index).getLight();
            }
            case "light-to-temperature map:" -> {
                return data.get(index).getTemperature();
            }
            case "temperature-to-humidity map:" -> {
                return data.get(index).getHumidity();
            }
            case "humidity-to-location map:" -> {
                return data.get(index).getLocation();
            }
            default -> {
                System.out.println("Input file is not formatted correctly.");
                System.exit(0);
            }
        }
        return -1;
    }

    private void setDestination(List<Info> data, int index, String category, long dest) {
        switch (category) {
            case "seed-to-soil map:" -> {
                data.get(index).setSoil(dest);
            }
            case "soil-to-fertilizer map:" -> {
                data.get(index).setFertilizer(dest);
            }
            case "fertilizer-to-water map:" -> {
                data.get(index).setWater(dest);
            }
            case "water-to-light map:" -> {
                data.get(index).setLight(dest);
            }
            case "light-to-temperature map:" -> {
                data.get(index).setTemperature(dest);
            }
            case "temperature-to-humidity map:" -> {
                data.get(index).setHumidity(dest);
            }
            case "humidity-to-location map:" -> {
                data.get(index).setLocation(dest);
            }
            default -> {
                System.out.println("Input file is not formatted correctly.");
                System.exit(0);
            }
        }
    }

    private void printData(Info info) {
        System.out.println("Seed: " + info.getSeed() + " | " + "Soil: " + info.getSoil() + " | " + "Fertilizer: " + info.getFertilizer() + " | " + "Water: " + info.getWater() + " | " + "Light: " + info.getLight() + " | " + "Temperature: " + info.getTemperature() + " | " + "Humidity: " + info.getHumidity() + " | " + "Location: " + info.getLocation());
    }
}

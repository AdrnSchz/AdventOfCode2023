package Days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day4 {

    class Card {
        int id;
        String[] numbers;
        String[] winning;

        public Card(int id, String[] numbers, String[] winning) {
            this.id = id;
            this.numbers = numbers;
            this.winning = winning;
        }
    }
    public void run() {
        List<Card> cards = new ArrayList<>();
        getData(cards);
        part1(cards);
        part2(cards);

    }

    private void part1(List<Card > cards) {
        int total = 0;
        for (int i = 0; i < cards.size(); i++) {
            int points = 0;

            for (int j = 0; j < cards.get(i).numbers.length; j++) {
                for (int k = 0; k < cards.get(i).winning.length; k++) {
                    if (cards.get(i).numbers[j].equals("")) break;
                    if (cards.get(i).winning[k].equals("")) continue;
                    if (cards.get(i).numbers[j].equals(cards.get(i).winning[k])) {
                        if (points == 0) points = 1;
                        else points *= 2;
                        break;
                    }
                }
            }
            total += points;
        }
        System.out.println("Part 1: " + total);
    }

    private void part2(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            int points = 0;
            for (int j = 0; j < cards.get(i).numbers.length; j++) {
                for (int k = 0; k < cards.get(i).winning.length; k++) {
                    if (cards.get(i).numbers[j].equals("")) break;
                    if (cards.get(i).winning[k].equals("")) continue;
                    if (cards.get(i).numbers[j].equals(cards.get(i).winning[k])) {
                        points++;
                        cards.add(cards.get(cards.get(i).id + points));
                        break;
                    }
                }
            }
        }
        System.out.println("Part 2: " + cards.size());
    }

    private void getData(List<Card> cards) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Datasets/day4.txt"));
            String line = reader.readLine();
            int i = 0;
            while (line != null) {
                String[] separate = line.split(":\\s", 2);
                String[] sides = separate[1].split("\\|");
                String[] numbers = sides[0].split("\\s+");
                String[] winning = sides[1].split("\\s+");
                cards.add(new Card(i, numbers, winning));

                line = reader.readLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

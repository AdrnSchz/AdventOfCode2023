package Days;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day7 {

    static class Hand {
        String cards;
        String cardsOg;
        int bid;
        int rank; // 0 = nothing, 1 = pair, 2 = two pairs, 3 = group of 3, 4 = full house, 5 = group of 4, 6 = 5 of a kind
        private Hand(String cards, int bid) {
            this.cards = cards;
            this.cardsOg = cards;
            this.bid = bid;
            this.rank = 0;
        }

        private String getCards() {
            return cards;
        }

        private void setCards(String cards) {
            this.cards = cards;
        }

        private String getCardsOg() {
            return cardsOg;
        }

        private int getBid() {
            return bid;
        }

        private int getRank() {
            return rank;
        }

        private void setRank(int rank) {
            this.rank = rank;
        }

        private int getValue(int index) {
            return switch (cardsOg.charAt(index)) {
                case 'A' -> 14;
                case 'K' -> 13;
                case 'Q' -> 12;
                case 'J' -> 11;
                case 'T' -> 10;
                default -> cardsOg.charAt(index) - '0';
            };
        }

        private int getValueP2(int index) {
            return switch (cardsOg.charAt(index)) {
                case 'A' -> 14;
                case 'K' -> 13;
                case 'Q' -> 12;
                case 'T' -> 10;
                case 'J' -> 1;
                default -> cardsOg.charAt(index) - '0';
            };
        }
    }
    public void run() {
        part1();
        part2();
    }

    private void part1() {
        List<Hand> hands = getData();
        int total = 0;

        assignRanks(hands);
        quickSort(hands, 0, hands.size() - 1, 1);

        for (int i = 0; i < hands.size(); i++) {
            total += hands.get(i).getBid() * (hands.size() - i);
        }

        System.out.println("Part 1: " + total);
    }

    private void part2() {
        List<Hand> hands = getData();
        int total = 0;

        assignRanksP2(hands);
        quickSort(hands, 0, hands.size() - 1, 2);

        for (int i = 0; i < hands.size(); i++) {
            total += hands.get(i).getBid() * (hands.size() - i);
        }

        System.out.println("Part 2: " + total);
    }

    private int getOccurrences(Hand hand, char c) {
        String cards = hand.getCards();
        int count = 0;
        for (int i = 0; i < cards.length(); i++) {
            if (cards.charAt(i) == c) {
                count++;
                if (i == 0) cards = cards.substring(i + 1);
                else if (i == cards.length() - 1) cards = cards.substring(0, i);
                else cards = cards.substring(0, i) + cards.substring(i + 1);
                i--;
            }
        }
        hand.setCards(cards);
        return count;
    }

    private int compareCards(Hand hand1, Hand hand2, int part) {
        if (hand1.getRank() > hand2.getRank()) return 1;
        else if (hand1.getRank() < hand2.getRank()) return -1;
        else {
            for (int i = 0; i < hand1.getCardsOg().length(); i++) {
                if (part == 1) {
                    if (hand1.getValue(i) > hand2.getValue(i)) return 1;
                    else if (hand1.getValue(i) < hand2.getValue(i)) return -1;
                }
                else {
                    if (hand1.getValueP2(i) > hand2.getValueP2(i)) return 1;
                    else if (hand1.getValueP2(i) < hand2.getValueP2(i)) return -1;
                }
            }
        }
        return 0;
    }
    void swap(List<Hand> hands, int i, int j) {
        Hand temp = hands.get(i);
        hands.set(i, hands.get(j));
        hands.set(j, temp);
    }
    int partition(List<Hand> hands, int low, int high, int part) {
        Hand pivot = hands.get(high);
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (hands.get(j).getRank() > pivot.getRank()) {
                i++;
                swap(hands, i, j);
            }
            else if (hands.get(j).getRank() == pivot.getRank()) {
                if (compareCards(hands.get(j), pivot, part) == 1) {
                    i++;
                    swap(hands, i, j);
                }
            }
        }
        swap(hands, i + 1, high);
        return (i + 1);
    }
    private void quickSort(List<Hand> hands, int low, int high, int part) {
        if (low < high) {
            int p = partition(hands, low, high, part);

            quickSort(hands, low, p - 1, part);
            quickSort(hands, p + 1, high, part);
        }
    }

    private void assignRanks(List<Hand> hands) {
        for (Hand hand : hands) {
            List<Integer> times = new ArrayList<>();

            while (hand.getCards().length() > 0) {
                times.add(getOccurrences(hand, hand.getCards().charAt(0)));
            }

            int count = 0;
            for (Integer time : times) {
                if (time == 5) {
                    hand.setRank(6);
                    break;
                } else if (time == 4) {
                    hand.setRank(5);
                    break;
                } else if (time == 3) count += 3;
                else if (time == 2) count += 2;
            }
            if (count == 2) hand.setRank(1);
            else if (count == 3) hand.setRank(3);
            else if (count == 4) hand.setRank(2);
            else if (count == 5) hand.setRank(4);
        }
    }

    private int takeOutJokers(Hand hand) {
        int count = 0;
        for (int i = 0; i < hand.getCards().length(); i++) {
            if (hand.getCards().charAt(i) == 'J') {
                count++;
                if (i == 0) hand.setCards(hand.getCards().substring(i + 1));
                else if (i == hand.getCards().length() - 1) hand.setCards(hand.getCards().substring(0, i));
                else hand.setCards(hand.getCards().substring(0, i) + hand.getCards().substring(i + 1));
                i--;
            }
        }
        return count;
    }
    private void assignRanksP2(List<Hand> hands) {
        for (Hand hand : hands) {
            List<Integer> times = new ArrayList<>();

            int jokers = takeOutJokers(hand);
            while (hand.getCards().length() > 0) {
                times.add(getOccurrences(hand, hand.getCards().charAt(0)));
            }

            if (jokers == 5) {
                hand.setRank(6);
                continue;
            }
            int best = 0, twos = 0;
            for (Integer time : times) {
                if (time == 5 || (jokers + time == 5)) {
                    hand.setRank(6);
                    best = 6;
                    break;
                } else if (time == 4 || (jokers + time == 4)) {
                    hand.setRank(5);
                    best = 5;
                } else if (time == 3 && times.contains(2) && best < 4) {
                        hand.setRank(4);
                        best = 4;
                }
                else if (time == 3 && best < 3) {
                        hand.setRank(3);
                        best = 3;
                }
                else if (time == 2) {
                    twos++;
                }
            }
            if (best >= 4) continue;
            if (twos == 2 && jokers == 1) hand.setRank(4);
            else if (twos == 1 && jokers == 1 && best < 3) hand.setRank(3);
            else if (twos == 0 && jokers == 2) hand.setRank(3);
            else if (twos == 2 && best < 2) hand.setRank(2);
            else if (twos == 1 && best < 1) hand.setRank(1);
            else if (jokers == 1) hand.setRank(1);
        }
    }

    private List<Hand> getData() {
        List<Hand> hands = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Datasets/day7.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                hands.add(new Hand(line.split("\\s")[0], Integer.parseInt(line.split("\\s")[1])));
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }

        return hands;
    }
}

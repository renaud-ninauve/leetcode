package fr.ninauve.renaud.leetcode.zuma;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Zuma {

    public int findMinStep(String board, String hand) {
        Board board0 = toBoard(board);
        Hand hand0 = toHand(hand);
        return findMinStep(board0, hand0);
    }

    int findMinStep(Board board0, Hand hand0) {
        int min = -1;
        Queue<Path> queue = new LinkedList<>();
        queue.offer(new Path(board0, hand0));
        while (!queue.isEmpty()) {
            Path path = queue.poll();
            Board board = path.board();
            Hand hand = path.hand();

            int played = hand0.size() - hand.size();
            if (board.isEmpty()) {
                min = min == -1 ? played : Math.min(min, played);
                continue;
            }
            if (hand.isEmpty()) {
                continue;
            }
            if (min != -1 && played >= min) {
                continue;
            }

            for (int i = 0; i < board.size(); i++) {
                ColoredBall current = board.ballAt(i);
                if (i > 0 && Objects.equals(current, board.ballAt(i - 1))) {
                    continue;
                }
                final List<ColoredBall> playableBalls = new ArrayList<>();
                if (i > 0) {
                    playableBalls.add(board.ballAt(i - 1));
                }
                playableBalls.add(board.ballAt(i));
                playableBalls.retainAll(hand.colors());
                for (ColoredBall playableBall : playableBalls) {
                    Board newBoard = board.insert(i, playableBall);
                    Hand newHand = hand.remove(playableBall);
                    queue.add(new Path(newBoard, newHand));
                }
            }
        }
        return min;
    }

    record Path(Board board, Hand hand) {
    }

    static void deleteBiggerThanTris(List<ColoredBall> board) {
        int previousSize;
        do {
            previousSize = board.size();
            int count = 1;
            int i = 0;
            while (i < board.size()) {
                ColoredBall current = board.get(i);
                if (i == 0) {
                    i++;
                    continue;
                }
                ColoredBall previous = board.get(i - 1);
                if (Objects.equals(current, previous)) {
                    count++;
                } else {
                    if (count >= 3) {
                        i = deleteUpToExcluded(board, i, count);
                    }
                    count = 1;
                }
                i++;
            }
            if (count >= 3) {
                deleteUpToExcluded(board, board.size(), count);
            }
        } while (board.size() < previousSize);
    }

    static int deleteUpToExcluded(List<ColoredBall> board, int upTo, int count) {
        int indexToDelete = upTo - count;
        for (int j = 0; j < count; j++) {
            board.remove(indexToDelete);
        }
        return indexToDelete;
    }

    static Board toBoard(String board) {
        List<ColoredBall> coloredBalls = new ArrayList<>();
        for (int i = 0; i < board.length(); i++) {
            char current = board.charAt(i);
            coloredBalls.add(new ColoredBall(current));
        }
        return new Board(coloredBalls);
    }

    static Hand toHand(String hand) {
        Map<ColoredBall, Integer> sumByColor = new HashMap<>();
        for (char c : hand.toCharArray()) {
            ColoredBall current = new ColoredBall(c);
            sumByColor.compute(current, (coloredBall, previousSum) -> previousSum == null ? 1 : previousSum + 1);
        }
        return new Hand(sumByColor);
    }

    record Hand(Map<ColoredBall, Integer> countsByColor) {
        Set<ColoredBall> colors() {
            return countsByColor.entrySet()
                    .stream()
                    .filter(e -> e.getValue() > 0)
                    .map(Entry::getKey)
                    .collect(Collectors.toSet());
        }

        Hand remove(ColoredBall coloredBall) {
            Map<ColoredBall, Integer> newHand = new HashMap<>(countsByColor);
            newHand.computeIfPresent(coloredBall, (key, count) -> count - 1);
            return new Hand(newHand);
        }

        int size() {
            return countsByColor.values()
                    .stream()
                    .mapToInt(i -> i)
                    .sum();
        }

        boolean isEmpty() {
            return colors().isEmpty();
        }
    }

    record Board(List<ColoredBall> coloredBalls) {
        Board insert(int index, ColoredBall newBall) {
            List<ColoredBall> newBalls = new ArrayList<>(coloredBalls);
            newBalls.add(index, newBall);
            deleteBiggerThanTris(newBalls);
            return new Board(newBalls);
        }

        ColoredBall ballAt(int index) {
            return coloredBalls.get(index);
        }

        int size() {
            return coloredBalls.size();
        }

        boolean isEmpty() {
            return coloredBalls().isEmpty();
        }
    }

    record ColoredBall(char color) {
    }
}

package fr.ninauve.renaud.leetcode.problems.zuma;

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
        Set<Turn> visited = new HashSet<>();
        int min = -1;
        Queue<Turn> queue = new LinkedList<>();
        queue.offer(new Turn(board0, hand0));
        while (!queue.isEmpty()) {
            Turn turn = queue.poll();
            if (visited.contains(turn)) {
                continue;
            }
            visited.add(turn);
            Board board = turn.board();
            Hand hand = turn.hand();

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
                ColoredBall coloredBall = board.ballAt(i);
                Set<ColoredBall> playableColors = new HashSet<>();
                if (hand.colors().contains(coloredBall)) {
                    playableColors.add(coloredBall);
                }
                if (i > 0 && Objects.equals(coloredBall, board.ballAt(i - 1))) {
                    playableColors.addAll(hand.colors());
                }
                for (ColoredBall playableBall : playableColors) {
                    Board newBoard = board.insert(i, playableBall);
                    Hand newHand = hand.remove(playableBall);
                    queue.add(new Turn(newBoard, newHand));
                }
            }
        }
        return min;
    }

    record Turn(Board board, Hand hand) {
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

        Set<ColoredBall> colors() {
            return new HashSet<>(coloredBalls);
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

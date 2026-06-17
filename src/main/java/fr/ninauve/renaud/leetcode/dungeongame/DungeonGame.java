package fr.ninauve.renaud.leetcode.dungeongame;

import java.util.LinkedList;
import java.util.Queue;

public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int maxMinLife = Integer.MIN_VALUE;
        final Queue<ToVisit> queue = new LinkedList<>();
        queue.add(new ToVisit(new Room(new Row(0), new Col(0)), 0, 0));
        final Row maxRow = new Row(dungeon.length - 1);
        final Col maxCol = new Col(dungeon[0].length - 1);
        final Room endRoom = new Room(maxRow, maxCol);
        while(!queue.isEmpty()) {
            ToVisit toVisit = queue.poll();
            Room room = toVisit.room();
            int life = dungeon[room.row().value()][room.col().value()];
            int lifeTotal = toVisit.lifeTotal();
            int minLifeTotal = toVisit.minLifeTotal();

            int newLifeTotal = lifeTotal + life;
            int newMinLifeTotal = Math.min(minLifeTotal, newLifeTotal);

            if (room.equals(endRoom)) {
                maxMinLife = Integer.max(newMinLifeTotal, maxMinLife);
                continue;
            }

            Room right = room.right();
            if (right.isValid(maxRow, maxCol)) {
                queue.offer(new ToVisit(right, newLifeTotal, newMinLifeTotal));
            }
            Room down = room.down();
            if (down.isValid(maxRow, maxCol)) {
                queue.offer(new ToVisit(down, newLifeTotal, newMinLifeTotal));
            }
        }
        return maxMinLife < 0 ? -maxMinLife + 1 : 1;
    }

    record ToVisit(Room room, int lifeTotal, int minLifeTotal) {}

    record Room(Row row, Col col){
        Room down() {
            return new Room(row().down(), col);
        }
        Room right() {
            return new Room(row, col().right());
        }
        boolean isValid(Row maxRow, Col maxCol) {
            return row.value() >= 0 && row.value() <= maxRow.value()
                    && col.value() >= 0 && col.value() <= maxCol.value();
        }
    }
    record Row(int value){
        Row down() {
            return new Row(value + 1);
        }
    }
    record Col(int value){
        Col right() {
            return new Col(value + 1);
        }
    }
}

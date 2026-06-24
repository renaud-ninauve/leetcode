package fr.ninauve.renaud.leetcode.dungeongame;

public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        int maxRow = dungeon.length - 1;
        int maxCol = dungeon[0].length - 1;
        int[][] minLife = new int[dungeon.length + 1][dungeon[0].length + 1];
        for (int row = 0; row < maxRow + 1; row++) {
            minLife[row][maxCol + 1] = Integer.MAX_VALUE;
        }
        for (int col = 0; col < maxCol + 1; col++) {
            minLife[maxRow + 1][col] = Integer.MAX_VALUE;
        }
        minLife[maxRow][maxCol + 1] = 1;
        minLife[maxRow + 1][maxCol] = 1;
        for (int row = maxRow; row >= 0; row--) {
            for (int col = maxCol; col >= 0; col--) {
                int previous = Math.min(minLife[row][col + 1], minLife[row + 1][col]);
                minLife[row][col] =
                        Math.max(1, previous - dungeon[row][col]);
            }
        }
        return minLife[0][0];
    }
}

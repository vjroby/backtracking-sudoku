package ro.robertgabriel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SolveBacktracking {
    public static final int n = 9;
    private static Integer[][] startGrid = new Integer[9][9];

    public static Integer[][] solve(Integer[][] grid) {
        int first = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                startGrid[i][j] = grid[i][j];
            }
        }
        boolean solved = addElement(grid, 0, 0);
        System.out.println("Solved:" + solved);
        return grid;
    }

    private static boolean addElement(Integer[][] grid, int i, int j) {
        int el = 1;

        if (!check(grid)) {
            return false;
        }
        boolean reversedB = false;
        while (i <= 8 && j <= 8 && el <= 9) {
            if (System.currentTimeMillis() / 1000 % 2 == 0) {

                printBoard(grid);
            }
            reversedB = false;
            if (grid[i][j] == null) {
                grid[i][j] = el;

                if (addElement(grid, i, j)) {

                    continue;
                } else {
                    reversedB = true;
                    el = grid[i][j] + 1;
                    grid[i][j] = null;
                }
                if (el == 10) {
                    return false;
                }
            }
            if (!reversedB) {
                if (j == 8) {
                    i++;
                    j = 0;

                } else {
                    j++;
                }
            }

        }
        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                if (grid[k][l] == null) {
                    return false;
                }
            }
        }

        return true;
    }



    public static boolean check(Integer[][] grid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = i + 1; k < n; k++) {
                    if (grid[i][j] != null && k != i && grid[k][j] != null && grid[i][j].equals(grid[k][j])) {
                        return false;
                    }
                }
                for (int m = j + 1; m < n; m++) {
                    if (grid[i][j] != null && m != j && grid[i][m] != null && grid[i][j].equals(grid[i][m])) {
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int j = 0, k = 0, sj = 0, sk = 0;
            switch (i) {
                case (0):
                    j = 0;
                    k = 0;
                    sj = 3;
                    sk = 3;
                    break;
                case (1):
                    j = 0;
                    k = 3;
                    sj = 3;
                    sk = 6;
                    break;
                case (2):
                    j = 0;
                    k = 6;
                    sj = 3;
                    sk = 9;
                    break;
                case (3):
                    j = 3;
                    k = 0;
                    sj = 6;
                    sk = 3;
                    break;
                case (4):
                    j = 3;
                    k = 3;
                    sj = 6;
                    sk = 6;
                    break;
                case (5):
                    j = 3;
                    k = 6;
                    sj = 6;
                    sk = 9;
                    break;
                case (6):
                    j = 6;
                    k = 0;
                    sj = 9;
                    sk = 3;
                    break;
                case (7):
                    j = 6;
                    k = 3;
                    sj = 9;
                    sk = 6;
                    break;
                case (8):
                    j = 6;
                    k = 6;
                    sj = 9;
                    sk = 9;
                    break;
            }
            List<Integer> testArray = new ArrayList<>();
            for (int m = j; m < sj; m++) {
                for (int l = k; l < sk; l++) {
                    if (grid[m][l] != null) {
                        testArray.add(grid[m][l]);
                    }
                }
            }
            Set<Integer> testSet = new HashSet<>(testArray);
            if (testSet.size() != testArray.size()) {
                return false;
            }
        }


        return true;
    }

    public static void printBoard(Integer[][] board) {
        for (int i = 0; i < 9; i++) {
            System.out.print("|");
            for (int j = 0; j < 9; j++) {
                String el = board[i][j] == null ? "-" : String.valueOf(board[i][j]);
                System.out.print(" " + el + " ");
                if (j % 3 == 2) {
                    System.out.print("|");
                }
            }
            if (i % 3 == 2) {
                System.out.println();
                for (int n = 0; n < 10 * 3 + 1; n++) {
                    System.out.print("_");
                }
            }
            System.out.println("");
        }
        System.out.println("==============================================");

    }
}

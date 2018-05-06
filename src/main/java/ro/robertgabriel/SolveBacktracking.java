package ro.robertgabriel;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class SolveBacktracking {
    public static final int n = 9;
    public static boolean printFlag = false;
    public static Integer[][] solve(Integer[][] grid) {
        boolean solved = addElement(grid, 0, 0);
        return grid;
    }

    private static boolean addElement(Integer[][] grid, int i, int j) {
        int el = 1;
        if (!check(grid)) {
            return false;
        }
        printBoard(grid);
        boolean reversedB = false;
        while (i <= 8 && j <= 8 && el <= 9) {
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
        if (checkLines(grid)) return false;
        return !checkSquares(grid);
    }

    private static boolean checkLines(Integer[][] grid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = i + 1; k < n; k++) {
                    if (grid[i][j] != null && k != i && grid[k][j] != null && grid[i][j].equals(grid[k][j])) {
                        return true;
                    }
                }
                for (int m = j + 1; m < n; m++) {
                    if (grid[i][j] != null && m != j && grid[i][m] != null && grid[i][j].equals(grid[i][m])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkSquares(Integer[][] grid) {
        for (int i = 0; i < n; i = i + 3) {
            for (int g = 0; g < n; g = g + 3) {
                List<Integer> testArray = new ArrayList<>();
                for (int m = i; m < i + 3; m++) {
                    for (int l = g; l < g + 3; l++) {
                        if (grid[m][l] != null) {
                            testArray.add(grid[m][l]);
                        }
                    }
                }
                Set<Integer> testSet = new HashSet<>(testArray);
                if (testSet.size() != testArray.size()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printBoard(Integer[][] board) {
        if (!printFlag) {
            return;
        }
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

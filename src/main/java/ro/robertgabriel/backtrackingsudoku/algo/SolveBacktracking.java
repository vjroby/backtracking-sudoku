package ro.robertgabriel.backtrackingsudoku.algo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.robertgabriel.backtrackingsudoku.controllers.GridObserver;
import ro.robertgabriel.backtrackingsudoku.exceptions.UnsolvableException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class SolveBacktracking {

    private static final int GRID_LENGTH = 9;
    private boolean printFlag = false;
    private GridObserver gridObserver = new GridObserver((a, b, c) -> {
    });

    public Integer[][] solve(Integer[][] grid) {
        boolean solved = addElement(grid, 0, 0);
        if (!solved) {
            throw new UnsolvableException("Sudoku can not be solved");
        }
        return grid;
    }

    private boolean addElement(Integer[][] grid, int i, int j) {
        int el = 1;
        if (!check(grid)) {
            return false;
        }
        printBoard(grid);
        while (i <= 8 && j <= 8 && el <= 9) {
            if (grid[i][j] == null) {
                gridObserver.onUpdate(el, i, j);
                grid[i][j] = el;
                if (!addElement(grid, i, j)) {
                    el = grid[i][j] + 1;
                    gridObserver.onUpdate(null, i, j);
                    grid[i][j] = null;
                }
                continue;
            }
            if (j == 8) {
                i++;
                j = 0;
            } else {
                j++;
            }
        }
        return !isNotSolved(grid);
    }

    private boolean isNotSolved(Integer[][] grid) {
        for (int k = 0; k < GRID_LENGTH; k++) {
            for (int l = 0; l < GRID_LENGTH; l++) {
                if (grid[k][l] == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(Integer[][] grid) {
        if (checkLines(grid)) return false;
        return checkSquares(grid);
    }

    private boolean checkLines(Integer[][] grid) {
        for (int i = 0; i < GRID_LENGTH; i++) {
            for (int j = 0; j < GRID_LENGTH; j++) {
                for (int k = i + 1; k < GRID_LENGTH; k++) {
                    if (grid[i][j] == null || grid[k][j] == null) {
                        continue;
                    }
                    if (grid[i][j].equals(grid[k][j])) {
                        return true;
                    }
                }
                for (int m = j + 1; m < GRID_LENGTH; m++) {
                    if (grid[i][j] == null || grid[i][m] == null) {
                        continue;
                    }
                    if (grid[i][j].equals(grid[i][m])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkSquares(Integer[][] grid) {
        for (int i = 0; i < GRID_LENGTH; i = i + 3) {
            for (int g = 0; g < GRID_LENGTH; g = g + 3) {
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
                    return false;
                }
            }
        }
        return true;
    }

    public void setPrintFlag(boolean printFlag) {
        this.printFlag = printFlag;
    }

    public void printBoard(Integer[][] board) {
        if (!printFlag) {
            return;
        }
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < 9; i++) {
            sb.append("|");
            for (int j = 0; j < 9; j++) {
                String el = board[i][j] == null ? "-" : String.valueOf(board[i][j]);
                sb.append(" ").append(el).append(" ");
                if (j % 3 == 2) {
                    sb.append("|");
                }
            }
            if (i % 3 == 2) {
                sb.append("\n");
                for (int n = 0; n < 10 * 3 + 1; n++) {
                    sb.append("_");
                }
            }
            sb.append("\n");
        }
        sb.append("==============================================");
        log.debug(sb.toString());
    }

    public void setGridObserver(GridObserver gridObserver) {
        this.gridObserver = gridObserver;
    }
}

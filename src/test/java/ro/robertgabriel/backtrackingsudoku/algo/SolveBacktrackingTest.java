package ro.robertgabriel.backtrackingsudoku.algo;

import org.junit.jupiter.api.Test;
import ro.robertgabriel.backtrackingsudoku.exceptions.UnsolvableException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class SolveBacktrackingTest {

    private SolveBacktracking solveBacktracking = new SolveBacktracking();

    @Test
    public void shouldSolve() throws Exception{
        Integer[][] resolved = solveBacktracking.solve(createGridToSolve());
        for (int i = 0; i < 9; i++) {
            assertArrayEquals(solved()[i],resolved[i]);
        }
    }

    private Integer[][] solved() throws FileNotFoundException {
        Scanner input = new Scanner(new File(this.getClass().getResource("/solved.csv").getPath()));
        int n = 9;
        Integer[][] board = new Integer[n][n];

        for (int i = 0; i < n; i++) {
            if(input.hasNext()){
                String line = input.nextLine();
                String[] lineArr = line.split(",");
                for (int j = 0; j < lineArr.length; j++) {
                    board[i][j] = Integer.valueOf(lineArr[j]);
                }
            }
        }
        return board;
    }

    @Test
    public void shouldNotSolve() throws Exception{
        Integer[][] grid = {
                {null,null,null,1,null,null,4,null,null},
                {1,null,null,null,4,null,null,null,null},
                {null,2,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,9},
                {null,null,null,null,null,null,null,8,null},
                {null,null,null,null,null,null,7,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {1,4,1,null,5,null,null,6,null}
        };
        Throwable exception = assertThrows(UnsolvableException.class, () -> {
            solveBacktracking.solve(grid);
        });

        assertEquals("Sudoku can not be solved", exception.getMessage());
    }


    @Test
    public void shouldCheckInvalidFirstGrid() throws Exception{
        Integer[][] grid = {
                {6,3,4,6,7,8,9,1,2},//0.0 <- error, should be 5
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,4,9}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }

    @Test
    public void shouldCheckInvalid2Grid() throws Exception{
        Integer[][] grid = {
                {5,3,4,6,7,8,9,1,2},//0.0 <- error, should be 5
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,9,8,6,1,4,9}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }

    @Test
    public void shouldCheckInvalidGridSquare1() throws Exception{
        Integer[][] grid = {
                {5,3,4,6,7,8,9,1,2},
                {6,7,3,1,9,5,null,4,8}, // 3 is twice in first square
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,null,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }

    @Test
    public void shouldCheckInvalidGridSquare2() throws Exception{
        Integer[][] grid = {
                {null,null,null,4,null,null,null,null,null},
                {null,null,null,null,4,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }

    @Test
    public void shouldCheckInvalidGridSquare3() throws Exception{
        Integer[][] grid = {
                {null,null,null,null,null,null,null,1,null},
                {null,null,null,null,null,null,null,null,1},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }
    @Test
    public void shouldCheckInvalidGridSquare4() throws Exception{
        Integer[][] grid = {
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,2,null,null,null,null,null,null,null},
                {2,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }

    @Test
    public void shouldCheckInvalidGridSquare5() throws Exception{
        Integer[][] grid = {
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,1,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,1,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }

    @Test
    public void shouldCheckInvalidGridSquare6() throws Exception{
        Integer[][] grid = {
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,2,null,null},
                {null,null,null,null,null,null,null,2,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }
    @Test
    public void shouldCheckInvalidGridSquare7() throws Exception{
        Integer[][] grid = {
                {null,null,null,4,null,null,null,null,null},
                {null,null,null,null,4,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,1,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {1,null,null,null,null,null,null,null,null}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }

    @Test
    public void shouldCheckInvalidGridSquare8() throws Exception{
        Integer[][] grid = {
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,2,null,null,null,null,null},
                {null,null,null,null,2,null,null,null,null}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }
    @Test
    public void shouldCheckInvalidGridSquare9() throws Exception{
        Integer[][] grid = {
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,null},
                {null,null,null,null,null,null,null,null,3},
                {null,null,null,null,null,null,3,null,null}
        };

        boolean checked = solveBacktracking.check(grid);

        assertFalse(checked);
    }

    @Test
    public void shouldCheckValidGrid() throws Exception{
        Integer[][] grid = {
                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };

        boolean checked = solveBacktracking.check(grid);

        assertTrue(checked);
    }

    @Test
    public void shouldPrint() {
        Integer[][] grid = {
                {5,3,4,6,7,8,9,1,2},
                {6,7,2,1,9,5,3,4,8},
                {1,9,8,3,4,2,5,6,7},
                {8,5,9,7,6,1,4,2,3},
                {4,2,6,8,5,3,7,9,1},
                {7,1,3,9,2,4,8,5,6},
                {9,6,1,5,3,7,2,8,4},
                {2,8,7,4,1,9,6,3,5},
                {3,4,5,2,8,6,1,7,9}
        };
        solveBacktracking.setPrintFlag(true);
        solveBacktracking.printBoard(grid);
        solveBacktracking.setPrintFlag(false);
    }

    private Integer[][] createGridToSolve() {
        return new Integer[][]{
                {5, 3, null, null, 7, null, null, null, null},
                {6, null, null, 1, 9, 5, null, null, null},
                {null, 9, 8, null, null, null, null, 6, null},
                {8, null, null, null, 6, null, null, null, 3},
                {4, null, null, 8, null, 3, null, null, 1},
                {7, null, null, null, 2, null, null, null, 6},
                {null, 6, null, null, null, null, 2, 8, null},
                {null, null, null, 4, 1, 9, null, null, 5},
                {null, null, null, null, 8, null, null, 7, 9}
        };
    }

}
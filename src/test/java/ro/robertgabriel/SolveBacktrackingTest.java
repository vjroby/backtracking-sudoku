package ro.robertgabriel;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SolveBacktrackingTest {

    @Test
    public void shouldSolve() throws Exception{
        Integer[][] resolved = SolveBacktracking.solve(createGridToSolve());
        for (int i = 0; i < 9; i++) {
            assertArrayEquals("Sudoku should be solved",solved()[i],resolved[i]);
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

        Integer[][] checked = SolveBacktracking.solve(grid);
        boolean hasNulls = false;
        for (int i = 0; i <9; i++) {
            for (int j = 0; j < 9; j++) {
              if(checked[i][j] == null){
                  hasNulls = true;
                  break;
              }
            }
        }
        assertTrue(hasNulls);
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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

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

        boolean checked = SolveBacktracking.check(grid);

        assertTrue(checked);
    }

    private Integer[][] createGridToSolve() {
        Integer[][] board = new Integer[9][9];

        board[0][0] = 5;
        board[0][1] = 3;
        board[1][0] = 6;
        board[2][1] = 9;
        board[2][2] = 8;

        board[0][4] = 7;
        board[1][3] = 1;
        board[1][4] = 9;
        board[1][5] = 5;

        board[2][7] = 6;

        board[3][0] = 8;
        board[4][0] = 4;
        board[5][0] = 7;

        board[3][4] = 6;
        board[4][3] = 8;
        board[4][5] = 3;
        board[5][4] = 2;

        board[3][8] = 3;
        board[4][8] = 1;
        board[5][8] = 6;

        board[6][1] = 6;

        board[7][3] = 4;
        board[7][4] = 1;
        board[7][5] = 9;
        board[8][4] = 8;

        board[6][6] = 2;
        board[6][7] = 8;
        board[7][8] = 5;
        board[8][7] = 7;
        board[8][8] = 9;
        return board;
    }

}
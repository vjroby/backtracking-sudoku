package ro.robertgabriel;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertArrayEquals;

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
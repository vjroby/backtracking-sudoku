package ro.robertgabriel;

public class Application {
    public static void main(String[] args) {
        Integer[][] board = {
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

        printBoard(board);

        printBoard(SolveBacktracking.solve(board));
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
            System.out.println();
        }
    }
}

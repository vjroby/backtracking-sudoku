package ro.robertgabriel;

public class SolveBacktracking {
    private static final int n = 9;

    public static Integer[][] solve(Integer[][] grid) {
        int first = 0;

        return addElement(grid, first);
    }

    private static Integer[][] addElement(Integer[][] grid, int el) {
        for (int i= 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == null) {
                    grid[i][j] = el;
                    if (!check(grid)) {
                        grid[i][j] = null;
                        el = increase(el);
                        printBoard(grid);
                        return (addElement(grid, el));
                    } else {
                        el = increase(el);
                        printBoard(grid);
                    }
                }
            }
        }
        return grid;
    }

    private static int increase(int el) {
        if (el == 9) {
            el=1;
        } else {
            el++;
        }
        return el;
    }


    private static boolean check(Integer[][] grid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                for (int k = i+1; k < n; k++) {
//                    System.out.println(grid[i][j] + " | " + grid[k][j]);
                    if (grid[i][j] != null && k!=i && grid[k][j] != null && grid[i][j].equals(grid[k][j])) {
                        return false;
                    }

                }


                for (int m = j+1; m < n; m++) {
//                    System.out.println(grid[i][j] + " | " + grid[i][m]);
                    if (grid[i][j] != null  && m!=j  && grid[i][m] != null && grid[i][j].equals(grid[i][m])) {
                        return false;
                    }
                }

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
                if(j%3 ==2){
                    System.out.print("|");
                }
            }
            if(i%3 ==2){
                System.out.println();
                for (int n = 0; n < 10*3 + 1; n++) {
                    System.out.print("_");
                }
            }
            System.out.println("");
        }
        System.out.println("==============================================");

    }
}

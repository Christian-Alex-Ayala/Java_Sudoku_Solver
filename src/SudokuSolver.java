import java.util.Scanner;

public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args){

        Scanner userInput = new Scanner(System.in);
        int[][] sudokuPuzzle = new int[9][9];

        System.out.println("Enter your Sudoku puzzle: ");

        // Setting sudokuPuzzle array to the user's own sudoku
        for(int r = 0 ; r < GRID_SIZE ; r++){
            for(int c = 0 ; c < GRID_SIZE ; c++){
                sudokuPuzzle[r][c] = userInput.nextInt();
            }
        }

        userInput.close();

        if(solveSudoku(sudokuPuzzle)){
            System.out.println("Solved Sudoku:");
            printSudoku(sudokuPuzzle);
        }
        else{
            System.out.println("Entered puzzle is not solvable.");
        }



    }

    private static void printSudoku(int[][] sudoku){

        for(int r = 0 ; r < GRID_SIZE ; r++){
            if(r % 3 == 0 && r != 0){
                System.out.println("-----------");
            }
            for(int c = 0 ; c < GRID_SIZE ; c++){
                if(c % 3 ==0 && c != 0){
                    System.out.print("|");
                }
                System.out.print(sudoku[r][c]);
            }
            System.out.println();
        }

    }

    // Checks if number currently being input into slot is present in the current row
    private static boolean isNumInRow(int[][] sudoku, int num, int row){
        for(int i = 0 ; i < GRID_SIZE ; i++){
            if(sudoku[row][i] == num){
                return true;
            }
        }
        return false;
    }

    // Checks if number currently being input into slot is present in the current column
    private static boolean isNumInCol(int[][] sudoku, int num, int col){
        for(int i = 0 ; i < GRID_SIZE ; i++){
            if(sudoku[i][col] == num){
                return true;
            }
        }
        return false;
    }

    // Checks if number currently being input into slot is present in the current 3x3 subgrid
    private static boolean isNumInSubgrid(int[][] sudoku, int num, int row, int col){

        // Finds upper left slot in the 3x3 subgrid based on the current row & column
        int currentSubgridRow = row - row % 3;
        int currentSubgridColumn = col - col % 3;

        for(int r = currentSubgridRow ; r < currentSubgridRow + 3 ; r++){
            for(int c = currentSubgridColumn ; c < currentSubgridColumn + 3 ; c++){

                if(sudoku[r][c] == num){
                    return true;
                }

            }
        }
        return false;
    }

    // Tests if current number is present in row, column, or 3x3 subgrid
    private static boolean numCanBeUsed(int[][] sudoku, int num, int row, int col){

        return !isNumInRow(sudoku, num, row) && !isNumInCol(sudoku, num, col) && 
            !isNumInSubgrid(sudoku, num, row, col);
    
    }

    // Calls numCanBeUsed method to solve the entire sudoku puzzle
    private static boolean solveSudoku(int[][] sudoku){

        for(int r = 0 ; r < GRID_SIZE ; r++){
            for(int c = 0 ; c < GRID_SIZE ; c++){

                if(sudoku[r][c] == 0){

                    for(int numBeingTested = 1 ; numBeingTested <= GRID_SIZE ; numBeingTested++){

                        if(numCanBeUsed(sudoku, numBeingTested, r, c)){

                            sudoku[r][c] = numBeingTested;

                            // Recursively calls solveSudoku method, returning true if the number is valid
                            // or setting the slot back to 0 if the number is false
                            if(solveSudoku(sudoku)){
                                return true;
                            }
                            else{
                                sudoku[r][c] = 0;
                            }

                        }

                    }
                    return false;
                }

            }
        }
        return true;
    }
    
}

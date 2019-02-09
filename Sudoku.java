public class Sudoku {

  //9x9 matrix as a sample example to solve
  public static int[][] SAMPLE_PUZZLE = {
    {8, 0, 2, 0, 5, 0, 7, 0, 1},
    {0, 0, 7, 0, 8, 2, 4, 6, 0},
    {0, 1, 0, 9, 0, 0, 0, 0, 0},
    {6, 0, 0, 0, 0, 1, 8, 3, 2},
    {5, 0, 0, 0, 0, 0, 0, 0, 9},
    {1, 8, 4, 3, 0, 0, 0, 0, 6},
    {0, 0, 0, 0, 0, 4, 0, 2, 0},
    {0, 9, 5, 6, 1, 0, 3, 0, 0},
    {3, 0, 8, 0, 9, 0, 6, 0, 7}
  };

  private int[][] board;
  public static final int EMPTY = 0; //indicates a cell with no input yet
  public static final int SIZE = 9; //indicates the size of the grid.

  public Sudoku(int[][] board) {
    this.board = new int[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        this.board[i][j] = board[i][j];
      }
    }
  }

  //CHECK 1 of 3
  //check to see if number is in the row already
  private boolean inRow(int row, int num) {
    for (int j= 0; j < SIZE; j++)
      if (board[row][j] == num)
        return true;

    return false;
  }

  //CHECK 2 of 3
  //check to see if number is in the column already
  private boolean inCol(int col, int num) {
    for (int i = 0; i<SIZE; i++)
      if (board[i][col] == num)
        return true;

    return false;
  }

  //CHECK 3 of 3
  //check to see if number is in its respective 3x3 block already
  private boolean inBlock(int row, int col, int num) {
    int r = row - row % 3;
    int c = col - col % 3;

    for (int i = r; i < r + 3; i++)
      for (int j = c; j < c + 3; j++)
        if (board[i][j] == num)
          return true;

    return false;
  }

  //checks whether all 3 checks are true-- is the basis
  // for whether there is a possible solution
  private boolean possible(int row, int col, int num) {
    return !inRow(row, num) && !inCol(col, num) && !inBlock(row, col, num);
  }

  private boolean solve() {
    for (int row=0; row < SIZE; row++) {
      for (int col=0; col < SIZE; col++) {
        //guard to check whether empty
        if (board[row][col] == EMPTY) {
          //iterate through possible nums and check possibility
          for (int possibles = 1; possibles <= SIZE; possibles++) {
            if(possible(row, col, possibles)) {
              board[row][col] = possibles;

              if (solve()) return true;
              else board[row][col] = EMPTY;
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  public void display(){
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        System.out.print(" " + board[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Sudoku sudoku = new Sudoku(SAMPLE_PUZZLE);
    System.out.println("Sample Grid to Solve: \n");
    sudoku.display();

    //attempt to solve the puzzle
    if (sudoku.solve()) {
      System.out.println("Solved! \n");
      sudoku.display();
    } else {
      System.out.println("This puzzle is not solvable, stop trying to break me. -Computer");
    }
  }


}

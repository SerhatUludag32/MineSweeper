public class MineSweeper {
    private int row;
    private int col;
    private char[][] board;
    private char[][] mineBoard;
    public MineSweeper(int row, int col) {
        this.row = row;
        this.col = col;
        this.board = new char[row][col];
        this.mineBoard = new char[row][col];
        initializeBoard();
        placeMines();
    }
    private void initializeBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = '-';
            }
        }
    }
    private void placeMines() {
        int totalMines = row * col / 4;
        int[] minePositions = new int[totalMines];
        for (int i = 0; i < totalMines; i++) {
            int position;
            do {
                position = (int) (Math.random() * (row * col));
            } while (contains(minePositions, position));
            minePositions[i] = position;
            int r = position / col;
            int c = position % col;
            mineBoard[r][c] = '*';
        }
    }
    private boolean contains(int[] array, int value) {
        for (int num : array) {
            if (num == value) {
                return true;
            }
        }
        return false;
    }
    public void printBoard() {
        /*System.out.println("Mayınların Konumu");
        for (char[] row : mineBoard) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }*/
        System.out.println("===========================");
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println("===========================");
    }
    public boolean play(int selectedRow, int selectedCol) {
        if (mineBoard[selectedRow][selectedCol] == '*') {
            System.out.println("Game Over!!");
            return false;
        } else {
            updateBoard(selectedRow, selectedCol);
            return true;
        }
    }
    private void updateBoard(int selectedRow, int selectedCol) {
        if (board[selectedRow][selectedCol] == '-') {
            int minesAround = countMines(selectedRow, selectedCol);
            board[selectedRow][selectedCol] = (char) (minesAround + '0');

            if (minesAround == 0) {
                revealEmptyCells(selectedRow, selectedCol);
            }
        }
    }
    private int countMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < this.row && newCol >= 0 && newCol < this.col) {
                    if (mineBoard[newRow][newCol] == '*') {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    private void revealEmptyCells(int row, int col) {
        if (board[row][col] == '0') {
            updateBoard(row, col);
        }
    }
    public boolean checkWin() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == '-' && mineBoard[i][j] != '*') {
                    return false;
                }
            }
        }
        System.out.println("Tebrikler Oyunu Kazandınız!");
        printBoard();
        return true;
    }
}

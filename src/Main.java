import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Satır sayısını giriniz: ");
        int row = scanner.nextInt();

        System.out.println("Sütun sayısını giriniz: ");
        int col = scanner.nextInt();

        MineSweeper game = new MineSweeper(row, col);

        while (true) {
            game.printBoard();

            System.out.println("Satır Giriniz: ");
            int selectedRow = scanner.nextInt();

            System.out.println("Sütun Giriniz: ");
            int selectedCol = scanner.nextInt();

            if (selectedRow < 0 || selectedRow >= row || selectedCol < 0 || selectedCol >= col) {
                System.out.println("Geçersiz giriş. Lütfen tekrar deneyin.");
                continue;
            }

            if (!game.play(selectedRow, selectedCol)) {
                break;
            }

            if (game.checkWin()) {
                break;
            }
        }
        scanner.close();
    }
}

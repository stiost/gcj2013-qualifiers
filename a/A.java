import java.io.FileNotFoundException;
import java.util.Scanner;

public class A {

    static final int NOT_FINISHED = 0;
    static final int X_WON = 1;
    static final int O_WON = 2;
    static final int DRAW = 3;

    static final String result[] = {
            "Game has not completed",
            "X won",
            "O won",
            "Draw"
    };

    static Scanner scanner;

    public static void main(String[] args) throws FileNotFoundException {
        scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int caseNr = 1; caseNr <= T; caseNr++) {
            int winner = getWinner(readGame());
            System.out.println("Case #" + caseNr + ": " + result[winner]);
        }
        scanner.close();
    }

    static int getWinner(byte[][] board) {
        int total = 0;
        int[] colSums = new int[4];
        int[] diagSums = new int[2];
        for (int row = 0; row < 4; row++) {
            int rowSum = 0;
            for (int col = 0; col < 4; col++) {
                int v = board[row][col];
                total += v;
                rowSum += v;
                colSums[col] += v;
                if (col == row) diagSums[0] += v;
                if (col == 3 - row) diagSums[1] += v;
            }
            if (didXWin(rowSum)) return X_WON;
            if (didOwin(rowSum)) return O_WON;
        }

        for (int i = 0; i < 4; i++) {
            if (didXWin(colSums[i])) return X_WON;
            if (didOwin(colSums[i])) return O_WON;
        }
        for (int i = 0; i < 2; i++) {
            if (didXWin(diagSums[i])) return X_WON;
            if (didOwin(diagSums[i])) return O_WON;
        }

        return total >= 83 ? DRAW : NOT_FINISHED;
    }

    static boolean didXWin(int sum) {
        return sum == 4 || sum == 8;
    }

    static boolean didOwin(int sum) {
        return sum == 40 || sum == 35;
    }

    static byte[][] readGame() {
        byte[][] board = new byte[4][4];
        for (int row = 0; row < 4; row++) {
            String line = scanner.next();
            for (int col = 0; col < 4; col++) {
                char c = line.charAt(col);
                board[row][col] = getValue(c);
            }
        }
        return board;
    }

    static byte getValue(char c) {
        switch (c) {
            case 'X':
                return 1;
            case 'O':
                return 10;
            case 'T':
                return 5;
            default:
                return 0;
        }
    }

}

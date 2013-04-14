import java.util.Scanner;

public class B {

    static Scanner scanner;

    public static void main(String[] args) throws Exception {
        scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        for (int caseNr = 1; caseNr <= T; caseNr++) {
            System.out.println("Case #" + caseNr + ": " + (solve() ? "YES" : "NO"));
        }
        scanner.close();
    }

    static boolean solve() {
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        byte[][] grass = new byte[N][M];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                grass[row][col] = scanner.nextByte();
            }
        }

        boolean possible = true;
        for (int row = 0; row < N && possible; row++) {
            for (int col = 0; col < M && possible; col++) {
                int h = grass[row][col];

                boolean higher = false;
                for (int i = 0; i < M && !higher; i++) {
                    if (grass[row][i] > h) higher = true;
                }
                if (!higher) continue;

                higher = false;
                for (int i = 0; i < N && !higher; i++) {
                    if (grass[i][col] > h) higher = true;
                }
                if (higher) possible = false;
            }
        }

        return possible;
    }

}

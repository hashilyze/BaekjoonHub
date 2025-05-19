import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int[][] time = new int[4][2];

            for (int i = 0; i < 4; i++) {
                String[] input = br.readLine().split(" ");
                time[i][0] = Integer.parseInt(input[0]);
                time[i][1] = Integer.parseInt(input[1]);
            }

            Arrays.sort(time, (a, b) -> {
                if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            });

            int a = Math.abs(time[0][0] - time[1][0]) + Math.abs(time[0][1] - time[1][1]);
            int b = Math.abs(time[0][0] - time[2][0]) + Math.abs(time[0][1] - time[2][1]);
            int c = Math.abs(time[1][0] - time[3][0]) + Math.abs(time[1][1] - time[3][1]);
            int d = Math.abs(time[2][0] - time[3][0]) + Math.abs(time[2][1] - time[3][1]);

            double diagonal_a = Math.sqrt(Math.pow(time[1][0] - time[2][0], 2) + Math.pow(time[1][1] - time[2][1], 2));
            double diagonal_b = Math.sqrt(Math.pow(time[0][0] - time[3][0], 2) + Math.pow(time[0][1] - time[3][1], 2));

            if (a == b && b == c && c == d && Math.abs(diagonal_a - diagonal_b) < 1e-6) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		int[][] a = new int[3][2];
		for(int i = 0; i < 3; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 2; ++j) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int A = Math.min(Math.min(a[0][0], a[1][1]), a[2][0]);
		int B = Math.min(Math.min(a[0][1], a[1][0]), a[2][1]);
		bw.append(Math.min(A, B) * 2L + (A != B ? 1 : 0) + "").flush();;
	}
}	
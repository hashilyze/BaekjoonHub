import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] mat = new int[N + 2][M + 2];
		for(int y = 1; y <= N; ++y) {
			st = new StringTokenizer(br.readLine()); 
			for(int x = 1; x <= M; ++x) {
				mat[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		long sum = 0;
		for(int y = 1; y <= N; ++y) { 
			for(int x = 1; x <= M; ++x) {
				if(mat[y][x] > 0) {
					int[] a = new int[] {
						mat[y][x] - 1,
						mat[y + 1][x],
						mat[y][x + 1],
						mat[y - 1][x],
						mat[y][x - 1]
					};
					sum += Arrays.stream(a).min().getAsInt();
				}
			}
		}
		bw.append(""+sum).flush();
	}
}	
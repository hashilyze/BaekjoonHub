import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	// variables
	static int N;
	static String stmt;
	static int[][] dpMax, dpMin;
	
	static int solution() {
		dpMax = new int[N][N];
		dpMin = new int[N][N];
		for(int i = 0; i < N; ++i) Arrays.fill(dpMax[i], Integer.MIN_VALUE);
		for(int i = 0; i < N; ++i) Arrays.fill(dpMin[i], Integer.MAX_VALUE);
		
		for(int i = 0; i < N; i += 2) {
			dpMax[i][i] = dpMin[i][i] = stmt.charAt(i) - '0';
		}
		
		for(int size = 3; size <= N; size += 2) {
			for(int from = 0; from < N - size + 1; ++from) {
				int to = from + size - 1;
				for(int i = from + 1; i < to; i += 2) {
					char op = stmt.charAt(i);
					if(op == '+') {
						// 최댓값 = 최댓값 + 최솟값
						dpMax[from][to] = Math.max(dpMax[from][to], dpMax[from][i - 1] + dpMax[i + 1][to]); 
						// 최솟값 = 최솟값 + 최솟값
						dpMin[from][to] = Math.min(dpMin[from][to], dpMin[from][i - 1] + dpMin[i + 1][to]);
					} else if(op == '-') { 
						// 최댓값 = 최댓값 - 최솟값
						dpMax[from][to] = Math.max(dpMax[from][to], dpMax[from][i - 1] - dpMin[i + 1][to]);
						// 최솟값 = 최솟값 - 최댓값						
						dpMin[from][to] = Math.min(dpMin[from][to], dpMin[from][i - 1] - dpMax[i + 1][to]);
					} else { // op == '*' 
						// 최댓값 = 최댓값 * 최댓값 or 최솟값 * 최솟값 
						dpMax[from][to] = Math.max(dpMax[from][to], dpMax[from][i - 1] * dpMax[i + 1][to]);
						dpMax[from][to] = Math.max(dpMax[from][to], dpMin[from][i - 1] * dpMin[i + 1][to]);
						// 최솟값 = 최댓값 * 최솟값 or 최솟값 * 최댓값 or 최솟값 * 최솟값
						dpMin[from][to] = Math.min(dpMin[from][to], dpMax[from][i - 1] * dpMin[i + 1][to]);
						dpMin[from][to] = Math.min(dpMin[from][to], dpMin[from][i - 1] * dpMax[i + 1][to]);
						dpMin[from][to] = Math.min(dpMin[from][to], dpMin[from][i - 1] * dpMin[i + 1][to]);
					}
				}
				
			}
		}
		return dpMax[0][N - 1];
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		stmt = br.readLine();
		bw.append(""+solution()).flush();;
	}
}
	
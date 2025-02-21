import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M;
	static int[] A = new int[100];
	
	static int max;
	
	static void nextSubset(int idx, int left, int sum) {
		if(sum > M) return;
		
		if(left == 0) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i = idx; i < N - left + 1; ++i) {
			nextSubset(i + 1, left - 1, sum + A[i]);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) A[i] = Integer.parseInt(st.nextToken());
		
		// solution
		nextSubset(0, 3, 0);
		
		System.out.print(max);
	}
}

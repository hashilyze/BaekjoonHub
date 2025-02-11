import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 200_000;
	// Variables	
	static int N;
	static long K;
	static int[] movables = new int[MAX_N];
	
	
	static long solution() {
		Arrays.sort(movables, 0, N);
		
		long min = Long.MAX_VALUE;
		for(int cur = N - 1; cur > 0; --cur) {
			long unit = (long)movables[0] * cur + (long)movables[cur] * (N - cur);
			min = Math.min(min, (K / unit) + (K % unit == 0 ? 0 : 1));
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		// Inputs
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			movables[i] = Integer.parseInt(st.nextToken());
		}
		// Outputs
		bw.append(""+solution()).flush();
	}
}
	
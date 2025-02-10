import java.io.*;
import java.util.*;

public class Solution {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// Variables
	static int T;
	static int N, K;
	

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			String S = br.readLine();
			
			int ans = solution(N, K, S);
			bw.append("#" + t + " " + ans + "\n");
		}
		bw.flush();
	}
	
	static int solution(int N, int K, String S) {
		Set<Integer> set = new HashSet<>();
		
		S += S;
		final int quater = N >> 2;
		for(int i = 0; i < N - 1; ++i) {
			for(int j = 0; j < N; j += quater) {
				set.add(Integer.parseInt(S.substring(i + j, i + j + quater), 16));
			}
		}
		
		Integer[] arr = set.toArray(new Integer[set.size()]);
		Arrays.sort(arr);
		return arr[arr.length - K];
	}
}

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
		TreeSet<Integer> set = new TreeSet<>((lhs, rhs) -> rhs - lhs);
		
		S += S;
		final int quater = N >> 2;
		for(int i = 0; i < N - 1; ++i) {
			for(int j = 0; j < N; j += quater) {
				set.add(Integer.parseInt(S.substring(i + j, i + j + quater), 16));
			}
		}
		
		Iterator<Integer> iterator = set.iterator();
		for(int i = 0; i < K - 1; ++i) {
			iterator.next();
		}
		return iterator.next();
	}
}
import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_M = 400;
	
	static int N, M;
	static int[] unvalidPairs = new int[MAX_M];
	
	static boolean isValid(int bitMask) {
		for(int i = 0; i < M; ++i) {
			int mask = unvalidPairs[i];
			if((bitMask & mask) == mask) return false;
		}
		return true;
	}
	
	static int solution() {
		int cnt = 0;
		for(int bitMask = 0, limit = 0x01 << N; bitMask < limit; ++bitMask) {
			if(isValid(bitMask)) ++cnt;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < M; ++i) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				unvalidPairs[i] = (0x01 << (a - 1)) | (0x01 << (b - 1));
			}
			
			sb.append("#").append(t).append(" ").append(solution()).append("\n");
		}
		System.out.print(sb);
	}
}
import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, B;
	static int[][] mat = new int[16][16];
	
	
	static int diff(int bitMask) {
		int a = 0, b = 0;
		for(int i = 0; i < N; ++i) {
			for(int j = i + 1; j < N; ++j) {
				if(((bitMask >> i) & 1) == ((bitMask >> j) & 1)) {
					if(((bitMask >> i) & 1) == 1) a += mat[i][j] + mat[j][i];
					else b += mat[i][j] + mat[j][i];
				}
			}
		}
		return Math.abs(a - b);
	}
	
	static boolean isValid(int bitMask) {
		int cnt = 0;
		while(bitMask > 0) {
			bitMask -= (bitMask & -bitMask);
			++cnt;
		}
		return (cnt << 1) == N;
	}
	
	static int solution() {
		int min = Integer.MAX_VALUE;
		for(int bitMask = 0, limit = 0x01 << N; bitMask < limit; ++bitMask) {
			if(isValid(bitMask)) {
				min = Math.min(min, diff(bitMask));
			}
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; ++j) {
					mat[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("#").append(t).append(" ").append(solution()).append("\n");			
		}
		System.out.print(sb);
	}
}

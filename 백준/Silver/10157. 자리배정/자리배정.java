import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static int[][] delta = {
			{0, 1}, {1, 0}, {0, -1}, {-1, 0}
	};
	// Variables
	static int C, R, K;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		int[] ans = solution();
		if(ans == null) bw.append("0");
		else bw.append((ans[0] + 1) + " " + (ans[1] + 1));
		bw.flush();
	}
	
	static int[] solution() {
		int next = 0;
		int d = 0;
		int c = -delta[d][0], r = -delta[d][1];
		--C;
		
		while(R > 0 || C > 0) {
			for(int i = 0; i < R; ++i) {
				c += delta[d][0];
				r += delta[d][1];
				++next;
				if(next == K) return new int[] {c, r};
			}
			d = (d + 1) % delta.length;
			--R;
			
			for(int i = 0; i < C; ++i) {
				c += delta[d][0];
				r += delta[d][1];
				++next;
				if(next == K) return new int[] {c, r};
			}
			d = (d + 1) % delta.length;
			--C;
		}
		return null;
	}
}

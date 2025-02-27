import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	
	static int a, b, c;
	
	static int solution() {
		int cnt = 0;
		for(int x = 1; x <= a; ++x) {			
			for(int y = 1; y <= b; ++y) {
				for(int z = 1; z <= c; ++z) {
					if(x % y == y % z && y % z == z % x) ++cnt;
				}
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; ++i) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			sb.append(solution()).append("\n");
		}
		System.out.print(sb);
		
	}
}
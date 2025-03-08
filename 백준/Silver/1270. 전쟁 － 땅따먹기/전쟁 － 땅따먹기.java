import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	
		
	static long solution() throws IOException {
		st = new StringTokenizer(br.readLine());
		
		Map<Long, Integer> map = new HashMap<>();
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < M; ++i) {
			long v = Long.parseLong(st.nextToken());
			int cnt = map.getOrDefault(v, 0) + 1;
			if(cnt > (M >> 1)) return v;
			map.put(v, cnt);
		}
		return Long.MIN_VALUE;
	}
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		while(N-- > 0) {
			long ans = solution();
			if(ans == Long.MIN_VALUE) sb.append("SYJKGW\n");
			else sb.append(ans).append("\n");
		}
		System.out.print(sb);
	}
}
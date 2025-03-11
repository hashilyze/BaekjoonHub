import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	// variables
	static int N, K;
	static Map<Long, Long> map = new HashMap<>(); // Prefix sum을 저장할 Map
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		K = readInt();
		
		long cnt = 0, prev = 0;
		map.put(0L, 1L);
		for(int i = 0; i < N; ++i) {
			long sum = prev + readInt(); // 0번쨰 원소부터 i번째 원소까지의 누적합
			// rangeSum(a, b) = sum[b] - sum[a - 1] 을 만족하는
			// sum[a-1]이 왼쪽에 등장하는 횟수만큼 구간 합이 K인 구역이 존재한다.
			cnt += map.getOrDefault(sum - K, 0L);
			// 구간합을 기록
			map.put(sum, map.getOrDefault(sum, 0L) + 1);
			prev = sum;
		}
		System.out.print(cnt);
	}
	
	static int readInt() throws IOException {
		int c, n = 0, s = 1;
		while((c = System.in.read()) <= 0x20);
		if(c == '-') {
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n * s;
	}
}

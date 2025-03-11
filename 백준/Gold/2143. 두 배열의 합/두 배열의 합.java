import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int SIZE = 1_000;
	// variables
	static int T, N, M;
	static int[] A = new int[SIZE];
	static int[] B = new int[SIZE];
	static long[] rangeSumA = new long[SIZE * (SIZE - 1) / 2 + SIZE];
	static long[] rangeSumB = new long[SIZE * (SIZE - 1) / 2 + SIZE];
	static Map<Long, Integer> map = new HashMap<>();
	
	
	static long solution() {
		int cur;
		int cntA = N * (N - 1) / 2 + N;
		int cntB = M * (M - 1) / 2 + M;
		
		// 배열 A로 만들 수 있는 범위 합을 모두 생성: C(N, 2) + C(N, 1)개
		cur = 0;
		for(int i = 0; i < N; ++i) {
			rangeSumA[cur] = A[i];
			++cur;
			
			for(int j = i+1; j < N; ++j) {
				rangeSumA[cur] = rangeSumA[cur - 1] + A[j];
				++cur;
			}
		}
		Arrays.sort(rangeSumA, 0, cur);
		cntA = cur;
		
		// 배열 B로 만들 수 있는 범위 합을 모두 생성: C(M, 2) + C(M, 1)개
		cur = 0;
		for(int i = 0; i < M; ++i) {
			rangeSumB[cur] = B[i];
			map.put(rangeSumB[cur], map.getOrDefault(rangeSumB[cur], 0) + 1);
			++cur;
			
			for(int j = i+1; j < M; ++j) {
				rangeSumB[cur] = rangeSumB[cur - 1] + B[j];
				map.put(rangeSumB[cur], map.getOrDefault(rangeSumB[cur], 0) + 1);
				++cur;
			}
		}
		Arrays.sort(rangeSumB, 0, cur);
		cntB = cur;
		
		// 배열A에서 (T - B의 구간합)의 개수만큼 계수
		long cnt = 0;
		for(int i = 0; i < cntA; ++i) {
			cnt += map.getOrDefault(T - rangeSumA[i], 0);
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		T = readInt();
		N = readInt();
		for(int i = 0; i < N; ++i) A[i] = readInt();
		M = readInt();
		for(int i = 0; i < M; ++i) B[i] = readInt();
		System.out.println(solution());
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

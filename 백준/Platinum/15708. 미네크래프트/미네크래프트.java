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
	static int N, T, P;
	static int[] K = new int[(int)10e5];
	
	
	static int solution() {
		// 채굴하는 돌의 개수를 최대화하려면, 지나온 길에 위치한 가장 작은 돌부터 채굴하면 된다.
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		long sum = 0;
		int max = 0;
		for(int i = 0; i < N && i * P <= T; ++i) { // 오른쪽 방향으로만 진행하여야 시간을 중복 낭비하지 않음
			sum += K[i];
			pq.offer(K[i]);
			// i번째 징검다리까지 이동할때 채굴에 사용할 수 있는 최대 시간은 (T - P*i)
			while(!pq.isEmpty() && sum > T - i * P) {
				sum -= pq.poll();
			}
			max = Math.max(max, pq.size());
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		T = readInt();
		P = readInt();
		for(int i = 0; i < N; ++i) K[i] = readInt();
		System.out.print(solution());
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
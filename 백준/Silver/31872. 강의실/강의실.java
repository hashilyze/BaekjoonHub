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
	static int N, K;
	static int[] rooms = new int[100_001];
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		K = readInt();
		for(int i = 1; i <= N; ++i) {
			rooms[i] = readInt();
		}
		Arrays.sort(rooms, 0, N + 1);
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i = 0; i < N; ++i) {
			pq.offer(rooms[i + 1] - rooms[i]);
		}
		int sum = 0;
		for(int i = K; i < N; ++i) {
			sum += pq.poll();
		}
		System.out.println(sum);
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
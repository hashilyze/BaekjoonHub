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
	static int N;
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	
	public static void main(String[] args) throws IOException {
		// Input
		N = readInt();
		int bound = (N + 1) >> 1;
		
		for(int i = 0; i < N; ++i) {
			int val = readInt();
			pq.offer(val);
			if(bound < pq.size()) pq.poll();
		}
		// Solution
		int cnt = -pq.size() + 1;
		while(!pq.isEmpty()) {
			int val = pq.poll();
			while(val > 0) {
				++cnt;
				val >>= 1;
			}
		}
		// Output
		System.out.println(cnt);
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
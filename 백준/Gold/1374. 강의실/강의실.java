import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Node implements Comparable<Node> {
		int type, time;
		
		Node(int type, int time) {this.type = type; this.time = time;}

		@Override
		public int compareTo(Node other) {
			return this.time != other.time ? this.time - other.time : this.type - other.type;
		}
	};
	// constants
	// variables
	static int N;
	static Node[] nodes = new Node[100_000 << 1];
	
	
	static int solution() {
		Arrays.sort(nodes, 0, N << 1);
		int max = 0, cnt = 0;
		for(int i = 0, n = N << 1; i < n; ++i) {
			if(nodes[i].type > 0) ++cnt;
			else max = Math.max(max, cnt--);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			readInt();
			nodes[i] = new Node(1, readInt());
			nodes[i + N] = new Node(-1, readInt());
		}
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
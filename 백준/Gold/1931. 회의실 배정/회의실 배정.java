import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static class Node implements Comparable<Node> {
		int begin, end;
		
		Node() { }
		Node(int beg, int end) { this.begin = beg; this.end = end; }
		
		public int compareTo(Node o) { 
				if(this.end != o.end) return this.end - o.end;
		return this.begin - o.begin;
		}
	};
	
	static int N;
	static Node[] edges = new Node[100_000];
	
	
	static int solution() {
		Arrays.sort(edges, 0, N);
		
		int cnt = 1;
		int end = edges[0].end;
		for(int i = 1; i < N; ++i) {
			if(end <= edges[i].begin) {
				end = edges[i].end;
				++cnt;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			edges[i] = new Node(readInt(), readInt());
		}
		System.out.println(solution());
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}
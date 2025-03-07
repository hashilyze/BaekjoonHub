import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node implements Comparable<Node>{
		int type, time;
		Node(int type, int time){this.type = type; this.time = time;}
		
		@Override
		public int compareTo(Node other) {
			return (this.time != other.time) 
				? this.time - other.time 
				: this.type - other.type;
		}
	}
	// constants
	static int MAX_N = 100_000;
	// variables
	static int N;
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			pq.add(new Node(1, readInt()));
			pq.add(new Node(-1, readInt()));
		}
		
		int max = 0, cnt = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			cnt += node.type;
			max = Math.max(max, cnt);
		}
		System.out.print(max);
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
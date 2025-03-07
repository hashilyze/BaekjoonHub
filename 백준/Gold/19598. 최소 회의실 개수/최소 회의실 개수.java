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
	static Node[] meetings = new Node[MAX_N << 1];
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			meetings[i] = new Node(1, readInt());
			meetings[i + N] = new Node(-1, readInt());
		}
		Arrays.sort(meetings, 0, N << 1);
		
		int max = 0, cnt = 0;
		for(int i = 0; i < N << 1; ++i) { 
			if(meetings[i].type > 0) ++cnt;
			else max = Math.max(max, cnt--);
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
import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Node implements Comparable<Node>{
		int dir;
		int pos;
		
		Node(){ }
		Node(int dir, int pos){ this.dir = dir; this.pos = pos; }
		
		@Override
		public int compareTo(Node other) {
			if(this.pos != other.pos) return this.pos - other.pos;
			return this.dir - other.dir;
		}
	}
	
	static int N;
	static List<Node> li = new ArrayList<Node>();
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			li.add(new Node(1, from));
			li.add(new Node(-1, to));
		}
		Collections.sort(li);
		int max = 0, cnt = 0;
		for(int i = 0; i < (N << 1); ++i) {
			cnt += li.get(i).dir;
			max = Math.max(max, cnt);
		}
		System.out.print(max);
	}
}
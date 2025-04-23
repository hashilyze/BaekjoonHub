import java.io.*;
import java.util.*;


public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Member implements Comparable<Member>{
		int id, score;
		Member(int id, int score) { this.id=id; this.score=score; }
		
		@Override
		public int compareTo(Member o) { return o.score - this.score; }
	}
	// constants
	// variables
	
	
	public static void main(String[] args) throws IOException {
		int N = readInt();
		PriorityQueue<Member> pq = new PriorityQueue<>();
		for(int id = 0; id < 9; ++id) {
			for(int i = 0; i < N; ++i) pq.offer(new Member(id, readInt()));
		}
		System.out.print(new String[] {"PROBRAIN", "GROW", "ARGOS", "ADMIN", "ANT", "MOTION", "SPG", "COMON", "ALMIGHTY"}[pq.poll().id]);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}

import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Line implements Comparable<Line> {
		int s, e;
		public Line(int s, int e) { this.s = s; this.e = e; }
		@Override
		public int compareTo(Line o) { return this.s - o.s; }
	}
	// constants
	// variables
	static int N; 
	
	public static void main(String[] args) throws IOException {
		PriorityQueue<Line> pq = new PriorityQueue<>();
		
		N = readInt();
		for(int i = 0; i < N; ++i) pq.offer(new Line(readInt(), readInt()));
		
		int sum = 0;
		int s = Integer.MIN_VALUE, e = s;
		while(!pq.isEmpty()) {
			Line line = pq.poll();
			
			if(e < line.s) {
				sum += e - s;
				s = line.s;
				e = line.e;
			} else {
				e = Math.max(e, line.e);
			}
		}
		sum += e - s;
		System.out.println(sum);
	}
	
	static int readInt() throws IOException{
		int c, n = System.in.read() & 0x0F, s = 1;
		if(n == ('-' & 0x0F)) {
			s = -1;
			n = 0;
		}
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n * s;
	}
}
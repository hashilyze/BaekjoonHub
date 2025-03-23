import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Node implements Comparable<Node> {
		String name;
		int day;
		int month;
		int year;
		
		@Override
		public int compareTo(Node o) {
			if(this.year != o.year) return this.year - o.year;
			else if(this.month != o.month) return this.month - o.month;
			else if(this.day != o.day) return this.day - o.day;
			return 0;
		}
	}
	// constants
	// variables
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		Node[] students = new Node[N];
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			Node node = new Node();
			node.name = st.nextToken();
			node.day = Integer.parseInt(st.nextToken());
			node.month = Integer.parseInt(st.nextToken());
			node.year = Integer.parseInt(st.nextToken());
			students[i] = node;
		}
		Arrays.sort(students);
		System.out.println(students[N - 1].name);
		System.out.println(students[0].name);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
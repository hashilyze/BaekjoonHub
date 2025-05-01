import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static final int MAX_N = 20_000;
	// types
	static class Node implements Comparable<Node> {
		int origin;
		String word;
		
		Node(int origin, String word){ 
			this.origin=origin; 
			this.word=word; 
		}
		
		@Override
		public int compareTo(Node other) { 
			return this.word.compareTo(other.word);
		}
	}
	static class Prefix implements Comparable<Prefix> {
		String prefix;
		int s;
		int t;
		
		public Prefix(String prefix, int s, int t) {
			this.prefix = prefix;
			this.s = s;
			this.t = t;
		}

		@Override
		public int compareTo(Prefix o) {
			if(this.prefix.length() != o.prefix.length()) 
				return o.prefix.length() - this.prefix.length();
			if(this.s != o.s) 
				return this.s - o.s;
			return this.t - o.t;
		}
	}
	// variables
	static int N;
	static String[] origin = new String[MAX_N];
	static Node[] nodes = new Node[MAX_N]; 
	static Map<String, TreeSet<Integer>> map = new HashMap<>();
	
	
	static int getLcp(String s, String t) {
		int lcp = 0;
		while(lcp < s.length() && lcp < t.length() && s.charAt(lcp) == t.charAt(lcp)) ++lcp;
		return lcp;
	}
	
	static Prefix solution() {
		Arrays.sort(nodes, 0, N);
		
		for(int i = 1; i < N; ++i) {
			Node S = nodes[i-1];
			Node T = nodes[i];
			
			int lcp = getLcp(S.word, T.word);
			String prefix = S.word.substring(0, lcp);
			
			TreeSet<Integer> set = map.get(prefix);
			if(set == null) {
				set = new TreeSet<>();
				map.put(prefix, set);
			}
			
			set.add(S.origin);
			set.add(T.origin);
		}
		
		Prefix max = null; 
		for(Map.Entry<String, TreeSet<Integer>> entry : map.entrySet()) {
			String prefix = entry.getKey();
			TreeSet<Integer> set = entry.getValue();
			int s = set.pollFirst();
			int t = set.pollFirst();
			
			Prefix next = new Prefix(prefix, s, t);
			if(max == null || next.compareTo(max) < 0) {
				max = next;
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			origin[i] = br.readLine();
			nodes[i] = new Node(i, origin[i]);
		}
		
		Prefix max = solution();
		System.out.print(sb.append(origin[max.s]).append("\n").append(origin[max.t]));
	}
}

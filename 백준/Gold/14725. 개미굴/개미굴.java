import java.awt.Insets;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node{
		String word;
		Map<String, Node> nexts = new TreeMap<>();
		
		Node(String word) {this.word=word;}
	}
	// constants
	
	// variables
	static int N;
	static int autoKey = 0;
	static Node root = new Node(null);
	
	
	static void insert(List<String> path) {
		Node cur = root;
		for(String word : path) {
			if(!cur.nexts.containsKey(word)) {
				cur.nexts.put(word, new Node(word));
			}
			cur = cur.nexts.get(word);
		}
	}
	static void display(Node node, int indent) {
		for(Map.Entry<String, Node> next : node.nexts.entrySet()) {
			for(int i = 0 ; i < indent; ++i) sb.append("--");
			sb.append(next.getKey()).append("\n");
			display(next.getValue(), indent+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			
			List<String> path = new ArrayList<String>();
			while(K-- > 0) path.add(st.nextToken());
			
			insert(path);
		}
		display(root, 0);
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
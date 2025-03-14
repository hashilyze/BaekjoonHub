import java.io.*;
import java.util.*;

import javax.print.attribute.HashAttributeSet;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node{
		boolean isWord = false;
		int[] next = new int[SIZE];
		
		Node() {}
	}
	// constants
	static int SIZE = 10;
	// variables
	static int N;
	static List<Node> pool = new ArrayList<Node>(1_000_000);
	static Node root = null;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			boolean flag = true;
			N = Integer.parseInt(br.readLine());
			pool.clear();
			pool.add(root = new Node());
			
			for(int i = 0; i < N; ++i) {
				int cursor = 0;
				Node node = root; 
				char[] numbers = br.readLine().toCharArray();
				
				for(int j = 0; j < numbers.length; ++j) {
					int digit = numbers[j] & 0x0F;
					if(node.next[digit] == 0) { // 노드 추가
						node.next[digit] = pool.size();
						pool.add(new Node());
					} else { // 이미 존재
						if(j + 1 == numbers.length) flag = false; // 추가하는 번호가 다른 번호의 접두사
					}
					cursor = node.next[digit];
					node = pool.get(cursor);
					if(node.isWord) flag = false;
				}
				node.isWord = true;
			}
			sb.append(flag ? "YES\n" : "NO\n");
		}
		System.out.print(sb);
	}
	

}
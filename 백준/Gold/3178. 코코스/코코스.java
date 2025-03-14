import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node{
		int[] next = new int[RANGE]; 
	}
	// constants
	static final int RANGE = 26;
	static final int LENGTH = 100; // MAX_K
	static final int NUM = 10_0000; // MAX_N
	// variables
	static int N, K;
	static int leftSize = 0, rightSize = 0;
	static Node[] leftPool = new Node[LENGTH * NUM + 10];
	static Node[] rightPool = new Node[LENGTH * NUM + 10];
	static int cnt = 0;
	
	
	static void init() {
		leftPool[leftSize++] = new Node();
		rightPool[rightSize++] = new Node();
	}
	static void addToLeftTrie(String word) {
		Node node = leftPool[0];
		for(int i = 0; i < K; ++i) {
			char ch = word.charAt(i);
			int id = ch - 'A';
			if(node.next[id] == 0) {
				node.next[id] = leftSize;
				leftPool[leftSize++] = new Node();
				
				++cnt;
			}
			node = leftPool[node.next[id]];
		}
	}
	static void addToRightTrie(String word) {
		Node node = rightPool[0];
		for(int i = 0; i < K; ++i) {
			char ch = word.charAt(2 * K - 1 -i);
			int id = ch - 'A';
			if(node.next[id] == 0) {
				node.next[id] = rightSize;
				rightPool[rightSize++] = new Node();
				
				++cnt;
			}
			node = rightPool[node.next[id]];
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		init();
		for(int i = 0; i < N; ++i) {
			String word = br.readLine();
			addToLeftTrie(word);
			addToRightTrie(word);
		}
		System.out.println(cnt);
	}
}
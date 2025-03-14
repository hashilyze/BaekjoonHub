import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Node{
		boolean isWord;
		int[] next = new int[RANGE]; 
	}
	// constants
	static final int RANGE = 26; // 분기 수
	static final int LENGTH = 1_000; // 단어의 최대 길이
	static final int SIZE = 4_000; // 단어의 최대 개수
	// variables
	static int C, N, Q;
	static int size = 0;
	static Node[] pool = new Node[SIZE * LENGTH + 10]; // 색상 저장
	static Set<String> set = new HashSet<>(); // 닉네임 저장
	
	static void initTrie() {
		pool[size++] = new Node();
	}
	static void addToTrie(String word) {
		Node node = pool[0];
		for(char ch : word.toCharArray()) {
			int childId = ch - 'a';
			if(node.next[childId] == 0) {
				node.next[childId] = size;
				pool[size++] = new Node();
			}
			node = pool[node.next[childId]];
		}
		node.isWord = true;
	}
	static void addToHash(String word) { set.add(word); }
	
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		initTrie();
		for(int i = 0; i < C; ++i) addToTrie(br.readLine());
		for(int i = 0; i < N; ++i) addToHash(br.readLine());
		
		Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; ++i) {
			String team = br.readLine();
			
			boolean flag = false;
			Node node = pool[0];
			for(int j = 0; j < team.length(); ++j) {
				int ch = team.charAt(j) - 'a';
				if(node.next[ch] == 0) break; // 등록된 색상명으로 시작하는 팀명이 아님
				node = pool[node.next[ch]];
				if(node.isWord) { // 접두사에서 색상명 발견
					if(set.contains(team.substring(j + 1))) { // 접미사가 닉네임인지 확인
						flag = true;
					}
				}
			}
			sb.append(flag ? "Yes\n" : "No\n");
		}
		System.out.print(sb);
	}
}
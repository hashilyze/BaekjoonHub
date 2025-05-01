import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// constants
	static final int SIZE = 20_000;
	static final int RANGE = 26;
	static final int LENGTH = 100;
	static final int ROOT = 0;
	// types
	static class Node{
		Node[] nexts = new Node[RANGE];
		int conquared;
		
		Node(int no) {this.conquared=no;}
	}
	// variables
	static int N;
	static String[] words = new String[SIZE];
	
	static int size = 0;
	static int[][] nexts = new int[1 + SIZE * LENGTH][];
	static int[] conquareds = new int[1 + SIZE * LENGTH];
	
	static int maxLength = 0, maxS = 0, maxT = 1;
	
	static void insert(int no, String word) {
		char[] cWord = word.toCharArray();
		
		int lcp = 0;
		int cur = ROOT;
		for(int i = 0; i < cWord.length; ++i) {
			int chi = cWord[i] - 'a';
			
			if(nexts[cur][chi] == 0) {
				nexts[cur][chi] = ++size;
				nexts[size] = new int[RANGE];
				conquareds[nexts[cur][chi]] = no;
			} else {
				int s = conquareds[nexts[cur][chi]];
				int t = no;
				
				++lcp;
				if(lcp > maxLength
					|| lcp == maxLength && s < maxS
					|| lcp == maxLength && s == maxS && t < maxT) {
					
					maxLength = lcp;
					maxS = s;
					maxT = t;
				}
			}
			cur = nexts[cur][chi];
		}
		maxLength = Math.max(maxLength, lcp);
	}
	
	public static void main(String[] args) throws IOException {
		nexts[ROOT] = new int[RANGE];
		
		N = readInt();
		for(int i = 0; i < N; ++i) {
			String word = br.readLine();
			words[i] = word;
			insert(i, word);
		}
		System.out.print(sb.append(words[maxS]).append("\n").append(words[maxT]));
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}
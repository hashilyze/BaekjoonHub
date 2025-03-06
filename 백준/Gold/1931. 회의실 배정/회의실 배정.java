import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static int N;
	static int[][] edges = new int[100_000][2];
	
	
	static int solution() {
		Arrays.sort(edges, 0, N, (lhs, rhs)-> {
			if(lhs[1] != rhs[1]) return lhs[1] - rhs[1];
			return lhs[0] - rhs[0];
		});
		
		int cnt = 1;
		int end = edges[0][1];
		for(int i = 1; i < N; ++i) {
			if(end <= edges[i][0]) {
				end = edges[i][1];
				++cnt;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			edges[i][0] = readInt();
			edges[i][1] = readInt();
		}
		System.out.println(solution());
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}
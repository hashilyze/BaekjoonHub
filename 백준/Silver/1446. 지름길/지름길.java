import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder(); 
	// types
	static class Bypass{
		int dest, dist;

		public Bypass(int dest, int dist) { this.dest = dest; this.dist = dist; }
	}
	// constants 
	static int MAX_D = 10_000;
	// variables
	static int N, D;
	static List<Bypass>[] bypasses = new List[MAX_D];
	static int[] dp = new int[MAX_D + 1];
	
	
	public static void main(String[] args) throws IOException {
		N = readInt(); D = readInt();
		for(int i = 0; i < D; ++i) bypasses[i] = new ArrayList<>();
		
		for(int i = 0; i < N; ++i) {
			int s = readInt(), e = readInt(), w = readInt();
			if(e > D) continue;
			bypasses[s].add(new Bypass(e, w));
		}
		
		for(int i = D - 1; i >= 0; --i) {
			dp[i] = dp[i + 1] + 1;
			for(Bypass bypass : bypasses[i]) {
				dp[i] = Math.min(dp[i], dp[bypass.dest] + bypass.dist);
			}
		}
		System.out.print(dp[0]);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}
import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	
	static long nextPrime(long N) {
		if(N <= 2) return 2;
		while(true) {
			boolean isPrime = true;
			for(long p = 2; p * p <= N; ++p) {
				if(N % p == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) return N;
			++N;
		}
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			long N = Long.parseLong(br.readLine());
			sb.append(nextPrime(N)).append("\n");
		}
		System.out.println(sb);
	}
}
import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final int MAX_N = 123_456;
	static final int SIZE = MAX_N << 1 + 100;
	
	static int[] lps = new int[SIZE + 1];
	static List<Integer> primes = new ArrayList<Integer>();
	
	static int lower_bound(int key) {
		int beg = 0, end = primes.size();
		while(beg < end) {
			int mid = (beg + end) >> 1;
			if(primes.get(mid) < key) beg = mid + 1;
			else end = mid;
		}
		return beg;
	}
	
	static int solution(int N) {
		int cnt = 0;
		for(int p : primes) {
			if(N < p && p <= 2 * N) ++cnt;
		}
		return cnt;
	}
	
	static void initEuler() {
		for(int i = 2; i <= SIZE; ++i) {
			if(lps[i] == 0) {
				lps[i] = i;
				primes.add(i);
			}
			for(int p : primes) {
				if((long)i * p > SIZE) break;
				lps[i * p] = i;
				if(lps[i] == lps[p]) break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		initEuler();
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			sb.append(solution(n)).append("\n");
		}
		System.out.println(sb);
	}
}
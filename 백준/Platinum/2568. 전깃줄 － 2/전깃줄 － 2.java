import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder(); 
	// types
	// constants 
	static final int SIZE = 500_000;
	static final int MAX_N = 100_000;
	// variables
	static int N;
	static int limit;
	static int[] A = new int[SIZE + 1];
	static int[] B = new int[SIZE + 1];
	static int[] dp = new int[SIZE + 1];	// A[i]를 마지막 원소로 하는 LIS의 길이
	static int len = 0;
	static int[] buffer = new int[MAX_N];
	
	
	static int lowerBound(int lo, int hi, int key) {
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
			if(buffer[mid] < key) lo = mid + 1;
			else hi = mid;
		}
		return lo;
	}
	
	static List<Integer> solution() {
		for(int i = 0; i <= limit; ++i) {
			if(A[i] == 0) continue;
			
			int at = lowerBound(0, len, A[i]);
			if(at == len) ++len;
			
			buffer[at] = A[i];			
			dp[i] = at + 1;
		}
		
		List<Integer> li = new ArrayList<>();
		int l = len;
		for(int i = limit; i >= 0; --i) {
			if(A[i] == 0) continue;
			if(dp[i] == l) {
				--l;
			} else {
				li.add(B[A[i]]);
			}
		}
		Collections.sort(li);
		return li;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			int s = readInt(), e = readInt();
			A[s] = e; B[e] = s;
			limit = Math.max(limit, s);
		}
		List<Integer> li = solution();
		
		sb.append(li.size()).append("\n");
		for(int e : li) sb.append(e).append("\n");
		System.out.println(sb);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}
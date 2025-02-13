import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int MAX_N = 1_000;
	// variables
	static int N;
	static int[] seq = new int[MAX_N];
	static int[] buffer = new int[MAX_N];
	static int[] dp = new int[MAX_N];
	static int[] lis= new int[MAX_N];
	
	
	static int lower_bound(int val, int[] arr, int lo, int hi) {
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
			if(arr[mid] < val) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}
		return lo;
	}
	
	static int getLIS(int beg, int end) {
		int size = 0;
		for(int i = beg; i < end; ++i) {
			int at;
			if(size == 0 || buffer[size - 1] < seq[i]) {
				at = size++;
			} else {
				at = lower_bound(seq[i], buffer, 0, size - 1);
			}
			buffer[at] = seq[i];
			dp[i] = at + 1;
		}
		
		// LIS 최대 길이일 때, 가장 오른쪽 원소의 인덱스를 구한다. 인덱스: ix, 길이: il, 값: iv
		// ix - 1 이하의 인덱스에서 왼쪽으로 순회하며, 
		// 길이가 (il - 1)이면서, 값이 ix보다 작은 인덱스를 구한다.
		// <ix, il, iv>를 그것으로 갱신한다.
		// il이 1이 될때까지 반복한다.
		
		int ix = beg;
		for(int i = beg; i < end; ++i) {
			if(dp[ix] < dp[i]) {
				ix = i;
			}
		}
		
		lis[dp[ix] - 1] = seq[ix];
		for(int i = ix - 1; i >= 0; --i) {
			if(dp[i] == dp[ix] - 1 && seq[i] < seq[ix]) {
				ix = i;
				lis[dp[ix] - 1] = seq[ix];
				if(dp[ix] == 1) break;
			}
		}	
		return size;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; ++i) seq[i] = Integer.parseInt(st.nextToken());
		
		int size = getLIS(0, N);
		bw.append(size + "\n");
		for(int i = 0; i < size; ++i) {
			bw.append(lis[i] + " ");
		}
		bw.flush();
	}
}
import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	// variables
	static int N;
	static int[] seq = new int[1_000];
	static int[] buffer = new int[1_000];
	
	
	static int lower_bound_asc(int val, int[] arr, int lo, int hi) {
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
	
	static int lower_bound_desc(int val, int[] arr, int lo, int hi) {
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
			if(arr[mid] > val) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}
		return lo;
	}
	
	static int getLISLength(int beg, int end, int limit) {
		if(beg >= end) return 0;
		
		int size = 0;
		for(int i = beg; i < end; ++i) {
			if(seq[i] >= limit) continue;
			
			if(size == 0 || buffer[size - 1] < seq[i]) {
				buffer[size++] = seq[i];
			} else {
				int at = lower_bound_asc(seq[i], buffer, 0, size - 1);
				buffer[at] = seq[i];
			}
		}
		return size;
	}
	
	static int getLDSLength(int beg, int end, int limit) {
		if(beg >= end) return 0;
		
		int size = 0;
		for(int i = beg; i < end; ++i) {
			if(seq[i] >= limit) continue;
			
			if(size == 0 || buffer[size - 1] > seq[i]) {
				buffer[size++] = seq[i];
			} else {
				int at = lower_bound_desc(seq[i], buffer, 0, size - 1);
				buffer[at] = seq[i];
			}
		}
		return size;
	}
	
	static int solution() {
		int max = 0;
		for(int i = 0; i < N; ++i) {
			int lis = getLISLength(0, i, seq[i]);
			int lds = getLDSLength(i + 1, N, seq[i]);
			max = Math.max(max, lis + lds + 1);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; ++i) seq[i] = Integer.parseInt(st.nextToken());
		bw.append(""+solution()).flush();
	}
}
	
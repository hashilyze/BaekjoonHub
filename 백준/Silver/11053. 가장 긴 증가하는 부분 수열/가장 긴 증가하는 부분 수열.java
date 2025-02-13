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
	
	static int getLISLength(int beg, int end) {
		if(beg >= end) return 0;
		
		int size = 1;
		int[] lis = new int[N]; lis[0] = seq[beg];
		for(int i = beg + 1; i < end; ++i) {
			if(lis[size - 1] < seq[i]) {
				lis[size++] = seq[i];
			} else {
				int at = lower_bound(seq[i], lis, 0, size - 1);
				lis[at] = seq[i];
			}
		}
		return size;
	}
	
	static int solution() {
		return getLISLength(0, N);
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; ++i) seq[i] = Integer.parseInt(st.nextToken());
		bw.append(""+solution()).flush();
	}
}
	
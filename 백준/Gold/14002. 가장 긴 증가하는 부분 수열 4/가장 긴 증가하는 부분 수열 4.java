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
	static int[] parents = new int[MAX_N];
	static int[] lis= new int[MAX_N];
	
	
	static int lower_bound(int val, int[] arr, int lo, int hi) {
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
			if(seq[arr[mid]] < val) {
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
			int at = lower_bound(seq[i], buffer, 0, size);
			if(at == size) ++size;
			buffer[at] = i;
			parents[i] = buffer[at != 0 ? at - 1 : i];
		}
		
		int cur = size - 1;
		int node = buffer[size - 1];
		while(cur >= 0) {
			lis[cur] = seq[node];
			--cur;
			node = parents[node];
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
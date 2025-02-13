import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;	
	// constants
	static int MAX_N = 1_000_000;
	// variables
	static int N;
	static int[] perm = new int[MAX_N];
	
	static int lower_bound(int val, int[] arr, int left, int right) {
		while(left < right) {
			int mid = (left + right) / 2;
			if(arr[mid] < val) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
	
	static int solution() {
		int size = 1;
		int[] lis = new int[N];
		lis[0] = perm[0];
		
		for(int i = 1; i < N; ++i) {
			if(lis[size - 1] < perm[i]) {
				lis[size++] = perm[i];
			} else {
				int at = lower_bound(perm[i], lis, 0, size - 1);
				lis[at] = perm[i];
			}
		}
		return size;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			perm[i] = Integer.parseInt(st.nextToken()); 
		}
		bw.append("" + solution()).flush();
	}
}

import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	// constants
	static final int MAX_N = 1_000;
	// variables
	static int N;
	static int[] arr = new int[MAX_N];
	
	// val과 같거나 작은 가장 왼쪽 원소의 인덱스를 반환
	static int lower_bound(int val, int[] arr, int left, int right) {
		while(left < right) {
			int mid = (left + right) / 2;
			if(arr[mid] > val) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}
	
	static int solution() {
		int size = 1;
		int[] dp = new int[MAX_N]; dp[0] = arr[0];
		
		for(int i = 1; i < N; ++i) {
			final int v = arr[i];
			if(dp[size - 1] > v) { // append
				dp[size++] = v;
			} else { //replace
				int at = lower_bound(v, dp, 0, size);
				dp[at] = v;
			}
		}
		
		return size;
	}

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		bw.append(""+solution()).flush();
	}
}

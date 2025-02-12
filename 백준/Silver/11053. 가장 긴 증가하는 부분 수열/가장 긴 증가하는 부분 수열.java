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
	static int[] result = new int[1_000];
	
	// val과 같거나 큰 원소중 가장 왼쪽 원소의 인덱스를 반환
	static int lower_bound(int val, int[] arr, int beg, int end) {
		int left = beg, right = end - 1;
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
		result[0] = seq[0];
		int size = 1;
		for(int i = 1; i < N; ++i) {
			if(result[size - 1] < seq[i]) {
				result[size++] = seq[i];
			} else {
				int at = lower_bound(seq[i], result, 0, size);
				result[at] = seq[i];
			}
		}
		return size;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; ++i) seq[i] = Integer.parseInt(st.nextToken());
		bw.append(""+solution()).flush();
	}
}
	
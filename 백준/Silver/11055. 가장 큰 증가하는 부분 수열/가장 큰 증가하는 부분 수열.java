import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static final int MAX_N = 1_000;
	// variables
	static int N;
	static int[] A = new int[MAX_N];
	static int[] buffer = new int[MAX_N]; // LIS 생성에 이용하는 버퍼; buffer[i]: LIS길이가 (i+1)인 가장 마지막 원소의 인덱스
	static int[] dpAcc = new int[MAX_N];
	
	static int lower_bound(int beg, int end, int val) {
		while(beg != end) {
			int mid = (beg + end) >> 1;
			if(buffer[mid] < val) beg = mid + 1;
			else end = mid;
		}
		return beg;
	}
	
	static int solution() {
		int length = 0; // LIS의 길이
		int max = 0;
		for(int i = 0; i < N; ++i) { 
			int at = lower_bound(0, length, A[i]); // 마지막 원소가 A[i]인 LIS의 길이 찾기 
			buffer[at] = A[i];
			if(at == 0) dpAcc[i] = A[i];
			else {
				for(int j = 0; j < i; ++j) {
					if(A[j] < A[i]) {
						dpAcc[i] = Math.max(dpAcc[i], dpAcc[j] + A[i]);
					}
				}
			}
			length = Math.max(length, at + 1);
			max = Math.max(max, dpAcc[i]);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) A[i] = readInt();
		solution();
		System.out.print(solution());
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
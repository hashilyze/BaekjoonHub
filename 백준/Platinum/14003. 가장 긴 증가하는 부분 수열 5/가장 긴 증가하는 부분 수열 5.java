import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static final int MAX_N = 1_000_000;
	// variables
	static int N;
	static int[] A = new int[MAX_N];
	static int[] buffer = new int[MAX_N]; // LIS 생성에 이용하는 버퍼; buffer[i]: LIS길이가 (i+1)인 가장 마지막 원소의 인덱스
	static int[] dp = new int[MAX_N]; // A[i]가 LIS의 마지막 원소일 때의 길이
	
	static int length = 0; // LIS의 길이
	static int[] lis = new int[MAX_N];
	
	static int lower_bound(int beg, int end, int val) {
		while(beg != end) {
			int mid = (beg + end) >> 1;
			if(buffer[mid] < val) beg = mid + 1;
			else end = mid;
		}
		return beg;
	}
	
	static void solution() {		
		for(int i = 0; i < N; ++i) { 
			int at = lower_bound(0, length, A[i]); // 마지막 원소가 A[i]인 LIS의 길이 찾기 
			buffer[at] = A[i];
			dp[i] = at;
			length = Math.max(length, at + 1);
		}
		
		for(int i = N - 1, at = length - 1; i >= 0; --i) {
			if(dp[i] == at) {
				lis[dp[i]] = A[i];
				--at;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) A[i] = readInt();
		solution();
		sb.append(length).append("\n");
		for(int i = 0; i < length; ++i) sb.append(lis[i]).append(" ");
		System.out.print(sb);
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0, s = 1;
		while((c = System.in.read()) <= 0x20);
		if(c == '-') {
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n * s;
	}
}
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
	static int[] buffer = new int[MAX_N]; // LIS를 생성하는 데 사용할 버퍼
	
	
	// value보다 작은 원소 중 가장 큰 값 바로 오른쪽 위치를 찾음
	static int lower_bound(int beg, int end, int value, int[] arr) {
		while(beg != end) {
			int mid = (beg + end) >> 1;
			
			if(arr[mid] < value) beg = mid + 1;
			else end = mid;
		}
		return beg;
	}
	
	static int solution() {
		int length = 0; // LIS의 길이
		for(int i = 0; i < N; ++i) {
			// A[i]를 마지막 원소로 하는 LIS의 길이를 구함
			int at = lower_bound(0, length, A[i], buffer);
			buffer[at] = A[i];
			length = Math.max(length, at + 1);
		}
		return length;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) A[i] = readInt();
		System.out.print(solution());
	}
	
	// 정수 읽기
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
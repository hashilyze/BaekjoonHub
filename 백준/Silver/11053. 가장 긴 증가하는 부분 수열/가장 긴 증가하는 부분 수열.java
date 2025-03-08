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
	static int[] buffer = new int[MAX_N]; // LIS를 생성하는 데 사용할 버퍼
	
	
	static int solution() {
		int length = 0; // LIS의 길이
		for(int i = 0; i < N; ++i) {
			int elem = A[i];
			int j = length; // A[i]를 마지막 원소로 하는 LIS의 길이를 구함
			while(j > 0 && buffer[j - 1] >= elem) {
				--j;
			}
			buffer[j] = elem;
			length = Math.max(length, j + 1);
		}
		return length;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) A[i] = readInt();
		System.out.print(solution());
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
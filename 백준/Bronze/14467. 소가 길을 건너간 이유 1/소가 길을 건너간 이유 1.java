import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static final int SIZE = 100;
	// variables
	static int N;
	static int[] A = new int[SIZE + 1];
	
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		Arrays.fill(A, 0, SIZE + 1, -1);
		
		int cnt = 0;
		while(N-- > 0) {
			int cow = readInt();
			int pos = readInt();
			if(A[cow] < 0) A[cow] = pos;
			else {
				if(A[cow] != pos) {
					A[cow] = pos;
					++cnt;
				}
			}
		}
		System.out.println(cnt);
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
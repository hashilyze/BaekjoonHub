import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	// variables
	static int R, C;
	static StringBuilder[] cols;
	
	
	static String getSuffix(int r, int c) {
		return cols[c].substring(r);
	}
	
	static int solution() {
		int lo = 0, hi = R-1;
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
		
			boolean isConflict = false;
			Set<String> s = new HashSet<>();
			for(int c = 0; c < C; ++c) {
				if(!s.add(cols[c].substring(mid+1))) {
					isConflict = true;
					break;
				}
			}
			if(isConflict) hi = mid; // 중복 문자열이 존재하는 첫번째 행을 찾기
			else lo = mid+1;
		}
		return lo;
	}
	
	public static void main(String[] args) throws IOException {
		R = readInt(); C = readInt();
		cols = new StringBuilder[C];
		for(int c = 0; c < C; ++c) cols[c] = new StringBuilder();
		
		for(int r = 0; r < R; ++r) {
			char[] row = br.readLine().toCharArray();
			for(int c = 0; c < C; ++c) {
				cols[c].append(row[c]);
			}
		}
		System.out.print(solution());
	}

	static int readInt() throws IOException {
		int c, n = 0;
		while ((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if (c == '\r') System.in.read();
		return n;
	}
}

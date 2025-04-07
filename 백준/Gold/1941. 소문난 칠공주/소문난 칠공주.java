import java.io.*;
import java.util.*;

public class Main {
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static int SIZE = 5;
	static int AREA = SIZE*SIZE; 
	static int[][] DELTA = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	// variables
	static char[] mat = new char[AREA];
	static int cnt = 0;
	static boolean[] isVisited = new boolean[0x01 << AREA];
	
	static void getCount(int cntY, int cntS, int bits) {
		if(isVisited[bits]) return;
		isVisited[bits] = true;
		
		if(cntY >= 4) return; // 임도연파가 과반
		
		if(cntY + cntS == 7) {
			++cnt;
			return;
		}
		
		for(int i = 0; i < AREA; ++i) { 
			if((bits & (0x01 << i)) == 0) continue;
			
			int y = i / SIZE, x = i % SIZE;
			for(int d = 0; d < DELTA.length; ++d) {
				int nx = x + DELTA[d][0];
				int ny = y + DELTA[d][1];
				if(!(0 <= ny && ny < SIZE && 0 <= nx && nx < SIZE)) continue;
				
				int j = ny*SIZE+nx;
				if((bits & (0x01 << j)) != 0) continue;
								
				if(mat[j] == 'Y') getCount(cntY + 1, cntS, bits|(0x01 << j));
				else getCount(cntY, cntS + 1, bits|(0x01 << j));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		for(int i = 0, y = 0; y < SIZE; ++y) {
			char[] rows = br.readLine().toCharArray();; 
			for(int x = 0; x < SIZE; ++x) {
				mat[i++] = rows[x];
			}
		}
		for(int i = 0; i < AREA; ++i) {
			if(mat[i] == 'Y') getCount(1, 0, 0x01 << i);
			else getCount(0, 1, 0x01 << i);
		}
		System.out.println(cnt);
	}
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}
import java.io.*;
import java.util.*;

public class Main {
    // IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static int SIZE = 10;
	static int PAPER_NUM = 5;
	// variables
	static int[] mat = new int[SIZE];
	static int[] papers = new int[] {5, 5, 5, 5, 5};
	static int min = Integer.MAX_VALUE;
	
	
	static boolean canSet(int y, int x, int p) {
		if(y + p >= SIZE || x + p >= SIZE) return false;
		
		int mask = ((0x01 << (p + 1)) - 1) << x;
		for(int i = 0; i <= p; ++i) {
			if((mat[i + y] & mask) != mask) return false;
		}
		return true;
	}
	
	static void setZero(int y, int x, int p) {
		int mask = ~(((0x01 << (p + 1)) - 1) << x);
		for(int i = 0; i <= p; ++i) mat[y + i] &= mask;
	}
	static void setOne(int y, int x, int p) {
		int mask = ((0x01 << (p + 1)) - 1) << x;
		for(int i = 0; i <= p; ++i) mat[y + i] |= mask;
	}
	
	
	static void dfs(int y, int x, int cnt) {
		if(y == 9 && x == 10) {
			min = Math.min(min, cnt);
			return;
		}
		if(x == 10) {
			y += 1;
			x = 0;
		}
		if(((mat[y] >> x) & 1) == 1) {
			for(int p = 0; p < PAPER_NUM; ++p) {
				if(papers[p] == 0) continue;
				if(!canSet(y, x, p)) break;
				
				--papers[p];
				setZero(y, x, p);
				dfs(y, x + 1, cnt + 1);
				setOne(y, x, p);
				++papers[p];
			}
		} else {
			dfs(y, x + 1, cnt);
		}
	}
	
    public static void main(String[] args) throws IOException {
    	for(int y = 0; y < SIZE; ++y) {
    		int bits = 0;
    		for(int x = 0; x < SIZE; ++x) {
    			bits = (readInt() << 10) + bits >> 1; 
    		}
    		mat[y] = bits;
    	}
    	dfs(0, 0, 0);
    	System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}
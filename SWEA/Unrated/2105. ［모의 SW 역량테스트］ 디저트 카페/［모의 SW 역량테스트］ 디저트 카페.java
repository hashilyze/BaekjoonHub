import java.io.*;
import java.util.*;

public class Solution {
    // IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static int MAX_N = 20;
	// variables
	static int N;
	static int[][] mat = new int[MAX_N][MAX_N];
	static Set<Integer> S = new HashSet<>();
	static int max = -1;
	
	static boolean inRange(int y, int x) { return 0 <= y && y < N && 0 <= x && x < N; }

	static void dfsTL(int ay, int ax, int tr, int br, int bl, int tl) {
		int y = ay + tr - br - bl + tl, x = ax + tr + br - bl - tl;
		if(!S.add(mat[y][x])) return; // 두 번 방문하는 카페가 존재
		
		if(tl < br) dfsTL(ay, ax, tr, br, bl, tl + 1);
		else max = Math.max(max, S.size());
		S.remove(mat[y][x]);
	}
	
	static void dfsBL(int ay, int ax, int tr, int br, int bl) {
		int y = ay + tr - br - bl, x = ax + tr + br - bl;
		if(!S.add(mat[y][x])) return; // 두 번 방문하는 카페가 존재
		
		if(bl < tr) dfsBL(ay, ax, tr, br, bl + 1);
		else dfsTL(ay, ax, tr, br, bl, 1);	
		S.remove(mat[y][x]);
	}
	
	
	static void dfsBR(int ay, int ax, int tr, int br) {
		int y = ay + tr - br, x = ax + tr + br;
		if(!inRange(y, x)) return; // 범위 이탈
		if(!inRange(ay - br, ax + br)) return; // 되돌아 가는 꼭짓점이 범위 이탈
		if(!S.add(mat[y][x])) return; // 두 번 방문하는 카페가 존재
		
		dfsBR(ay, ax, tr, br + 1);
		dfsBL(ay, ax, tr, br, 1);
		
		S.remove(mat[y][x]); // 백트래킹
	}
	
	static void dfsTR(int ay, int ax, int tr) {
		int y = ay + tr, x = ax + tr;
		if(!inRange(y, x)) return; // 범위 이탈
		if(!S.add(mat[y][x])) return; // 두 번 방문하는 카페가 존재
		
		dfsTR(ay, ax, tr + 1);
		dfsBR(ay, ax, tr, 1);
		
		S.remove(mat[y][x]); // 백트래킹
	}
	
	static int solution() {
		max = -1;
		for(int y = 1; y < N - 1; ++y) {
			for(int x = 0; x < N - 2; ++x) {
				dfsTR(y, x, 1);
			}
		}
		return max;
	}
	
    public static void main(String[] args) throws IOException {
    	int T = readInt();
    	for(int t = 1; t <= T; ++t) {
    		N = readInt();
    		for(int y = 0; y < N; ++y) {
    			for(int x = 0; x < N; ++x) {
    				mat[y][x] = readInt();
    			}
    		}
    		sb.append('#').append(t).append(' ').append(solution()).append('\n');
    	}
    	System.out.println(sb);
    }
    
    static int readInt() throws IOException{
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}
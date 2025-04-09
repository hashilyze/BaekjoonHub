import java.io.*;
import java.util.*;

public class Main {
    // IO Handler
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;
    // types
    static class Line{
    	int height, length;
		public Line(int height, int length) { this.height = height; this.length = length; }
    }
    // constants
    static int MAX_N = 100;
    // variables
    static int N, X;
    static int[][] mat = new int[MAX_N][MAX_N];
    
    
    static boolean canRunway(int y, int x, int dy, int dx) {
    	// 동일한 높이의 절벽을 압축하여 하나로 표현
    	List<Line> lines = new ArrayList<Line>();
    	int prev = mat[y][x], len = 0;
    	while(y < N && x < N) {
    		if(prev == mat[y][x]) ++len;
    		else {
    			lines.add(new Line(prev, len));

    			prev = mat[y][x];
    			len = 1;
    		}
    		y += dy;
    		x += dx;
    	}
    	lines.add(new Line(prev, len));
    	
    	for(int i = 1; i < lines.size(); ++i) {
    		Line lhs = lines.get(i - 1);
    		Line rhs = lines.get(i);
    		// 높이 차가 1 이상이면 활주로 건설 불가
    		if(Math.abs(lhs.height - rhs.height) != 1) return false;
    		
    		// 높이가 더 낮은 족에 활수로를 설치함
    		if(lhs.height < rhs.height) { 
    			if(lhs.length < X) { // 활주로 설치 공간이 부족
    				return false;
    			}
    			lhs.length -= X; // 활주로를 설치한 만큼 길이를 줄임 -> 같은 절벽에서 또 다른 활주로가 설치될 지 여부를 판단하기 위함
    		} else { // lhs.height > rhs.height
    			if(rhs.length < X) { 
    				return false;
    			}
    			rhs.length -= X;
    		}
    	}
    	return true;
    }
    
    static int solution() {
    	int cnt = 0;
    	// 모든 방향에 대해 활주로 건설 여부 확인
    	for(int y = 0; y < N; ++y) { // 수평 방향 확인
    		if(canRunway(y, 0, 0, 1)) ++cnt;
    	}
    	for(int x = 0; x < N; ++x) { // 수직 방향 확인
    		if(canRunway(0, x, 1, 0)) ++cnt;
		}
    	return cnt;
    }
    
    
    public static void main(String[] args) throws IOException {
		N = readInt(); X = readInt();
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				mat[y][x] = readInt();
			}
		}
    	System.out.print(solution());
    }
     
    static int readInt() throws IOException{
        int c, n = 0;
        while((c = System.in.read()) <= 0x20);
        n = c & 0x0F;
        while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
        return n;
    }
}
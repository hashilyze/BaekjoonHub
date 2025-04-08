import java.io.*;
import java.util.*;

/**
 * 메모리: 27,896 kb
 * 시간: 1,072 ms
 * @author 배준수
 */
public class Solution {
    // IO Handler
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;
    // types
    // constants
    static int[][] DELTA = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] REFLECTS = {
            {1, 3, 0, 2},
            {1, 2, 3, 0},
            {2, 0, 3, 1},
            {3, 0, 1, 2},
            {1, 0, 3, 2}
    };
    // variables
    static int N;
    static int[][] mat = new int[102][102];
    static List<int[]>[] wormholes = new List[5];
     
     
    static int moveWormhole(int y, int x) {
        int id = mat[y][x] - 6;
        int[] hole = wormholes[id].get(0);
        if(hole[0] == x && hole[1] == y) return 1; 
        return 0;
    }
     
    static int getScore(int ay, int ax, int ad) {
        int score = 0;
        boolean isStart = false;
        int y = ay, x = ax, d = ad;
        
         
        while(true) {
            if(y == ay && x == ax) {
                if(isStart) break; // 출발점 회귀 
                isStart = true; 
            }
            if(mat[y][x] == -1) break; // 블랙홀에 빠짐
            if(1 <= mat[y][x] && mat[y][x] <= 5) { // 블록 만남
                d = REFLECTS[mat[y][x] - 1][d];
                ++score;
            }
            if(6 <= mat[y][x] && mat[y][x] <= 10) { // 웜홀 만남
                int w = mat[y][x] - 6;
                int wi = moveWormhole(y, x);
                int[] hole = wormholes[w].get(wi);
                x = hole[0];
                y = hole[1];
            }
             
            // 다음 위치로 이동
            x = x + DELTA[d][0];
            y = y + DELTA[d][1];
        }
        return score;
    }
     
    static int solution() {
        int max = 0;
        for(int y = 1; y <= N; ++y) {
            for(int x = 1; x <= N; ++x) {
                if(mat[y][x] != 0) continue;
                for(int d = 0; d < DELTA.length; ++d) {
                    max = Math.max(max, getScore(y, x, d));
                     
//                    int nx = x + DELTA[d][0];
//                    int ny = y + DELTA[d][1];
//                    if(mat[ny][nx] != 0) {
//                        max = Math.max(max, getScore(y, x, d));
//                    }
                }
            }
        }
        return max;
    }
     
    public static void main(String[] args) throws IOException {
        for(int i = 0; i < 5; ++i) wormholes[i] = new ArrayList<>();
        for(int i = 0; i <= 101; ++i) {
        	mat[i][0] = mat[0][i] = 5;
        }
        
        int T = readInt();
        for(int t = 1; t <= T; ++t) {
            for(int i = 0; i < 5; ++i) wormholes[i].clear();
             
            N = readInt();
            for(int y = 1; y <= N; ++y) {
                for(int x = 1; x <= N; ++x) {
                    mat[y][x] = readInt();
                     
                    if(6 <= mat[y][x] && mat[y][x] <= 10) { // 월홀 저장
                        wormholes[mat[y][x] - 6].add(new int[] {x, y});
                    }
                }
                mat[y][N + 1] = 5;
            }
            for(int i = 0; i <= N+1; ++i) {
            	mat[i][N+1] = mat[N+1][i] = 5;
            }
             
            sb.append("#").append(t).append(" ").append(solution()).append("\n");
        }
        System.out.print(sb);
    }
     
    static int readInt() throws IOException{
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
import java.io.*;
import java.util.*;
 
/**
 * 메모리: 74,488KB
 * 시간: 298ms
 * @author 
 *
 */
public class Solution {
    // Input Handler
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    // types
    static class Event implements Comparable<Event>{
        int life, y, x, time;
        public Event() { }
        public Event(int life, int y, int x, int time) {
            this.life = life;
            this.y = y;
            this.x = x;
            this.time = time;
        }
 
        @Override
        public int compareTo(Event o) { 
            if(this.time != o.time) return this.time-o.time; // 시간이 빠른 순
            else return o.life-this.life; // 생명력이 높은 순
        }
    }
    // constants
    static final int MAX_N = 50;
    static final int MAX_M = 50;
    static final int MAX_K = 300;
    static final int SIZE = (MAX_K + MAX_K + MAX_N + 10);
    static final int[][] DELTA = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    static final int ESLEEP = 0, EALIVE = 1, EREPRODUCT = 2, EDEAD = 3;
    static final int[] EMPTY_ROW = new int[SIZE];
    // variables
    static int N, M, K;
    static int[][] mat = new int[SIZE][SIZE];
    static int total;
    static PriorityQueue<Event> pq = new PriorityQueue<>();
     
     
    static void makeCell(int time, int life, int y, int x) {
        pq.offer(new Event(life, y, x, time + life + 1)); // 활성화
        if(time + life * 2 > K) ++total;
    }
    static void reproductCell(Event e) {
        for(int d = 0; d < DELTA.length; ++d) {
            int nx = e.x + DELTA[d][0];
            int ny = e.y + DELTA[d][1];
            if(mat[ny][nx] == 0) { // 생명력이 높은 세포가 낮은 세포보다 먼저 번식 -> 가장 생명력이 높은 세포가 번식 
                mat[ny][nx] = e.life;
                makeCell(e.time, e.life, ny, nx);
            }
        }
    }
     
    static int solution() {
        for(int t = 1; t <= K; ++t) {
            while(!pq.isEmpty() && pq.peek().time <= t) {
                Event event = pq.poll();
                reproductCell(event);
            }
        }
        return total;
    }
     
    /* 
     * 생명력 X에 대해, 시간 T에 생성된 세포는 T+X에 활성화되고, T+X+1에 번식, T+2*X에 비활성화된다.
     * 번식은 사방으로 한다.
     * 번식하려는 위치에 이미 세포가 존재하면, 번식할 수 없다.
     * 동일한 시간에 같은 위치에 여러 세포가 번식하면, 생명력이 가장 큰 세포가 번식된다.
     */
    public static void main(String[] args) throws IOException {
        int T = readInt();
        for(int t = 1; t <= T; ++t) {
            // 초기화
            final int size = 2 * K + Math.max(N, M) + 10;
            total = 0;
            pq.clear();
            for(int y = 0; y < size; ++y) System.arraycopy(EMPTY_ROW, 0, mat[y], 0, size);
             
            // Input
            N = readInt(); M = readInt(); K = readInt();
             
            for(int y = 0; y < N; ++y) {
                for(int x = 0; x < M; ++x) {
                    int py = y + K, px = x + K;
                    int life = readInt();
                     
                    if((mat[py][px] = life) != 0) { // 세포가 존재
                        makeCell(0, life, py, px);
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(solution()).append("\n");
        }
        System.out.print(sb);
    }
     
    static int readInt() throws IOException{
        int c, n = 0;
        while((c = System.in.read()) <= 0x20);
        n = c & 0x0F;
        while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
        if(c == '\r') System.in.read();
        return n;
    }
}
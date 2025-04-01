import java.io.*;
import java.util.*;
 
/**
 * 메모리: 25,856KB
 * 시간: 91ms
 * @author 배준수
 */
public class Solution {
    // IO Handler
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st = null;
    // types
    static class Node implements Comparable<Node>{
    	int val, y, x;
    	boolean isbind;
    	
		public Node(int val, int y, int x) {
			this.val = val;
			this.y = y;
			this.x = x;
		}

		@Override
		public int compareTo(Node o) { return o.val - this.val; }
    }
    // constants
    static final int MAX_N = 10;
    static final int MAX_M = 5;
    static final int MAX_C = 30;
    // variables
    static int N, M, C;
    static int[][] mat = new int[MAX_N][MAX_N];
    static int[][] dp = new int[MAX_M + 1][MAX_C + 1];
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static Node[] buffer = new Node[MAX_N * MAX_N >> 1];
     
    static int pow2(int v) {return v * v;}
    
    static int knapsack(int y, int x) {
    	for(int m = 1; m <= M; ++m) {
    		for(int c = 1; c <= C; ++c) {
    			dp[m][c] = dp[m - 1][c];
    			
    			int v = mat[y][x + m - 1];
    			if(c - v >= 0) dp[m][c] = Math.max(dp[m][c], dp[m - 1][c - v] + pow2(v)); 
    		}
    	}
        return dp[M][C];
    }
    
    static void insert(Node node) {
    	for(int i = 0; i < buffer.length; ++i) {
    		if(buffer[i] == null) {
    			buffer[i] = node;
    			break;
    		}
    		if(buffer[i].isbind == true) continue;
    		if(buffer[i].y != node.y || (node.x + M - 1 < buffer[i].x || buffer[i].x + M - 1 < node.x)) {
    			buffer[i].val += node.val;
    			buffer[i].isbind = true;
    		}
    	}
    }
    
    static int solution() {
    	pq.clear();
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N - M + 1; ++x) {
                int honey = knapsack(y, x);
                pq.offer(new Node(honey, y, x));             
            }
        }
        
        Arrays.fill(buffer, null);
        for(int i = 0; i < N; ++i) {
        	insert(pq.poll());
        }
        int max = 0;
        for(int i = 0; i < N * N; ++i) {
        	if(buffer[i] == null) break;
        	max = Math.max(max, buffer[i].val);
        }
        return max;
    }
     
    public static void main(String[] args) throws IOException {
        int T = readInt();
        for(int t = 1; t <= T; ++t) {
            N = readInt(); M = readInt(); C = readInt();
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
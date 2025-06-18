import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static class Problem implements Comparable<Problem> {
		Problem() {}
		Problem(int P, int L) { this.P = P; this.L = L; }
		
		int P, L;

		@Override
		public int compareTo(Problem o) {
			if(this.L != o.L) return this.L - o.L;
			return this.P - o.P;
		}
	}
	
	static int N, M;
	static PriorityQueue<Problem> minHeap = new PriorityQueue<>();
	static PriorityQueue<Problem> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	static Map<Integer, Problem> pool = new HashMap<>();
	
    public static void main(String[] args) throws IOException {
    	N = Integer.parseInt(br.readLine());
    	for(int i = 0; i < N; ++i) {
    		st = new StringTokenizer(br.readLine());
    		int P = Integer.parseInt(st.nextToken());
    		int L = Integer.parseInt(st.nextToken());
    		
    		Problem problem = new Problem(P, L); 
    		minHeap.add(problem);
    		maxHeap.add(problem);
    		pool.put(P, problem);
    	}
    	
    	M = Integer.parseInt(br.readLine());
    	while(M-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		String cmd = st.nextToken();
    		if(cmd.equals("recommend")) {
    			int x = Integer.parseInt(st.nextToken());
    			if(x > 0) {
    				while(pool.getOrDefault(maxHeap.peek().P, null) != maxHeap.peek()) {
    					maxHeap.poll();
    				}
    				sb.append(maxHeap.peek().P).append("\n");
    			} else {
    				while(pool.getOrDefault(minHeap.peek().P, null) != minHeap.peek()) {
    					minHeap.poll();
    				}
    				sb.append(minHeap.peek().P).append("\n");
    			}
    		} else {
    			if(cmd.equals("add")) {
    				int P = Integer.parseInt(st.nextToken());
        			int L = Integer.parseInt(st.nextToken());
        			
        			Problem problem = new Problem(P, L); 
            		minHeap.add(problem);
            		maxHeap.add(problem);
            		pool.put(P, problem);
    			} else {
    				int P = Integer.parseInt(st.nextToken());
    				pool.remove(P);
    			}
    		}
    	}
    	System.out.print(sb);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}
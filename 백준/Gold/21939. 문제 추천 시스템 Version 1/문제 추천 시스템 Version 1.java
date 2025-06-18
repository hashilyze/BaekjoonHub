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
	static TreeSet<Problem> tree = new TreeSet<>();
	static Map<Integer, Integer> map = new HashMap<>();
	
    public static void main(String[] args) throws IOException {
    	N = Integer.parseInt(br.readLine());
    	for(int i = 0; i < N; ++i) {
    		st = new StringTokenizer(br.readLine());
    		int P = Integer.parseInt(st.nextToken());
    		int L = Integer.parseInt(st.nextToken());
    		tree.add(new Problem(P, L));
    		map.put(P, L);
    	}
    	
    	M = Integer.parseInt(br.readLine());
    	while(M-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		String cmd = st.nextToken();
    		if(cmd.equals("recommend")) {
    			int x = Integer.parseInt(st.nextToken());
    			if(x > 0) {
    				sb.append(tree.last().P).append("\n");
    			} else {
    				sb.append(tree.first().P).append("\n");
    			}
    		} else {
    			if(cmd.equals("add")) {
    				int P = Integer.parseInt(st.nextToken());
        			int L = Integer.parseInt(st.nextToken());
    				tree.add(new Problem(P, L));
    				map.put(P, L);
    			} else {
    				int P = Integer.parseInt(st.nextToken());
    				tree.remove(new Problem(P, map.get(P)));
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
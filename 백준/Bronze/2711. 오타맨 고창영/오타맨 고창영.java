import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int T = Integer.parseInt(br.readLine());
    	while(T-- > 0) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		int N = Integer.parseInt(st.nextToken());
    		for(char ch : st.nextToken().toCharArray()) {
    			if(--N == 0) continue;
    			sb.append(ch);
    		}
    		sb.append('\n');
    	}
    	System.out.print(sb);
    }
    

}

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		int[] ans = new int[N];
		int[] heights = new int[N + 1];
		heights[0] = 100_000_000 + 1;
		
		int top = 0;
		int[] stk = new int[N + 1];
				
		for(int i = 0; i < ans.length; ++i) {
			int num = i + 1;
			int height = Integer.parseInt(st.nextToken());
			heights[num] = height;
			
			while(heights[stk[top]] < height) --top;
			ans[i] = stk[top];
			stk[++top] = num;;
		}
		
		
		for(int e : ans) bw.append(e + " ");
		bw.flush();
	}
}
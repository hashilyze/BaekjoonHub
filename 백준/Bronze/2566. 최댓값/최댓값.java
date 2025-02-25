import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	
	public static void main(String[] args) throws IOException {
		int maxX = 0, maxY = 0, maxVal = -1;
		for(int y = 0; y < 9; ++y) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < 9; ++x) {
				int val = Integer.parseInt(st.nextToken());
				if(maxVal < val) {
					maxVal = val;
					maxY = y;
					maxX = x;
				}
			}
		}
		
		sb.append(maxVal).append("\n").append(maxY + 1).append(" ").append(maxX + 1);
		System.out.print(sb);
	}
}
import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	// variables
	static int N, M;
	static int J;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		J = Integer.parseInt(br.readLine());
		
		int mv = 0;
		int anchor = 1;
		for(int i = 0; i < J; ++i) {
			int drop = Integer.parseInt(br.readLine());
			if(drop < anchor) { // 바구니 왼쪽으로 낙하
				int delta = (anchor - drop);
				anchor -= delta;
				mv += delta;
			} else if(drop < anchor + M) { // 바구니로 낙하
				continue;
			} else { // 바구니 오른쪽으로 낙하
				int delta = drop - (anchor + M) + 1;
				anchor += delta;
				mv += delta;
			}
		}
		bw.append(mv+"").flush();
	}
}
	
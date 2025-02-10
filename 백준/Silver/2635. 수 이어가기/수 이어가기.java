import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		// Input
		int N = Integer.parseInt(br.readLine());
		// Solution
		int maxI = N;
		int maxL = maxLength(N, maxI);
		for(int i = 1; i < N; ++i) {
			int l = maxLength(N, i);
			if(maxL < l) {
				maxI = i;
				maxL = l;
			}
		}
		// Output
		bw.append(maxL + "\n");
		bw.append(N + " ");
		int p1 = N, p2 = maxI;
		while(p1 >= p2) {
			bw.append(p2 + " ");
			int p3 = p1 - p2;
			p1 = p2;
			p2 = p3;
		}
		bw.append(p2 + "");
		bw.flush();
	}
	
	static int maxLength(int p1, int p2) {
		int len = 2;
		while(p1 >= p2) {
			int p3 = p1 - p2;
			p1 = p2;
			p2 = p3;
			++len;
		}
		return len;
	}
}
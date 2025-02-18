import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < 2 * N; ++i) {
			int pad = Math.abs(N - 1 - i);
			int fill = N - pad;
			for(int j = 0; j < 2 * N; ++j) {
				bw.append(j < fill || (2 * N - fill) <= j ? '*' : ' ');
			}
			bw.append('\n');
		}
		bw.flush();
	}
}
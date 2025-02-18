import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < 2 * N - 1; ++i) {
			int pad = (N - 1) - Math.abs(N - 1 - i);
			int fill = (2 * N - 1) - 2 * pad;
			for(int j = 0; j < pad + fill; ++j) {
				bw.append(j < pad ? ' ' : '*');
			}
			bw.append('\n');
		}
		bw.flush();
	}
}
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
			int pad = Math.abs(N - 1 - i);
			for(int j = 0; j < N; ++j) {
				bw.append(j < pad ? ' ' : '*');
			}
			bw.append('\n');
		}
		bw.flush();
	}
}
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	// variables
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; ++i) {
			for(int j = 0; j < N; ++j) {
				bw.append(j < (N - i) ? ' ' : '*');
			}
			bw.append('\n');
		}
		bw.flush();
	}
}
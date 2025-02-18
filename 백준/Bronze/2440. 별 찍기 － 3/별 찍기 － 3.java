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
		for(int i = N; i >= 1; --i) {
			for(int j = 0; j < i; ++j) {
				bw.append('*');
			}
			bw.append('\n');
		}
		bw.flush();
	}
}
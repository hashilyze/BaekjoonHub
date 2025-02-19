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
			for(int j = 0; j < N; ++j) {
				bw.append((i + j) % 2 == 0 ? '*' : ' ');
			}
			bw.append('\n');
		}
		bw.flush();
	}
}
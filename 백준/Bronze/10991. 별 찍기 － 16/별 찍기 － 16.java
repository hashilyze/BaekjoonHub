import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			int pad = N - 1 - i;
			for(int j = 0; j < (N + i); ++j) {
				if(j < pad) bw.append(' ');
				else if((j - pad) % 2 == 0) bw.append('*');
				else bw.append(' ');
			}
			bw.append('\n');
		}
		bw.flush();
	}
}
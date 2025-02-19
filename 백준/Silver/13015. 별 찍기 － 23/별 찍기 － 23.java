import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		int size = 4 * (N - 1) + 1;
		int center = 2 * (N - 1);
		int gap = N - 2;
		for(int j = 0; j < size; ++j) bw.append(Math.abs(center - j) > gap ? '*' : ' ');
		bw.append('\n');
		
		for(int i = 1; i < 2 * N - 2; ++i) {
			int outPad = N - 1 - Math.abs((N - 1) - i);
			int inPad = Math.abs((N - 1) - i);
			
			for(int j = 0; j < N; ++j) {
				bw.append(j == outPad ? '*' : ' ');
			}
			
			for(int j = 0; j < 2 * (N - 2) + 1; ++j) {
				bw.append(Math.abs(N - 2 - j) == inPad ? '*' : ' ');
			}
			
			for(int j = 0; j < N - outPad; ++j) {
				bw.append(j == N - outPad - 1 ? '*' : ' ');
			}
			bw.append('\n');
		}
		
		
		for(int j = 0; j < size; ++j) bw.append(Math.abs(center - j) > gap ? '*' : ' ');
		
		bw.flush();
		
	}
}
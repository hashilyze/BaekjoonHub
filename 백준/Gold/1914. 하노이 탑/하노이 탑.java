import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// variables
	static int N;
	
	static void moveTo(int lv, int from, int to, int pass) throws IOException {
		if(lv == 1) {
			bw.append(from + " " + to + "\n");
		} else {
			moveTo(lv - 1, from, pass, to);
			bw.append(from + " " + to + "\n");
			moveTo(lv - 1, pass, to, from);
		}
	}	
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		
		bw.append(BigInteger.valueOf(2).pow(N).subtract(BigInteger.valueOf(1)).toString()).append("\n");
		if(N <= 20) {
			moveTo(N, 1, 3, 2);
		}
		bw.flush();
	}
}
	
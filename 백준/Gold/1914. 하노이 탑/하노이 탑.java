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
	static List<int[]> result = new ArrayList<int[]>();
	
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
		
		if(N <= 20) {
			bw.append(((0x01 << N) - 1) + "\n");
			moveTo(N, 1, 3, 2);
		} else {
			BigInteger bi = BigInteger.valueOf(2)
					.pow(N)
					.subtract(BigInteger.valueOf(1));
			bw.append(bi.toString());
		}
		bw.flush();
	}
}
	
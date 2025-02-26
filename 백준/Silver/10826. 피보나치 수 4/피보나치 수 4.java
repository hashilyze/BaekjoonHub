import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		BigInteger f0 = BigInteger.ZERO;
		BigInteger f1 = BigInteger.ONE;
		while(N-- > 0) {
			BigInteger tmp = f1.add(f0); 
			f0 = f1;
			f1 = tmp;
		}
		System.out.println(f0);
	}
}
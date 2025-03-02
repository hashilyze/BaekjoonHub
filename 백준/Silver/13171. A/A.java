import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static final BigInteger MOD = new BigInteger("1000000007");
	
	static BigInteger A, X;
	
	static BigInteger solution() {
		BigInteger ans = BigInteger.ONE, b = A, e = X;
		while(!e.equals(BigInteger.ZERO)) {
			if(e.testBit(0)) ans = ans.multiply(b).mod(MOD);
			e = e.shiftRight(1);
			b = b.multiply(b).mod(MOD);
		}
		return ans;
	}
	public static void main(String[] args) throws IOException {
		A = new BigInteger(br.readLine());
		X = new BigInteger(br.readLine());
		System.out.print(solution());
	}
}
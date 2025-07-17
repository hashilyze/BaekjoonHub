import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	
	
	static BigInteger factorial(int lo, int hi) {
        if (lo == hi) return BigInteger.valueOf(lo);
        return factorial(lo, (lo + hi) >> 1).multiply(factorial(((lo + hi) >> 1) + 1, hi));
    }
	
	public static void main(String[] args) throws IOException {
		int N = readInt();
		
		if(N == 0) System.out.println(1);
		else System.out.print(factorial(1, N));
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}
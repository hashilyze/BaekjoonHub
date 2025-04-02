import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    // IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	
	
    public static void main(String[] args) throws IOException {
    	while(true) {
    		st = new StringTokenizer(br.readLine());
    		BigInteger a = new BigInteger(st.nextToken());
	    	BigInteger b = new BigInteger(st.nextToken());
	    	if(a.compareTo(BigInteger.ZERO) == 0 && b.compareTo(BigInteger.ZERO) == 0) break;
	    	
	    	BigInteger f0 = new BigInteger("1");
	    	BigInteger f1 = new BigInteger("2");
	    	
	    	long cnt = 0;
	    	while(f0.compareTo(b) <= 0) {
	    		if(f0.compareTo(a) >= 0) ++cnt;
	    		BigInteger tmp = f0.add(f1); f0 = f1; f1 = tmp;
	    	}
	    	sb.append(cnt).append('\n');
    	}
    	System.out.print(sb);
    }
}
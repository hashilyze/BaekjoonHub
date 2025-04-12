import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] freqs = new int[10];
    
	public static void main(String[] args) throws IOException {
		BigInteger a = BigInteger.valueOf(Integer.parseInt(br.readLine()));
		BigInteger b = BigInteger.valueOf(Integer.parseInt(br.readLine()));
        BigInteger c = BigInteger.valueOf(Integer.parseInt(br.readLine()));
		
		BigInteger mul = a.multiply(b).multiply(c);
		char[] digits = mul.toString().toCharArray();
		for (char digit : digits) ++freqs[digit - '0'];
        
		for (int freq : freqs) sb.append(freq).append("\n");
        System.out.print(sb);
	}
}
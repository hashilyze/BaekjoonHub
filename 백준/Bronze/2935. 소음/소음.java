import java.io.*;
import java.math.BigInteger;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
		BigInteger A = new BigInteger(br.readLine());
		String op = br.readLine();
		BigInteger B = new BigInteger(br.readLine());
		
		if(op.equals("+")) System.out.println(A.add(B));
		else System.out.println(A.multiply(B));
	}

}
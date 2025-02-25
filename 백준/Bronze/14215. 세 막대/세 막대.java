import java.io.*;
import java.math.BigInteger;
import java.sql.Array;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int[] A = new int[3];
		for(int i = 0; i < 3; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(A);
		
		if(A[0] + A[1] <= A[2]) {
			A[2] = A[0] + A[1] - 1; 
		}
		System.out.println(A[0] + A[1] + A[2]);
	}
}
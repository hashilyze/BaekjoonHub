import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	
	public static void main(String[] args) throws IOException {
		int[] A = new int[3];
		for(int i = 0; i < 3; ++i) A[i] = Integer.parseInt(br.readLine());
		
		if(A[0] + A[1] + A[2] != 180) System.out.println("Error");
		else if(A[0] == A[1] && A[1] == A[2]) System.out.println("Equilateral");
		else if(A[0] == A[1] || A[1] == A[2] || A[2] == A[0]) System.out.println("Isosceles");
		else System.out.println("Scalene");
	}
}
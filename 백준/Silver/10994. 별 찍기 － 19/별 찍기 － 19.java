import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	
	static char[][] mat;
	
	static int getSize(int n) { return 4 * (n - 1) + 1; }
	
	static void makeStar(int ay, int ax, int n) {
		final int size = getSize(n);
		for(int d = 0; d < size; ++d) {
			mat[ay + d][ax] 
				= mat[ay + d][ax + (size - 1)]
				= mat[ay][ax + d]
				= mat[ax + (size - 1)][ax + d]
				= '*';
		}
		if(n > 1) makeStar(ay + 2, ax + 2, n - 1);
	}
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		int size = getSize(N);
		mat = new char[size][size];
		
		for(int i = 0; i < size; ++i) Arrays.fill(mat[i], ' ');
		makeStar(0, 0, N);
		for(int i = 0; i < size; ++i) System.out.println(mat[i]);
	}
}
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
	static Integer[] arr;
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new Integer[N];
		for(int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr, (lhs, rhs)->rhs-lhs);
		
		for(int i = 0; i < N; ++i) {
			bw.append(arr[i].toString()).append("\n");
		}
		bw.flush();
	}
}
	
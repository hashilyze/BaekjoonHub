import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int f0 = 0, f1 = 1;
		while(N-- > 0) {
			int tmp = f1;
			f1 = f1 + f0;
			f0 = tmp;
		}
		System.out.print(f0);
	}
}
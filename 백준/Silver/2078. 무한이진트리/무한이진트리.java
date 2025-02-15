import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	// variables
	static int A, B;
	
	
	static int[] solution(int A, int B) {
		int cntLeft = 0, cntRight = 0;
		while(A != 1 || B != 1) {
			if(A < B) {
				++cntRight;
				B -= A;
			} else {
				++cntLeft;
				A -= B;
			}
		}
		return new int[] {cntLeft, cntRight};
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		int[] result = solution(A, B);
		bw.append(result[0] + " " + result[1]).flush();
	}
}
	
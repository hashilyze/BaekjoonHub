import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) A[i] = Integer.parseInt(st.nextToken());
		String ans = solution(A);
		bw.append(ans).flush();
	}
	
	static String solution(int[] A) {
		int[] stacks = new int[4];
		Arrays.fill(stacks, 0);
		for(int e : A) {
			// e보다 작은 값이면서 가장 큰 값을 저장한 스택의 번호
			int max = -1;
			for(int i = 0; i < stacks.length; ++i) {
				if(stacks[i] < e && (max == -1 || stacks[max] < stacks[i])) {
					max = i;
				}
			}
			if(max == -1) return "NO";
			stacks[max] = e;
		}
		return "YES";
	}
}
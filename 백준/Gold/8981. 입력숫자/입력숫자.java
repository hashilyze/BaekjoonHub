import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	// variables
	static int N;
	static int[] output;
	
	
	static int[] solution(int[] output) {
		int[] input = new int[N];
		int outCur = 0, inCur = 0;
		
		// output[]은 0번 인덱스부터 순서대로 채워짐
		// input[]을 시뮬레이션 했을 때 가리키는 위치의 값이 output이 가리키는 값
		while(outCur < N) {
			while(input[inCur] != 0) inCur = (inCur + 1) % N;
			input[inCur] = output[outCur++];
			inCur = (inCur + input[inCur]) % N;
		}
		return input;
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		output = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) output[i] = Integer.parseInt(st.nextToken()); 
		
		int[] input = solution(output);
		
		bw.append(N + "\n");
		for(int i = 0; i < N; ++i) {
			bw.append(input[i] + " ");
		}
		bw.flush();
	}
}
	
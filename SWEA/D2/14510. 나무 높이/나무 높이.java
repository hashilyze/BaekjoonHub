import java.io.*;
import java.util.*;

public class Solution {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	// constants
	static final int MAX_N = 100;
	// Variables	
	static int T;
	static int N;
	static int[] heights = new int[MAX_N];
	
	
	static int soluton() {
		int max = 0;
		for(int i = 0; i < N; ++i) max = Math.max(max, heights[i]);
		
		int sum = 0;
		int countDiffOdd = 0;
		for(int i = 0; i < N; ++i) {
			int diff = max - heights[i];
			sum += diff;
			if(diff % 2 == 1) {
				++countDiffOdd;
			}
		}
		
		// 홀수일만으로 목표 높이만큼 나무를 키울 수 있다.
		// 마지막 홀수일 두개를 짝수일 하나로 치환할 수 있다.
		// 홀수일은 최소한 차가 홀수개인 것 만큼 필요하다.
		int min = Integer.MAX_VALUE;
		int cntOddDay = sum;
		int cntEvenDay = 0;
		while(countDiffOdd <= cntOddDay && cntEvenDay <= cntOddDay + 1) {
			int lastOddDay = cntOddDay * 2 - 1;
			int lastEvenDay = cntEvenDay * 2;
			min = Math.min(min, Math.max(lastOddDay, lastEvenDay));
			cntOddDay -= 2;
			cntEvenDay += 1;
		} 
		
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		String line = br.readLine();
		T = Integer.parseInt(line);
		for(int t = 1; t <= T; ++t) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; ++i) {
				heights[i] = Integer.parseInt(st.nextToken());	
			}
			bw.append("#" + t + " " + soluton() + "\n");
		}
		bw.flush();
	}
}

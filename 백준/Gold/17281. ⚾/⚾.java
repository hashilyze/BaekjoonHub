import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int MOD = 9;
	static final int MAX_N = 50;
	static final int NUM_STONE = 3;
	// variables
	static int N;
	static int[][] hits = new int[MAX_N][MOD];
	static int result = -1;
	
	static void solution(int[] perm) {
		// 야구 
		int score = 0;
		
		int playerCur = 0;
		int stoneCur = 0;
		boolean[] stones = new boolean[NUM_STONE];
		for(int e = 0; e < N; ++e) { // e번째 이닝
			// 초기화
			int out = 0;
			Arrays.fill(stones, false);
			
			while(out < 3) {
				int playerId = perm[playerCur];
				int hit = hits[e][playerId];
				if(hit == 0) { // 아웃
					++out;
				} else { // 진루
					for(int i = 0; i < hit; ++i) {
						if(stones[stoneCur]) 
							++score;
						stones[stoneCur] = i == 0;
						stoneCur = (stoneCur + 1) % NUM_STONE;
					}
				}
				playerCur = (playerCur + 1) % MOD;
			}
		}
		
		result = Math.max(result, score);
	}
	
	static void nextPermutation(int idx, int size, int[] perm, boolean[] isUsed) {
		if(idx == size) { // 기저
			solution(perm);
			return;
		}
		
		if(perm[idx] >= 0) { // 순열의 4번째 원소는 1번 선수로 고정
			nextPermutation(idx + 1, size, perm, isUsed); 
		} else {
			for(int i = 0; i < size; ++i) {
				if(isUsed[i]) continue;
				
				isUsed[i] = true;
				perm[idx] = i;
				nextPermutation(idx + 1, size, perm, isUsed);
				perm[idx] = -1;
				isUsed[i] = false;
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < MOD; ++j) {
				hits[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] perm = new int[MOD]; Arrays.fill(perm, -1); perm[3] = 0;
		boolean[] isUsed = new boolean[MOD]; isUsed[0] = true;
		nextPermutation(0, MOD, perm, isUsed);
		bw.append(""+result).flush();
	}
}
	
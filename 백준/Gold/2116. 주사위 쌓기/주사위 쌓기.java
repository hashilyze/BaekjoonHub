import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int[] opposites = new int[] {5, 3, 4, 1, 2, 0};
	static final int[][] adjacences = new int[6][4];
	// variables
	static int N;
	static int[][] dices;
	
	static void init() {
		for(int face = 0; face < 6; ++face) {
			int opposite = opposites[face];
			int k = 0;
			for(int i = 0; i < 6; ++i) {
				if(i != face && i != opposite)
				adjacences[face][k++] = i;
			}
		}
	}
	
	static int solution(int at) {
		int bot = dices[0][at];
		
		int sum = 0;
		for(int i = 0; i < N; ++i) {
			int[] dice = dices[i];
			// dice에서 bot이 몇번째 원소인지(x) 찾는다.
			int x = -1;
			for(int j = 0; j < 6; ++j) {
				if(dice[j] == bot) {
					x = j;
					break;
				}
			}
			// x의 opposite로 bot을 갱신
			bot = dice[opposites[x]];
			// x의 adjacence 원소들 중 가장 큰 값을 sum에 더한다.
			int[] adjacence = adjacences[x];
			int max = dice[adjacence[0]];
			for(int j = 1; j < 4; ++j) {
				max = Math.max(max, dice[adjacence[j]]);
			}
			sum += max;
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		init();
		// inputs
		N = Integer.parseInt(br.readLine());
		dices = new int[N][6];
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 6; ++j) {
				dices[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// solution
		int max = solution(0);
		for(int i = 1; i < 6; ++i) {
			int val = solution(i);
			max = Math.max(max, val);
		}
		bw.append(max + "").flush();
	}
}
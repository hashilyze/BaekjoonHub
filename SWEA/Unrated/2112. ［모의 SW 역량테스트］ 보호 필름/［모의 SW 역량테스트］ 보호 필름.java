import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int A = 0x00; // A로만 이루어진 단층
	static final int B = (int)((0x01L << 32) - 1); // B로만 구성된 단층
	
	static int D, W, K;
	static int[] firms = new int[13];
	static int min;

	
	static boolean isPassed() {
		for(int x = 0; x < W; ++x) {
			// x번째 열이 성능검사를 통과하는 지 확인
			boolean pass = false;
			int prev = -1, len = 0;
			for(int y = 0; y < D; ++y) {
				int cur = (firms[y] >> x) & 1;
				if(prev == cur) {
					++len;
				} else {
					prev = cur;
					len = 1;
				}
				
				if(len >= K) { // x번째 열이 성능 검사 통과
					pass = true;
					break;
				}
			}
			if(!pass) return false; // 성능 검사 불합
		}
		return true;
	}
	
	static void eachSubset(int idx, int cnt) {
		if(min < cnt) return; // 가지치기: 최솟값보다 더 투여할 경우 중단
		
		if(idx == D) { // 기저
			if(isPassed()) min = Math.min(min, cnt);
			return;
		}
		
		int cached = firms[idx];
		eachSubset(idx + 1, cnt); 		// case.1: 투여 X
		firms[idx] = A;
		eachSubset(idx + 1, cnt + 1); 	// case.2: 약물 A 투여
		firms[idx] = B;
		eachSubset(idx + 1, cnt + 1);	// case.3: 약물 B 투여
		firms[idx] = cached; // 복원
	}
	
	static int solution() {
		min = Integer.MAX_VALUE;
		eachSubset(0, 0);
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for(int y = 0; y < D; ++y) {
				firms[y] = Integer.parseInt(br.readLine().replaceAll(" ", ""), 2); // 비트마스킹으로 변환
			}
			sb.append("#").append(t).append(" ").append(solution()).append("\n");
		}
		System.out.print(sb);
	}
}

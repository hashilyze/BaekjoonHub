import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int A = 0x00;
	static final int B = (int)((0x01L << 32) - 1);
	
	static int D, W, K;
	static int[] cells = new int[13];
	static int min;
	
	static void toggle(int bitMask) {
		for(int i = 0; i < D; ++i) {
			if((bitMask & (0x01 << i)) == 1) {
				cells[i] = ~cells[i];
			}
		}
	}
	
	static int countOneBit(int bits) {
		int cnt = 0;
		while(bits > 0) {
			bits -= (bits & -bits);
			++cnt;
		}
		return cnt;
	}
	
	static boolean isPassed() {
		for(int x = 0; x < W; ++x) {
			boolean pass = false;
			int prev = -1, len = 0;
			for(int y = 0; y < D; ++y) {
				int cur = (cells[y] >> x) & 1;
				if(prev == cur) {
					++len;
				} else {
					prev = cur;
					len = 1;
				}
				
				if(len >= K) {
					pass = true;
					break;
				}
			}
			if(!pass) return false;
		}
		return true;
	}
	
	static void eachSubset(int idx, int cnt) {
		if(min < cnt) return; // 가지치기: 최솟값보다 더 투여할 경우 중단
		
		if(idx == D) {
			if(isPassed()) min = Math.min(min, cnt);
			return;
		}
		
		int cached = cells[idx];
		eachSubset(idx + 1, cnt); // 투여 X
		cells[idx] = A;
		eachSubset(idx + 1, cnt + 1); // 약물 A 투여
		cells[idx] = B;
		eachSubset(idx + 1, cnt + 1); // 약물 B 투여
		cells[idx] = cached; // 복원
	}
	
	static int solution() {
		/*
		 *  D = 3 ~ 13
		 *  W = 1 ~ 20
		 *  경우의 수: 2^D = 2^13 = 8,000
		 *  단위 시간 복잡도: DW = 260
		 *  => 2,080,000
		 */
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
				cells[y] = Integer.parseInt(br.readLine().replaceAll(" ", ""), 2);
			}
			sb.append("#").append(t).append(" ").append(solution()).append("\n");
		}
		System.out.print(sb);
	}
}

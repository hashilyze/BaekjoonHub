import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	// variables
	static int N, D;
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		D = readInt(); // 윈도우의 최대 크기
		
		long max = Long.MIN_VALUE;
		Deque<long[]> deq = new ArrayDeque<long[]>();
		for(int i = 0; i < N; ++i) {			
			// 슬라이드: 윈도우가 오른쪽으로 이동하면서 더이상 윈도우에 포함되지 않는 원소 제거
			while(!deq.isEmpty() && deq.peekFirst()[1] < i - D) deq.pollFirst();
			
			// 값 생성: 징검다리 i를 마지막으로 하는 최대값 계산
			int val = readInt();
			// 현위치에서 시작하거나, 거리가 D이하인 징검다리에서 넘어옴
			long rangeMax = val + Math.max(0, (deq.isEmpty() ? 0 : deq.peekFirst()[0])); 
			
			// 저장: 윈도우에 저장되는 마지막 값을 기준으로 단조 감소하도록 함
			while(!deq.isEmpty() && deq.peekLast()[0] < rangeMax) deq.pollLast();
			deq.offerLast(new long[] {rangeMax, i});
			
			max = Math.max(max, rangeMax);
		}
		System.out.print(max);
	}
	
	// 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0, s = 1;
		while((c = System.in.read()) <= 0x20);
		if(c == '-') {
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n * s;
	}
}
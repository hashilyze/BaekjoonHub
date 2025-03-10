import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	static PriorityQueue<Integer> leftHeap = new PriorityQueue<>(Collections.reverseOrder());
	static PriorityQueue<Integer> rightHeap = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		int T = readInt();
		while(T-- > 0) {
			// 힙 초기화
			leftHeap.clear(); // 중앙값보다 작은 값을 저장
			rightHeap.clear(); // 중앙값과 중앙값보다 큰 값을 저장
			
			int N = readInt();
			sb.append((N >> 1) + 1).append("\n"); // 홀수번째 마다 중앙값을 출력하므로 N이하의 홀수 개수
			
			for(int i = 1; i <= N; ++i) {
				// 새로운 값을 두 힙 중 하나에 저장
				int val = readInt();
				if(leftHeap.isEmpty() || leftHeap.peek() < val) rightHeap.offer(val);
				else leftHeap.offer(val);
				// 힙 재분배
				while(leftHeap.size() + 1 < rightHeap.size()) leftHeap.offer(rightHeap.poll());
				while(leftHeap.size() + 1 > rightHeap.size()) rightHeap.offer(leftHeap.poll());
				
				// 중앙값 출력
				if(i % 2 == 1) {
					sb.append(rightHeap.peek())
						.append(i % 20 == 0 ? "\n" : " ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	// 부호없는 정수 읽기
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
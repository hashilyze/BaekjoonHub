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
	static int N;
	static int[] commands = new int[1_000_000];
	static Deque<Integer> deq = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = N - 1; i >= 0; --i) commands[i] = readInt();
		for(int i = 1; i <= N; ++i) { // 가장 마지막 상태부터 역으로 명령을 수행해 상태를 되돌림
			int cmd = commands[i - 1];
			
			if(cmd == 1) {
				deq.offerFirst(i);
			} else if(cmd == 2) {
				int tmp = deq.pollFirst();
				deq.offerFirst(i);
				deq.offerFirst(tmp);
			} else { // cmd == 3
				deq.offerLast(i);
			}
		}
		
		while(!deq.isEmpty()) sb.append(deq.pollFirst()).append(" ");
		System.out.println(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}
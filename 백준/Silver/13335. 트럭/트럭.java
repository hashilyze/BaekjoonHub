import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static int N, W, L;
	static Deque<Integer> trucks = new ArrayDeque<Integer>();
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken()); // 다리의 길이 = 다리를 건너는 데 걸리는 시간
		L = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			trucks.offerLast(Integer.parseInt(st.nextToken()));
		}
		
		int currentTime = 0;
		int leftWeight = L;
		Deque<int[]> bridge = new ArrayDeque<int[]>(); // <트럭의 하중, 트럭이 나가는 시간>
		while(!bridge.isEmpty() || !trucks.isEmpty()) {
			++currentTime;
			// 나갈 수 있는 트럭이 있으면 다리에서 제거
			if(!bridge.isEmpty() && bridge.peekFirst()[1] <= currentTime) {
				leftWeight += bridge.pollFirst()[0];
			}
			// 들어올 수 있는 트럭이 있으면 다리에 추가
			if(!trucks.isEmpty() && leftWeight >= trucks.peekFirst()) {
				bridge.offer(new int[] {trucks.peekFirst(), currentTime + W});
				leftWeight -= trucks.pollFirst();
			}
			
		}
		bw.append(""+currentTime).flush();
	}
}
import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// types
	static class Node{
		long value;
		int index;
		
		public Node() {	}
		public Node(long value, int index) {
			this.value = value;
			this.index = index;
		}
	};
	// constants
	// variables
	static int N;
	static Node[] arr;
	
	
	static long[] solution() {
		for(int i = 1; i <= N; ++i) arr[i].value += arr[i - 1].value; // 누적합
		Arrays.sort(arr, (lhs, rhs)->{
			if(lhs.value == rhs.value) return 0;
			return lhs.value < rhs.value ? -1 : 1;
		});

		int min = 1;
		for(int i = 2; i <= N; ++i) {
			long minVal = arr[min].value - arr[min - 1].value;
			long val = arr[i].value - arr[i - 1].value; 
			if(Math.abs(val) < Math.abs(minVal)) {
				min = i;
			}
		}
		
		Node from = arr[min - 1], to = arr[min];
		if(arr[min - 1].index > arr[min].index) {
			Node tmp = from; from = to; to = tmp;
		}
		return new long[] {
			to.value - from.value,
			from.index + 1,
			to.index
		};
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new Node[N + 1];
		
		st = new StringTokenizer(br.readLine());
		arr[0] = new Node(0, 0);
		for(int i = 1; i <= N; ++i) {	
			arr[i] = new Node(Long.parseLong(st.nextToken()), i);
		}
		
		long[] result = solution(); 
		bw.append(result[0] + "\n")
			.append(result[1] + " " + result[2])
			.flush();
	}
}
	
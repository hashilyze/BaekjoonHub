import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) arr[i] = Integer.parseInt(st.nextToken());
		
		List<Integer> li = new ArrayList<Integer>(N);
		li.add(arr[0]);
		for(int i = 1; i < N; ++i) {
			if(li.get(li.size() - 1) < arr[i]) {
				li.add(arr[i]);
			} else {
				int pos = Collections.binarySearch(li, arr[i]);
				if(pos < 0) pos = -pos - 1;
				li.set(pos, arr[i]);
			}
		}
		bw.append("" + li.size()).flush();
	}
}
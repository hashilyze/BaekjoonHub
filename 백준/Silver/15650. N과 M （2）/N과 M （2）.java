import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int N, M;
	static List<Integer> li = new ArrayList<>();
	
	static void nextCombination(int left, int idx) throws IOException {
		if(left == 0) {
			for(int e : li) bw.append(e + " ");
			bw.append("\n");
			return;
		}
		for(int i = idx; i < N - left + 1; ++i) {
			li.add(i + 1);
			nextCombination(left - 1, i + 1);
			li.remove(li.size() - 1);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nextCombination(M, 0);
		bw.flush();
	}
}

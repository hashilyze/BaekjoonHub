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
		for(int i = 0; i < N; ++i) arr[i] = Integer.parseInt(st.nextToken());
		
		int cnt = 0;
		for(int i = 0; i < 4; ++i) {
			int lower = 0;
			for(int j = 0; j < N; ++j) {
				if(lower < arr[j]) {
					lower = arr[j];
					arr[j] = 0;
					++cnt;
				}
			}
		}
		bw.append(cnt == N ? "YES" : "NO").flush();;
	}
}	
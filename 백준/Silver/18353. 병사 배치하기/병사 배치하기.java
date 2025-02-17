import java.io.*;
import java.util.*;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static int N;
	static int[] arr;
	
	
	static int lower_bound(int val, int[] arr, int lo, int hi) {
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
			if(arr[mid] > val) lo = mid + 1;
			else hi = mid;
		}
		return lo;
	}
	
	static int getLDSLength() {
		int size = 0;
		int[] lds = new int[N];
		for(int i = 0; i < N; ++i) {
			int at = lower_bound(arr[i], lds, 0, size);
			lds[at] = arr[i];
			if(at == size) ++size;
		}
		return size;
	}
	static int solution() {
		return N - getLDSLength();
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		bw.append(solution()+"").flush();
	}

}

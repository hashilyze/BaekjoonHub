import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(br.readLine());
		
		List<Integer> vertical = new ArrayList<Integer>(N); // 세로로 자르는 위치
		List<Integer> horizontal = new ArrayList<Integer>(N); // 가로로 자르는 위치
		vertical.add(0); vertical.add(W);
		horizontal.add(0); horizontal.add(H);
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			
			if(dir == 0) {
				horizontal.add(point);
			} else { // dir == 1
				vertical.add(point);
			}
		}
		Collections.sort(vertical);
		Collections.sort(horizontal);
		
		int maxW = vertical.get(1) - vertical.get(0);
		for(int i = 2; i < vertical.size(); ++i) {
			int w = vertical.get(i) - vertical.get(i - 1);
			maxW = Math.max(maxW, w);
		}
		
		int maxH = horizontal.get(1) - horizontal.get(0);
		for(int i = 2; i < horizontal.size(); ++i) {
			int h = horizontal.get(i) - horizontal.get(i - 1);
			maxH = Math.max(maxH, h);
		}
		
		bw.append(maxH * maxW + "").flush();
	}
}
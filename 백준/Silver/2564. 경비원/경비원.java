import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	
	static final int NORTH = 1, SOUTH = 2, WEST = 3, EAST = 4;
	
	static int W, H, N;
	static int[][] locations;
	static int[] source;
	
	public static void main(String[] args) throws IOException {
		// Input
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		locations = new int[N][2];
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			locations[i][0] = Integer.parseInt(st.nextToken());
			locations[i][1] = Integer.parseInt(st.nextToken());
		}
		source = new int[2];
		st = new StringTokenizer(br.readLine());
		source[0] = Integer.parseInt(st.nextToken());
		source[1] = Integer.parseInt(st.nextToken());
		
		// Output
		bw.append(""+solution()).flush();
	}
	
	static int solution() {
		int sum = 0;
		for(int i = 0; i < N; ++i) {
			sum += distance(source, locations[i]);
		}
		return sum;
	}
	
	static int distance(int[] from, int[] to) {
		int dist = 0;
		
		int[] fromPos = locationToPosition(from);
		int[] toPos = locationToPosition(to);
		
		if(from[0] == NORTH && to[0] == SOUTH 
			|| from[0] == SOUTH && to[0] == NORTH) { // 마주보는 변: 북-남
			dist = H + Math.min(fromPos[0] + toPos[0], 2 * W - (fromPos[0] + toPos[0]));
		} else if(from[0] == WEST && to[0] == EAST 
			|| from[0] == EAST && to[0] == WEST) { // // 마주보는 변: 서-동
			dist = W + Math.min(fromPos[1] + toPos[1], 2 * H - (fromPos[1] + toPos[1]));
		} else { // 같은 변, 이웃 변
			dist = Math.abs(fromPos[0] - toPos[0])
					+ Math.abs(fromPos[1] - toPos[1]);
		}
		return dist;
	}
	
	static int[] locationToPosition(int[] location) {
		switch(location[0]) {
		case NORTH: return new int[] {location[1], 0};
		case SOUTH: return new int[] {location[1], H};
		case WEST: return new int[] {0, location[1]};
		case EAST: return new int[] {W, location[1]};
		}
		return null;
	}
}
import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// Constants
	static final int[] delta = { 1, 1 };
	// Variables
	static int w, h;
	static int p, q;
	static int t;
	
	static void reflectVertical() { delta[0] *= -1; }
	static void reflectHorizontal() { delta[1] *= -1; }
	
	public static void main(String[] args) throws IOException {
		// Inputs
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		t = Integer.parseInt(br.readLine());
		
		// Solution
		solution();
		bw.append(p + " " + q).flush();
	}
	
	static void solution() {
		if(p == w) reflectVertical();
		if(q == h) reflectHorizontal();
		
		move();
		int sp = p, sq = q, st = t;
		int[] sdelta = delta.clone();
		
		while(t > 0) {
			move();
			
			if(p == sp && q == sq && Arrays.equals(delta, sdelta)) {
				t %= (st - t);
			}
		}
	}
	static void move() {
		int min = t;
		min = Math.min(min, delta[0] > 0 ? w - p : p);
		min = Math.min(min, delta[1] > 0 ? h - q : q);
		p += min * delta[0];
		q += min * delta[1];
		t -= min;
		
		if(p == 0 || p == w) reflectVertical();
		if(q == 0 || q == h) reflectHorizontal();
	}
}

import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// Variables
	static int w, h;
	static int p, q;
	static int t;
	
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
		p = (t + p) % (w << 1);
		q = (t + q) % (h << 1);
		if(w < p) p = (w << 1) - p;
		if(h < q) q = (h << 1) - q;
	}
}

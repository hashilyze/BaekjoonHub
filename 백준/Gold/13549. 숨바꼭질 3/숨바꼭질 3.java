import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// constants
	static final int SIZE = 200_100;
	// variables
	static int N, K;
	static int[] isVisited = new int[SIZE + 1];

	static void solution() {
		Arrays.fill(isVisited, -1);
		Deque<Integer> q = new ArrayDeque<Integer>();
		q.offerLast(N);
		isVisited[N] = 0;

		while (!q.isEmpty()) {
			int x = q.pollFirst();
			if (x == K) return;

			for (int nx : new int[] { x - 1, x + 1, x << 1 }) {
				if (0 <= nx && nx <= SIZE && isVisited[nx] == -1) {
					if (nx == x * 2) {
						q.offerFirst(nx);
						isVisited[nx] = isVisited[x];
					} else {
						q.offerLast(nx);
						isVisited[nx] = isVisited[x] + 1;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		N = readInt();
		K = readInt();
		solution();
		System.out.print(isVisited[K]);
	}

	static int readInt() throws IOException {
		int c, n = 0;
		while ((c = System.in.read()) >= 0x30)
			n = (n << 3) + (n << 1) + (c & 0x0F);
		if (c == '\r')
			System.in.read();
		return n;
	}
}

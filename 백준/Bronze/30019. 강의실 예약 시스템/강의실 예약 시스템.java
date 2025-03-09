import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	// variables
	static int N, M;
	static int[] rooms = new int[200_001];
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		M = readInt();
		for(int i = 0; i < M; ++i) {
			int id = readInt();
			int begin = readInt(), end = readInt();
			if(rooms[id] <= begin) {
				rooms[id] = end;
				sb.append("YES\n");
			} else{
				sb.append("NO\n");
			}
		}
		System.out.print(sb);
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
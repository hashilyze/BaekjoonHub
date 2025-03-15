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
	
	public static void main(String[] args) throws IOException {
		int P3 = readInt(), P4 = readInt(), P0 = readInt();
		int T3 = 0, T4 = 0;
		
		
		T3 = P3 / 3; 
		P3 %= 3; 
		if(P3 > 0) {
			if(P0 < 3 - P3) {
				System.out.println(-1);
				return;
			}
			P0 -= 3 - P3;
			T3 += 1;
		}
 
		T4 = P4 / 4;
		P4 %= 4;
		if(P4 > 0) {
			if(P0 < 4 - P4) {
				System.out.println(-1);
				return;
			}
			P0 -= 4 - P4;
			T4 += 1;
		}
		
		// 3x+4y=P0
		int x = 0;
		while(P0 - 3 * x >=0) {
			if((P0 - 3 * x) % 4 == 0) {
				T3 += x;
				T4 += (P0 - 3 * x) / 4;
				sb.append(T3).append(" ").append(T4);
				System.out.println(sb);
				return;
			}
			++x;
		}
		System.out.println(-1);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) <= 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		return n;
	}
}
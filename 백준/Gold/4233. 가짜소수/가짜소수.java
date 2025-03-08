import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Node{
		int pos;
		long sum;
		Node(int pos, long sum) { this.pos = pos; this.sum = sum; }
	}
	// constants
	// variables
	static int P, A;
	
	static int pow(int b, int e) {
		long ans = 1L;
		long f = b;
		while(e > 0) {
			if((e & 1) == 1) ans = ans * f % P;
			f = f * f % P;
			e >>= 1;
		}
		return (int)ans;
	}
	
	static boolean isPrime(int p) {
		if(p < 2) return false;
		for(int i = 2; i * i <= p; ++i) {
			if(p % i == 0) return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		while(true) {
			P = readInt();
			A = readInt();
			if(P == 0 && A == 0) break;
			
			if(pow(A, P) == A % P && !isPrime(P)) {
				sb.append("yes\n");
			} else {
				sb.append("no\n");
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
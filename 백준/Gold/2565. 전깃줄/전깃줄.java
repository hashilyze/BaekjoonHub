import java.io.*;
import java.util.*;

public class Main {
	// types
	// constants 
	static final int SIZE = 500;
	// variables
	static int N;
	static int[] A = new int[SIZE + 1];
	static int len = 0;
	static int[] buffer = new int[SIZE + 1];
	
	static int lowerBound(int lo, int hi, int key) {
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
			if(buffer[mid] < key) lo = mid + 1; 
			else hi = mid;
		}
		return lo;
	}
	
	static int solution() {
		for(int i = 0; i <= SIZE; ++i) {
			int at = lowerBound(0, len, A[i]);
			buffer[at] = A[i];
			if(at == len) ++len;
		}
		return N - len + 1;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			A[readInt()] = readInt();
		}
		System.out.println(solution());
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}
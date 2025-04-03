import java.io.*;
import java.util.*;

public class Main {
    // IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	
    public static void main(String[] args) throws IOException {
    	int x = readInt(), b = readInt();
    	if(x == 0) {
    		System.out.println(0);
    		return;
    	}
    	if(b > 0) {
    		int s = x < 0 ? -1 : 1;
    		x *= s;
    		while(x != 0) {
    			sb.append(x % b);
    			x /= b;
    		}
    		if(s < 0) sb.append('-');
    	} else {
    		while(x != 0) {
				int div = x / b;
				int mod = x % b; // mod = x - div * b;
				
				if(mod < 0) {
					if(x < 0 && b > 0) {
						--div;
						mod = x - div * b;
					} else {
						++div;
						mod = x - div * b;
					}
				}
				x = div;
				sb.append(mod);
    		}
    	}
    	sb.reverse();
    	System.out.println(sb);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0, s = 1;
    	if((n = System.in.read() & 0x0F) == ('-' & 0x0F)) {
    		s = -1;
    		n = 0;
    	}
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n * s;
    }
}
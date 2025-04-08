import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder(); 
	// types
	// constants 
	// variables
	static int N;
	static int cnt = -1;
	
	
	static boolean dfs(int upper, int length) {
		if(length == 0) { // 기저: 수 생성
			return ++cnt == N;
		}
		
		// 자릿수가 length인 수의 가장 왼쪽 자릿수가 (length - 1) 커야 감소하는 수가 존재 -> 조합
		for(int digit = length - 1; digit < upper; ++digit) { 
			if(dfs(digit, length - 1)) {
				sb.append(digit);
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int len = 1; len <= 10; ++len) {
			if(dfs(10, len)) {
				System.out.print(sb.reverse());
				return;
			}
		}
		System.out.print("-1");
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}
import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder(); 
	// types
	// constants
	static int NUM_TEST = 4;
	static int NUM_COUNTRY = 6;
	// variables
	static int[] wins = new int[NUM_COUNTRY];
	static int[] draws = new int[NUM_COUNTRY];
	static int[] loses = new int[NUM_COUNTRY];
	
	
	static boolean match(int[] items1, int country, int[] items2, int versus) {
		if(items1[country] > 0 && items2[versus] > 0) {
			--items1[country];
			--items2[versus];
			if(eachPermutation(country, versus + 1)) return true;
			++items1[country];
			++items2[versus];
		}
		return false;
	}
	
	static boolean eachPermutation(int country, int versus) {
		if(versus == NUM_COUNTRY) {
			//if(wins[country] != 0 || draws[country] != 0 || loses[country] != 0) return false; // 경기 수 총합이 불일치
			return isValid(country + 1);
		}
		
		for(int v = versus; v < NUM_COUNTRY; ++v) {
			if(v == country) continue;
			if(match(wins, country, loses, v) // 승 
				|| match(draws, country, draws, v) // 무
				|| match(loses, country, wins, v)) { // 패
				return true; // 상대 국가와 승무패를 결정하는 게 가능
			} else {
				return false;
			}
		}
		return false;
	}
	
	static boolean isValid(int country) {
		if(country == NUM_COUNTRY) return true; // 결과 생성 성공
		// 승무패의 수가 비정상적이면 생성 불가능한 것으로 판단
		//if(wins[country] + draws[country] + loses[country] != NUM_COUNTRY - 1 - country) return false;
		// 국가 country의 승무패를 다른 국가에 분배하는 모든 경우의 수(순열)에 대해 결과를 생성할 수 있는 지 탐색
		return eachPermutation(country, country + 1);  
		
	}
	
	static boolean solution() {
		return isValid(0);
	}
	
	public static void main(String[] args) throws IOException {
		for(int t = 0; t < NUM_TEST; ++t) {
			boolean validScore = true;
			for(int i = 0; i < NUM_COUNTRY; ++i) {
				wins[i] = readInt();
				draws[i] = readInt();
				loses[i] = readInt();
				
				if(wins[i] + draws[i] + loses[i] != NUM_COUNTRY - 1) validScore = false;
			}
			sb.append(!validScore || !solution() ? 0 : 1).append(' ');
		}
		System.out.println(sb);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}
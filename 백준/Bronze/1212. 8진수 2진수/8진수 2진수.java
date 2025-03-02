import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {				
		String[] octToBin = new String[128];
		octToBin['0'] = "000";
		octToBin['1'] = "001";
		octToBin['2'] = "010";
		octToBin['3'] = "011";
		octToBin['4'] = "100";
		octToBin['5'] = "101";
		octToBin['6'] = "110";
		octToBin['7'] = "111";
		
		String octString = br.readLine();
		for(char digit : octString.toCharArray()) {
			sb.append(octToBin[digit]);
		}
		int trimSize = sb.indexOf("1");
		if(trimSize < 0) trimSize = sb.length();
		if(trimSize > 0) sb.replace(0, trimSize, "");
		if(sb.length() == 0) sb.append("0");
		System.out.println(sb);
	}
}
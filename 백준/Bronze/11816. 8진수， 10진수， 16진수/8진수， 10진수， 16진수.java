import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		String s = br.readLine();
		if(s.startsWith("0x")) {
			System.out.println(Integer.parseInt(s.substring(2), 16));
		} else if(s.startsWith("0")) {
			System.out.println(Integer.parseInt(s, 8));
		} else {
			System.out.println(s);
		}
	}
}
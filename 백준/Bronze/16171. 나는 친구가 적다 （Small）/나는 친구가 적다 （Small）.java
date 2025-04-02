import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// types
	// constants
	// variables
	
	public static void main(String[] args) throws IOException {
		String text = br.readLine().replaceAll("[0-9]", "");
		String pattern = br.readLine();
		System.out.println(text.contains(pattern) ? 1 : 0);
	}
}
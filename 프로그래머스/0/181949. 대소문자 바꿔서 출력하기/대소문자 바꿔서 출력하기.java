import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String str = br.readLine();
        for(char ch : str.toCharArray()){
            if(Character.isUpperCase(ch)) bw.append(Character.toLowerCase(ch));
            else bw.append(Character.toUpperCase(ch));
        }
        bw.flush();
    }
}
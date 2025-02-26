// 2805. 농작물 수확하기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB

import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static int N; 
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; ++t) {
        	N = Integer.parseInt(br.readLine());
        	
        	int r = N >> 1; // 중점에서의 거리
        	int cx = r, cy = r; // 중점
        	
        	int sum = 0;
        	for(int y = 0; y < N; ++y) {
        		String row = br.readLine();
        		for(int x = 0; x < N; ++x) {
        			int v = row.charAt(x) - '0';
        			// 중점에서 멘허튼 거리로 r 이하인 농작물만 수확
        			if(Math.abs(y - cy) + Math.abs(x - cx) <= r) { 
        				sum += v;
        			}
        		}
        	}
        	
        	sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        System.out.print(sb);
        
    }
}
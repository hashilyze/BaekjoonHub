// 4796. 의석이의 우뚝 선 산
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWS2h6AKBCoDFAVT

import java.io.*;
import java.util.*;

public class Solution {
    static Scanner sc = new Scanner(System.in);
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static final int MAX_N = 50_000;
    
    static int N;
    static int[] H = new int[MAX_N];
    
    
    static int solution() {
    	int cnt = 0;
    	int lcur = 0, tcur = 0, rcur = 0;
    	while(lcur < N - 2) { // lcur: 오름차순의 가장 왼쪽 원소
    		if(H[lcur] < H[lcur + 1]) {  
    			tcur = lcur; // tcur: 오름차순의 가장 오른쪽 원소 | 내림차순의 가장 왼쪽 원소
    			while(tcur < N - 1 && H[tcur] < H[tcur + 1]) { 
    				++tcur;
    			}
    			rcur = tcur; // rcur: 내림차순의 가장 오른쪽 원소
    			while(rcur < N - 1 && H[rcur] > H[rcur + 1]) {
    				++rcur;
    			}
    			cnt += (tcur - lcur) * (rcur - tcur);
    			lcur = rcur;
    		} else {
    			++lcur;
    		}
    	}
    	return cnt;
    }
    
    public static void main(String[] args) {
        int T = sc.nextInt();
        for(int t = 1; t <= T; ++t) {
        	N = sc.nextInt();
        	for(int i = 0; i < N; ++i) H[i] = sc.nextInt();
        	sb.append("#").append(t).append(" ").append(solution()).append("\n");
        }        
        System.out.print(sb);
    }
}
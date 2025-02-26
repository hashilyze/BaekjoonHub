// 1873. 상호의 배틀필드
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc

import java.io.*;
import java.util.*;


public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static final char 
    		PLANE = '.',
    		BRICK = '*',
    		METAL = '#',
    		WATER = '-';
    static final String TANK_SET = "^v<>";
    static final String CMD_SET = "UDLRS";
    static final int[][] DELTA = {
    	{0, -1},
    	{0, +1},
    	{-1, 0},
    	{+1, 0},
    };
    
    static final int MAX_SIZE = 20;
    
    static int H, W, N;
    static char[][] map = new char[MAX_SIZE][];
    static char[] commands;
    static int tx, ty, td; // 전차의 좌표와 방향 표현
    
    
    static boolean isInOfRange(int y, int x) { return 0 <= y && y < H && 0 <= x && x < W; }
    
    static void shoot() {
    	int sx = tx; // 포탄의 좌표
    	int sy = ty;
    	
    	while(isInOfRange(sy, sx)) {
    		if(map[sy][sx] == METAL) { // 강철 벽
    			return;
    		} else if(map[sy][sx] == BRICK) { // 벽돌 벽: 충돌하면 부숴짐 => 평원으로 변경
    			map[sy][sx] = PLANE;
    			return;
    		}
    		
    		sx += DELTA[td][0];
    		sy += DELTA[td][1];
    	}
    }
    
    static void move(int dir) {
    	int nx = tx + DELTA[dir][0];
    	int ny = ty + DELTA[dir][1];
    	
    	if(isInOfRange(ny, nx) /*지도 내의 좌표인지 확인*/
    		&& map[ny][nx] == PLANE /*이동 가능한 지역인지 확인 (평원만 가능)*/
    		) {
    		tx = nx;
        	ty = ny;
    	} 
    	td = dir; // 이동하려는 방향으로 회전
    }
    
    static void solution() {
    	for(char cmd : commands) {
    		int idx = CMD_SET.indexOf(cmd);
    		if(idx == 4) { // 발사
    			shoot();
    		} else { // 이동
    			move(idx);
    		}
    	}
    }
    
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; ++t) {
        	// Inputs
        	st = new StringTokenizer(br.readLine());
        	H = Integer.parseInt(st.nextToken());
        	W = Integer.parseInt(st.nextToken());
        	
        	td = -1;
        	for(int y = 0; y < H; ++y) {
        		String row = br.readLine();
        		map[y] = row.toCharArray();
        		for(int x = 0; x < W; ++x) {
        			if(td == -1) { // 탱크 찾기
        				td = TANK_SET.indexOf(map[y][x]); // 탱크의 방향에 대한 인덱스 반환 
            			if(td != -1) {
            				tx = x;
            				ty = y;
            				
            				map[ty][tx] = PLANE; // 탱크가 이동하는 동안 지도에서 지움
            			}
        			}
        		}
    		}
        	N = Integer.parseInt(br.readLine());
        	commands = br.readLine().toCharArray();

        	// Solution
        	solution();
        	map[ty][tx] = TANK_SET.charAt(td); // 이동을 마친 후 지도에 다시 표시
        	
        	// Outputs
        	sb.append("#").append(t).append(" ");
        	for(int y = 0; y < H; ++y) {
        		for(int x = 0; x < W; ++x) {
        			sb.append(map[y][x]);
        		}
        		sb.append("\n");
        	}
        }
        System.out.print(sb);
    }
}
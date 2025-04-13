import java.io.*;
import java.util.*;

public class Main {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 2048;
	// variables
	static int N, M;
	static int[][] segment = new int[(SIZE + 1) << 1][(SIZE + 1) << 1];
	
	
	static void initImos() {
		for(int y = SIZE; y <= SIZE*2; ++y) {
			for(int x = SIZE+1; x <= SIZE*2; ++x) {
				segment[y][x] += segment[y][x-1];
			}
		}
		for(int x = SIZE; x <= SIZE*2; ++x) {
			for(int y = SIZE+1; y <= SIZE*2; ++y) {	
				segment[y][x] += segment[y-1][x];
			}
		}
	}
	
	static int initSegmentX(int rangeY, int rangeLx, int rangeRx, int indexY, int indexX) {
		if(rangeLx == rangeRx) return segment[indexY][indexX];
		int mid = (rangeLx + rangeRx) >> 1;
		int lhs = initSegmentX(rangeY, rangeLx, mid, indexY, indexX*2);
		int rhs = initSegmentX(rangeY, mid+1, rangeRx, indexY, indexX*2+1);
		return segment[indexY][indexX] = Math.max(lhs, rhs);
	}
	
	static void initSegmentY(int rangeLy, int rangeRy, int indexY) {
		if(rangeLy == rangeRy) {
			initSegmentX(rangeLy, 1, SIZE, indexY, 1);
			return;
		}
		int mid = (rangeLy + rangeRy) >> 1;
		initSegmentY(rangeLy, mid, indexY*2);
		initSegmentY(mid+1, rangeRy, indexY*2+1);
		
		for(int x = 1; x < segment[indexY].length; ++x) {
			segment[indexY][x] = Math.max(segment[indexY*2][x], segment[indexY*2+1][x]);
		}
	}
	
	static void initSegment() {
		for(int y = SIZE; y < SIZE*2; ++y) {
			for(int x = SIZE-1; x >= 1; --x) {
				segment[y][x] = Math.max(segment[y][x*2], segment[y][x*2+1]);
			}
		}
		for(int y = SIZE-1; y >= 1; --y) {
			for(int x = 1; x < SIZE*2; ++x) {
				segment[y][x] = Math.max(segment[y*2][x], segment[y*2+1][x]);
			}
		}
	}
	
	static int querySegmentX(int queryLx, int queryRx, int rangeLx, int rangeRx, int indexY, int indexX) {
		if(rangeRx < queryLx || queryRx < rangeLx) return 0;
		if(queryLx <= rangeLx && rangeRx <= queryRx) return segment[indexY][indexX];
		
		int mid = (rangeLx + rangeRx) >> 1;
		int lhs = querySegmentX(queryLx, queryRx, rangeLx, mid, indexY, indexX*2);
		int rhs = querySegmentX(queryLx, queryRx, mid+1, rangeRx, indexY, indexX*2+1);
		return Math.max(lhs, rhs);
	}
	static int querySegmentY(int queryLy, int queryRy, int queryLx, int queryRx, int rangeLy, int rangeRy, int indexY) {
		if(rangeRy < queryLy || queryRy < rangeLy) return 0;
		if(queryLy <= rangeLy && rangeRy <= queryRy) return querySegmentX(queryLx, queryRx, 1, SIZE, indexY, 1);
		
		int mid = (rangeLy + rangeRy) >> 1;
		int lhs = querySegmentY(queryLy, queryRy, queryLx, queryRx, rangeLy, mid, indexY*2);
		int rhs = querySegmentY(queryLy, queryRy, queryLx, queryRx, mid+1, rangeRy, indexY*2+1);
		return Math.max(lhs, rhs);
	}
	
	
	public static void main(String[] args) throws IOException {
		N = readInt(); M = readInt();
		while(N-- > 0) {
			int y1 = readInt(), x1 = readInt(), y2 = readInt(), x2 = readInt();
			++segment[SIZE+y1][SIZE+x1];
			--segment[SIZE+y1][SIZE+x2];
			--segment[SIZE+y2][SIZE+x1];
			++segment[SIZE+y2][SIZE+x2];
		}
		initImos();
		//initSegment();
		initSegmentY(1, SIZE, 1);
		
		while(M-- > 0) {
			int y1 = readInt(), x1 = readInt(), y2 = readInt(), x2 = readInt();
			sb.append(querySegmentY(y1+1, y2, x1+1, x2, 1, SIZE, 1)).append("\n");
		}
		System.out.println(sb);
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}
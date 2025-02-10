import java.io.*;
import java.util.*;

public class Solution {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	static final int[][] delta = new int[][]{
			{ 0, -1},
			{ 0, +1},
			{-1,  0},
			{+1,  0}
	};
	// Variables
	static int T;
	static int N, M, K;
	

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; ++t) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			List<Cell> cells = new LinkedList<Cell>();
			for(int i = 0; i < K; ++i) {
				Cell cell = new Cell();
				st = new StringTokenizer(br.readLine());
				cell.setPosY(Integer.parseInt(st.nextToken()));
				cell.setPosX(Integer.parseInt(st.nextToken()));
				
				cell.setSize(Integer.parseInt(st.nextToken()));
				
				int[] dir = delta[Integer.parseInt(st.nextToken()) - 1];
				cell.setDirY(dir[1]);
				cell.setDirX(dir[0]);
				
				cells.add(cell);
			}
			int ans = solution(N, M, K, cells);
			bw.append("#" + t + " " + ans + "\n");
		}
		bw.flush();
	}
	
	static int solution(int N, int M, int K, List<Cell> cells) {
		Cell[][] gridOrigin = new Cell[N][N];
		Cell[][] gridNext = new Cell[N][N];
		for(int y = 0; y < N; ++y) {
			for(int x = 0; x < N; ++x) {
				gridOrigin[y][x] = gridNext[y][x] = null;
			}
		}
		for(Cell cell : cells) {
			gridOrigin[cell.getPosY()][cell.getPosX()] = cell;
			
		}
		cells.sort((lhs, rhs)-> rhs.getSize() - lhs.getSize());
		log(gridOrigin);
		while(M-- > 0) {
			Iterator<Cell> iter = cells.iterator();
			while(iter.hasNext()) {
				Cell cell = iter.next();
				int y = cell.getPosY();
				int x = cell.getPosX();
				gridOrigin[y][x] = null;
				
				int ny = y + cell.getDirY();
				int nx = x + cell.getDirX();
				
				if(isBoundary(ny, nx)) { // 경계에 위치하면 절반 감소 또는 제거
					killCell(cell);
					if(cell.getSize() == 0) {
						iter.remove();
					} else {
						cell.setPosY(ny);
						cell.setPosX(nx);
						gridNext[ny][nx] = cell;
					}
				} else if(gridNext[ny][nx] != null) { // 다른 미생물과 합쳐지면 제거
					mergeCells(cell, gridNext[ny][nx]);
					iter.remove();
				} else {
					cell.setPosY(ny);
					cell.setPosX(nx);
					gridNext[ny][nx] = cell;
				}
			}
			
			// swap
			{
				Cell[][] tmp = gridOrigin;
				gridOrigin = gridNext;
				gridNext = tmp;
			}
			log(gridOrigin);
			cells.sort((lhs, rhs)-> rhs.getSize() - lhs.getSize());
		}
		
		int sum = 0;
		for(Cell cell : cells) {
			sum += cell.getSize();
		}
		return sum;
	}
	
	
	@SuppressWarnings("unused")
	static void log(Cell[][] grid) {
		if(false) {
			System.out.println("=== Log ===");
			for(int y = 0; y < N; ++y) {
				for(int x = 0; x < N; ++x) {
					System.out.printf("%3d ", grid[y][x] != null ? grid[y][x].getSize() : 0);
				}
				System.out.println();
			}
		}
	}
	
	static boolean isBoundary(int y, int x) { return y == 0 || y == N - 1 || x == 0 || x == N - 1; }
	static boolean killCell(Cell cell) {
		cell.setSize(cell.getSize() / 2);
		if(cell.getSize() == 0) return true;
		
		cell.setDirX(cell.getDirX() * -1);
		cell.setDirY(cell.getDirY() * -1);
		return false;
	}
	static void mergeCells(Cell from, Cell to) {
		if(from.getSize() > to.getSize()){
			to.setDirY(from.getDirY());
			to.setDirX(from.getDirX());
		}
		to.setSize(to.getSize() + from.getSize());
	}
	
	
	static class Cell{
		private int posY, posX;
		private int dirY, dirX;
		private int size;
		
		
		public int getPosY() { return posY;}
		public void setPosY(int posY) { this.posY = posY; }
		public int getPosX() { return posX; }
		public void setPosX(int posX) { this.posX = posX; }
		
		public int getDirY() { return dirY; }
		public void setDirY(int dirY) { this.dirY = dirY; }
		public int getDirX() { return dirX; }
		public void setDirX(int dirX) { this.dirX = dirX; }
		
		public int getSize() { return size; }
		public void setSize(int size) { this.size = size; }
	}
}

import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Item implements Comparable<Item> {
		int m, v;
		
		Item() { }
		Item(int m, int v) { this.m = m; this.v = v; }
		
		@Override
		public int compareTo(Item other) { // 무게의 오름차; 가격의 내림차
			if(this.m != other.m) return this.m - other.m;
			return other.v - this.v;
		}
	}
	// constants
	static final int SIZE = 300_000;
	// variables
	static int N, K;
	static Item[] items = new Item[SIZE];
	static int[] bags = new int[SIZE];
	
	
	static long solution() {
		/**
		 * 1. 가장 작은 가방부터 보석을 넣을 시도를 한다.
		 * 2. 가방에 들어갈 보석은 가방의 적재량 이면서 가치가 가장 높은 보석이다.
		 * 3. 모든 가방에 대해 반복
		 */
		long sum = 0;
		int j = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>((lhs, rhs)->rhs - lhs); // bags[i]의 적재량 이하의 보석들의 가치가 저장됨
		for(int i = 0; i < K; ++i) {
			while(j < N && items[j].m <= bags[i]) {
				pq.offer(items[j++].v);
			}
			if(!pq.isEmpty()) {
				sum += pq.poll();
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		K = readInt();
		
		for(int i = 0; i < N; ++i) items[i] = new Item(readInt(), readInt());
		for(int i = 0; i < K; ++i) bags[i] = readInt();
		
		Arrays.sort(items, 0, N);
		Arrays.sort(bags, 0, K);
		
		System.out.println(solution());
	}
	
	// 부호없는 정수 읽기
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) < 0x20);
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n;
	}
}
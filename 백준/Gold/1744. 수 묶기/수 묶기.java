import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	static class Lecture implements Comparable<Lecture>{
		int type;
		int time;
		
		Lecture(){ }
		Lecture(int type, int time){ this.type = type; this.time = time; }
		
		@Override
		public int compareTo(Lecture other) { // 시간의 오름차순; 유형 순 정렬
			if(this.time != other.time) return this.time - other.time;
			return this.type - other.type;
		}
	};
	// constants
	static final int SIZE = 50;
	// variables
	static int N;
	static List<Integer> A = new ArrayList<Integer>();
	static List<Integer> B = new ArrayList<Integer>();
	static int C = 0;
	
	static int solution() {
		/*
		 * [왼쪽부터 두개씩 곱하기: 음수 | 0][더하기: 1][오른쪽부터 순서대로 곱하기: 2 이상의 양수]
		 */
		Collections.sort(A, (lhs, rhs)->rhs-lhs);
		Collections.sort(B);
		int sum = 0;
		
		int i = 0;
		while(i < A.size()) {
			if(i < A.size() - 1) {
				sum += A.get(i) * A.get(i + 1);
				i += 2;
			} else {
				sum += A.get(i);
				i += 1;
			}
		}
		i = 0;
		while(i < B.size()) {
			if(i < B.size() - 1) {
				sum += B.get(i) * B.get(i + 1);
				i += 2;
			} else {
				sum += B.get(i);
				i += 1;
			}
		}
		sum += C;
		return sum;
	}
	public static void main(String[] args) throws IOException {
		N = readInt();
		for(int i = 0; i < N; ++i) {
			int v = readInt();
			if(v >= 2) A.add(v);
			else if(v == 1) ++C;
			else B.add(v);
		}
		System.out.println(solution());
	}
	
	// 정수 읽기
	static int readInt() throws IOException {
		int c, n = 0, s = 1;
		while((c = System.in.read()) < 0x20);
		if(c == '-') {
			s = -1;
			c = System.in.read();
		}
		n = c & 0x0F;
		while((c = System.in.read()) >= 0x30) {
			n = (n << 3) + (n << 1) + (c & 0x0F);
		}
		return n * s;
	}
}
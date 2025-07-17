import java.io.*;
import java.util.*;

public class Main {

    private static char[][] map;
    private static Queue<int[]> people, fire;
    private static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        people = new ArrayDeque<>();
        fire = new ArrayDeque<>();

        for (int y = 0; y < R; ++y) {
            String s = br.readLine();
            
            for (int x = 0; x < C; ++x) {
                switch (map[y][x] = s.charAt(x)){
                    case 'J': people.offer(new int[]{x, y, 1}); break;
                    case 'F': fire.offer(new int[]{x, y}); break;
                }
            }
        }
        
        int ans = bfs();
        System.out.print(ans <= 0 ? "IMPOSSIBLE" : ans);
    }

    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    private static int bfs(){
        while (!people.isEmpty()){
        	// 사람 이동
            for(int k = 0, size = people.size(); k < size; ++k) {
                int[] cur = people.poll();
                int x = cur[0], y = cur[1], d = cur[2];
                
                if(map[y][x] == 'F') continue;
                if (x == 0 || y == 0 || x == C - 1 || y == R - 1) return d;
                
                for (int i=0; i < 4; i++){
                	int nx = x + dx[i];
                	int ny = y + dy[i];
                    
                	if (nx < 0 || nx >= C || ny < 0 || ny >= R) continue;
                    if (map[ny][nx] != '.') continue;
                    
                    people.offer(new int[]{nx, ny, d + 1});
                    map[ny][nx] = 'J';
                }
            }
            
            // 불 이동
            for(int k = 0, size = fire.size(); k < size; ++k) {
                int[] cur = fire.poll();
                int x = cur[0], y = cur[1];
                
                for (int i=0;i<4;i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    
                    if (nx < 0 || nx >= C || ny < 0 || ny >= R) continue;
                    if (map[ny][nx] == 'F' || map[ny][nx] == '#') continue;

                    fire.offer(new int[]{nx, ny});
                    map[ny][nx] = 'F';
                }
            }
        }

        return -1;
    }
}

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14716_현수막 {
    public static int M, N;
    public static boolean[][] visited;
    public static int[][] map;
    public static int[] dr = {-1,-1,-1,0,0,1,1,1};
    public static int[] dc = {-1,0,1,-1,1,-1,0,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int r = 0; r<M;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        for (int r = 0; r<M;r++){
            for (int c = 0; c<N;c++){
                if (visited[r][c] || map[r][c] != 1) continue;
                DFS(r,c);
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    public static void DFS(int r, int c){
        visited[r][c] = true;

        for (int d = 0; d<8;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(nr < 0 || nr >= M || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] != 1) continue;
            DFS(nr,nc);
        }
    }
}

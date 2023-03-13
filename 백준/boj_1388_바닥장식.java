package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1388_바닥장식 {
    public static int N, M;
    public static char[][] map;
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        for (int r = 0; r<N;r++){
            String input = br.readLine();
            for (int c = 0; c<M;c++){
                map[r][c] = input.charAt(c);
            }
        }

        int ans = 0;
        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                if(visited[r][c]) continue;

                DFS(r,c,map[r][c]);
                ans++;
            }
        }
        System.out.println(ans);

    }

    public static void DFS(int r, int c, char shape){
        visited[r][c] = true;
        if(shape == '-'){
            int nc = c+1;
            if(nc < 0 || nc >=M || map[r][nc] != '-') return;
            DFS(r,nc,shape);
        }else if(shape == '|'){
            int nr = r+1;
            if (nr < 0 || nr >= N || map[nr][c] != '|') return;
            DFS(nr,c,shape);
        }
    }
}

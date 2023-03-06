package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1103_게임 {
    public static int N, M, ans;
    public static char[][] board;
    public static int[][] dp;
    public static boolean isLoop;
    public static boolean[][] visited;
    public static int[] dr = {-1,1,0,0};
    public static int[] dc = {0,0,-1,1};
    public static class Point{
        int r,c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;
        dp = new int[N][M];
        board = new char[N][M];
        visited = new boolean[N][M];
        isLoop = false;

        for (int r = 0; r<N;r++){
            String str = br.readLine();
            for (int c = 0;c<M;c++){
                board[r][c] = str.charAt(c);
            }
        }

        DFS(new Point(0,0),1);

        if(isLoop){
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }


    }


    public static void DFS(Point current, int cnt){

        if(isLoop) return;

        if(cnt > ans) ans = cnt;


        dp[current.r][current.c] = cnt;

        int num = board[current.r][current.c]-'0';

        for (int d = 0; d<4;d++){
            int nr = current.r+(dr[d]*num);
            int nc = current.c+(dc[d]*num);

            if(nr < 0 || nr >= N || nc < 0 || nc >= M || board[nr][nc] == 'H') continue;


            if(visited[nr][nc]){
                isLoop = true;
                return;
            }

            if(dp[nr][nc] > cnt) continue;


           if(!visited[nr][nc]){
               visited[nr][nc] = true;
               DFS(new Point(nr,nc), cnt+1);
               visited[nr][nc] = false;
           }
        }
    }
}

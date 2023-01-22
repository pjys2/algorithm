package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1937_욕심쟁이판다 {
    public static int N,ans;
    public static int[][] map;
    public static int[][] dp;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        dp = new int[N+1][N+1];
        ans = 0;

        for (int r = 1;r<=N;r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 1;r<=N;r++){
            for (int c = 1; c<=N;c++){
                DFS(new Point(r,c));
            }
        }

        for (int r = 1;r<=N;r++){
            for (int c = 1; c<=N;c++){
                if(ans < dp[r][c]){
                    ans = dp[r][c];
                }
            }
        }


        System.out.println(ans);


    }


    public static int DFS(Point current){

        if(dp[current.r][current.c] != 0){
            return dp[current.r][current.c];
        }


        dp[current.r][current.c] = 1;

        for (int d = 0; d<4;d++){
            int nr = current.r+dr[d];
            int nc = current.c+dc[d];
            if (nr >=1 && nr <= N && nc >= 1 && nc <= N && map[current.r][current.c] < map[nr][nc]){
                dp[current.r][current.c] = Math.max(dp[current.r][current.c],DFS(new Point(nr,nc))+1);
            }
        }



        return dp[current.r][current.c];
    }


}

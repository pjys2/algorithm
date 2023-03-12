package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16929_TwoDots {
    public static int N, M;
    public static boolean isLoop;
    public static char[][] map;
    public static boolean[][] visited;
    public static boolean[][] check;
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N+1][M+1];

        for (int r = 1;r<=N;r++){
            String input = br.readLine();
            for (int c = 1;c<=M;c++){
                map[r][c] = input.charAt(c-1);
            }
        }

        visited = new boolean[N+1][M+1];
        check = new boolean[N+1][M+1];
        isLoop = false;
        for (int r = 1;r<=N;r++){
            for (int c = 1;c<=M;c++){
                if (visited[r][c]) continue;
                ;
                DFS(new Point(r,c),null);

                if (isLoop){
                    System.out.println("Yes");
                    return;
                }

            }
        }


        System.out.println("No");


    }


    public static void DFS(Point current, Point before){
        if(isLoop){
            return;
        }

        visited[current.r][current.c] = true;


        for (int d = 0;d <4;d++){
            int nr = current.r + dr[d];
            int nc = current.c + dc[d];

            if (nr < 1 || nr > N || nc < 1 || nc > M || map[current.r][current.c] != map[nr][nc]) continue;
            if (before != null && nr == before.r && nc == before.c) continue;


            if (check[nr][nc]){
                isLoop = true;
                return;
            }

            if(!check[nr][nc]){
                check[nr][nc] = true;
                DFS(new Point(nr,nc),current);
                check[nr][nc] = false;
            }
        }
    }


}

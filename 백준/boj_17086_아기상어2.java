package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17086_아기상어2 {
    public static int N, M;
    public static int[][] map;
    public static int[][] safeDis;
    public static int[] dr = {-1,-1,-1,0,0,1,1,1};
    public static int[] dc = {-1,0,1,-1,1,-1,0,1};
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
        map = new int[N][M];
        safeDis = new int[N][M];
        for (int r = 0; r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<M;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }


        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                if(map[r][c] == 0) continue;
                BFS(new Point(r,c));
            }
        }

        int ans = 0;

        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                if(safeDis[r][c] == 0) continue;
                ans = Math.max(ans,safeDis[r][c]);
            }
        }


//        print();
        System.out.println(ans);

    }

    public static void BFS(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[N][M];
        visited[start.r][start.c] = true;

        int cnt = 1;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                Point current = queue.poll();

                for (int d = 0; d<8;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc] || map[nr][nc] == 1) continue;

                    if(safeDis[nr][nc] == 0 || (safeDis[nr][nc] > cnt)) safeDis[nr][nc] = cnt;
                    else continue;

                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }

            cnt++;
        }

    }
    public static void print(){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                System.out.print(safeDis[r][c]+" ");
            }
            System.out.println();
        }
    }
}

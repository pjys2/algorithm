package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2206_벽부수고이동하기 {
    public static int N, M, ans;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static int[][] map;
    public static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static boolean possible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        ans = Integer.MAX_VALUE;


        for (int r = 1; r<=N;r++){
            String str = br.readLine();
            for (int c = 1; c<=M;c++){
                map[r][c] = str.charAt(c-1)-48;
            }
        }






        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }

    }


    public static void BFS(Point start, int count){
        Queue<Point> queue = new LinkedList<Point>();
        boolean[][] visited = new boolean[N+1][M+1];
        visited[start.r][start.c] = true;
        queue.offer(start);

        loop:while(!queue.isEmpty()){

            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++){
                Point current = queue.poll();


                if(count >= ans) return;

                if(current.r == N && current.c == M && count < ans){
                    ans = count;
                    break loop;
                }

                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];

                    if(nr >= 1 && nr <=N && nc >= 1 && nc <=M && map[nr][nc] == 0 && !visited[nr][nc]){
                        queue.offer(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }
                }

            }
        }
    }

    public static void print(){
        for (int r = 1; r<=N;r++){
            for (int c = 1; c<=M;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}

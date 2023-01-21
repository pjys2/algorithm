package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1743_음식물피하기 {
    public static int N, M, K, ans;
    public static char[][] map;
    public static boolean[][] visited;
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
        K = Integer.parseInt(st.nextToken());
        map = new char[N+1][M+1];
        visited = new boolean[N+1][M+1];
        ans = 0;

        for (int i = 0;i<=N;i++){
            Arrays.fill(map[i],'.');
        }

        for (int i = 1; i<=K;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r][c] = '#';
        }

        for (int r = 1; r<=N;r++){
            for (int c = 1; c<=M;c++){
                if(!visited[r][c] && map[r][c] =='#'){
                    BFS(new Point(r,c));
                }
            }
        }

        System.out.println(ans);
    }

    public static void BFS(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        visited[start.r][start.c] = true;

        int size = 1;



        while(!queue.isEmpty()){
            Point current = queue.poll();

            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];

                if(nr >= 1 && nr <=N && nc >= 1 && nc <= M && !visited[nr][nc] && map[nr][nc]=='#'){
                    size++;
                    queue.offer(new Point(nr,nc));
                    visited[nr][nc] = true;

                }
            }
        }

        ans = Math.max(ans,size);

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

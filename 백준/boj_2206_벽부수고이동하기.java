package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2206_벽부수고이동하기 {
    public static int N, M, ans;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static char[][] map;
    public static class Point{
        int r, c, cnt;
        boolean destroyed;

        public Point(int r, int c, int cnt, boolean destroyed){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.destroyed = destroyed;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N+1][M+1];
        ans = 0;


        for (int r = 1; r<=N;r++){
            String str = br.readLine();
            for (int c = 1; c<=M;c++){
                map[r][c] = str.charAt(c-1);
            }
        }

        ans = BFS(new Point(1,1,1,false));


        System.out.println(ans);

    }


    public static int BFS(Point start){
        Queue<Point> queue = new LinkedList<Point>();
        boolean[][][] visited = new boolean[N+1][M+1][2];
        visited[start.r][start.c][0] = true;
        queue.offer(start);

        while(!queue.isEmpty()){

            Point current = queue.poll();


            if(current.r == N && current.c == M){
                return current.cnt;
            }

            for (int d = 0; d<4;d++) {
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];

                if(nr < 1 || nr > N || nc < 1 || nc > M ) continue;

                if(map[nr][nc] == '0'){
                    if(!current.destroyed && !visited[nr][nc][0]){
                        queue.add(new Point(nr,nc,current.cnt+1, false));
                        visited[nr][nc][0] = true;
                    }else if(current.destroyed && !visited[nr][nc][1]){
                        queue.add(new Point(nr,nc,current.cnt+1,true));
                        visited[nr][nc][1] = true;
                    }
                }else if(map[nr][nc] == '1'){
                    if (!current.destroyed){
                        queue.add(new Point(nr,nc, current.cnt+1, true));
                        visited[nr][nc][1] = true;
                    }
                }
            }

        }

        return -1;
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

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16933_벽부수고이동하기3  {
    public static int N, M, K;
    public static int[][] map, min;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point{
        int r, c, cnt,dist;
        boolean isDay;
        public Point(int r, int c,int cnt,int dist, boolean isDay){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dist = dist;
            this.isDay = isDay;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        min = new int[N+1][M+1];

        for (int r = 1; r<=N;r++){
            String input = br.readLine();
            for (int c = 1; c<=M;c++){
                map[r][c] = input.charAt(c-1) - '0';
            }
        }

        BFS();

    }

    public static void BFS(){
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(1,1,0,1,true));
        boolean[][][][] visited = new boolean[N+1][M+1][K+1][2];
        visited[1][1][0][1] = true;


        while(!queue.isEmpty()){
                Point current = queue.poll();
//                System.out.println("위치 : "+current.r+" "+current.c+" 부신벽 : "+current.cnt+" 낮임? : "+current.isDay);

                if (current.r == N && current.c == M){
                    System.out.println(current.dist);
                    return;
                }


                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];


                    if(nr < 1 || nr > N || nc < 1 || nc > M) continue;



                    if(current.isDay && map[nr][nc] == 1 && current.cnt<K && !visited[nr][nc][current.cnt+1][0]){
                        //낮이고 다음위치가 벽일 때
                        queue.add(new Point(nr,nc,current.cnt+1,current.dist+1,!current.isDay));
                        visited[nr][nc][current.cnt+1][0] = true;
                    }else if(current.isDay && map[nr][nc] == 0 && !visited[nr][nc][current.cnt][0]){
                        //낮이고 다음위치가 벽이 아닐 때
                        queue.add(new Point(nr,nc,current.cnt,current.dist+1,!current.isDay));
                        visited[nr][nc][current.cnt][0] = true;
                    }else if(!current.isDay && map[nr][nc] == 0 && !visited[nr][nc][current.cnt][1]){
                        //밤이고 다음위치가 벽이 아닐 때
                        queue.add(new Point(nr,nc,current.cnt,current.dist+1,!current.isDay));
                        visited[nr][nc][current.cnt][1] = true;
                    }else if (!current.isDay && map[nr][nc] == 1 && !visited[current.r][current.c][current.cnt][1]){
                        queue.add(new Point(current.r,current.c,current.cnt,current.dist+1,!current.isDay));
                        visited[current.r][current.c][current.cnt][1] = true;
                    }
                }


//            System.out.println("---------------------------------");

        }
        System.out.println(-1);
    }
}

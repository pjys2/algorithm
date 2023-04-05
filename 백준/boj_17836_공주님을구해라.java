package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_17836_공주님을구해라 {
    public static int N, M, T;
    public static int[][] map;
    public static int[] dr = {-1,1,0,0};
    public static int[] dc = {0,0,-1,1};
    public static class Point{
        int r, c;
        boolean gram;
        public Point(int r, int c,boolean gram){
            this.r = r;
            this.c = c;
            this.gram = gram;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];

        for (int r = 1;r<=N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=M;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();
    }


    public static void BFS(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1,1,false));
        boolean[][][] visited = new boolean[N+1][M+1][2];
        visited[1][1][0] = true;
        int time = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                Point current = queue.poll();

                if(current.r == N && current.c == M){
                    System.out.println(time);
                    return;
                }

                for (int d = 0; d<4;d++){
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    if(nr < 1 || nr > N || nc < 1 || nc > M ) continue;

                    if(!current.gram && map[nr][nc]==1) continue;

                    if(current.gram){
                        if(visited[nr][nc][1]) continue;
                        queue.add(new Point(nr,nc,true));
                        visited[nr][nc][1] = true;
                    }else{
                        if(visited[nr][nc][0]) continue;
                        if(map[nr][nc] == 2){
                            queue.add(new Point(nr,nc,true));
                            visited[nr][nc][1] = true;
                        }else{
                            queue.add(new Point(nr,nc,false));
                            visited[nr][nc][0] = true;
                        }
                    }

                }
            }


            if(time > T) break;

            time++;
        }

        System.out.println("Fail");
    }
}

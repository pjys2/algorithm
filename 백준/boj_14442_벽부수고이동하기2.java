package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14442_벽부수고이동하기2 {
    public static int N, M, K;
    public static int[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static boolean[][][] visited;
    public static class Point{
        int r, c, brick;
        public Point(int r, int c, int brick){
            this.r = r;
            this.c = c;
            this.brick = brick;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];

        for (int r = 1; r<=N;r++){
            String input = br.readLine();
            for (int c = 1; c<=M;c++){
                map[r][c] = input.charAt(c-1)-'0';
            }
        }

        BFS();
    }

    public static void BFS(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1,1,0));
        visited = new boolean[N+1][M+1][K+1];
        visited[1][1][0] = true;

        int cnt = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s<size;s++){
                Point current = queue.poll();

                if(current.r == N && current.c == M){
                    System.out.println(cnt);
                    return;
                }
                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];

                    if(nr < 1 || nr > N || nc < 1 || nc > M) continue;


                    if (!visited[nr][nc][current.brick] && map[nr][nc] == 0){
                        queue.add(new Point(nr,nc,current.brick));
                        visited[nr][nc][current.brick] = true;
                    }else if(current.brick < K && !visited[nr][nc][current.brick+1] && map[nr][nc] == 1){
                        queue.add(new Point(nr,nc,current.brick+1));
                        visited[nr][nc][current.brick+1] = true;
                    }
                }
            }
            cnt++;
        }


        System.out.println(-1);
    }
}

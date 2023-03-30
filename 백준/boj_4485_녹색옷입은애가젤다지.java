package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_4485_녹색옷입은애가젤다지 {
    public static int N,cnt;
    public static int[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point implements Comparable<Point>{
        int r ,c,len;
        public Point(int r, int c,int len){
            this.r = r;
            this.c = c;
            this.len = len;
        }

        @Override
        public int compareTo(Point o) {
            return this.len - o.len;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cnt = 1;
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            map = new int[N][N];

            for (int r = 0;r<N;r++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c<N;c++){
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();
            cnt++;
        }

    }

    public static void dijkstra(){
        PriorityQueue<Point> pq = new PriorityQueue<>();

        pq.add(new Point(0,0,map[0][0]));
        int[][] distance = new int[N][N];
        for (int i = 0; i<N;i++){
            Arrays.fill(distance[i],100000000);
        }

        while(!pq.isEmpty()){
            Point current = pq.poll();

            for (int d = 0; d<4;d++){
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                if(distance[nr][nc] <= current.len + map[nr][nc]) continue;

                pq.add(new Point(nr,nc,current.len+map[nr][nc]));
                distance[nr][nc] = current.len + map[nr][nc];
            }
        }
//        print(distance);
        System.out.println("Problem "+cnt+": "+distance[N-1][N-1]);

    }

    public static void print(int[][] distance){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<N;c++){
                System.out.print(distance[r][c]+" ");
            }
            System.out.println();
        }
    }
}

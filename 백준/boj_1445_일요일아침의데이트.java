package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1445_일요일아침의데이트 {
    public static int N, M;
    public static int[][] map;
    public static Point start, end;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point implements Comparable<Point>{
        int r, c, g, gs;
        public Point(int r, int c,int g, int gs){
            this.r = r;
            this.c = c;
            this.g = g;
            this.gs = gs;
        }

        @Override
        public int compareTo(Point o) {
            if (this.g == o.g){
                return this.gs - o.gs;
            }

            return this.g - o.g;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int r = 0; r<N;r++){
            String input = br.readLine();
            for (int c = 0; c<M;c++){
                char ch = input.charAt(c);
                if (ch == 'S'){
                    start = new Point(r,c,0,0);
                }else if(ch == 'F'){
                    end = new Point(r,c,0,0);
                }else if (ch == 'g'){
                    map[r][c] = 5;
                    for (int d = 0; d<4;d++){
                        int nr = r+dr[d];
                        int nc = c+dc[d];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                        map[nr][nc]++;
                    }
                }
            }
        }

        dijkstra();
    }

    public static void dijkstra(){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(start);

        int[][] distance = new int[N][M];
        for (int i =0;i<N;i++){
            Arrays.fill(distance[i],999999999);
        }

        distance[start.r][start.c] = 0;

        while(!pq.isEmpty()){
            Point current = pq.poll();
//            System.out.println(current.r+" "+current.c+" "+current.g+" "+current.gs);
            if (current.r == end.r && current.c == end.c){
                System.out.println(current.g+" "+current.gs);
                return;
            }

            if (distance[current.r][current.c] < current.g) continue;

            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;


                if (map[nr][nc] >= 5){
                    if (distance[nr][nc] < current.g + 1) continue;
                    pq.add(new Point(nr,nc,current.g+1, current.gs));
                    distance[nr][nc] = current.g+1;
                }else{
                    if (distance[nr][nc] < current.g) continue;
                    pq.add(new Point(nr,nc,current.g, current.gs + map[nr][nc]));
                    distance[nr][nc] = current.g;
                }



            }
        }
    }

    public static void print(){
        for (int r = 0;r<N;r++){
            for (int c = 0; c<M;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}

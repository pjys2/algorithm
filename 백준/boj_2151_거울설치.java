package 백준;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class boj_2151_거울설치 {
    public static int N;
    public static char[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point implements Comparable<Point>{
        //dir : 방향 , cnt : 설치한 거울의 수
        int r, c, dir, cnt;
        public Point(int r, int c, int dir, int cnt){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        //0이 출발 1이 도착
        Point[] points = new Point[2];
        int idx = 0;
        for (int r = 0;r<N;r++){
            String input = br.readLine();
            for (int c = 0; c<N;c++){
                map[r][c] = input.charAt(c);
                if (map[r][c] == '#'){
                    points[idx] = new Point(r,c,0,0);
                    idx++;
                }
            }
        }

        dijkstra(points);
    }

    public static void dijkstra(Point[] points){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int sr = points[0].r;
        int sc = points[0].c;

        pq.add(new Point(sr,sc,0,0));
        pq.add(new Point(sr,sc,1,0));
        pq.add(new Point(sr,sc,2,0));
        pq.add(new Point(sr,sc,3,0));

        boolean[][][] visited = new boolean[N][N][4];

        while(!pq.isEmpty()){
            Point current = pq.poll();

            visited[current.r][current.c][current.dir] = true;

            if (current.r == points[1].r && current.c == points[1].c){
                System.out.println(current.cnt);
                return;
            }


            //현재위치에 거울이 없다면 직진
            if (map[current.r][current.c] != '!'){
                int nr = current.r+dr[current.dir];
                int nc = current.c+dc[current.dir];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != '*' && !visited[nr][nc][current.dir]){
                    pq.add(new Point(nr,nc,current.dir,current.cnt));
                }
            }else{
                for (int d = 0; d<4;d++){
                    if ((d == 1 && current.dir == 0) || (d == 0 && current.dir == 1) || (d == 2 && current.dir == 3) || (d == 3 && current.dir == 2)) continue;
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    int nCnt = current.cnt;
                    if (current.dir != d) nCnt++;
                    if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != '*' && !visited[nr][nc][d]){
                        pq.add(new Point(nr,nc,d,nCnt));
                    }
                }
            }
        }

//        print(mirror);

    }

    public static void print(int[][] mirror){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<N;c++){
                System.out.print(mirror[r][c]+" ");
            }
            System.out.println();
        }
    }
}

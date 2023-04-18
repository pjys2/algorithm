package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_6087_레이저통신 {
    public static int W,H;
    public static char[][] map;
    public static int[][]log;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static Point[] points;
    public static class Point implements Comparable<Point>{
        int r, c,dir,before,cnt;
        public Point(int r, int c,int dir,int cnt){
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        log = new int[H][W];
        points = new Point[2];

        for (int i = 0;i<H;i++){
            Arrays.fill(log[i],H*W);
        }
        int pIdx = 0;
        for (int r = 0; r<H;r++){
            String input = br.readLine();
            for (int c = 0; c<W;c++){
                map[r][c] = input.charAt(c);
                if (map[r][c] == 'C'){
                    points[pIdx] = new Point(r,c,0,0);
                    pIdx++;
                }
            }
        }

        BFS();
//            print();
//            System.out.println("--------------");

    }

    public static void BFS(){
        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int d = 0; d<4;d++){
            int sr = points[0].r+dr[d];
            int sc = points[0].c+dc[d];

            if(sr < 0 || sr >= H || sc < 0 || sc >= W || map[sr][sc] == '*') continue;

            pq.add(new Point(sr,sc,d,0));
            log[sr][sc] = 1;
        }


        while(!pq.isEmpty()){
            Point current = pq.poll();

            if (current.r == points[1].r && current.c == points[1].c){
                System.out.println(current.cnt);
                return;
            }

            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                int ncnt = current.cnt;

                //맵을 벗어나거나 벽임
                if(nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == '*') continue;


                //진행방향의 반대방향은 불가능
                if ((current.dir == 0 && d == 1) || (current.dir == 1 && d == 0) || (current.dir == 2 && d == 3) || (current.dir == 3 && d == 2)) continue;

                if (current.dir == d){
                    if (log[nr][nc] >= ncnt){
                        pq.add(new Point(nr,nc,d,ncnt));
                        log[nr][nc] = ncnt;
                    }
                }else{
                    if (log[nr][nc] > ncnt+1){
                        pq.add(new Point(nr,nc,d,ncnt+1));
                        log[nr][nc] = ncnt+1;
                    }
                }

            }
        }

    }

    public static void print(){
        for (int r = 0; r<H;r++){
            for (int c = 0; c<W;c++){
                System.out.print(log[r][c]+" ");
            }
            System.out.println();
        }
    }
}

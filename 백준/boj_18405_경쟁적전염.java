package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_18405_경쟁적전염 {
    public static int N, K, S, X, Y;
    public static int[][] map;
    public static PriorityQueue<Point> queue;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point implements Comparable<Point>{
        int r, c, num;
        public Point(int r, int c,int num){
            this.r = r;
            this.c = c;
            this.num = num;
        }

        @Override
        public int compareTo(Point o) {
            return this.num - o.num;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        queue = new PriorityQueue<>();
        for (int r = 1;r<=N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] != 0){
                    queue.add(new Point(r,c,map[r][c]));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        for (int time = 0; time<S;time++){
            BFS();
        }

        System.out.println(map[X][Y]);

    }

    public static void BFS(){
        PriorityQueue<Point> temp = new PriorityQueue<>();
        while(!queue.isEmpty()){

            Point current = queue.poll();
            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                if(nr < 1 || nr > N || nc < 1 || nc > N || map[nr][nc] != 0) continue;
                map[nr][nc] = current.num;
                temp.add(new Point(nr,nc,current.num));

            }
        }

        queue = temp;
    }

    public static void print(){
        for (int r = 1;r<=N;r++){
            for (int c = 1; c<=N;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }

    }
}


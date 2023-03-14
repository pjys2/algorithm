package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_19238_스타트택시 {
    public static int N, M, K;
    public static int[][] map;
    public static Taxi taxi;
    public static class Point{
        int r,c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static class Taxi{
        int r, c;
        Point end;
        int fuel;
        public Taxi(int r, int c, Point end, int fuel){
            this.r = r;
            this.c = c;
            this.end = end;
            this.fuel = fuel;
        }
    }
    public static boolean[][][][] visited;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        visited = new boolean[N+1][N+1][N+1][N+1];
        for (int r = 1; r<=N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());


        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int mr = Integer.parseInt(st.nextToken());
            int mc = Integer.parseInt(st.nextToken());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());

            map[mr][mc] = 2;
        }

        BFS();


    }

    public static void BFS(){
        Queue<Taxi> queue = new LinkedList<>();


    }
}

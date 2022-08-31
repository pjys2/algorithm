package 백준;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15686_치킨배달 {

    public static int N,M;
    public static int[][] map;
    public static List<Point> homeList;
    public static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        int cnt = 0;
        homeList = new ArrayList<Point>();
        for (int r = 1; r <= N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 2){
                    cnt++;
                }else if(map[r][c] == 1){
                    homeList.add(new Point(r,c));
                }
            }
        }


        solve(0,cnt-M);


        print();
    }

    public static void solve(int k, int end){
        if(k == end){
            sumDistance();
            return;
        }

        for (int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                if(map[r][c] == 2){
                    map[r][c] = 0;
                    solve(k+1,end);
                    map[r][c] = 2;
                }
            }
        }
    }

    private static void sumDistance() {
        for(Point home : homeList){
            
        }

    }

    public static void print(){

        for (int r = 1; r <= N; r++){
            for(int c = 1; c <= N; c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }

    }
}

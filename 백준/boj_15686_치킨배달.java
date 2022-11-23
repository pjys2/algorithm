package 백준;

import java.io.*;
import java.sql.Array;
import java.util.*;

public class boj_15686_치킨배달 {

    public static int N,M,ans;
    public static int[][] map;
    public static List<Point> homeList;
    public static List<Point> chickList;
    public static boolean[] open;
    public static int[] dr = {1,-1,0,0};
    public static int[] dc = {0,0,1,-1};
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
        ans = 99999;
        map = new int[N+1][N+1];

        int cnt = 0;
        homeList = new ArrayList<Point>();
        chickList = new ArrayList<Point>();
        for (int r = 1; r <= N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 2){
                    cnt++;
                    chickList.add(new Point(r,c));
                }else if(map[r][c] == 1){
                    homeList.add(new Point(r,c));
                }
            }
        }

        open = new boolean[chickList.size()];
        solve(0,0);

        System.out.println(ans);
//        print();
    }

    public static void solve(int k,int idx){
        if(k == M){
            int sum = 0;
            for(Point home : homeList){
                sum += sumDistance(home);
                if(sum > ans){
                    return;
                }
            }

            if(sum < ans){
                ans = sum;
            }
            return;
        }

        for (int i = idx; i < chickList.size(); i++){
            open[i] = true;
            solve(k+1,i+1);
            open[i] = false;
        }
    }

    private static int sumDistance(Point home) {
        int distance = 0;

            for(int i = 0; i<chickList.size();i++){
                if(open[i]){
                    if(distance == 0){
                        distance = Math.abs(home.r- chickList.get(i).r) + Math.abs(home.c - chickList.get(i).c);
                    }else{
                        distance = Math.min(distance, Math.abs(home.r- chickList.get(i).r) + Math.abs(home.c - chickList.get(i).c));
                    }
                }
            }
        return distance;
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

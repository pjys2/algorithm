package 백준;

import java.io.*;
import java.util.*;

public class boj_15686_치킨배달 {

    public static int N,M,ans;
    public static int[][] map;
    public static List<Point> homeList;
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

        System.out.println(ans);
//        print();
    }

    public static void solve(int k, int end){
        if(k == end){
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

    private static int sumDistance(Point home) {
        int distance = 0;
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(home);
        boolean[][] visited = new boolean[N+1][N+1];
        visited[home.r][home.c] = true;
        loop : while(!queue.isEmpty()){
            int size = queue.size();
            distance++;
            for(int s = 0; s < size; s++){
                Point current = queue.poll();

                for(int d = 0; d<4;d++){
                    int nr = current.r +dr[d];
                    int nc = current.c +dc[d];
                    if(nr >= 1 && nr <=N && nc >= 1 && nc <= N && !visited[nr][nc]){
                        if(map[nr][nc] == 2){
                            break loop;
                        }

                        queue.offer(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }
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

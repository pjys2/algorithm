package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
public class boj_19238_스타트택시 {
    public static int[][] map;
    public static int N, M, K;
    public static Queue<Taxi> queue;
    public static Map<Point,Point> goalMap;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Taxi{
        int r,c,fuel;
        public Taxi(int r, int c, int fuel){
            this.r = r;
            this.c = c;
            this.fuel = fuel;
        }
    }
    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        queue = new LinkedList<>();
        for (int r = 1; r<=N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 1) map[r][c] = -1;
            }
        }

        st = new StringTokenizer(br.readLine());
        int sr = Integer.parseInt(st.nextToken());
        int sc = Integer.parseInt(st.nextToken());
        queue.add(new Taxi(sr,sc,K));
        goalMap = new HashMap<>();

        for (int i = 1; i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            map[r1][c1] = i;
            Point start = new Point(r1,c1);
            Point end = new Point(r2,c2);
            goalMap.put(start,end);
        }

        for (int i = 1; i<=M;i++){
            Point goal = findPassenger();
            findGoal(goal);
        }
    }

    public static Point findPassenger(){
        Point passenger = new Point(0,0);
        Taxi start = queue.peek();
        boolean[][] visited = new boolean[N+1][N+1];
        visited[start.r][start.c] = true;
        int number = M+1;
        int fuel = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0;s<size;s++){
                Taxi current = queue.poll();


                if(map[current.r][current.c] != 0 && number > map[current.r][current.c]){
                    number = map[current.r][current.c];
                    passenger.r = current.r;
                    passenger.c = current.c;
                    fuel = current.fuel;
                }


                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr < 1 || nr > N || nc < 1 || nc > N || visited[nr][nc] || map[nr][nc] == -1) continue;

                    if(current.fuel-1 < 0) continue;

                    queue.add(new Taxi(nr,nc,current.fuel-1));
                    visited[nr][nc] = true;
                }
            }

            if(number != M+1){
                map[passenger.r][passenger.c] = 0;
                queue.clear();
                queue.add(new Taxi(passenger.r, passenger.c, fuel));
                break;
            }
        }


        return goalMap.get(passenger);
    }

    public static void findGoal(Point goal){

    }
}

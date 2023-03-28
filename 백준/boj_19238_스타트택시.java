package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
public class boj_19238_스타트택시 {
    public static int[][] map;
    public static int N, M, fuel,use;
    public static boolean impossible;
    public static Queue<Point> queue;
    public static Map<Point,Point> goalMap;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
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
        fuel = Integer.parseInt(st.nextToken());
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
        queue.add(new Point(sr,sc));
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
            if (impossible || goal.r == 0){
                System.out.println(-1);
                return;
            }


            findGoal(goal);
            if (impossible){
                System.out.println(-1);
                return;
            }
        }

        if (!impossible){
            System.out.println(fuel);
        }
    }

    public static Point findPassenger(){
        Point passenger = new Point(0,0);
        Point start = queue.peek();
        boolean[][] visited = new boolean[N+1][N+1];
        visited[start.r][start.c] = true;
        use = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0;s<size;s++){
                Point current = queue.poll();

                if(map[current.r][current.c] != 0){
                    if(passenger.r == 0 && passenger.c == 0){
                        passenger.r = current.r;
                        passenger.c = current.c;
                    }else if(passenger.r > current.r){
                        passenger.r = current.r;
                        passenger.c = current.c;
                    }else if(passenger.r == current.r && passenger.c > current.c){
                        passenger.r = current.r;
                        passenger.c = current.c;
                    }

                }


                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr < 1 || nr > N || nc < 1 || nc > N || visited[nr][nc] || map[nr][nc] == -1) continue;

                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }

            if(passenger.r != 0 || passenger.c != 0){

                map[passenger.r][passenger.c] = 0;
                queue.clear();
                queue.add(new Point(passenger.r, passenger.c));
                break;
            }

            fuel--;

            if(fuel < 0){
                impossible = true;
                return null;
            }
        }

        if (passenger.r == 0 && passenger.c == 0){
            impossible = true;
            return null;
        }



        Point goal = new Point(0,0);
        for (Point key : goalMap.keySet()){
            if (key.r == passenger.r && key.c == passenger.c){
                goal = goalMap.get(key);
            }
        }


        return goal;
    }

    public static void findGoal(Point goal){
        Point start = queue.peek();
        boolean[][] visited = new boolean[N+1][N+1];
        visited[start.r][start.c] = true;
        use = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0;s<size;s++){
                Point current = queue.poll();

                if(current.r == goal.r && current.c == goal.c){
                    queue.clear();
                    queue.add(new Point(current.r, current.c));
                    fuel = fuel + (use * 2);
                    return;
                }

                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr < 1 || nr > N || nc < 1 || nc > N || visited[nr][nc] || map[nr][nc] == -1) continue;

                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }

            fuel--;
            use++;
            if(fuel < 0){
                impossible = true;
                return;
            }
        }

        impossible = true;
    }
}

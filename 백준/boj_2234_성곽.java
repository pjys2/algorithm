package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2234_성곽 {
    public static int N, M, cnt, ans;
    public static int[][] wall;
    public static int[][] roomNum;
    public static Map<Integer,Integer> ansMap;
    public static int[] dr = {0,-1,0,1};
    public static int[] dc = {-1,0,1,0};
    public static int[] bit = {1,2,4,8};
    public static boolean[][] visited;
    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        wall = new int[M+1][N+1];
        roomNum = new int[M+1][N+1];
        visited = new boolean[M+1][N+1];
        ansMap = new HashMap<>();
        ans = 0;
        for (int r = 1;r<=M;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=N;c++){
                wall[r][c] = Integer.parseInt(st.nextToken());
            }
        }


        cnt = 0;
        for (int r = 1;r<=M;r++){
            for (int c = 1; c<=N;c++){
                if(visited[r][c]) continue;
                cnt++;
                BFS(new Point(r,c));
            }
        }
        System.out.println(cnt);

        System.out.println(ans);

        for (int r = 1;r<=M;r++){
            for (int c = 1; c<=N;c++){
                for (int d = 0; d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr < 1 || nr > M || nc <1 || nc > N) continue;

                    if(roomNum[r][c] == roomNum[nr][nc]) continue;

                    int temp = ansMap.get(roomNum[r][c]) + ansMap.get(roomNum[nr][nc]);
                    ans = Math.max(ans,temp);

                }
            }
        }

        System.out.println(ans);


    }

    public static void BFS(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.r][start.c] = true;

        int roomCnt = 0;
        while(!queue.isEmpty()){
            Point current = queue.poll();
            roomNum[current.r][current.c] = cnt;
            roomCnt++;
            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                if(nr < 1 || nr > M || nc <1 || nc > N || visited[nr][nc]) continue;

                if((wall[current.r][current.c] & bit[d]) == bit[d]) continue;

                queue.add(new Point(nr,nc));
                visited[nr][nc] = true;
            }
        }

        ansMap.put(cnt,roomCnt);
        ans = Math.max(ans,roomCnt);
    }

    public static void print(){
        for (int r = 1;r<=M;r++){
            for (int c = 1; c<=N;c++){
                System.out.print(roomNum[r][c]+" ");
            }
            System.out.println();
        }
    }
}

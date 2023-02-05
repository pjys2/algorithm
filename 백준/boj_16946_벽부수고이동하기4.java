package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16946_벽부수고이동하기4 {
    public static int N, M, count;
    public static int[][] map;
    public static int[][] counts;
    public static int[][] ans;
    public static boolean[][] visited;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
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
        map = new int[N][M];
        counts = new int[N][M];
        ans = new int[N][M];
        visited = new boolean[N][M];

        for (int r = 0; r<N;r++){
            String str = br.readLine();
            for (int c = 0; c<M;c++){
                map[r][c] = str.charAt(c)-'0';
            }
        }

        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                if(map[r][c] != 0 || visited[r][c]) continue;

                BFS(r,c);
            }
        }

        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                if(map[r][c] != 1) continue;

                int sum = 0;
                for (int d = 0; d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(nr >= 0 && nr < N && nc >= 0 && nc < M){
                        sum += counts[nr][nc];
                    }
                }
                sum += 1;

                ans[r][c] = sum;
            }
        }

        print();

    }

    public static int number;
    public static void BFS(int r, int c){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r,c));
        visited[r][c] = true;
        ArrayList<Point> pointList = new ArrayList<>();
        int cnt = 0;
        while(!queue.isEmpty()){
            Point current = queue.poll();

            cnt++;
            pointList.add(new Point(current.r, current.c));
            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                if(nr >= 0 && nr < N && nc >=0 && nc <M && !visited[nr][nc] && map[nr][nc] == 0){
                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }
        }

        for (Point point : pointList){
            counts[point.r][point.c] = cnt;
        }

    }


    public static void print(){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                System.out.print(ans[r][c]);
            }
            System.out.println();
        }
    }
}

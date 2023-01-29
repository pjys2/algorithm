package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16946_벽부수고이동하기4 {
    public static int N, M, count;
    public static int[][] map;
    public static int[][] ans;
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
        ans = new int[N][M];

        for (int r = 0; r<N;r++){
            String str = br.readLine();
            for (int c = 0; c<M;c++){
                map[r][c] = str.charAt(c)-'0';
            }
        }

        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                if(map[r][c] == 0) continue;

                map[r][c] = 0;
                BFS(r,c);
                map[r][c] = 1;
            }
        }

        print();

    }
    public static void BFS(int r, int c){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(r,c));
        boolean[][] visited = new boolean[N][M];
        visited[r][c] = true;

        while(!queue.isEmpty()){
            Point current = queue.poll();
            ans[r][c]++;
            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && map[nr][nc] == 0){
                    visited[nr][nc] = true;
                    queue.add(new Point(nr,nc));
                }
            }
        }

        ans[r][c] = ans[r][c]%10;
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

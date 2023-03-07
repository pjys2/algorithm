package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class boj_2665_미로만들기 {
    public static int N, ans;
    public static int[][] board;
    public static class Point implements Comparable<Point>{
        int r, c,black;
        public Point(int r, int c,int black){
            this.r = r;
            this.c = c;
            this.black = black;
        }


        @Override
        public int compareTo(Point o) {
            return this.black - o.black;
        }
    }
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        ans = N*N-1;
        for (int r = 0; r<N;r++){
            String str = br.readLine();
            for (int c = 0; c<N;c++){
                board[r][c] = str.charAt(c)-'0';
            }
        }

        BFS();
    }

    public static void BFS(){
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(0,0,0));
        boolean[][] visited = new boolean[N][N];

        visited[0][0] = true;

        while(!queue.isEmpty()){

            Point current = queue.poll();

            if(current.r == N-1 && current.c == N-1){
                System.out.println(current.black);
                return;
            }

            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] ) continue;


                if(board[nr][nc] == 1){
                    queue.add(new Point(nr,nc,current.black));
                    visited[nr][nc] = true;
                }else{
                    queue.add(new Point(nr,nc,current.black+1));
                    visited[nr][nc] = true;
                }
            }

        }

    }
}

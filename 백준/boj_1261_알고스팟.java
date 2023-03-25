package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1261_알고스팟 {
    public static int N,M,ans;
    public static int[][] miro;
    public static int[][] min;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point implements Comparable<Point>{
        int r, c,cnt;
        public Point(int r, int c,int cnt){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Point o) {
            return this.cnt - o.cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        miro = new int[M+1][N+1];
        ans = 10000;

        for (int r = 1; r<=M;r++){
            String input = br.readLine();
            for (int c = 1;c<=N;c++){
                miro[r][c] = input.charAt(c-1)-'0';
            }
        }

        BFS(new Point(1,1,0));

        System.out.println(ans);
    }

    public static void BFS(Point start){
        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(start);
        boolean[][] visited = new boolean[M+1][N+1];
        visited[start.r][start.c] = true;

        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                Point current = queue.poll();

                if(current.r == M && current.c == N){
                    ans = Math.min(ans, current.cnt);
                }

                for (int d = 0; d<4;d++){
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];

                    if(nr < 1 || nr > M || nc < 1 || nc > N || visited[nr][nc]) continue;


                    if (miro[nr][nc] == 1){
                        queue.add(new Point(nr,nc,current.cnt+1));
                    }else{
                        queue.add(new Point(nr,nc,current.cnt));
                    }
                    visited[nr][nc] = true;
                }
            }
        }
    }


}

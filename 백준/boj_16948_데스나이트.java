package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16948_데스나이트 {
    public static int N,r1,c1,r2,c2;
    public static int[] dr = {-2,-2,0,0,2,2};
    public static int[] dc = {-1,1,-2,2,-1,1};
    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());



        BFS(new Point(r1,c1));

    }

    public static void BFS(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[N][N];
        visited[start.r][start.c] = true;

        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                Point current = queue.poll();

                if(current.r == r2 && current.c == c2){
                    System.out.println(cnt);
                    return;
                }
                for (int d = 0; d<6;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;

                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }


            }
            cnt++;
        }

        System.out.println(-1);
    }
}

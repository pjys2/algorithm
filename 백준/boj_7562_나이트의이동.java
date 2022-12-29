package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_7562_나이트의이동 {
    public static int[] dr = {-2,-2,-1,1,2,2,1,-1};
    public static int[] dc = {-1,1,2,2,1,-1,-2,-2};
    public static int I;
    public static int[][] map;
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

        int tc = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= tc; t++){
            st = new StringTokenizer(br.readLine());
            I = Integer.parseInt(st.nextToken());
            map = new int[I][I];
            visited = new boolean[I][I];
            st = new StringTokenizer(br.readLine());
            int startR = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken());
            Point start = new Point(startR, startC);

            st = new StringTokenizer(br.readLine());
            int endR = Integer.parseInt(st.nextToken());
            int endC = Integer.parseInt(st.nextToken());
            Point end = new Point(endR,endC);
            BFS(start,end);
        }

    }

    public static void BFS(Point start, Point end){
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(start);
        visited[start.r][start.c] = true;
        int count = 0;
        loop:while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s < size; s++){
                Point current = queue.poll();
                if(current.r == end.r && current.c == end.c){
                    System.out.println(count);
                    break loop;
                }
                for (int d = 0; d<8;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr >= 0 && nr < I && nc >= 0 && nc < I && !visited[nr][nc]){


                        visited[nr][nc] = true;
                        queue.offer(new Point(nr,nc));
                    }
                }
            }
            count++;

        }
    }
}

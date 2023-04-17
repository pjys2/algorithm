package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_14940_쉬운최단거리 {
    public static int n,m;
    public static int[][] map,ans;
    public static Point start;
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

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        ans = new int[n][m];
        for (int r = 0; r<n;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0;c<m;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 2) start = new Point(r,c);
            }
        }

        BFS();

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r<n;r++){
            for (int c = 0;c<m;c++){
                if (map[r][c] == 1 && ans[r][c] == 0){
                    sb.append("-1 ");
                    continue;
                }

                sb.append(ans[r][c]+" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void BFS(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[n][m];
        visited[start.r][start.c] = true;

        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                Point current = queue.poll();
                ans[current.r][current.c] = cnt;
                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] == 0 || visited[nr][nc]) continue;

                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }

            cnt++;
        }

    }
}

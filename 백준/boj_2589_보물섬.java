package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_2589_보물섬 {
    public static int R,C,ans;
    public static char[][] map;
    public static class Point{
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    public static int[] dr = {1,-1,0,0};
    public static int[] dc = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ans = 0;
        map = new char[R][C];

        for (int r = 0; r<R;r++){
            String str = br.readLine();
            for (int c = 0; c<C;c++){
                map[r][c] = str.charAt(c);
            }
        }

        for (int r = 0; r<R;r++){
            for (int c = 0; c<C;c++){
                BFS(new Point(r,c));
            }
        }

        System.out.println(ans);

    }

    public static void BFS(Point start){
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(start);
        boolean[][] visited = new boolean[R][C];
        visited[start.r][start.c] = true;
        int length = 1;
        while(!queue.isEmpty()){
            int size = queue.size();

            for(int s = 0; s < size;s++){
                Point current = queue.poll();
                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];

                    if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc]&& map[nr][nc] =='L'){
                        queue.offer(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }
                }
            }
            if(length > ans){
                ans = length;
            }
            length++;
        }

    }

    public static void print(){
        for (int r = 0; r<R;r++){
            for (int c = 0; c<C;c++){
                System.out.print(map[r][c]+" ");

            }
            System.out.println();
        }
    }
}

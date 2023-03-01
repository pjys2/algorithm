package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_4179_불 {
    public static int R,C;
    public static char[][] map;
    public static Queue<Point> fireList;
    public static Point start;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point{
        int r, c;
        public Point(int r,int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fireList = new LinkedList<>();
        for (int r = 0; r<R;r++){
            String str = br.readLine();
            for (int c = 0;c<C;c++){
                map[r][c] = str.charAt(c);
                if(map[r][c] == 'J'){
                    start = new Point(r,c);
                    map[r][c] = '.';
                }else if(map[r][c] == 'F'){
                    fireList.add(new Point(r,c));
                }

            }
        }



        BFS();


    }

    public static void BFS(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[R][C];
        visited[start.r][start.c] = true;

        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            fireMove();
            for (int s = 0; s<size;s++){
                Point current = queue.poll();

                if(current.r == 0 || current.c == 0 || current.r == R-1 || current.c == C-1){
                    System.out.println(cnt+1);
                    return;
                }
                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];

                    if(nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && map[nr][nc] == '.'){
                        queue.add(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }
                }
            }

            cnt++;
        }

        System.out.println("IMPOSSIBLE");

    }

    public static void fireMove(){

        int size = fireList.size();

        for (int s = 0;s<size;s++){
            Point current = fireList.poll();
            for (int d = 0; d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];
                if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] == '.'){
                    fireList.add(new Point(nr,nc));
                    map[nr][nc] = 'F';
                }
            }
        }


    }
}

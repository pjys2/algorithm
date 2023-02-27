package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_5427_불 {
    public static int T,W,H;
    public static char[][] map;
    public static boolean[][] fire;
    public static boolean[][] visited;
    public static Queue<Point> fireList;
    public static Point start;
    public static int[] dr = {1,-1,0,0};
    public static int[] dc = {0,0,1,-1};
    public static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc<=T;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            fire = new boolean[H][W];
            visited = new boolean[H][W];
            fireList = new LinkedList<>();


            for (int r = 0; r<H;r++){
                String str = br.readLine();
                for (int c = 0; c<W;c++){
                    map[r][c] = str.charAt(c);

                    if(map[r][c] == '*'){
                        fireList.add(new Point(r,c));
                    }else if(map[r][c] == '@'){
                        start = new Point(r,c);
                        map[r][c] = '*';
                    }
                }
            }

            move(start);

        }
    }


    public static void move(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        visited[start.r][start.c] = true;
        int cnt = 0;

        while(!queue.isEmpty()){

            fireCheck();
            int size = queue.size();

            for (int s =0; s<size;s++){
                Point current = queue.poll();

                if(current.r == 0 || current.r == H-1 || current.c == 0 || current.c == W-1){

                    System.out.println(cnt+1);
                    return;
                }

                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];


                    if(nr >=0 && nr < H && nc >= 0 && nc < W && !fire[nr][nc] && map[nr][nc] == '.' && !visited[nr][nc]){

                        queue.add(new Point(nr,nc));
                        visited[nr][nc] = true;
                    }
                }
            }

            cnt++;
        }

        System.out.println("IMPOSSIBLE");

    }

    public static void fireCheck(){

        int size = fireList.size();

        for (int s = 0; s<size;s++){
            Point current = fireList.poll();

            for (int d=0;d<4;d++){
                int nr = current.r+dr[d];
                int nc = current.c+dc[d];


                if(nr >=0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == '.' && !fire[nr][nc]){


                    fireList.add(new Point(nr,nc));
                    fire[nr][nc] = true;
                }
            }
        }
    }


    public static void print(){
        for (int r = 0; r<H;r++){
            for (int c = 0; c<W;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }

    }

    public static void printFire(){
        for (int r = 0; r<H;r++){
            for (int c = 0; c<W;c++){
                System.out.print(fire[r][c]+" ");
            }
            System.out.println();
        }

    }
}

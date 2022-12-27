package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2638_치즈 {
    public static int N,M;
    public static int[][] map;
    public static boolean[][] air;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point{
        int r,c;

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
        for (int r = 0; r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<M;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while(!IsEnd()){
            air = new boolean[N][M];
            findAir(new Point(0,0));
            delete();
            time++;
        }
        System.out.println(time);
    }
    public static void delete(){
        List<Point> delList = new ArrayList<Point>();
        for (int r = 0; r<N;r++){
            int count = 0;
            for (int c = 0; c<M;c++){
                if(map[r][c] != 1) continue;
                for (int d = 0; d<4;d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(map[nr][nc] == 0 && air[nr][nc]) count++;
                }
                if(count >= 2){
                    delList.add(new Point(r,c));
                }
            }
        }

        for (Point point : delList){
            map[point.r][point.c] = 0;
        }
    }

    public static void findAir(Point point){
        air[point.r][point.c] = true;
        for (int d = 0; d<4;d++){
            int nr = point.r+dr[d];
            int nc = point.c+dc[d];
            if(nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0 && !air[nr][nc]){
                findAir(new Point(nr,nc));
            }
        }
    }

    public static boolean IsEnd(){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                if(map[r][c] == 1){
                    return false;
                }
            }
        }

        return true;
    }


    public static void print(){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                System.out.print(map[r][c] +" ");
            }
            System.out.println();
        }
    }
}

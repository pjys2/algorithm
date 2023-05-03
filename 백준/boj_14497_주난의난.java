package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14497_주난의난 {
    public static int N, M;
    public static char[][] map;
    public static Point start,end;
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

        map = new char[N+1][M+1];


        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        start = new Point(x1,y1);
        end = new Point(x2,y2);

        for (int r = 1; r<=N;r++){
            String input = br.readLine();
            for (int c = 1; c<=M;c++){
                map[r][c] = input.charAt(c-1);
            }
        }



    }
}

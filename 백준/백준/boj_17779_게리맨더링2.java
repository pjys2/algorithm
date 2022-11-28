package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17779_게리맨더링2 {

    public static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static int N;
    public static int[][] map;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int r =0; r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c= 0; c<N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }


        System.out.println(N);
        Print();
    }

    public static void Print(){
        for (int r =0; r<N;r++) {
            for (int c= 0; c<N;c++){
                System.out.print(map[r][c] +" ");
            }
            System.out.println();
        }

    }
}

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17837_새로운게임2__v2 {

    public static int N, K;
    public static int[][] map;

    public static class Horse{
        int r,c,dir;

        public Horse(int r, int c, int dir){
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int r = 0; r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}

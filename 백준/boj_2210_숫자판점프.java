package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_2210_숫자판점프 {

    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static int[][] board;

    public static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[5][5];

        for (int i = 0; i<5;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0;j<5;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        Map<Integer,Integer> numList = new HashMap<>();

        for (int r = 0; r<5;r++){
            for (int c = 0; c<5;c++){
                DFS(new Point(r,c), 0);
            }
        }
    }

    public static void DFS(Point point, int cnt){


        for (int d = 0; d<4;d++){
            int nr = point.r+dr[d];
            int nc = point.c+dc[d];
            if(nr >= 0 && nr <5 && nc >= 0 && nc <5){

            }
        }
    }
}

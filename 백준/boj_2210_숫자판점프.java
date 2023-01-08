package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2210_숫자판점프 {

    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[5][5];

        for (int i = 0; i<5;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0;j<5;j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}

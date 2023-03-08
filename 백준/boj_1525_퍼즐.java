package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1525_퍼즐 {
    public static int[][] puzzle;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        puzzle = new int[3][3];

        for (int r = 0 ;r < 3;r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c<3;c++){
                puzzle[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }
}

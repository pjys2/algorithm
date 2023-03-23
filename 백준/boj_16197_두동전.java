package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16197_두동전 {
    public static int N, M;
    public static char[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        board = new char[N][M];

        for (int r = 0; r<N;r++){
            String input = br.readLine() ;
            for (int c = 0; c<M;c++){
                board[r][c] = input.charAt(c);
            }
        }
        print();
    }

    public static void print(){
        for (int r = 0;r<N;r++){
            for (int c = 0; c<M;c++){
                System.out.print(board[r][c]+" ");
            }
            System.out.println();
        }
    }
}

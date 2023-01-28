package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_16946_벽부수고이동하기4 {
    public static int N, M;
    public static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int r = 0; r<N;r++){
            String str = br.readLine();
            for (int c = 0; c<M;c++){
                map[r][c] = Integer.parseInt(str,c);
            }
        }

        print();

    }

    public static void print(){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}

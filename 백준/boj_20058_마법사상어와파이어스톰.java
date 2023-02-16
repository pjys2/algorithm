package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_20058_마법사상어와파이어스톰 {

    public static int N, Q;
    public static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        int size = (int) Math.pow(2,N);
        map = new int[size][size];


        for (int r = 0; r<size;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<size;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }





    }
}

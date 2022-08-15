package 백준;

import java.io.*;
import java.util.*;

public class boj_24513_좀비바이러스 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        for(int i = 0; i< N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j  = 0 ; j< M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        print(map,N,M);

    }

    public static void print(int[][] map,int N,int M){
        for(int i = 0; i< N; i++){
            for(int j  = 0 ; j< M; j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }

}

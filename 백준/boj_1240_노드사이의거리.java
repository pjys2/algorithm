package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1240_노드사이의거리 {
    public static int N, M;
    public static int[][] adjMat;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjMat = new int[N+1][N+1];

        for (int i = 1; i<=N;i++){
            Arrays.fill(adjMat[i],999999999);
        }

        for (int i = 1; i <= N-1;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjMat[A][B] = v;
            adjMat[B][A] = v;
        }


        for (int k = 1; k<=N;k++){
            for (int i = 1; i<=N;i++){
                for (int j = 1; j<=N;j++){
                    if(adjMat[i][j] > adjMat[k][j] + adjMat[i][k]){
                        adjMat[i][j] = adjMat[k][j] + adjMat[i][k];
                    }
                }
            }
        }



        for (int i =0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            System.out.println(adjMat[A][B]);
        }

    }

    public static void print(){
        for (int i =1;i<=N;i++){
            for (int j = 1;j<=N;j++){
                System.out.print(adjMat[i][j]+" ");
            }
            System.out.println();
        }
    }

}

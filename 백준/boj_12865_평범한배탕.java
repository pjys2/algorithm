package 백준;

import java.util.*;
import java.io.*;


public class boj_12865_평범한배탕 {

    public static void main(String[] args) throws IOException {
        int N, K, ans;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] W = new int[N+1];
        int[] V = new int[N+1];

        for(int i = 1; i <= N;i++){
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }


        ans = solve(W,V,N,K);

        System.out.println(ans);
    }

    public static int solve(int[] W,int[] V, int N,int K){

        int[][] data = new int[N+1][K+1];
        for(int k = 1; k <= K; k++){
            for(int i = 1; i <= N; i++){
                data[i][k] = data[i-1][k];
                if(k > W[i]){
                    data[i][k] = Math.max(data[i][k],data[i-1][k-W[i]]+V[i]);
                }
            }
        }


        return data[N][K];
    }

}

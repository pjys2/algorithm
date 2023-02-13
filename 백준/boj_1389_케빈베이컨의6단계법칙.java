package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1389_케빈베이컨의6단계법칙 {
    public static int N,M;
    public static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N+1][N+1];
        for (int i = 1; i<=N;i++){
            for (int j=1;j<=N;j++){
                if (i != j) dist[i][j] = 9999999;
            }
        }

        for (int i =1 ;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            dist[A][B] = 1;
            dist[B][A] = 1;
        }

        for(int m=1; m<=N; m++){
            for(int s=1; s<=N; s++) {
                for (int e = 1; e <= N; e++) {
                    if (dist[s][e] > dist[s][m] + dist[m][e])
                        dist[s][e] = dist[s][m] + dist[m][e];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int minIdx = 0;
        for (int i = 1;i<=N;i++){
            int sum = 0;
            for (int j = 1; j<=N;j++){
                sum += dist[i][j];
            }

            if(sum < min){
                min = sum;
                minIdx = i;
            }
        }

        System.out.println(minIdx);
    }
}

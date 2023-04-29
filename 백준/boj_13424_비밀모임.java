package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_13424_비밀모임 {
    public static int T, N, M,K;
    public static int[] room;
    public static int[][] nodeMat;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 1;tc<=T;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            nodeMat = new int[N+1][N+1];



            for (int i = 1; i<=N;i++){
                Arrays.fill(nodeMat[i],999999999);
                nodeMat[i][i] = 0;
            }

            for (int i = 1;i<=M;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                nodeMat[a][b] = c;
                nodeMat[b][a] = c;
            }

            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            room = new int[K+1];

            for (int i =1; i<=K;i++){
                int roomNum = Integer.parseInt(st.nextToken());
                room[i] = roomNum;
            }

            for (int k = 1; k<=N;k++){
                for (int i = 1; i<=N;i++){
                    for (int j = 1; j<=N;j++){
                        if (nodeMat[i][j] > nodeMat[i][k] + nodeMat[k][j]){
                            nodeMat[i][j] = nodeMat[i][k] + nodeMat[k][j];
                        }
                    }
                }
            }

//            print();
            int ans = 0;
            int ansLen = 999999999;
            for (int i =1;i<=N;i++){
                int sum = 0;
                for (int j = 1; j<=K;j++){
                    sum += nodeMat[i][room[j]];
                }

                if (ansLen > sum){
                    ansLen = sum;
                    ans = i;

                }else if(ansLen == sum && ans > i){
                    ansLen = sum;
                    ans = i;

                }
            }

            System.out.println(ans);


        }
    }

    public static void print(){
        for (int i = 1;i<=N;i++){
            for (int j = 1;j<=N;j++){
                System.out.print(nodeMat[i][j]+" ");
            }
            System.out.println();
        }
    }
}

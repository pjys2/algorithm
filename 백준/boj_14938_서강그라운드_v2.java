package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_14938_서강그라운드_v2 {
    public static int n,m,r,ans;
    public static int[] item;
    public static int[][] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        item = new int[n+1];
        distance = new int[n+1][n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i<=n;i++){
            item[i] =  Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i<=n;i++){
            Arrays.fill(distance[i],999999999);
            distance[i][i] = 0;
        }

        for (int i = 0; i<r;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            distance[a][b] = len;
            distance[b][a] = len;
        }

        ans = 0;
        for (int k = 1; k<=n;k++){
            for (int i = 1; i<=n;i++){
                for (int j = 1; j<=n;j++){
                    if(distance[i][j] > distance[i][k] + distance[k][j]){
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }


        for (int i = 1; i<=n;i++){
            int cnt = 0;
            for (int j = 1; j<=n;j++){
                if (distance[i][j] <= m){
                    cnt+=item[j];
                }
            }
            ans = Math.max(ans,cnt);
        }

        System.out.println(ans);
    }

}

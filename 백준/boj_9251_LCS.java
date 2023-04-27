package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9251_LCS {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int[][] dp = new int[s1.length()+1][s2.length()+1];

        int ans = 0;
        for (int i = 0;i<= s1.length();i++){
            for (int j = 0; j<=s2.length();j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }else if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                    ans = Math.max(ans,dp[i][j]);
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(ans);
    }

//    public static void print(int[][] dp){
//        for (int r = 0; r<7;r++){
//            for (int c = 0; c<7;c++){
//                System.out.print(dp[r][c]+" ");
//            }
//            System.out.println();
//        }
//    }
}

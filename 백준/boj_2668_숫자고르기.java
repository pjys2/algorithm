package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2668_숫자고르기 {

    public static int N,count;
    public static int[] numList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numList = new int[N+1];

        for (int i = 1; i <= N; i++){
            
        }

    }


    public static void DFS(int i){

    }



    public static void print(){
        for (int i = 1; i<=N;i++){
            System.out.print(numList[i]+" ");
        }
    }
}

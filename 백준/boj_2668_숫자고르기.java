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
    public static boolean[] visited;
    public static int[][] selectSet;
    public static int[][] select;
    public static int[] numList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numList = new int[N+1];
        selectSet = new int[2][N+1];
        boolean[] visitSet = new boolean[N+1];
        int countSet = 0;
        for (int i = 1; i <= N; i++){
            int number = Integer.parseInt(br.readLine());
            numList[i] = number;

            if(number == i){
                countSet++;
                visitSet[i] = true;
                select[0][i] = number;
                select[1][i] = number;
            }

        }

        for (int i = 1; i<=N;i++){
            visited = visitSet;
            count = countSet;
            select = selectSet;
            DFS(i);
        }

    }


    public static void DFS(int i){
        count++;
        if(!visited[numList[i]]){
            DFS(i);
        }

    }

    public static boolean IsEqual(){

        for (int i = 1; i <= N;i++){
            if(select[0][i] != select[1][i]){
                return false;
            }
        }

        return true;
    }

    public static void print(){
        for (int i = 1; i<=N;i++){
            System.out.print(numList[i]+" ");
        }
    }
}

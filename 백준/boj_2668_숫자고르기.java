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
    public static boolean[][] select;
    public static int[] numList;
    public static List<Integer> subList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numList = new int[N+1];
        boolean[][] selectSet = new boolean[2][N+1];
        boolean[] visitSet = new boolean[N+1];
        int countSet = 0;
        List<Integer> ansList = new ArrayList<Integer>();
        for (int i = 1; i <= N; i++){
            int number = Integer.parseInt(br.readLine());
            numList[i] = number;

            if(number == i){
                countSet++;
                visitSet[i] = true;
                selectSet[0][i] = true;
                selectSet[1][i] = true;
            }
        }
        int max = 0;
        for (int i = 1; i<=N;i++){
            visited = visitSet;
            count = countSet;
            select = selectSet;
            subList = new ArrayList<Integer>();
            DFS(i);

            if(IsEqual()){
                if(count > max){
                    max = count;
                }
            }

        }



    }


    public static void DFS(int i){
        count++;
        subList.add(i);
        if(!visited[numList[i]]){
            select[0][i] = true;
            select[1][numList[i]] = true;
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

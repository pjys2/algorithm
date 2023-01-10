package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class boj_2668_숫자고르기 {
    public static int N, ans;
    public static List<Integer> ansList;
    public static int[] numList;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numList = new int[N+1];
        visited = new boolean[N+1];
        ansList = new ArrayList<>();
        for (int i =1; i<=N;i++){
            numList[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i<=N;i++){

            if(!visited[i]){

                visited[i] = true;
                DFS(i,i);
                visited[i] = false;
            }
        }

        System.out.println(ansList.size());
        Collections.sort(ansList);
        for (int num : ansList){
            System.out.println(num);
        }
    }

    public static void DFS(int idx, int target){

        if(numList[idx] == target){
            ansList.add(target);
            return;
        }

        if (!visited[numList[idx]]){
            visited[numList[idx]] = true;
            DFS(numList[idx],target);
            visited[numList[idx]] = false;
        }

    }





}

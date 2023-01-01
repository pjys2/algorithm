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
    public static boolean[] visited;
    public static List<Integer> subList;
    public static List<Integer> ansList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        numList = new int[N+1];
        ansList = new ArrayList<>();
        for (int i = 1; i<=N;i++){
            int num = Integer.parseInt(br.readLine());
            numList[i] = num;
        }

        int max = 0;
        for (int i = 1; i<=N;i++){
            count = 0;
            visited = new boolean[N+1];
            subList = new ArrayList<>();
            DFS(i);

            if(count < max){
                count = max;
                ansList = subList;
            }
        }


        System.out.println(max);
        Collections.sort(ansList);

        for (Integer num : ansList){
            System.out.println(num);
        }
    }


    public static void DFS(int i){
        count++;
        visited[i] = true;
        ansList.add(i);
        if(!visited[numList[i]]){
            DFS(numList[i]);
        }
    }



    public static void print(){
        for (int i = 1; i<=N;i++){
            System.out.print(numList[i]+" ");
        }
    }
}

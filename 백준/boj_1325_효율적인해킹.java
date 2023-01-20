package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1325_효율적인해킹 {
    public static int N, M;
    public static int[] counts;
    public static ArrayList<Integer>[] computerList;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        computerList = new ArrayList[N+1];
        counts = new int[N+1];

        for (int i = 1; i <= N; i++){
            computerList[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            computerList[A].add(B);
        }

        int max = 0;


        for (int i = 1; i<=N;i++){
            BFS(i);

//            max = Math.max(max, counts[i]);

        }



        for (int i = 1; i<=N;i++){
            max = Math.max(max, counts[i]);
        }




        StringBuilder sb = new StringBuilder();

        for (int i = 1; i<=N;i++){
            if(max == counts[i]){
                sb.append(i+" ");
            }
        }

        System.out.println(sb);
    }

    public static void BFS(int idx){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        visited[idx] = true;
        queue.add(idx);

        while (!queue.isEmpty()){
            idx = queue.remove();

            for (int num : computerList[idx]){
                if(!visited[num]){
                    visited[num] = true;
                    counts[num]++;
                    queue.add(num);
                }
            }
        }

    }
}

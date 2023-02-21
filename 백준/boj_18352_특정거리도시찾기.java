package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_18352_특정거리도시찾기 {
    public static int N,M,K,X;
    public static List<Integer>[] city;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        city = new ArrayList[N+1];

        for (int i =0; i<=N;i++){
            city[i] = new ArrayList<>();
        }

        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            city[A].add(B);
        }

        BFS();


    }


    public static void BFS(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);
        boolean[] visited = new boolean[N+1];

        visited[X] =  true;


        List<Integer> ansList = new ArrayList<>();
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0;s<size;s++){
                int current = queue.poll();


                if (cnt == K){
                    ansList.add(current);
                    continue;
                }

                if (city[current] == null) continue;

                for (int next : city[current]){
                    if(!visited[next]){
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }
            cnt++;
        }

        Collections.sort(ansList);
        StringBuilder sb = new StringBuilder();

        for (int ans : ansList){
            sb.append(ans+"\n");
        }


        if(sb.length() == 0){
            System.out.println(-1);
        }else{
            System.out.println(sb);
        }

    }
}

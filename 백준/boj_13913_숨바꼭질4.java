package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_13913_숨바꼭질4 {
    public static int N, K;
    public static boolean[] visited;
    public static int[] parrent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        visited = new boolean[100001];
        parrent = new int[100001];

        Arrays.fill(parrent,-1);
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        BFS();
    }

    public static void BFS(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited[N] = true;
        int time = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                int current = queue.poll();

                if(current == K){
                    System.out.println(time);
                    StringBuilder sb = new StringBuilder();

                    List<Integer> numList = new ArrayList<>();
                    for (int n = K; parrent[n] != -1; n = parrent[n]){
                        numList.add(n);
                    }

                    numList.add(N);

                    Collections.reverse(numList);

                    for (int num : numList){
                        sb.append(num+" ");
                    }

                    System.out.println(sb);
                    return;
                }

                int next = current-1;

                if(next >= 0 && next <=100000 && !visited[next]){
                    queue.add(next);
                    visited[next] = true;
                    parrent[next] = current;
                }

                next = current+1;

                if(next >= 0 && next <=100000 && !visited[next]){
                    queue.add(next);
                    visited[next] = true;
                    parrent[next] = current;
                }


                next = current*2;

                if(next >= 0 && next <=100000 && !visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    parrent[next] = current;
                }

            }

            time++;
        }
    }
}

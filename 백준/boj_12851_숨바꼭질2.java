package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_12851_숨바꼭질2 {
    public static int N, K;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[100001];



        if(N==K){
            System.out.println(0);
            System.out.println(1);
        }else{
            BFS();
        }
    }

    public static void BFS(){
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(N);
        int time = 0;
        int cnt = 0;
        boolean isEnd = false;
        while(!queue.isEmpty()){
            int size = queue.size();
            Set<Integer> visitSet = new HashSet<>();
            for (int s = 0; s<size;s++){
                int current = queue.poll();



                int next = current+1;
                if(next == K) {
                    cnt++;
                    isEnd = true;
                }
                if(next >=0 && next <= 100000 && !visited[next]){
                    queue.add(next);
                    visitSet.add(next);
                }

                next = current-1;
                if(next == K) {
                    cnt++;
                    isEnd = true;
                }
                if(next >=0 && next <= 100000 && !visited[next]){
                    queue.add(next);
                    visitSet.add(next);
                }

                next = current*2;
                if(next == K) {
                    cnt++;
                    isEnd = true;
                }
                if(next >=0 && next <= 100000 && !visited[next]){
                    queue.add(next);
                    visitSet.add(next);
                }
            }

            for (int n : visitSet){
                visited[n] = true;
            }


            time++;

            if(isEnd){
                System.out.println(time);
                System.out.println(cnt);
                return;
            }
        }

    }
}

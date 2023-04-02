package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_6118_숨바꼭질 {

    public static int N, M;
    public static List<Integer>[] nodeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new List[N+1];
        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodeList[a].add(b);
            nodeList[b].add(a);
        }

        BFS();
    }


    public static void BFS(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        int len = -1;
        int ans = 1;
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            cnt = queue.size();
            for (int s = 0; s<size;s++){
                int current = queue.poll();
                if(s == 0 || ans > current){
                    ans = current;
                }
                for (int next : nodeList[current]){
                    if (visited[next]) continue;

                    queue.add(next);
                    visited[next] = true;
                }
            }

            len++;
        }


        System.out.println(ans+" "+len+" "+cnt);
    }
}

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_24445_너비우선탐색2 {
    public static int N, M, R;
    public static int[] ans;
    public static List<Integer>[] nodeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        nodeList = new List[N+1];
        ans = new int[N+1];
        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 0; i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodeList[u].add(v);
            nodeList[v].add(u);
        }

        for (int i = 1; i<=N;i++){
            Collections.sort(nodeList[i]);
            Collections.reverse(nodeList[i]);
        }

        BFS();

        for (int i = 1; i<=N;i++){
            System.out.println(ans[i]);
        }

    }

    public static void BFS(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(R);
        boolean[] visited = new boolean[N+1];
        visited[R] = true;

        int num = 1;
        while(!queue.isEmpty()){
            int current = queue.poll();
            ans[current] = num++;

            for (int next : nodeList[current]){
                if(visited[next]) continue;

                queue.add(next);
                visited[next] = true;
            }
        }
    }
}

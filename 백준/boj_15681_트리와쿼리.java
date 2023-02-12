package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_15681_트리와쿼리 {
    public static int N,R,Q;
    public static int[] ans;
    public static boolean[] visited;
    public static List<Integer>[] nodeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        ans = new int[N+1];
        visited = new boolean[N+1];
        nodeList = new ArrayList[N+1];
        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 1; i<N;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            nodeList[u].add(v);
            nodeList[v].add(u);
        }

        DFS(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 1;i<=Q;i++){
            int q = Integer.parseInt(br.readLine());
            sb.append(ans[q]+"\n");
        }

        System.out.println(sb);
    }


    public static int DFS(int current){
        visited[current] = true;

        if(nodeList[current] == null){
            System.out.println("출력");
            return  1;
        }

        for (int next : nodeList[current]){
            if(visited[next]) continue;

            ans[current] += DFS(next);
        }

        ans[current]++;
        return ans[current];
    }
}

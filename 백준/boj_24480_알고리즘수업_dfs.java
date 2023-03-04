package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_24480_알고리즘수업_dfs {
    public static int N, M, R, cnt;
    public static List<Integer>[] nodeList;
    public static boolean[] visited;
    public static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        cnt = 1;
        visited = new boolean[N+1];
        nodeList = new List[N+1];
        ans = new int[N+1];
        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodeList[u].add(v);
            nodeList[v].add(u);
        }

        for (int i = 1;i<=N;i++){
            if(nodeList[i].isEmpty()) continue;

            Collections.sort(nodeList[i]);
        }
        DFS(R);


        for (int i =1; i<=N;i++){
            System.out.println(ans[i]);
        }
    }


    public static void DFS(int num){
        if(visited[num]) return;
        visited[num] = true;
        ans[num] = cnt++;

        int idx = nodeList[num].size()-1;
        for (int i = idx; i>=0;i--){
            DFS(nodeList[num].get(i));
        }


    }

}

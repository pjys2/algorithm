package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_24479_알고리즘수업_dfs {
    public static int N,M,R,cnt;
    public static int[] ans;
    public static boolean[] visited;
    public static ArrayList<Integer>[] nodeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        nodeList = new ArrayList[N+1];
        ans = new int[N+1];
        visited = new boolean[N+1];
        cnt = 1;

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

        for (int i = 1; i<=N;i++){
            Collections.sort(nodeList[i]);
        }


        DFS(R);

        for (int i = 1; i<=N;i++){
            System.out.println(ans[i]);
        }

    }

    public static void DFS(int idx){
        visited[idx] = true;
        ans[idx] = cnt++;
        for (int next : nodeList[idx]){
            if (!visited[next]){
                DFS(next);
            }
        }
    }
}

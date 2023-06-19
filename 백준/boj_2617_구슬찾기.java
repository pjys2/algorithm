package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_2617_구슬찾기 {
    public static int N, M;
    public static List<Integer>[] marble;
    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        marble = new List[N+1];
        visited = new boolean[N+1];

        for (int i = 1; i<=N;i++){
            marble[i] = new ArrayList<Integer>();
        }

        for (int i = 0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            marble[a].add(b);
        }

        for (int i = 1; i<=N;i++){
            DFS(i);
        }


        int res = 0;
        for (int i = 1; i<=N;i++){
            if (!visited[i] || marble[i].isEmpty()){
                res++;
            }
        }

        System.out.println(res);
    }

    public static void DFS(int num){
        if (marble[num].isEmpty()) return;

        for (int next : marble[num]){
            System.out.println(num+" "+next);
            if (visited[next]) continue;
            visited[next] = true;
            DFS(next);
        }
    }
}

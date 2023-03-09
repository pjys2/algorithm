package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_4803_트리 {
    public static int n, m, ans;
    public static List<Integer>[] nodeList;
    public static boolean[] visited;
    public static boolean isCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int tc = 1;
        while(n != 0 || m != 0){
            nodeList = new List[n+1];
            for (int i = 1;i<=n;i++){
                nodeList[i] = new ArrayList<>();
            }

            for (int i = 1; i<=m;i++){
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                nodeList[A].add(B);
                nodeList[B].add(A);
            }

            ans = 0;
            visited = new boolean[n+1];


            for (int i = 1;i<=n;i++){
                if(visited[i]) continue;
                isCycle = false;
                visited[i] = true;
                if(DFS(i ,i)) ans++;
            }

            if(ans == 0){
                sb.append("Case "+tc+": No trees.\n");
            }else if(ans == 1){
                sb.append("Case "+tc+": There is one tree.\n");
            }else{
                sb.append("Case "+tc+": A forest of "+ ans+" trees.\n");
            }

            tc++;
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
        }
        System.out.println(sb);
    }

    public static boolean DFS(int current,int before){
        for (int next : nodeList[current]){
            if (next == before) continue;
            if (visited[next]) return false;
            visited[next] = true;
            if(!DFS(next,current)) return false;
        }

        return true;
    }
}

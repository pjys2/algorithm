package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_11403_경로찾기 {
    public static int N;
    public static int[][] adjMat,ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjMat = new int[N+1][N+1];
        ans = new int[N+1][N+1];

        for (int r = 1;r<=N;r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=N;c++){
                adjMat[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i<=N;i++){
            BFS(i);
        }

        for (int r = 1;r<=N;r++){
            for (int c = 1; c<=N;c++){
                System.out.print(ans[r][c]+" ");
            }
            System.out.println();
        }
    }

    public static void BFS(int start){
        Queue<Integer>queue =new LinkedList<>();
        queue.add(start);
        boolean[] visited = new boolean[N+1];
        visited[start] = true;


        while(!queue.isEmpty()){
            int current = queue.poll();

            for (int next = 1; next<=N;next++){
                if (adjMat[current][next] == 0) continue;

                if (next == start) {
                    ans[start][start] = 1;
                    continue;
                }

                if (visited[next]) continue;

                ans[start][next] = 1;

                queue.add(next);
                visited[next] = true;
            }
        }
    }
}

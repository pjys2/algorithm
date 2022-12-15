package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11724_연결요소의개수 {


    public static int N, M;

    public static int[][] map;

    public static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        visited = new boolean[N+1];

        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map[from][to] = 1;
            map[to][from] = 1;
        }

        int count = 0;
        for (int i = 1; i<=N;i++){
            if(visited[i]) continue;
            DFS(i);
            count++;
        }

        System.out.println(count);

    }

    public static void DFS(int i){
        visited[i] = true;

        for (int j = 1; j<=N;j++){
            if(map[i][j] != 1 || visited[j]) continue;
            DFS(j);
        }
    }

    public static void print(int[][] arr){
        for (int r = 1; r<arr.length;r++){
            for (int c = 1; c<arr[r].length;c++){
                System.out.print(arr[r][c]+" ");
            }
            System.out.println();
        }
    }
}

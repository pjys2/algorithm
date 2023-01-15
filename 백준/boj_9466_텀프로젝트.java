package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_9466_텀프로젝트 {
    public static int N, count;
    public static int[] students;
    public static boolean[] visited;
    public static boolean[] checked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T;tc++){
            N = Integer.parseInt(br.readLine());

            students = new int[N+1];
            visited = new boolean[N+1];
            checked = new boolean[N+1];
            count = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i<=N;i++){
                students[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i<=N;i++){
                if(!checked[i]){
                    DFS(i);
                }
            }

            System.out.println(N-count);
        }

    }

    public static void DFS(int idx){

        visited[idx] = true;
        int next = students[idx];


        if(!visited[next]){
            DFS(next);
        }else{
            if(!checked[next]){
                count++;
                for (int i = next; i != idx; i = students[i]){
                    count++;
                }
            }
        }
        checked[idx] = true;



    }
}

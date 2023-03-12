package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2660_회장뽑기 {
    public static int N;
    public static int[] level;
    public static List<Integer>[] nodeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nodeList = new List[N+1];
        level = new int[N+1];
        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(A == -1 && B == -1) break;
            nodeList[A].add(B);
            nodeList[B].add(A);
        }

        for (int i = 1; i <= N;i++){
            BFS(i);
        }


        List<Integer> ansList = new ArrayList<>();
        int ansCnt = 50;
        for (int i = 1; i <= N;i++){
            if (level[i] < ansCnt){
                ansCnt = level[i];
                ansList = new ArrayList<>();
                ansList.add(i);
            }else if (ansCnt == level[i]){
                ansList.add(i);
            }
        }

        System.out.println(ansCnt+" "+ansList.size());
        for (int ans : ansList){
            System.out.print(ans+" ");
        }

    }

    public static void BFS(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean[] visited = new boolean[N+1];
        visited[start] = true;
        int cnt = -1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s<size;s++){
                int current = queue.poll();

                for (int next : nodeList[current]){
                    if (!visited[next]){
                        queue.add(next);
                        visited[next] = true;
                    }
                }
            }

            cnt++;
        }

        level[start] = cnt;
    }
}

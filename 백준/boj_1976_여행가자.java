package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1976_여행가자 {
    public static int N,M;
    public static List<Integer>[] nodeList;
    public static boolean[] visited;
    public static List<Integer> plan;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        nodeList = new List[N+1];
        visited = new boolean[N+1];
        plan = new ArrayList<>();

        for (int i =1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }


        for (int i = 1; i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j =1; j<=N;j++){
                int num = Integer.parseInt(st.nextToken());
                if (num == 1){
                    nodeList[i].add(j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = st.countTokens();
        for (int i = 0; i<size;i++){
            int num = Integer.parseInt(st.nextToken());
            plan.add(num);
        }


        BFS(plan.get(0));

        boolean isPossible = true;
        for (int num : plan){
            if (!visited[num]){
                isPossible = false;
                break;
            }
        }

        if (isPossible){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    public static void BFS(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();
//            System.out.println(current);

            for (int next : nodeList[current]){
                if (!visited[next]){
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
    }

}

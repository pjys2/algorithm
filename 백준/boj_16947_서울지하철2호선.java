package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16947_서울지하철2호선 {
    public static int N;
    public static List<Integer>[] nodeList;
    public static int[] ans;
    public static boolean[] loop;
    public static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodeList = new List[N+1];
        loop = new boolean[N+1];
        ans = new int[N+1];

        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }
        for (int i = 1; i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodeList[a].add(b);
            nodeList[b].add(a);
        }



        boolean[] visited = new boolean[N+1];
        visited[1] = true;
        findLoop(1,1,visited);



        queue = new LinkedList<>();
        for (int i = 1; i<=N;i++){
            if(loop[i]) queue.add(i);
        }

        BFS();

        for (int i = 1; i<=N;i++){
            System.out.print(ans[i]+" ");
        }

//        System.out.println(Arrays.toString(loop));
    }

    public static void BFS(){
        boolean[] visited = new boolean[N+1];
        int minDis = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s<size;s++){
                int current = queue.poll();
                ans[current] = minDis;
//                System.out.println(current);
                for (int next : nodeList[current]){
                    if (loop[next] || visited[next]) continue;

                    queue.add(next);
                    visited[next] = true;
                }
            }

//            System.out.println("-------------------------");

            minDis++;
        }
    }

    public static int findLoop(int current, int before, boolean[] visited){
        for (int next : nodeList[current]){
            if (next == before) continue;

            if (visited[next]){
                loop[next] = true;
                return next;
            }

            visited[next] = true;
            int loopNum = findLoop(next, current,visited);

            if (loopNum != 0 && next != loopNum){
                loop[next] = true;
                return loopNum;
            }

            visited[next] = false;
        }

        return 0;
    }
}

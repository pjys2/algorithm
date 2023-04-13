package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_5719_거의최단경로 {
    public static int N, M,S,D,minDis;
    public static List<Node>[] nodeList;
    public static Set<Node> minSet;
    public static class Node implements Comparable<Node>{
        int num, len;

        public Node(int num,int len){
            this.num = num;
            this.len = len;
        }

        @Override
        public int compareTo(Node o) {
            return this.len - o.len;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;

            st = new StringTokenizer(br.readLine());

            nodeList = new List[N];
            minSet = new HashSet<>();

            for (int i = 0; i<N;i++){
                nodeList[i] = new ArrayList<>();
            }

            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i = 0; i<M;i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                nodeList[u].add(new Node(v,p));
            }

            minDis = dijkstra();
            if (minDis == 0){
                System.out.println(-1);
                continue;
            }

            //최단 경로인 것 찾아서 minSet에 기록함
            DFS(S,0,new boolean[N]);


            //거의 최단 경로 찾기
            int ans = dijkstra();

            if (ans == 0){
                System.out.println(-1);
            }else{
                System.out.println(ans);
            }
        }
    }

    public static int dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S,0));
        boolean[] visited = new boolean[N];
        visited[S] = true;

        int[] distance = new int[N];
        Arrays.fill(distance,999999999);

        distance[S] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if(current.num == D){
                return current.len;
            }

            for (Node next : nodeList[current.num]){
                if (minSet.contains(next)) {
                    System.out.println("해시" + current.num +" "+next.num+" "+next.len);
                    continue;
                }
                if(!visited[next.num] && distance[next.num] > current.len + next.len){
                    distance[next.num] = current.len + next.len;
                    pq.add(new Node(next.num,distance[next.num]));
                    visited[next.num] = true;
                }
            }
        }

        return 0;
    }

    public static boolean DFS(int current, int len, boolean[] visited){
        if(current == D && len == minDis){
            return true;
        }
        visited[current] = true;

        boolean check = false;
        for(Node next : nodeList[current]){
//            if(minSet.contains(next)) continue;
            if(!visited[next.num] && DFS(next.num, len+next.len,visited)){
                minSet.add(next);
                check = true;
            }
        }

        return check;
    }

}

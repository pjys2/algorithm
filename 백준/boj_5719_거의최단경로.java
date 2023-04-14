package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_5719_거의최단경로 {
    public static int N, M,S,D,minDis;
    public static List<Node>[] nodeList;
    public static List<Node>[] revList;
    public static Set<Node> minSet;

    public static int[] distance;
    public static int[] revDis;
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
            distance = new int[N];

            revList = new List[N];
            revDis = new int[N];

            minSet = new HashSet<>();

            for (int i = 0; i<N;i++){
                nodeList[i] = new ArrayList<>();
                revList[i] = new ArrayList<>();
            }

            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            for (int i = 0; i<M;i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                nodeList[u].add(new Node(v,p));
                revList[v].add(new Node(u,p));
            }

            //시작지점부터 출발지점까지 최소거리 찾기
            minDis = dijkstra();
            if (minDis == 0){
                System.out.println(-1);
                continue;
            }
//            System.out.println("최단거리 : "+minDis);

            //역방향 dijkstra
            reverse();

            //최단 경로인 것 찾아서 minSet에 기록함
            findLoad();


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

        Arrays.fill(distance,999999999);
        distance[S] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();
//            System.out.println(current.num+" "+current.len);
            visited[current.num] = true;

            if(current.num == D){
                return current.len;
            }

            for (Node next : nodeList[current.num]){

                if (minSet.contains(next)) {
//                    System.out.println("최단경로" + current.num +" "+next.num+" "+next.len);
                    continue;
                }

                if(!visited[next.num] && distance[next.num] > current.len + next.len){
                    distance[next.num] = current.len + next.len;
                    pq.add(new Node(next.num,distance[next.num]));
                }
            }
        }

        return 0;
    }

    public static void findLoad(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(S,0));
        boolean[][] visited = new boolean[N][N];

        while(!queue.isEmpty()){
            Node current = queue.poll();

            for (Node next : nodeList[current.num]){
                if (current.len + next.len > distance[next.num] || visited[current.num][next.num]) continue;

                int lenSum = revDis[next.num] + current.len + next.len;

                if (lenSum == minDis){
//                    System.out.println("최단경로" + current.num +" "+next.num+" "+next.len);
                    minSet.add(next);
                    queue.add(new Node(next.num, current.len + next.len));
                    visited[current.num][next.num] = true;
                }
            }
        }
    }

    public static void reverse(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(D,0));
        boolean[] visited = new boolean[N];
        visited[D] = true;

        Arrays.fill(revDis,999999999);
        revDis[D] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();
//            System.out.println(current.num+" "+current.len);
            visited[current.num] = true;

            if (current.num == S) return;

            for (Node next : revList[current.num]){
                if(!visited[next.num] && revDis[next.num] > current.len + next.len){
                    revDis[next.num] = current.len + next.len;
                    pq.add(new Node(next.num,revDis[next.num]));
                }
            }
        }

    }


}

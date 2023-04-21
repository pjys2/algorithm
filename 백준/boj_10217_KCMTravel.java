package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_10217_KCMTravel {
    public static int T,N,M,K;
    public static List<Node>[] nodeList;
    public static class Node implements Comparable<Node>{
        int num, money,time;
        public Node(int num, int money, int time){
            this.num = num;
            this.money = money;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc<=T;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());


            nodeList = new List[N+1];
            for (int i = 1; i<=N;i++){
                nodeList[i] = new ArrayList<>();
            }

            for (int k = 1; k<=K;k++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                nodeList[u].add(new Node(v,c,d));
            }

            dijkstra();

        }
    }


    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1,0,0));

        int[][] minTime = new int[102][10002];
        boolean[][] visited = new boolean[102][10002];

        for (int i = 1; i<=N;i++){
            Arrays.fill(minTime[i],999999999);
        }

        minTime[1][0] = 0;
        visited[1][0] = true;

        while(!pq.isEmpty()){
            Node current = pq.poll();

            if (current.num == N){
                System.out.println(current.time);
                return;
            }

            for (Node next:nodeList[current.num]){
                int nMoney = current.money + next.money;
                int nTime = current.time + next.time;

                if (nMoney > M) continue;

                if (!visited[next.num][nMoney] || minTime[next.num][nMoney] > nTime){
                    pq.add(new Node(next.num,nMoney,nTime));
                    minTime[next.num][nMoney] = nTime;
                    visited[next.num][nMoney] = true;
                }
            }
        }

        System.out.println("Poor KCM");
    }
}

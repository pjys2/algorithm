package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_5551_쇼핑몰 {
    public static int N, M, K;
    public static List<Node>[] nodeList;
    public static List<NodeData> dataList;
    public static Set<Integer> shopCity;
    public static int[] minDis;
    public static class Node implements Comparable<Node>{
        int num, len;

        public Node(int num, int len){
            this.num = num;
            this.len = len;
        }

        @Override
        public int compareTo(Node o) {
            return this.len - o.len;
        }
    }

    public static class NodeData{
        int a, b, len;

        public NodeData(int a, int b, int len){
            this.a = a;
            this.b = b;
            this.len = len;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        minDis = new int[N+1];
        nodeList = new List[N+1];
        dataList = new ArrayList<>();
        shopCity = new HashSet<>();


        Arrays.fill(minDis, 300000000);
        for (int i = 1; i<=N;i++){
            nodeList[i] = new ArrayList<>();
        }

        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            nodeList[a].add(new Node(b,l));
            nodeList[b].add(new Node(a,l));

            dataList.add(new NodeData(a,b,l));
        }

        for (int i = 0; i<K;i++){
            int num = Integer.parseInt(br.readLine());
            shopCity.add(num);
            dijkstra(num);
        }

        double ans = 0;

        for (NodeData nodeData : dataList){
            ans = Math.max(ans, (minDis[nodeData.a] + minDis[nodeData.b] + nodeData.len+1)/2);
        }

        System.out.println(Math.round(ans));
    }

    public static void dijkstra(int start) {
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pQueue = new PriorityQueue<>();


        minDis[start] = 0;
        pQueue.offer(new Node(start, minDis[start]));
        while (!pQueue.isEmpty()) {
            Node current = pQueue.poll();

            if (minDis[current.num] < current.len) continue;

            if (visited[current.num]) continue;
            visited[current.num] = true;

            for (Node node : nodeList[current.num]) {
                if (!visited[node.num] && minDis[node.num] > minDis[current.num] + node.len) {
                    minDis[node.num] = minDis[current.num] + node.len;
                    pQueue.offer(new Node(node.num, minDis[node.num]));
                }
            }
        }
    }
}

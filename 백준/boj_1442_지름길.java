package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1442_지름길 {
    public static int N, D;
    public static List<Node>[] nodeList;
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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        nodeList = new List[D+1];

        for (int i = 0; i<=D;i++){
            nodeList[i] = new ArrayList<>();
            if (i < D){
                nodeList[i].add(new Node(i+1,1));
            }
        }

        for (int i =1 ; i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            if (s > D || e > D) continue;
            nodeList[s].add(new Node(e,l));
        }


        dijkstra();
    }

    public static void dijkstra(){
        PriorityQueue<Node>pq = new PriorityQueue<>();
        pq.add(new Node(0,0));
        boolean[] visited = new boolean[D+1];
        visited[0] = true;
        while (!pq.isEmpty()){
            Node current = pq.poll();

            if (current.num == D){
                System.out.println(current.len);
                return;
            }

            visited[current.num] = true;

            for (Node next : nodeList[current.num]){
                if (next.num > D) continue;

                if (!visited[next.num]){
                    pq.add(new Node(next.num, current.len + next.len));
                }
            }
        }
    }
}

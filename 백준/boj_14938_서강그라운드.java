package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_14938_서강그라운드 {
    public static int n,m,r,ans;
    public static int[] item;
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

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        item = new int[n+1];
        nodeList = new List[n+1];
        for (int i = 1;i<=n;i++){
            nodeList[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i<=n;i++){
            item[i] =  Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i<r;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            nodeList[a].add(new Node(b,len));
            nodeList[b].add(new Node(a,len));
        }

        ans = 0;
        for (int i = 1; i<=n;i++){
            dijkstra(i);
        }

        System.out.println(ans);
    }

    public static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start,0));
        boolean[] visited = new boolean[n+1];
        int sum = 0;
        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(visited[current.num]) continue;
            visited[current.num] = true;
            sum += item[current.num];
            for (Node node : nodeList[current.num]){
                if(!visited[node.num] && node.len + current.len <= m){
                    pq.add(new Node(node.num,node.len + current.len));

                }
            }
        }

        ans = Math.max(ans,sum);
    }
}

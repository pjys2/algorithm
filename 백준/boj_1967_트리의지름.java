package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1967_트리의지름 {
    public static int N;
    public static Node[] nodeList;
    public static int ans;
    public static class Node{
        int num;
        int len;
        Node node;

        public Node(int num,int len, Node node){
            this.num = num;
            this.len = len;
            this.node = node;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        N = Integer.parseInt(br.readLine());

        nodeList = new Node[N+1];


        for (int i = 0; i < N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            nodeList[from] = new Node(to,len,nodeList[from]);
            nodeList[to] = new Node(from,len,nodeList[to]);
        }

        ans = 0;

        for (int i = 1; i<=N;i++){
            DFS(i, new boolean[N+1],0);
        }

        System.out.println(ans);
    }

    public static void DFS(int current, boolean[] visited,int sum) {
        visited[current] = true;

        for (Node node = nodeList[current]; node != null; node = node.node) {
            if (!visited[node.num]) {
                DFS(node.num,visited,sum+node.len);
                ans = Math.max(ans, sum+node.len);
            }
        }
    }

}

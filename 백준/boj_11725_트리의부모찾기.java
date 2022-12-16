package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_11725_트리의부모찾기 {

    public static int N;
    public static boolean[] visited;
    public static int[] ans;
    public static Node[] nodeList;
    public static class Node{
        int number;
        Node node;

        public Node(int number,Node node){
            this.number = number;
            this.node = node;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nodeList = new Node[N+1];
        visited = new boolean[N+1];
        ans = new int[N+1];
        for (int i = 0; i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            nodeList[from] = new Node(to,nodeList[from]);
            nodeList[to] = new Node(from,nodeList[to]);
        }

        DFS(1);

        for (int i = 2; i<=N;i++){
            System.out.println(ans[i]);
        }

    }

    public static void DFS(int i){
        visited[i] = true;

        for (Node temp = nodeList[i]; temp != null; temp = temp.node){
            if(!visited[temp.number]){
                ans[temp.number] = i;
                DFS(temp.number);
            }
        }
    }
}

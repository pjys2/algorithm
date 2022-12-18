package 백준;


import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1707_이분그래프 {
    public static int K, V, E;
    public static Node[] nodeList;
    public static class Node{
        int num;
        Node node;

        public Node(int num, Node node){
            this.num = num;
            this.node = node;
        }
    }
    public static int[] connect;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < K; tc++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            nodeList = new Node[V+1];
            connect = new int[V+1];
            for (int e = 0; e < E; e++){
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                nodeList[from] = new Node(to, nodeList[from]);
                nodeList[to] = new Node(from, nodeList[to]);

            }

            int number = 0;
            for (int v = 1; v<=V;v++){
                if(connect[v] != 0) continue;

                DFS(v, number);
                number++;
            }


            if(number == 2){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    public static void DFS(int v, int number){
        for (Node temp = nodeList[v];temp = nodeList[v].node;)
    }
}

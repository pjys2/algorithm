package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1967_트리의지름 {
    public static int N;
    public static Node[] nodeList;
    public static int ans;
    public static class Node{
        int num;
        int len;
        Node node;

        public Node(int num, Node node){
            this.num = num;
            this.node = node;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        nodeList = new Node[N+1];


        for (int i = 0; i < N-1; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            nodeList[from] = new Node(to,nodeList[from]);

        }

        ans = 0;

        int first = 0;
        int second = 0;
        for (Node temp = nodeList[0]; temp != null; temp = temp.node){

        }
    }
}

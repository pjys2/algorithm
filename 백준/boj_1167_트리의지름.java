package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1167_트리의지름 {
    public static int N,A,B,ans;
    public static Node[] nodeList;
    public static class Node{
        int num,v;
        Node node;
        public Node(int num, int v, Node node){
            this.num = num;
            this.v = v;
            this.node = node;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodeList = new Node[N+1];
        ans = 0;

        for (int i = 1; i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());

            while(true){
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) break;
                int v = Integer.parseInt(st.nextToken());
                nodeList[from] = new Node(to,v,nodeList[from]);
            }
        }

        A = 1;
        //임의의 끝점 B 찾기
        DFS(A,new boolean[N+1],0, true);
        //B를 넣고 가장 긴 거리에 있는 노드 찾기
        DFS(B,new boolean[N+1],0,false);



        System.out.println(ans);
    }

    public static void DFS(int current, boolean[] visited,int sum,boolean isA){
        visited[current] = true;


        for (Node node = nodeList[current];node!=null;node = node.node){
            if(visited[node.num]) continue;

            DFS(node.num,visited,sum+node.v,isA);

            if(ans < sum+node.v ){
                ans = sum+node.v;
                B = node.num;
            }
        }
    }

    public static void print(){
        for (int i = 1; i<=N;i++){
            for (Node node = nodeList[i];node != null;node = node.node){
                System.out.print("["+node.num+" "+node.v+"]"+" ");
            }
            System.out.println();
        }
    }
}

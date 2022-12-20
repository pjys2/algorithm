package 백준;


import com.sun.org.apache.xpath.internal.objects.XNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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


            for (int v = 1; v<=V;v++){
                if(connect[v] != 0) continue;

                DFS(v, 1);

            }

            boolean isPossible = true;

            for (int i = 1; i < connect.length;i++){
                if(connect[i] == 0 || connect[i] == -1){
                    isPossible = false;
                    break;
                }
            }

            if(isPossible){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }


        }
    }

    public static void DFS(int v, int number){
        connect[v] = number;
        if(number == 1){
            number = 2;
        }else{
            number = 1;
        }

        for (Node temp = nodeList[v]; temp != null; temp = temp.node){
            if( (number == 1 && connect[temp.num] == 2) || (number == 2 && connect[temp.num] == 1)){
                connect[temp.num] = -1;
            }

            if(connect[temp.num] == 0){
                DFS(temp.num, number);
            }
        }
    }
}

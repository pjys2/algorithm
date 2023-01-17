package 백준;

import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1325_효율적인해킹 {
    public static int N, M;
    public static boolean[] visited;
    public static int[] counts;
    public static Node[] nodeList;
    public static class Node{
        int num;
        Node node;
        public Node(int num, Node node){
            this.num= num;
            this.node = node;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodeList = new Node[N+1];
        counts = new int[N+1];

        for (int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            nodeList[B] = new Node(A,nodeList[B]);
        }

        int max = 0;

        for (int i = 1; i<=N;i++){
            visited = new boolean[N+1];
            visited[i] = true;
            DFS(i);

            if(max < counts[i]){
                max = counts[i];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 1; r<= N;r++){
            if(counts[r] == max){
                sb.append(r+" ");
            }
        }

        System.out.println(sb);

    }

    public static void DFS(int idx){


        for (Node temp = nodeList[idx]; temp!= null; temp = temp.node){
            if(!visited[temp.num]){
                counts[temp.num]++;
                visited[temp.num] = true;
                DFS(temp.num);
            }
        }
    }
}

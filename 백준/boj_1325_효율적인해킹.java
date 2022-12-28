package 백준;

import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1325_효율적인해킹 {
    public static int N, M;
    public static boolean[] visited;
    public static int count;
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


        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            nodeList[from] = new Node(to, nodeList[from]);
        }


        int max = 0;
        for (int num = 1; num<= N;num++){
            visited = new boolean[N+1];
            count = 0;
            DFS(num);
            counts[num] = count;

            if(max < counts[num]){
                max = counts[num];
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


    public static void DFS(int num){

        visited[num] = true;

        for (Node temp = nodeList[num];temp != null; temp = temp.node){
            if(visited[temp.num]) continue;
            count++;
            DFS(temp.num);
        }

    }
}

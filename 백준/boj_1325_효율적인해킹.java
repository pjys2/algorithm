package 백준;

import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_1325_효율적인해킹 {
    public static int N,M;

    public static class Node{
        int num;
        Node node;

        public Node(int num, Node node){
            this.num = num;
            this.node = node;
        }
    }

    public static Node[] nodeList;
    public static int[] counts;
    public static int count;
    public static void main(String[] args) throws IOException {
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

        for (int i = 1; i<=N;i++){
            count = 0;
            DFS(i);
            counts[i] = count;

            if(max < counts[i]){
                max = counts[i];
            }
        }

        List<Integer> ansList = new ArrayList<>();
        for (int i = 1; i<=N;i++){
            if(counts[i] == max){
                ansList.add(i);
            }
        }

        for (int i : ansList){
            System.out.print(i + " ");
        }

    }

    public static void DFS(int i){
        count++;

        for (Node temp = nodeList[i]; temp != null; temp = temp.node){
            if(counts[temp.num] != 0){
                count += counts[temp.num];
                continue;
            }

            DFS(temp.num);
        }
    }
}

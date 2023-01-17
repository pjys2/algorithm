package 백준;

import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        for (int i = 1; i <= M; i++){

        }



    }


}

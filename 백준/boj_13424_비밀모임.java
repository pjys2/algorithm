package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_13424_비밀모임 {
    public static int T, N, M;
    public static List<Node>[] nodeList;
    public static class Node{
        int num, len;
        public Node(int num, int len){
            this.num = num;
            this.len = len;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 1;tc<=T;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            nodeList = new List[N+1];

            for (int i = 1;i<=N;i++){
                nodeList[i] = new ArrayList<>();
            }

            for (int i = 1;i<=M;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                nodeList[a].add(new Node(b,c));
                nodeList[b].add(new Node(a,c));
            }


            
        }
    }
}

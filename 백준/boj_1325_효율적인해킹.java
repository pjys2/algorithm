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

    public static int[][] pc;
    public static int count;
    public static int[] counts;

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

        pc = new int[N+1][N+1];
        counts = new int[N+1];

        for (int i = 0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());

            pc[from][to] = 1;
        }


        int max = 0;
        for (int r = 1; r<= N;r++){
            count = 0;
            DFS(r);
            counts[r] = count;

            if(max < counts[r]){
                max = counts[r];
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


    public static void DFS(int r){
        for (int c = 1; c<=N;c++){
            if (pc[r][c] == 1){
                count++;
                DFS(c);
            }
        }
    }
}

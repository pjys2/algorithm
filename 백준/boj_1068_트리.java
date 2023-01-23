package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1068_트리 {
    public static int N;
    public static int[] node;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        node = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N;i++){

            node[i] = Integer.parseInt(st.nextToken());
        }

        int delNum = Integer.parseInt(br.readLine());

        delete(delNum);


    }

    public static void delete(int delNum){
        int nextNum = node[delNum];
        

    }
}

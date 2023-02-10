package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2250_트리의높이와너비 {
    public static int N, colNum, maxLevel,ansLevel,ansSize;
    public static int[] nodeNum;
    public static int[][] node;
    public static int[] min;
    public static int[] max;
    public static int[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        check = new int[N+1];
        nodeNum = new int[N+1];
        node = new int[N+1][2];
        max = new int[10000];
        min = new int[10000];
        Arrays.fill(min,Integer.MAX_VALUE);
        colNum = 0;
        ansLevel = 0;
        ansSize = 0;
        int start = 0;
        for (int i = 1; i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            nodeNum[i] = num;
            //0 왼쪽, 1 오른쪽
            node[num][0] = Integer.parseInt(st.nextToken());
            node[num][1] = Integer.parseInt(st.nextToken());

            if(node[num][0] != -1){
                check[node[num][0]] = num;
            }

            if(node[num][1] != -1){
                check[node[num][1]] = num;
            }
        }

        for (int i = 1; i<=N;i++){
            if(check[i] != 0) continue;
            start = nodeNum[i];
        }

        DFS(start, 1);

        for (int i = 1; i<=maxLevel;i++){
            int size = max[i]-min[i]+1;
            if(size > ansSize){
                ansSize = size;
                ansLevel = i;
            }
        }

        System.out.println(ansLevel+" "+ansSize);
    }

    public static void DFS(int current, int Level){
        maxLevel = Math.max(maxLevel, Level);



        if(node[current][0] != -1){
            DFS(node[current][0], Level+1);
        }

        colNum++;

//        System.out.println("colNum : "+colNum+" current : "+current);

        max[Level] = Math.max(max[Level],colNum);
        min[Level] = Math.min(min[Level],colNum);

        if(node[current][1] != -1){
            DFS(node[current][1], Level+1);
        }


    }
}

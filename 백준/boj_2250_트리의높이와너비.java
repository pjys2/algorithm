package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2250_트리의높이와너비 {
    public static int N, colNum,maxLevel,ansLevel,ansSize;
    public static int[][] childList;
    //node번호 : 인덱스
    public static int[] nodeList;
    public static int[] min;
    public static int[] max;
    public static Map<Integer,Integer> indexMap;
    public static Set<Integer> parrentCheck;
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        childList = new int[N][2];
        nodeList = new int[N];
        max = new int[10000];
        min = new int[10000];
        Arrays.fill(min,Integer.MAX_VALUE);
        indexMap = new HashMap<>();
        parrentCheck = new HashSet<>();
        for (int i = 0; i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int child1 = Integer.parseInt(st.nextToken());
            int child2 = Integer.parseInt(st.nextToken());

            childList[i][0] = child1;
            childList[i][1] = child2;

            nodeList[i] = num;
            indexMap.put(num,i);
            parrentCheck.add(child1);
            parrentCheck.add(child2);
        }

        int start = 0;
        for (int i = 0;i<N;i++){
            if(!parrentCheck.contains(nodeList[i])){
                start = nodeList[i];
                break;
            }
        }

        DFS(start,1);

        for (int i = 1; i<=maxLevel;i++){

        }

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

        int idx = indexMap.get(current);
        if(childList[idx][0] != -1){
            DFS(childList[idx][0], Level+1);
        }

        colNum++;

//        System.out.println("colNum : "+colNum+" current : "+current);

        max[Level] = Math.max(max[Level],colNum);
        min[Level] = Math.min(min[Level],colNum);

        if(childList[idx][1] != -1){
            DFS(childList[idx][1], Level+1);
        }


    }


}

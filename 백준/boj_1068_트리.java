package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1068_트리 {
    public static int N, ans;
    public static ArrayList<Integer>[] nodeList;
    public static int[] parrents;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = 0;
        nodeList = new ArrayList[N];
        parrents = new int[N];

        for (int i = 0; i<N;i++){
            nodeList[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N;i++){
            int num = Integer.parseInt(st.nextToken());
            parrents[i] = num;
        }

//        for (int i = 0; i<N;i++){
//            System.out.println(nodeList[i].toString());
//        }

        int delNum = Integer.parseInt(br.readLine());


        for (int i =0; i<N;i++){
            if (parrents[i] == -1 || i == delNum) continue;

            nodeList[parrents[i]].add(i);
        }
//        System.out.println("------------------");
        DFS(delNum);


        for (int i = 0; i<N;i++){
            if(parrents[i] == -2) continue;

            if (nodeList[i].size() == 0){
                ans++;
            }
        }


//        for (int i = 0; i<N;i++){
//            System.out.println(nodeList[i].toString());
//        }


        System.out.println(ans);
    }

    public static void DFS(int delNum){

        List<Integer> delList = nodeList[delNum];
        parrents[delNum] = -2;
        for (int num : delList){
            DFS(num);
        }

        nodeList[delNum].clear();
    }

}

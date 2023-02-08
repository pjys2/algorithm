package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2251_물통 {
    public static int[] size;
    public static Set<Integer> ansSet;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        size = new int[3];
        size[0] = Integer.parseInt(st.nextToken());
        size[1] = Integer.parseInt(st.nextToken());
        size[2] = Integer.parseInt(st.nextToken());

        int[] water = new int[3];
        water[2] = size[2];

        ansSet = new HashSet<>();

        DFS(2,water,true);

        for (int num : ansSet){
            System.out.print(num+" ");
        }

    }

    public static void DFS(int current,int[] water,boolean isStart){
        System.out.println(Arrays.toString(water));

        if(!isStart && water[0] == 0){
            ansSet.add(water[2]);
            return;
        }


        for (int next = 0; next<3;next++){
            if(next == current) continue;
            int[] nextWater = new int[3];
            for (int i = 0; i<3;i++){
                nextWater[i] = water[i];
            }
            int possible = size[next]-nextWater[next];

            if(possible > nextWater[current]){
                nextWater[next] += nextWater[current];
                nextWater[current] = 0;
            }else{
                nextWater[current] -= possible;
                nextWater[next] += possible;
            }

            DFS(next,nextWater,false);
        }
    }
}

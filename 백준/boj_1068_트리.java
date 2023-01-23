package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_1068_트리 {
    public static int N, ans;
    public static ArrayList<Integer>[] nodeList;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ans = 0;
        nodeList = new ArrayList[N];

        for (int i = 0; i<N;i++){
            nodeList[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N;i++){
            int num = Integer.parseInt(st.nextToken());
            if (num == -1) continue;
            nodeList[num].add(i);
        }

        int delNum = Integer.parseInt(br.readLine());

        nodeList[delNum].clear();


        for (int i=0; i<N;i++){
            int cnt = nodeList[i].size();
            if(cnt >= 2) continue;


            if (cnt == 0){
                ans++;
            }else{
                int child = nodeList[i].get(0);

                if (child == delNum) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

}

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1039_교환 {
    public static int N, K,ans,len;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = 0;
        int current = N;
        if(current/1000000 != 0){
            len = 7;
        }else if(current/100000 != 0){
            len = 6;
        }else if(current/10000 != 0){
            len = 5;
        }else if(current/1000 != 0){
            len = 4;
        }else if(current/100 != 0){
            len = 3;
        }else if(current/10 != 0){
            len = 2;
        }else{
            len = 1;
        }

        BFS();

    }

    public static void BFS(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        Set<Integer> visitSet = new HashSet<>();
        boolean[][] visited = new boolean[1000001][K+1];
        visited[N][0] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                int current = queue.poll();

                if(cnt == K){
                    ans = Math.max(ans, current);
                    continue;
                }


                int[] numbers = new int[len];

                int idx = (int)Math.pow(10,len-1);
                for (int k = 0; k<len;k++){
                    numbers[k] = current/idx;
                    current = current%idx;
                    idx = idx / 10;
                }

                for (int i = 0; i<len-1;i++){
                    for (int j = i;j<len;j++){

                        if(i == j) continue;
                        int next = 0;
                        boolean check = true;

                        idx = 1;
                        for (int k = len-1; k>=0;k--){
                            if(k==i){
                                if(i == 0 && numbers[j] == 0) check = false;
                                next += numbers[j]*idx;
                            }else if (k == j){
                                if(j == 0 && numbers[i] == 0) check = false;
                                next += numbers[i]*idx;
                            }else{
                                if(k == 0 && numbers[k] == 0) check = false;
                                next += numbers[k]*idx;
                            }
                            idx = idx * 10;
                        }

                        if (visited[next][cnt+1]) continue;

                        if(check){

                            queue.add(next);
                            visited[next][cnt+1] = true;
                        }
                    }
                }
            }

            if(cnt == K) break;
            cnt++;
        }


        if(ans == 0){
            System.out.println(-1);
        }else{
            System.out.println(ans);
        }
    }
}

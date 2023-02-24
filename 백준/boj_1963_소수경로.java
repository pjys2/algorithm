package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1963_소수경로 {
    public static int T, A, B;
    public static boolean[] primeNum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        primeNum = new boolean[10000];
        Arrays.fill(primeNum,true);

        findPrimeNum();
        for (int tc = 1; tc <= T;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            BFS();
        }

    }


    public static void findPrimeNum(){
        int n = 10000;
        int sqrtNum = (int) Math.sqrt(n);

        for (int i = 2; i<=sqrtNum;i++){
            for (int j = 2; i * j < n; j++){
                primeNum[i*j] = false;
            }
        }
    }

    public static void BFS(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(A);
        Set<Integer> visitSet = new HashSet<>();
        visitSet.add(A);

        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                int current = queue.poll();


                if (current == B){
                    System.out.println(cnt);
                    return;
                }

                int next = 0;
                //천의 자리
                for (int i = 1; i<=9;i++){
                    if(i == current/1000) continue;

                    next = (current%1000) + (i*1000);

                    if(next >= 1000 && next <= 9999 &&primeNum[next] && !visitSet.contains(next)){
                        queue.add(next);
                        visitSet.add(next);
                    }
                }

                //백의 자리
                for (int i = 0; i<=9;i++){
                    if(i == (current%1000)/100) continue;

                    next = ((current/1000) *1000) + (current%100) + (i*100);

                    if(next >= 1000 && next <= 9999 &&primeNum[next] && !visitSet.contains(next)){
                        queue.add(next);
                        visitSet.add(next);
                    }
                }

                //십의 자리
                for (int i = 0; i<=9;i++){
                    if(i == (current%100)/10) continue;

                    next = ((current/100)*100) + (current%10) + (i*10);

                    if(next >= 1000 && next <= 9999 &&primeNum[next] && !visitSet.contains(next)){
                        queue.add(next);
                        visitSet.add(next);
                    }
                }




                //일의 자리
                for (int i = 0; i<=9;i++){
                    if(i == (current%10)) continue;

                    next = ((current/10)*10) + i;

                    if(next >= 1000 && next <= 9999 &&primeNum[next] && !visitSet.contains(next)){
                        queue.add(next);
                        visitSet.add(next);
                    }
                }
            }

            cnt++;
        }

        System.out.println("Impossible");
    }
}

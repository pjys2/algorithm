package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_16953_A에서B {
    public static long A, B;
    public static boolean isPossible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        if (A <= B){
            BFS();
        }

        if(!isPossible){
            System.out.println(-1);
        }

    }

    public static void BFS(){
        Queue<Long> queue = new LinkedList<>();
        queue.add(A);
        Set<Long> visitSet = new HashSet<>();
        visitSet.add(A);
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 1; s<=size;s++){
                long current = queue.poll();


                if(current == B){
                    System.out.println(cnt+1);
                    isPossible =true;
                    return;
                }


                long next = current*2;

                if(next<=B && !visitSet.contains(next)){
                    queue.add(next);
                    visitSet.add(next);
                }

                next = current*10+1;

                if(next<=B && !visitSet.contains(next)){
                    queue.add(next);
                    visitSet.add(next);
                }

            }

            cnt++;

        }

    }
}

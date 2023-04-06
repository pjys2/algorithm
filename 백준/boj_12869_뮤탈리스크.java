package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_12869_뮤탈리스크 {
    public static int N;
    public static SCV start;
    public static int[] damage = {9,3,1};
    public static class SCV{
        int a, b, c;
        public SCV(int a, int b, int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 1; i<=N;i++){
            if(i == 1){
                a = Integer.parseInt(st.nextToken());
            }else if(i == 2){
                b = Integer.parseInt(st.nextToken());
            }else{
                c = Integer.parseInt(st.nextToken());
            }
        }

        start = new SCV(a,b,c);


        BFS();
    }

    public static void BFS(){
        Queue<SCV> queue = new LinkedList<>();
        queue.add(start);
        boolean[][][] visited = new boolean[61][61][61];
        visited[start.a][start.b][start.c] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                SCV current = queue.poll();
                if(current.a == 0 && current.b == 0 && current.c == 0){
                    System.out.println(cnt);
                    return;
                }

                int[] nextA = new int[3];
                int[] nextB = new int[3];
                int[] nextC = new int[3];

                for (int i = 0; i<3;i++){
                    nextA[i] = current.a-damage[i];
                    nextB[i] = current.b-damage[i];
                    nextC[i] = current.c-damage[i];

                    if(nextA[i] < 0) nextA[i] = 0;
                    if(nextB[i] < 0) nextB[i] = 0;
                    if(nextC[i] < 0) nextC[i] = 0;
                }


                for (int i = 0; i<3;i++){
                    for (int j = 0; j<3;j++){
                        for (int k = 0; k<3;k++){
                            if(i == j || i == k || j == k) continue;
                            if(!visited[nextA[i]][nextB[j]][nextC[k]]){
                                queue.add(new SCV(nextA[i],nextB[j],nextC[k]));
                                visited[nextA[i]][nextB[j]][nextC[k]] = true;
                            }
                        }
                    }
                }

            }
            cnt++;
        }

    }
}

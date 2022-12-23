package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1926_그림 {
    public static int N, M;
    public static int[][] map;
    public static int[][] connect;
    public static List<Integer> size;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        connect = new int[N][M];
        size = new ArrayList<>();
        for (int r = 0; r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<M;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int num = 0;
        for (int r = 0; r<N;r++){
            for (int c = 0; c<M;c++){
                if(connect[r][c] != 0 || map[r][c] == 0) continue;

                count = 0;
                num++;
                connect[r][c] = num;
                DFS(r, c, num);

                size.add(count);
            }
        }


        Collections.sort(size);


        Collections.reverse(size);

        if(num != 0){
            System.out.println(num);
            System.out.println(size.get(0));
        }else{
            System.out.println(num);
            System.out.println(0);
        }



    }

    public static void DFS(int r, int c, int num){
        count++;
        for (int d = 0; d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];

            if (nr>=0&&nr<N&& nc>=0&&nc<M&&map[nr][nc] == 1 && connect[nr][nc] == 0){
                connect[nr][nc] = num;
                DFS(nr,nc,num);
            }
        }

    }

    public static void print(int[][] arr){
        for (int r = 0; r< arr.length;r++){
            for (int c = 0; c<arr[c].length;c++){
                System.out.print(arr[r][c]+" ");
            }
            System.out.println();
        }
    }
}

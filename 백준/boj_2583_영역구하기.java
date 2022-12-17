package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2583_영역구하기 {

    public static int[] dr = {0,0,-1,1};
    public static int[] dc = {1,-1,0,0};
    public static int N, M, K;
    public static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int i = 0; i < K; i++){

//            왼쪽아래 r 끝점, c 시작점 오른쪾 위 r 시작점 c 끝점
            st = new StringTokenizer(br.readLine());
            int firstC = Integer.parseInt(st.nextToken());
            int firstR = Integer.parseInt(st.nextToken());
            int secondC = Integer.parseInt(st.nextToken());
            int secondR = Integer.parseInt(st.nextToken());
            int sizeR = Math.abs(firstR - secondR);
            int sizeC = Math.abs(secondC - firstC);
            int startC = firstC;
            int startR = M-secondR;
//            System.out.println("startR = "+startR + "startC = "+startC);

            for (int r = startR;r<startR+sizeR;r++){
                for (int c = startC;c<startC+sizeC;c++){
                    map[r][c] = -1;
                }
            }

        }


        int number = 1;
        for (int r=0;r<M;r++){
            for (int c = 0;c<N;c++){
                if(map[r][c] != 0) continue;

                DFS(r,c,number);
                number++;

            }
        }

//        print(map);

        int[] size = new int[number-1];

        for (int r=0;r<M;r++){
            for (int c = 0;c<N;c++){
                if (map[r][c] == -1) continue;
                size[map[r][c]-1]++;
            }
        }

        Arrays.sort(size);
        System.out.println(number-1);
        for (int i = 0; i < size.length;i++){
            System.out.print(size[i]+" ");
        }
    }

    public static void DFS(int r, int c, int number){
        map[r][c] = number;
        for(int d = 0; d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];
            if(nr >= 0 && nr < M && nc >= 0 && nc < N && map[nr][nc] == 0){
                DFS(nr,nc,number);
            }
        }
    }

    public static void print(int[][] arr){
        for (int r = 0; r<arr.length;r++){
            for (int c = 0; c<arr[r].length;c++){
                System.out.print(arr[r][c] + " ");
            }
            System.out.println();
        }
    }
}

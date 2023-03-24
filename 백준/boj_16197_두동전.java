package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16197_두동전 {
    public static int N, M;
    public static char[][] board;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Coin{
        int r1,c1,r2,c2;

        public Coin(int r1, int c1, int r2,int c2){
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }
    }
    public static Queue<Coin> queue;
    public static boolean[][][][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        board = new char[N][M];

        queue = new LinkedList<>();
        visited = new boolean[N][M][N][M][11];

        int r1 = 0;
        int r2 = 0;
        int c1 = 0;
        int c2 = 0;
        for (int r = 0; r<N;r++){
            String input = br.readLine() ;
            for (int c = 0; c<M;c++){
                board[r][c] = input.charAt(c);
                if(r1 == 0 && c1 == 0 && board[r][c] == 'o'){
                    r1 = r;
                    c1 = c;
                }else if(board[r][c] == 'o'){
                    r2 = r;
                    c2 = c;
                }
            }
        }
        queue.add(new Coin(r1,c1,r2,c2));
        visited[r1][c1][r2][c2][1] = true;



        BFS();
    }


    public static void BFS(){
        int cnt = 1;
        while(!queue.isEmpty()){

            if(cnt > 10){
                System.out.println(-1);
                return;
            }

            int size = queue.size();

            for (int s = 0; s<size;s++){
                Coin current = queue.poll();

                for (int d = 0; d<4;d++){

                    int nr1 = current.r1+dr[d];
                    int nc1 = current.c1+dc[d];
                    int nr2 = current.r2+dr[d];
                    int nc2 = current.c2+dc[d];

                    boolean check1 = false;
                    boolean check2 = false;
                    if(nr1 < 0 || nr1 >= N || nc1 < 0 || nc1 >= M) check1 = true;
                    if(nr2 < 0 || nr2 >= N || nc2 < 0 || nc2 >= M) check2 = true;

                    if (check1 && check2) continue;
                    else if(check1 || check2){
                        System.out.println(cnt);
                        return;
                    }

//                    System.out.println(cnt+" "+nr1+" "+nc1+" "+nr2+" "+nc2+" "+check1+" "+check2);
                    if(board[nr1][nc1] == '#'){
                        nr1 = current.r1;
                        nc1 = current.c1;
                    }

                    if(board[nr2][nc2] == '#'){
                        nr2 = current.r2;
                        nc2 = current.c2;
                    }


                    if (visited[nr1][nc1][nr2][nc2][cnt]) continue;


                    queue.add(new Coin(nr1,nc1,nr2,nc2));
                    visited[nr1][nc1][nr2][nc2][cnt] = true;
                }
            }

            cnt++;
        }
    }

    public static void print(){
        for (int r = 0;r<N;r++){
            for (int c = 0; c<M;c++){
                System.out.print(board[r][c]+" ");
            }
            System.out.println();
        }
    }
}

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class boj_1938_통나무옮기기 {
    public static int N;
    public static char[][] map;
    public static Tree start,end;
    public static int[] dr = {-1,1,0,0,1,1,-1,-1};
    public static int[] dc = {0,0,-1,1,1,-1,1,-1};
    public static class Tree{
        int r1, c1, r2,c2,r3,c3;

        public Tree(int r1, int c1, int r2,int c2,int r3,int c3){
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.r3 = r3;
            this.c3 = c3;
        }
    }
    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N+1][N+1];

        Point[] s = new Point[3];
        Point[] e = new Point[3];

        int sIdx = 0;
        int eIdx = 0;
        for (int r = 1; r<=N;r++){
            String input = br.readLine();
            for (int c = 1; c<=N;c++){
                map[r][c] = input.charAt(c-1);
                if(map[r][c] == 'B'){
                    s[sIdx] = new Point(r,c);
                    sIdx++;
                }else if(map[r][c] == 'E'){
                    e[eIdx] = new Point(r,c);
                    eIdx++;
                };
            }
        }



        start = new Tree(s[0].r,s[0].c,s[1].r,s[1].c,s[2].r,s[2].c);
        end = new Tree(e[0].r,e[0].c,e[1].r,e[1].c,e[2].r,e[2].c);


        BFS();
    }

    public static void BFS(){
        Queue<Tree> queue = new LinkedList<>();
        queue.add(start);
        boolean[][][][] visited = new boolean[N+1][N+1][N+1][N+1];
        visited[start.r1][start.c1][start.r2][start.c2] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                Tree current = queue.poll();

//                System.out.println(current.r1+" "+current.c1);
//                System.out.println(current.r2+" "+current.c2);
//                System.out.println(current.r3+" "+current.c3);
//                System.out.println("--------위치---------");

                if (current.r1 == end.r1 && current.c1 == end.c1 && current.r2 == end.r2 && current.c2 == end.c2 && current.r3 == end.r3 && current.c3 == end.c3){
                    System.out.println(cnt);
                    return;
                }

                for (int d = 0; d<4;d++){
                    int nr1 = current.r1+dr[d];
                    int nc1 = current.c1+dc[d];
                    if (nr1 < 1 || nr1 > N || nc1 < 1 || nc1 > N || map[nr1][nc1] == '1') continue;

                    int nr2 = current.r2+dr[d];
                    int nc2 = current.c2+dc[d];
                    if (nr2 < 1 || nr2 > N || nc2 < 1 || nc2 > N || map[nr2][nc2] == '1') continue;

                    int nr3 = current.r3+dr[d];
                    int nc3 = current.c3+dc[d];
                    if (nr3 < 1 || nr3 > N || nc3 < 1 || nc3 > N || map[nr3][nc3] == '1') continue;

                    if (visited[nr1][nc1][nr2][nc2]) continue;

                    queue.add(new Tree(nr1,nc1,nr2,nc2,nr3,nc3));
                    visited[nr1][nc1][nr2][nc2] = true;
                }

                boolean check = false;
                for (int d = 4; d<8;d++){
                    int nr = current.r2+dr[d];
                    int nc = current.c2+dc[d];
                    if (nr < 1 || nr > N || nc < 1 || nc >N || map[nr][nc] == '1'){
                        check = true;
                        break;
                    }
                }

                if(check) continue;
                //회전일때
                int nr1 = current.r1;
                int nc1 = current.c1;
                int nr2 = current.r2;
                int nc2 = current.c2;
                int nr3 = current.r3;
                int nc3 = current.c3;
                //가로방향
               if(nr1 == nr2 && nc1 == nc2-1){
                   nr1 = nr2 -1;
                   nc1 = nc2;
                   nr3 = nr2 +1;
                   nc3 = nc2;
               }else{
                   nr1 = nr2;
                   nc1 = nc2 - 1;
                   nr3 = nr2;
                   nc3 = nc2+1;
               }

                if (nr1 < 1 || nr1 > N || nc1 < 1 || nc1 > N || map[nr1][nc1] == '1') continue;
                if (nr3 < 1 || nr3 > N || nc3 < 1 || nc3 > N || map[nr3][nc3] == '1') continue;

                if (visited[nr1][nc1][nr2][nc2]) continue;

                queue.add(new Tree(nr1,nc1,nr2,nc2,nr3,nc3));
                visited[nr1][nc1][nr2][nc2] = true;


            }

            cnt++;
//            System.out.println("-----------"+cnt+"----------------");
        }

        System.out.println(0);


    }

    public static void print(){
        for (int r = 1; r<=N;r++){
            for (int c = 1; c<=N;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}

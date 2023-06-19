package 백준;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_17141_연구소2 {
    public static int N,M, ans;
    public static int[][] state;

    public static List<Point> virus;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        state = new int[N][N];

        virus = new ArrayList<>();
        ans = -1;
        for (int r = 0; r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<N;c++){
                state[r][c] = Integer.parseInt(st.nextToken());
                if(state[r][c] == 2){
                    virus.add(new Point(r, c));
                    state[r][c] = 0;
                }
            }
        }

        comb(new int[M], virus.size(),0, 0);

        System.out.println(ans);

    }
    public static void comb(int[] sel, int listSize,int k, int idx){
        if (k == sel.length){
            BFS(sel);
            return;
        }

        for (int i = idx;i<listSize;i++){
            sel[k] = i;
            comb(sel,listSize,k+1,i+1);
        }

    }

    public static void BFS(int[] sel){
        Queue<Point> queue = new LinkedList<>();
        int[][] copyState = new int[N][N];
        boolean[][] visited = new boolean[N][N];

        copy(copyState);


        for (int i = 0; i<sel.length;i++){
            Point point = virus.get(sel[i]);
            copyState[point.r][point.c] = 2;
            queue.add(point);
            visited[point.r][point.c] = true;
        }


        int time = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){

                Point current = queue.poll();

                for (int d = 0; d<4;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    if(nr<0 || nr>=N || nc < 0 || nc >= N || visited[nr][nc] || copyState[nr][nc] == 1) continue;
                    copyState[nr][nc] = 2;
                    queue.add(new Point(nr,nc));
                    visited[nr][nc] = true;
                }
            }

            time++;

        }


        if(!check(copyState)){
            return;
        }else if (ans == -1){
            ans = time-1;
        }else if (ans != -1){
            ans = Math.min(ans,time-1);
        }

    }

    public static boolean check(int[][] copyState){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<N;c++){
                if(copyState[r][c] == 0) return false;
            }
        }

        return true;
    }


    public static void copy(int[][] copyState){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<N;c++){
                copyState[r][c] = state[r][c];
            }
        }
    }


}

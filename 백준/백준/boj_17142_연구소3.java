package 백준.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_17142_연구소3 {

    public static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }


    public static int N, M;
    public static int[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static List<Point> virusList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        virusList = new ArrayList<>();

        for (int r = 0; r<N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == 2){
                    virusList.add(new Point(r,c));
                }
            }
        }


        comb(virusList.size(),new int[M],0);


    }

    public static int[][] MCopy(){
        int[][] copyMap = new int[N][N];
        for (int r = 0; r<N;r++) {
            for (int c = 0; c < N; c++) {
                copyMap[r][c] = map[r][c];
            }
        }

        return copyMap;

    }

    public static void start(int[] sel){
        int[][] copyMap = MCopy();

        Queue<Point> queue = new LinkedList<Point>();
        for (int i = 0 ; i<sel.length;i++){
            queue.offer(virusList.get(sel[i]));
        }

        int time = 0;
        while(!queue.isEmpty()){

            int size = queue.size();
            for (int i = 0; i<size;i++){

            }
            Point current = queue.poll();

            for (int d = 0; d<4;d++){
                int nr = current.r + dr[d];
                int nc = current.c + dc[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < N && copyMap[nr][nc] != 1){
                    if(copyMap[nr][nc] == 0){
                        copyMap[nr][nc] = 3;
                        queue.offer(new Point(nr,nc));
                    }else if(copyMap[nr][nc] == 2){
                        
                    }
                }
            }
        }
    }

    public static void diffusion(int[][] copyMap, int r, int c){
        for (int d = 0; d<4;d++){
            int nr = r+dr[d];
            int nc = c+dc[d];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && copyMap[nr][nc] != 1){
                copyMap[nr][nc] = 3;
            }
        }
    }
    public static void comb(int size,int[] sel,int k){
        if(k == sel.length){
            start(sel);
            return;
        }


        for(int i = 0; i<size;i++){
            sel[k] = i;
            comb(size,sel,k+1);
        }

    }


    public static void print(){
        for (int r = 0; r<N;r++){
            for (int c = 0; c<N;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }


}

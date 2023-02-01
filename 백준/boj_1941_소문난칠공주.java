package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class boj_1941_소문난칠공주 {

    public static int ans;
    public static char[][] table;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static ArrayList<Point> pointList;
    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ans = 0;
        table = new char[5][5];
        pointList = new ArrayList<>();
        for (int r = 0; r<5;r++){
            String str = br.readLine();
            for (int c = 0; c<5;c++){
                table[r][c] = str.charAt(c);
                pointList.add(new Point(r,c));
            }
        }


        comb(0,0, new Point[7]);

//        print();
    }

    public static void comb(int k, int idx, Point sel[]){
        if(k == 7){
            BFS(sel);
            return;
        }

        for (int i = idx;i<pointList.size();i++){
            sel[k] = pointList.get(i);
            comb(k+1,i+1,sel);
        }
    }

    public static void BFS(Point[] sel){
        boolean[] visited = new boolean[7];
        Queue<Point> queue = new LinkedList<>();
        queue.add(sel[0]);
        visited[0] = true;

        while(queue.isEmpty()){

        }


    }

    public static void print(){
        for (int r = 0; r<5;r++){
            for (int c = 0; c<5;c++){
                System.out.print(table[r][c]+" ");
            }
            System.out.println();
        }
    }
}

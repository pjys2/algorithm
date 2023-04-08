package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj_17825_주사위윷놀이 {
    public static int[] dice;
    public static int[][] map = {{0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,0},
            {10,13,16,19,25},
            {20,22,24,25},
            {30,28,27,26,25},
            {25,30,35,40,0}
    };

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

        dice = new int[10];
        for (int i = 0; i<10;i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        permutation(new int[10],0);
    }

    public static void permutation(int[] sel, int k){
        if(k == sel.length){
            move(sel);
//            System.out.println(Arrays.toString(sel));
            return;
        }

        for (int i = 0; i<4;i++){
            sel[k] = i;
            permutation(sel,k+1);
        }
    }

    public static void move(int[] sel){
        Point[] horse = new Point[4];
        for (int i = 0; i<4;i++){
            horse[i] = new Point(0,0);
        }


        for (int i = 0; i<10;i++){
            int hNum = sel[i];
            int nr = horse[hNum].r;
            int nc = horse[hNum].c+dice[i];

            if(nc < map[nr].length){
                if(nr == 0 && nc == 10){
                    nr = 1;
                    nc = 0;
                } else if(nr == 1 && nc == 20){
                    nr = 2;
                    nc = 0;
                } else if (nr == 3 && nc == 30){
                    nr = 3;
                    nc = 0;
                } else if (map[nr][nc] == 25){
                    nr = 4;
                    nc = 0;
                }
            }else{



            }

        }
    }
}

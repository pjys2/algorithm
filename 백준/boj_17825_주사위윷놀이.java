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
    public static int ans;
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
        ans = 0;
        for (int i = 0; i<10;i++){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        permutation(new int[10],0);
        System.out.println(ans);
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
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i<4;i++){
            horse[i] = new Point(0,0);
        }

        int total = 0;

        for (int i = 0; i<10;i++){
            int hNum = sel[i];
            int nr = horse[hNum].r;
            int nc = horse[hNum].c+dice[i];


            //도착지점일 때는 이동 가능해야함
            //맵 위에서 겹치면면 때 함수 리턴


            if(nc < map[nr].length){
                if(nr == 0 && map[nr][nc] == 10){
                    nr = 1;
                    nc = 0;
                } else if(nr == 0 && map[nr][nc] == 20){
                    nr = 2;
                    nc = 0;
                } else if (nr == 0 && map[nr][nc] == 30){
                    nr = 3;
                    nc = 0;
                } else if (map[nr][nc] == 25){
                    nr = 4;
                    nc = 0;
                }
            }else{
                if(nr == 0 || nr == 4) nc = map[nr].length-1;
                else{
                    int len = nc - map[nr].length+1;
                    nr = 4;
                    nc = len;
                }
            }
            if(nr == 4 && nc == 3) {
                nr = 0;
                nc = 20;
            }

            if (map[nr][nc] != 0 && nr == horse[0].r && nc == horse[0].c) return;
            if (map[nr][nc] != 0 && nr == horse[1].r && nc == horse[1].c) return;
            if (map[nr][nc] != 0 && nr == horse[2].r && nc == horse[2].c) return;
            if (map[nr][nc] != 0 && nr == horse[3].r && nc == horse[3].c) return;

            horse[hNum] = new Point(nr,nc);
            total += map[nr][nc];
            list.add(map[nr][nc]);
        }



//        if(ans < total){
//            System.out.println(Arrays.toString(dice));
//            System.out.println(Arrays.toString(sel));
//            for (int n : list){
//                System.out.print(n+" ");
//            }
//            System.out.println("total : "+total);
//            System.out.println("-------------------------");
//        }
        ans = Math.max(ans, total);

    }
}

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_11559_puyopuyo {

    public static char[][] map;
    public static List<Point> puyoList;

    public static class Point{
        int r, c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for (int i = 0; i<12;i++){
            String input = br.readLine();
            for (int j = 0; j<6;j++){
                map[i][j] = input.charAt(j);
            }
        }

        while(findPuyo()){

        }




    }

    public static boolean findPuyo(){

        puyoList = new ArrayList<>();


        for (int r = 0; r<12;r++){
            for (int c = 0;c<6;c++){

            }
        }


        if(!puyoList.isEmpty()){
            return true;
        }


        return false;
    }


    public static void print(){
        for (int i = 0; i<12;i++){
            for (int j = 0; j<6;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}

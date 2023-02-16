package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_20058_마법사상어와파이어스톰 {

    public static int N, Q,mapSize;
    public static int[][] map;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static int[] L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        mapSize = (int) Math.pow(2,N);
        map = new int[mapSize][mapSize];
        L = new int[Q];

        for (int r = 0; r<mapSize;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c<mapSize;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<Q;i++){
            L[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i<Q;i++){
            divide(i);
        }


        print();
    }


    public static void divide(int i){
        int range = (int) Math.pow(2,L[i]);

        for (int r = 0; r<mapSize;r=r+range){
           for (int c = 0;c<mapSize;c=c+range){

           }
        }

    }

    public static void print(){
        for (int r = 0; r<map.length;r++){
            for (int c = 0; c<map[r].length;c++){
                System.out.print(map[r][c]+" ");
            }
            System.out.println();
        }
    }
}

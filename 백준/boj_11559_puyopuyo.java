package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11559_puyopuyo {

    public static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];

        for (int i = 0; i<12;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j<6;j++){
                map[i][j] = st.nextToken().charAt(0);
            }
        }

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

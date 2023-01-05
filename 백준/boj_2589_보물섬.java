package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2589_보물섬 {
    public static int R,C;
    public static char[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int r = 0; r<R;r++){
            String str = br.readLine();
            for (int c = 0; c<C;c++){
                map[r][c] = str.charAt(c);
            }
        }

        print();

    }

    public static void print(){
        for (int r = 0; r<R;r++){
            for (int c = 0; c<C;c++){
                System.out.print(map[r][c]+" ");

            }
            System.out.println();
        }
    }
}

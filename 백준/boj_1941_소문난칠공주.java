package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_1941_소문난칠공주 {

    public static char[][] table;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        table = new char[5][5];

        for (int r = 0; r<5;r++){
            String str = br.readLine();
            for (int c = 0; c<5;c++){
                table[r][c] = str.charAt(c);
            }
        }

        print();
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

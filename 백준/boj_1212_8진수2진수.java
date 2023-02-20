package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class boj_1212_8진수2진수 {
    public static String N;
    public static String[] data = {"000","001","010","011","100","101","110","111"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = br.readLine();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N.length(); i++){
            int num = N.charAt(i) - '0';
            sb.append(data[num]);
        }

        if (sb.charAt(0)=='0') sb.deleteCharAt(0);
        if (sb.charAt(0)=='0') sb.deleteCharAt(0);


        System.out.println(sb);
    }
}

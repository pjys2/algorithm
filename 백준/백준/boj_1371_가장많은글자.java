package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class boj_1371_가장많은글자 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//      scanner sc = new Scanner(System.in);
        int[] arr = new int[26];
        int max = 0;


        while(true){

            String strList = br.readLine();
//            System.out.println(strList);
            StringTokenizer st = new StringTokenizer(strList);

            int length = st.countTokens();
            for(int i = 0 ;  i< length; i++) {
                String str = st.nextToken();
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) - 'a' >= 0 && str.charAt(j) - 'a' <= 26) {
                        arr[str.charAt(j) - 'a']++;
                        if (max < arr[str.charAt(j) - 'a']) {
                            max = arr[str.charAt(j) - 'a'];
                        }
                    }
                }
            }


        }


//        System.out.println(Arrays.toString(arr));





    }

}

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj_9935_문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String word = br.readLine();

        Stack<Character> stack = new Stack<>();


        for (int i = 0; i<input.length();i++){
            stack.push(input.charAt(i));

            boolean isPossible = false;

            if (stack.get(stack.size()-1) == word.charAt(word.length()-1) && stack.size() >= word.length()){
                int idx = stack.size()-word.length();
                isPossible = true;
                for (int j = 0;j <word.length();j++){
                    if (stack.get(idx) != word.charAt(j)){
                        isPossible = false;
                        break;
                    }
                    idx++;
                }
            }

            if (isPossible){
                for (int j = 0; j<word.length();j++){
                    stack.pop();
                }
            }

//            for (int j = 0; j<stack.size();j++){
//                System.out.print(stack.get(j));
//            }
//            System.out.println();
        }



        if (stack.isEmpty()){
            System.out.println("FRULA");
        }else{
            StringBuilder sb = new StringBuilder();
            for (char c : stack){
                sb.append(c);
            }

            System.out.println(sb);
        }


    }


}

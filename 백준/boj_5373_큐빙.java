package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class boj_5373_큐빙 {
    public static int n;
    public static Map<Character,Integer> targetMap;
    public static char[][][] cube;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        targetMap = new HashMap<>();
        targetMap.put('U',0);
        targetMap.put('D',1);
        targetMap.put('F',2);
        targetMap.put('B',3);
        targetMap.put('L',4);
        targetMap.put('R',5);

        for (int t = 1; t <= tc;t++){


            cube = new char[][][]{{{'w','w','w'},{'w','w','w'},{'w','w','w'}},
                                    {{'y','y','y'},{'y','y','y'},{'y','y','y'}},
                                    {{'r','r','r'},{'r','r','r'},{'r','r','r'}},
                                    {{'o','o','o'},{'o','o','o'},{'o','o','o'}},
                                    {{'g','g','g'},{'g','g','g'},{'g','g','g'}},
                                    {{'b','b','b'},{'b','b','b'},{'b','b','b'}}};

            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i<n;i++){
                String input = st.nextToken();
                char target = input.charAt(0);
                char dir = input.charAt(1);

                rotate(targetMap.get(target),dir);
            }

            print();
        }

    }

    public static void rotate(int target, char dir){

        if(dir == '-'){
            //반시계 회전

            char[][] newtarget = new char[3][3];
            //타겟 면 회전시키기
            for (int r = 0; r<3;r++){
                for (int c = 0;c<3;c++){
                    newtarget[r][c] = cube[target][c][2-r];
                }
            }

            for (int r = 0; r<3;r++){
                for (int c = 0;c<3;c++){
                    cube[target][r][c] = newtarget[r][c];
                }
            }

            if (target == 0){
                char[] temp = {cube[2][0][0],cube[2][0][1],cube[2][0][2]};
                cube[2][0][0] = cube[4][2][0];
                cube[2][0][1] = cube[4][1][0];
                cube[2][0][2] = cube[4][0][0];

                cube[4][2][0] = cube[3][2][2];
                cube[4][1][0] = cube[3][2][1];
                cube[4][0][0] = cube[3][2][0];

                cube[3][2][2] = cube[5][0][2];
                cube[3][2][1] = cube[5][1][2];
                cube[3][2][0] = cube[5][2][2];

                cube[5][0][2] = temp[0];
                cube[5][1][2] = temp[1];
                cube[5][2][2] = temp[2];
            }else if(target == 1){
                char[] temp = {cube[2][2][0],cube[2][2][1],cube[2][2][2]};
                cube[2][2][0] = cube[5][0][0];
                cube[2][2][1] = cube[5][1][0];
                cube[2][2][2] = cube[5][2][0];

                cube[5][0][0] = cube[3][0][2];
                cube[5][1][0] = cube[3][0][1];
                cube[5][2][0] = cube[3][0][0];

                cube[3][0][2] = cube[4][2][2];
                cube[3][0][1] = cube[4][1][2];
                cube[3][0][0] = cube[4][0][2];

                cube[4][2][2] = temp[0];
                cube[4][1][2] = temp[1];
                cube[4][0][2] = temp[2];
            }else if(target == 2){
                char[] temp = {cube[0][2][0],cube[0][2][1],cube[0][2][2]};
                cube[0][2][0] = cube[5][0][2];
                cube[0][2][1] = cube[5][0][1];
                cube[0][2][2] = cube[5][0][0];

                cube[5][0][2] = cube[1][0][2];
                cube[5][0][1] = cube[1][0][1];
                cube[5][0][0] = cube[1][0][0];

                cube[1][0][2] = cube[4][0][2];
                cube[1][0][1] = cube[4][0][1];
                cube[1][0][0] = cube[4][0][0];

                cube[4][0][2] = temp[0];
                cube[4][0][1] = temp[1];
                cube[4][0][0] = temp[2];
            }else if(target == 3){
                char[] temp = {cube[1][2][0],cube[1][2][1],cube[1][2][2]};
                cube[1][2][0] = cube[5][2][0];
                cube[1][2][1] = cube[5][2][1];
                cube[1][2][2] = cube[5][2][2];

                cube[5][2][0] = cube[0][0][2];
                cube[5][2][1] = cube[0][0][1];
                cube[5][2][2] = cube[0][0][0];

                cube[0][0][2] = cube[4][2][0];
                cube[0][0][1] = cube[4][2][1];
                cube[0][0][0] = cube[4][2][2];

                cube[4][2][0] = temp[0];
                cube[4][2][1] = temp[1];
                cube[4][2][2] = temp[2];
            }else if (target == 4){
                char[] temp = {cube[0][0][0],cube[0][1][0],cube[0][2][0]};

                cube[0][0][0] = cube[2][0][0];
                cube[0][1][0] = cube[2][1][0];
                cube[0][2][0] = cube[2][2][0];

                cube[2][0][0] = cube[1][0][0];
                cube[2][1][0] = cube[1][1][0];
                cube[2][2][0] = cube[1][2][0];

                cube[1][0][0] = cube[3][0][0];
                cube[1][1][0] = cube[3][1][0];
                cube[1][2][0] = cube[3][2][0];

                cube[3][0][0] = temp[0];
                cube[3][1][0] = temp[1];
                cube[3][2][0] = temp[2];
            }else if (target == 5){
                char[] temp = {cube[0][2][2],cube[0][1][2],cube[0][0][2]};

                cube[0][2][2] = cube[3][2][2];
                cube[0][1][2] = cube[3][1][2];
                cube[0][0][2] = cube[3][0][2];

                cube[3][2][2] = cube[1][2][2];
                cube[3][1][2] = cube[1][1][2];
                cube[3][0][2] = cube[1][0][2];

                cube[1][2][2] = cube[2][2][2];
                cube[1][1][2] = cube[2][1][2];
                cube[1][0][2] = cube[2][0][2];

                cube[2][2][2] = temp[0];
                cube[2][1][2] = temp[1];
                cube[2][0][2] = temp[2];
            }


        }else{
            //시계 회전
            char[][] newtarget = new char[3][3];
            //타겟 면 회전시키기
            for (int r = 0; r<3;r++){
                for (int c = 0;c<3;c++){
                    newtarget[r][c] = cube[target][2-c][r];
                }
            }

            for (int r = 0; r<3;r++){
                for (int c = 0;c<3;c++){
                    cube[target][r][c] = newtarget[r][c];
                }
            }


            if (target == 0){
                char[] temp = {cube[2][0][0],cube[2][0][1],cube[2][0][2]};
                cube[2][0][0] = cube[5][0][2];
                cube[2][0][1] = cube[5][1][2];
                cube[2][0][2] = cube[5][2][2];

                cube[5][0][2] = cube[3][2][2];
                cube[5][1][2] = cube[3][2][1];
                cube[5][2][2] = cube[3][2][0];

                cube[3][2][2] = cube[4][2][0];
                cube[3][2][1] = cube[4][1][0];
                cube[3][2][0] = cube[4][0][0];

                cube[4][2][0] = temp[0];
                cube[4][1][0] = temp[1];
                cube[4][0][0] = temp[2];
            }else if(target == 1){
                char[] temp = {cube[2][2][0],cube[2][2][1],cube[2][2][2]};
                cube[2][2][0] = cube[4][2][2];
                cube[2][2][1] = cube[4][1][2];
                cube[2][2][2] = cube[4][0][2];

                cube[4][2][2] = cube[3][0][2];
                cube[4][1][2] = cube[3][0][1];
                cube[4][0][2] = cube[3][0][0];

                cube[3][0][2] = cube[5][0][0];
                cube[3][0][1] = cube[5][1][0];
                cube[3][0][0] = cube[5][2][0];

                cube[5][0][0] = temp[0];
                cube[5][1][0] = temp[1];
                cube[5][2][0] = temp[2];
            }else if(target == 2){
                char[] temp = {cube[0][2][0],cube[0][2][1],cube[0][2][2]};
                cube[0][2][0] = cube[4][0][2];
                cube[0][2][1] = cube[4][0][1];
                cube[0][2][2] = cube[4][0][0];

                cube[4][0][2] = cube[1][0][2];
                cube[4][0][1] = cube[1][0][1];
                cube[4][0][0] = cube[1][0][0];

                cube[1][0][2] = cube[5][0][2];
                cube[1][0][1] = cube[5][0][1];
                cube[1][0][0] = cube[5][0][0];

                cube[5][0][2] = temp[0];
                cube[5][0][1] = temp[1];
                cube[5][0][0] = temp[2];
            }else if(target == 3){
                char[] temp = {cube[1][2][0],cube[1][2][1],cube[1][2][2]};
                cube[1][2][0] = cube[4][2][0];
                cube[1][2][1] = cube[4][2][1];
                cube[1][2][2] = cube[4][2][2];

                cube[4][2][0] = cube[0][0][2];
                cube[4][2][1] = cube[0][0][1];
                cube[4][2][2] = cube[0][0][0];

                cube[0][0][2] = cube[5][2][0];
                cube[0][0][1] = cube[5][2][1];
                cube[0][0][0] = cube[5][2][2];

                cube[5][2][0] = temp[0];
                cube[5][2][1] = temp[1];
                cube[5][2][2] = temp[2];
            }else if (target == 4){
                char[] temp = {cube[0][0][0],cube[0][1][0],cube[0][2][0]};

                cube[0][0][0] = cube[3][0][0];
                cube[0][1][0] = cube[3][1][0];
                cube[0][2][0] = cube[3][2][0];

                cube[3][0][0] = cube[1][0][0];
                cube[3][1][0] = cube[1][1][0];
                cube[3][2][0] = cube[1][2][0];

                cube[1][0][0] = cube[2][0][0];
                cube[1][1][0] = cube[2][1][0];
                cube[1][2][0] = cube[2][2][0];

                cube[2][0][0] = temp[0];
                cube[2][1][0] = temp[1];
                cube[2][2][0] = temp[2];
            }else if (target == 5){
                char[] temp = {cube[0][2][2],cube[0][1][2],cube[0][0][2]};

                cube[0][2][2] = cube[2][2][2];
                cube[0][1][2] = cube[2][1][2];
                cube[0][0][2] = cube[2][0][2];

                cube[2][2][2] = cube[1][2][2];
                cube[2][1][2] = cube[1][1][2];
                cube[2][0][2] = cube[1][0][2];

                cube[1][2][2] = cube[3][2][2];
                cube[1][1][2] = cube[3][1][2];
                cube[1][0][2] = cube[3][0][2];

                cube[3][2][2] = temp[0];
                cube[3][1][2] = temp[1];
                cube[3][0][2] = temp[2];
            }


        }
    }

    public static void print(){
        for (int r = 0; r<3;r++){
            for (int c = 0; c<3;c++){
                System.out.print(cube[0][r][c]);
            }
            System.out.println();
        }
    }

}

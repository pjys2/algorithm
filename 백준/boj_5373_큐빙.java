package 백준;

import java.util.Scanner;

public class boj_5373_큐빙 {

    public static char[][][] cube = {{{'w','w','w'},{'w','w','w'},{'w','w','w'}},    //윗면 흰색 0번
            {{'r','r','r'},{'r','r','r'},{'r','r','r'}},                             //앞 면 빨간색 1번
            {{'y','y','y'},{'y','y','y'},{'y','y','y'}},                             //아랫면 노란색 2번
            {{'g','g','g'},{'g','g','g'},{'g','g','g'}},                             //왼쪽 면 초록색 3번
            {{'o','o','o'},{'o','o','o'},{'o','o','o'}},                             //뒷 면 오렌지색 4번
            {{'b','b','b'},{'b','b','b'},{'b','b','b'}}};                            //오른쪽 면 파란색 5번
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        int T = sc.nextInt();
//
//        for(int tc = 1; tc <= T; tc++){
//
//        }
        int N = sc.nextInt();
        for(int i =  0; i<N;i++){
            String oper = sc.next();
//            System.out.println(oper.charAt(0)+" "+oper.charAt(1) );
            solve(oper.charAt(0),oper.charAt(1));
        }


//        print();
    }

    public static void solve(char pos, char dir){
        if(pos == 'U'){
            if(dir == '+'){
                turnRight(0);
                char[] tempArray = cube[1][0];
                cube[1][0] = cube[5][0];
                cube[5][0] = cube[4][0];
                cube[4][0] = cube[3][0];
                cube[3][0] = tempArray;
            }else{
                turnLeft(0);
                char[] tempArray = cube[1][0];
                cube[1][0] = cube[3][0];
                cube[3][0] = cube[4][0];
                cube[4][0] = cube[5][0];
                cube[5][0] = tempArray;
            }
        }else if(pos == 'D'){

        }else if(pos == 'F'){

        }else if(pos == 'B'){

        }else if(pos == 'L'){
            if(dir == '+'){
                turnRight(3);
                for(int i = 0 ; i<3;i++){

                }

            }else{
                turnLeft(3);
                for(int i = 0 ; i<3;i++){
                    char temp = cube[1][0][i];
                    cube[1][0][i] = cube[0][0][i];
                    cube[0][0][i] = cube[4][0][i];

                }
                char[] tempArray = cube[1][0];
                cube[1][0] = cube[3][0];
                cube[3][0] = cube[4][0];
                cube[4][0] = cube[5][0];
                cube[5][0] = tempArray;
            }
        }else if(pos == 'R'){

        }
    }

    public static void turnRight(int pos){
        char temp = cube[pos][0][0];
        cube[pos][0][0] = cube[pos][1][0];
        cube[pos][1][0] = cube[pos][2][0];
        cube[pos][2][0] = cube[pos][2][1];
        cube[pos][2][1] = cube[pos][2][2];
        cube[pos][2][2] = cube[pos][1][2];
        cube[pos][1][1] = cube[pos][0][2];
        cube[pos][0][2] = cube[pos][0][1];
        cube[pos][0][1] = temp;
    }

    public static void turnLeft(int pos){
        char temp = cube[pos][0][0];
        cube[pos][0][0] = cube[pos][0][1];
        cube[pos][0][1] = cube[pos][0][2];
        cube[pos][0][2] = cube[pos][1][2];
        cube[pos][1][2] = cube[pos][2][2];
        cube[pos][2][2] = cube[pos][2][1];
        cube[pos][2][1] = cube[pos][2][0];
        cube[pos][2][0] = cube[pos][1][0];
        cube[pos][1][0] = temp;
    }

    public static void print(){
        for(int r = 0; r < 3; r++){
            for(int c = 0 ;c<3;c++){
                System.out.print(cube[0][r][c]);
            }
            System.out.println();
        }
    }

}

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class boj_17837_새로운게임2 {
    public static int N, K;
    public static int[][] board;
    public static Horse[] horseList;
    public static List<Integer>[][] state;
    public static int[] dr = {0,0,0,-1,1};
    public static int[] dc = {0,1,-1,0,0};
    public static class Horse{
        int r,c,dir;
        boolean isBlue;

        public Horse(int r, int c, int dir, boolean isBlue){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.isBlue = isBlue;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N+1][N+1];
        state = new ArrayList[N+1][N+1];
        horseList = new Horse[K+1];

        for (int r = 1; r<=N;r++){
            for (int c = 1; c<=N;c++){
                state[r][c] = new ArrayList<Integer>();
            }
        }


        for (int r = 1; r<=N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=N;c++){
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 1; k<=K;k++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            state[r][c].add(k);
            horseList[k] = new Horse(r,c,dir,false);
        }

        for (int i = 1; i<=1000;i++){
            for (int k = 1; k<=K;k++){
                ArrayList<Integer> moveList = new ArrayList<>();
                boolean flag = false;
                int cr = horseList[k].r;
                int cc = horseList[k].c;
                int nr = horseList[k].r+dr[horseList[k].dir];
                int nc = horseList[k].c+dc[horseList[k].dir];

                if(nr < 1 || nr > N || nc < 1 || nc > N || board[nr][nc] == 2){


                    if(horseList[k].dir == 1){
                        horseList[k].dir = 2;
                    }else if(horseList[k].dir == 2){
                        horseList[k].dir = 1;
                    }else if(horseList[k].dir == 3){
                        horseList[k].dir = 4;
                    }else if(horseList[k].dir == 4){
                        horseList[k].dir = 3;
                    }


                    if(horseList[k].isBlue) continue;

                    horseList[k].isBlue = true;
                    int dir = horseList[k].dir;

                    for(int num :state[cr][cc]){
                        if (num == k) flag = true;
                        if (!flag) continue;
                        reverse(horseList[num],dir);
                        moveList.add(num);
                    }

                    nr = horseList[k].r;
                    nc = horseList[k].c;

                    //위치를 변경한 값이 그대로면 continue
//                    if(nr == cr && nc == cc) continue;

                    // 방향을 바꾸고 이동할 바닥이 흰색이면 그냥이동 빨간색이면 순서 바꿔서 이동
                    if(board[nr][nc] == 0){
                        state[cr][cc].removeAll(moveList);
                        state[nr][nc].addAll(moveList);
                    }else if(board[nr][nc] == 1){
                        state[cr][cc].removeAll(moveList);
                        Collections.reverse(moveList);
                        state[nr][nc].addAll(moveList);
                    }

                }else if(board[nr][nc] == 0){
                    for(int num :state[cr][cc]){
                        if (num == k) flag = true;
                        if (!flag) continue;
                        move(horseList[num],nr,nc);
                        moveList.add(num);
                    }

                    state[cr][cc].removeAll(moveList);
                    state[nr][nc].addAll(moveList);

                }else if(board[nr][nc] == 1){
                    for(int num :state[cr][cc]){
                        if (num == k) flag = true;
                        if (!flag) continue;
                        move(horseList[num],nr,nc);
                        moveList.add(num);
                    }
                    state[cr][cc].removeAll(moveList);
                    Collections.reverse(moveList);
                    state[nr][nc].addAll(moveList);
                }

                if(state[nr][nc].size() >= 4){
                    System.out.println(i);
                    return;
                }
            }
        }

        System.out.println(-1);

    }

    //흰색 또는 빨간색일때 다음칸 이동
    public static void move(Horse horse, int nr, int nc){
        horse.r = nr;
        horse.c = nc;
    }

    //파란색일때 위치 바꾸기
    public static void reverse(Horse horse, int dir){

        int nr = horse.r+dr[dir];
        int nc = horse.c+dc[dir];

        if(nr < 1 || nr > N || nc < 1 || nc > N || board[nr][nc] == 2) return;

        horse.r = nr;
        horse.c = nc;
    }


    //각 지점별 가장 아래에 있는 말 출력
    public static void print(){
        for (int r = 1; r<=N;r++){
            for (int c = 1; c<=N;c++){
                if(state[r][c].isEmpty()){
                    System.out.print(0+" ");
                    continue;
                }

                System.out.print(state[r][c].get(0)+" ");
            }
            System.out.println();
        }
    }
}

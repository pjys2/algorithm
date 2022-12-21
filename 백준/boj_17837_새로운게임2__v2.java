package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class boj_17837_새로운게임2__v2 {

    public static int N, K;
    public static int[][] map;
    public static int[][] state;
    public static boolean complete;
    public static int[] dr = {0,0,0,-1,1};
    public static int[] dc = {0,1,-1,0,0};
    public static List<Horse> horseList;
    public static class Horse{
        int r,c,dir;
        boolean isBlue = false;
        Horse before;
        Horse after;
        public Horse(int r, int c, int dir){
            this.r = r;
            this.c = c;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "["+this.r + " "+ this.c + " " + this.dir+"]";
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        state = new int[N+1][N+1];
        horseList = new ArrayList<Horse>();

        for(int r = 1; r<=N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c<=N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 1; k<=K;k++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            state[r][c] = k;
            horseList.add(new Horse(r,c,dir));
        }


        loop:for (int i =1; i<=1000;i++){
            for (Horse horse:horseList) {
                int nr = horse.r + dr[horse.dir];
                int nc = horse.c + dc[horse.dir];

                if(nr >= 1 && nr <= N && nc >= 1 && nc <= N){
                    if(map[nr][nc] == 0){
                        if(state[nr][nc] != 0){
                            discnnect(horse);
                            move(horse,nr,nc);
                            connect(horse, state[nr][nc]);
                        }else{
                            discnnect(horse);
                            state[nr][nc] = horseList.indexOf(horse)+1;
                            move(horse,nr,nc);
                        }
                    }else if(map[nr][nc] == 1){
                        if(state[nr][nc] != 0){
                            discnnect(horse);
                            move(horse,nr,nc);
                            Horse lastHorse = lastFind(horse);
                            reverse(horse);
                            connect(lastHorse,state[nr][nc]);
                        }else{
                            discnnect(horse);
                            move(horse,nr,nc);
                            Horse lastHorse = lastFind(horse);
                            reverse(horse);
                            state[nr][nc] = horseList.indexOf(lastHorse)+1;
                        }
                    }else if(map[nr][nc] == 2){
                        if(horse.dir == 1){
                            horse.dir = 2;
                        }else if(horse.dir == 2){
                            horse.dir = 1;
                        }else if(horse.dir == 3){
                            horse.dir = 4;
                        }else if(horse.dir == 4){
                            horse.dir = 3;
                        }

                        nr = horse.r +dr[horse.dir];
                        nc = horse.c +dc[horse.dir];
                        if(!horse.isBlue){
                            horse.isBlue = true;
                            if((nr < 1 || nr > N || nc < 1 || nc > N)  || map[nr][nc] == 2) continue;
                            //흰색 빨간색 구분
                            if(map[nr][nc] == 0){
                                if(state[nr][nc] != 0){
                                    discnnect(horse);
                                    move(horse,nr,nc);
                                    connect(horse, state[nr][nc]);
                                }else{
                                    discnnect(horse);
                                    state[nr][nc] = horseList.indexOf(horse)+1;
                                    move(horse,nr,nc);
                                }
                            }else if(map[nr][nc] == 1){
                                if(state[nr][nc] != 0){
                                    discnnect(horse);
                                    move(horse,nr,nc);
                                    Horse lastHorse = lastFind(horse);
                                    reverse(horse);
                                    connect(lastHorse,state[nr][nc]);
                                }else{
                                    discnnect(horse);
                                    move(horse,nr,nc);
                                    Horse lastHorse = lastFind(horse);
                                    reverse(horse);
                                    state[nr][nc] = horseList.indexOf(lastHorse)+1;
                                }
                            }
                        }
                    }
                }else{
                    if(horse.dir == 1){
                        horse.dir = 2;
                    }else if(horse.dir == 2){
                        horse.dir = 1;
                    }else if(horse.dir == 3){
                        horse.dir = 4;
                    }else if(horse.dir == 4){
                        horse.dir = 3;
                    }

                    nr = horse.r +dr[horse.dir];
                    nc = horse.c +dc[horse.dir];
                    if(!horse.isBlue){
                        horse.isBlue = true;
                        if(nr < 1 && nr > N && nc < 1 && nc > N  || map[nr][nc] == 2) continue;
                        //흰색 빨간색 구분
                        if(map[nr][nc] == 0){
                            if(state[nr][nc] != 0){
                                discnnect(horse);
                                move(horse,nr,nc);
                                connect(horse, state[nr][nc]);
                            }else{
                                discnnect(horse);
                                state[nr][nc] = horseList.indexOf(horse)+1;
                                move(horse,nr,nc);
                            }
                        }else if(map[nr][nc] == 1){
                            if(state[nr][nc] != 0){
                                discnnect(horse);
                                move(horse,nr,nc);
                                Horse lastHorse = lastFind(horse);
                                reverse(horse);
                                connect(lastHorse,state[nr][nc]);
                            }else{
                                discnnect(horse);
                                move(horse,nr,nc);
                                Horse lastHorse = lastFind(horse);
                                reverse(horse);
                                state[nr][nc] = horseList.indexOf(lastHorse)+1;
                            }
                        }
                    }
                }

                if(nr>=1 && nr <=N && nc >= 1 && nc <= N && state[nr][nc]!=0 &&isEnd(horseList.get(state[nr][nc]-1),1)){
                    complete = true;
                    System.out.println(i);
                    break loop;
                }
//                System.out.println(horseList.indexOf(horse)+1);
//                print(state);
//                System.out.println("--------------------------------");
            }

        }

        if (!complete){
            System.out.println(-1);
        }
    }

    public static boolean isEnd(Horse horse, int cnt){
        if(cnt == 4){
            return true;
        }

        if(horse.after != null){
            return isEnd(horse.after,cnt+1);
        }

        return false;
    }

    public static void discnnect(Horse horse){

        //이전 말이 있을 경우
        if(horse.before != null){
            horse.before.after = null;
            horse.before = null;
        }else{
            state[horse.r][horse.c] = 0;
        }
    }

    public static Horse lastFind(Horse horse){
        if(horse.after == null){
            return horse;
        }
        return lastFind(horse.after);
    }

    public static void reverse(Horse horse){

        Horse beforeHorse = horse.before;
        Horse afterHorse = horse.after;
//        System.out.println("출력");
        horse.before = afterHorse;
        horse.after = beforeHorse;
//        System.out.println("출력");
        if(afterHorse != null){
            reverse(afterHorse);
        }
    }


    public static void connect(Horse horse, int idx){

        idx -= 1;
        horse.before = lastFind(horseList.get(idx));
        lastFind(horseList.get(idx)).after = horse;


//        System.out.println("after:"+horseList.indexOf(horse.after));
//        System.out.println("before:"+horseList.indexOf(horse.before));
//
//        System.out.println("before.after:"+horseList.indexOf(horse.before.after));
//        System.out.println("before.before:"+horseList.indexOf(horse.before.before));

    }

    public static void move(Horse horse, int nr, int nc){
        horse.r = nr;
        horse.c = nc;

        if(horse.after != null){
            move(horse.after,nr,nc);
        }
    }

    public static void print(int[][] arr){
        for(int r = 1; r<arr.length;r++){
            for (int c = 1; c<arr[r].length;c++){
                System.out.print(arr[r][c]+" ");
            }
            System.out.println();
        }
    }
}

package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_17837_새로운게임2 {

    public static class Unit{
        int r, c,dir;
        boolean isBlue = false;
        Unit child;
        public Unit(int r, int c, int dir, Unit child){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.child = child;
        }


        @Override
        public String toString() {
            return "["+r+", "+c+", "+dir+"]";
        }
    }

    public static int N, K;
    public static Unit[] units;
    public static int[] parent;
    public static int[][] map;
    public static int[][] state;
    public static int[] dr = {0,-1,1,0,0};
    public static int[] dc = {0,0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N+1][N+1];
        state = new int[N+1][N+1];

        for (int r = 1; r <= N;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1 ; c <= N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        units = new Unit[K+1];
        parent = new int[K+1];
        for (int k = 1; k <=K;k++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            units[k] = new Unit(r,c,dir,units[k]);
            state[r][c] = k;
        }

        for (int i = 1; i<=1001;i++){
            if(i == 1001){
                System.out.println(-1);
                break;
            }

            for (int k = 1; k<=K;k++){
                int nr = units[k].r + dr[units[k].dir];
                int nc = units[k].c + dc[units[k].dir];
                state[units[k].r][units[k].c] = 0;
                if(nr >= 1 && nr <= N && nc >= 1 && nc <= N){
                    if(map[nr][nc] == 0){
                        if(state[nr][nc] != 0){
                            connect(units[state[nr][nc]], units[k]);
                            move(units[k],nr,nc);
                        }else{
                            state[nr][nc] = k;
                            cut(units[state[units[k].r][units[k].c]], units[k]);
                            move(units[k],nr,nc);
                        }
                    }else if(map[nr][nc] == 1){
                        if(state[nr][nc] != 0){
                            Unit firstUnit = reverse(units[k], units[k]);
                            connect(units[state[nr][nc]], firstUnit);
                            move(firstUnit,nr,nc);
                        }else{
                            Unit firstUnit = reverse(units[k], units[k]);

                            //Unit에 번호 추가
                            for (int n = 1; n<=K;n++){
                                if(units[n] == firstUnit){
                                    state[nr][nc] = n;
                                }
                            }
                            move(firstUnit,nr,nc);

                        }
                    }else if(map[nr][nc] == 2){
                        if(units[k].dir == 1){
                            units[k].dir = 2;
                        }else if(units[k].dir == 2){
                            units[k].dir = 1;
                        }else if(units[k].dir == 3){
                            units[k].dir = 4;
                        }else if(units[k].dir == 4){
                            units[k].dir = 3;
                        }

                        if(units[k].isBlue) continue;
                        units[k].isBlue = true;
                        //방향 돌리고 범위 판단 필요
                        if(state[nr][nc] != 0){
                            move(units[k],nr,nc);
                        }else{
                            state[nr][nc] = k;
                            cut(units[state[units[k].r][units[k].c]], units[k]);
                            move(units[k],nr,nc);
                        }
                    }

                }else {
                    if (units[k].dir == 1) {
                        units[k].dir = 2;
                    } else if (units[k].dir == 2) {
                        units[k].dir = 1;
                    } else if (units[k].dir == 3) {
                        units[k].dir = 4;
                    } else if (units[k].dir == 4) {
                        units[k].dir = 3;
                    }

                    if (units[k].isBlue) continue;
                    units[k].isBlue = true;
                    //방향 돌리고 범위 판단 필요
                    if (state[nr][nc] != 0) {
                        move(units[k], nr, nc);
                    } else {
                        state[nr][nc] = k;
                        cut(units[state[units[k].r][units[k].c]], units[k]);
                        move(units[k], nr, nc);
                    }
                }
            }

        }

    }

    public static void connect(Unit parent, Unit child){
        if(parent.child == parent){
            parent.child = child;
            return;
        }

        connect(parent.child, child);
    }

    public static Unit reverse(Unit unit, Unit parent){

        if(unit.child == unit) {
            unit.child = parent;
            return unit;
        }

        Unit lastUnit = reverse(unit.child, unit);
        unit.child = parent;

        return lastUnit;
    }

    public static void move(Unit unit, int nr, int nc){
        if(unit.child == unit){
            return;
        }
        unit.r = nr;
        unit.c = nc;
        move(unit,nr,nc);
    }

    public static void cut(Unit root, Unit unit){
        if(root.child == unit){
            root.child = root;
            return;
        }

        cut(root.child,unit);
    }


    public static void print(int[][] map){
        for (int r = 0; r < N;r++){
            for (int c = 0 ; c < N; c++){
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }
}

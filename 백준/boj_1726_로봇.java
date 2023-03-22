package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1726_로봇 {
    public static int M,N;
    public static int[][] map;
    public static int[] dr = {0,0,0,1,-1};
    public static int[] dc = {0,1,-1,0,0};
    public static Robot start,end;
    public static class Robot{
        int r,c,dir,cnt;
        public Robot(int r, int c,int dir, int cnt){
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M+1][N+1];

        for (int r = 1; r<=M;r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 1;c<=N;c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i<2;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if(i == 0){
                start = new Robot(r,c,dir,0);
            }else{
                end = new Robot(r,c,dir,0);
            }
        }

        BFS();
    }

    public static void BFS(){
        Queue<Robot> queue = new LinkedList<>();
        queue.add(start);
        boolean[][][] visited = new boolean[M+1][N+1][5];
        visited[start.r][start.c][start.dir] = true;
        int cnt = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s<size;s++){
                Robot current = queue.poll();


//                System.out.println(cnt+" : "+current.r +" "+current.c+" "+current.dir+" "+current.cnt);
                if(current.r == end.r && current.c == end.c && current.dir == end.dir){
                    System.out.println(current.cnt);
                    return;
                }

                // 명령 1번 코드
                for (int k = 1; k<=3;k++){
                    int nr = current.r+(dr[current.dir]*k);
                    int nc = current.c+(dc[current.dir]*k);
                    if(nr < 1 || nr > M || nc < 1 || nc > N || visited[nr][nc][current.dir] || map[nr][nc] == 1) break;

                    queue.add(new Robot(nr,nc,current.dir,current.cnt+1));
                    visited[nr][nc][current.dir] = true;
                }

                // 왼쪽방향으로 회전
                if (current.dir == 1 || current.dir == 2){
                    for (int i = 3; i<=4;i++){
                        int ndir = i;
                        if(!visited[current.r][current.c][ndir]){
                            queue.add(new Robot(current.r,current.c,ndir,current.cnt+1));
                            visited[current.r][current.c][ndir] = true;
                        }
                    }
                }else if(current.dir == 3 || current.dir == 4){
                    for (int i = 1; i<=2;i++){
                        int ndir = i;
                        if(!visited[current.r][current.c][ndir]){
                            queue.add(new Robot(current.r,current.c,ndir,current.cnt+1));
                            visited[current.r][current.c][ndir] = true;
                        }
                    }
                }

            }

            cnt++;


        }
    }
}

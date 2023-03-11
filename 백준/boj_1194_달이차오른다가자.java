package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_1194_달이차오른다가자 {
    public static int N, M;
    public static char[][] map;
    public static Point start,end;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Point{
        int r, c, key;
        public Point(int r, int c,int key){
            this.r = r;
            this.c = c;
            this.key = key;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N+1][M+1];
        for (int r = 1;r<=N;r++){
            String input = br.readLine();
            for (int c = 1; c<=M;c++){
                map[r][c] = input.charAt(c-1);
                if(map[r][c] == '0'){
                    start = new Point(r,c,0);
                }
            }
        }
        BFS();


//        int test = 0;
//        test += 1;
//        test += 1<<1;
//        test += 1<<2;
//        test += 1<<3;
//        test += 1<<4;
//        test += 1<<5;
//
//        System.out.println(test & 1);


    }

    public static void BFS(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][][] visited = new boolean[N+1][M+1][128];
        visited[start.r][start.c][0] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int s = 0; s<size;s++){
                Point current = queue.poll();
                if(map[current.r][current.c] == '1'){
                    System.out.println(cnt);
                    return;
                }

                for (int d = 0; d<4;d++){
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];

                    if(nr < 1 || nr > N || nc <1 || nc > M || map[nr][nc] == '#') continue;
                    int nextKey = current.key;

                    //key일 때
                    if(map[nr][nc] >= 'a' && map[nr][nc] <= 'f'){
                        int num = map[nr][nc]-'a'+1;
                        num = 1 << num;

                        if((nextKey & num) != num){
                            nextKey = nextKey+num;
                        }

                    }

                    if (visited[nr][nc][nextKey]) continue;


                    if(map[nr][nc] >= 'A' && map[nr][nc] <= 'F'){
                        int num = map[nr][nc]-'A'+1;
                        num = 1 << num;
                        if ((nextKey & num) == 0){
                            continue;
                        }
                    }


                    queue.add(new Point(nr,nc,nextKey));
                    visited[nr][nc][nextKey] = true;
                }
            }


            cnt++;
        }

        System.out.println(-1);

    }
}

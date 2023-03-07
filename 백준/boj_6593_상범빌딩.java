package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_6593_상범빌딩 {
    public static int L,R,C;
    public static Point start, end;
    public static char[][][] building;
    public static int[] dr = {1,-1,0,0,0,0};
    public static int[] dc = {0,0,1,-1,0,0};
    public static int[] dl = {0,0,0,0,1,-1};
    public static class Point{
        int l,r,c;
        public Point(int l, int r, int c){
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());



        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        while(L != 0 && R != 0 && C != 0){

            building = new char[L][R][C];

            for (int l = 0; l<L;l++){
                for (int r = 0; r<R;r++){
                    String str = br.readLine();
                    for (int c = 0; c<C;c++){
                        building[l][r][c] = str.charAt(c);
                        if(building[l][r][c] == 'S'){
                            start = new Point(l,r,c);
                        }else if(building[l][r][c] == 'E'){
                            end = new Point(l,r,c);
                        }

                    }
                }
                br.readLine();
            }


            BFS();


            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }


    }

    public static void BFS(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        boolean[][][] visited = new boolean[L][R][C];
        visited[start.l][start.r][start.c] = true;

        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                Point current = queue.poll();

                if (current.l == end.l && current.r == end.r && current.c == end.c){
                    System.out.println("Escaped in "+cnt+" minute(s).");
                    return;
                }

                for (int d = 0;d<6;d++){
                    int nr = current.r+dr[d];
                    int nc = current.c+dc[d];
                    int nl = current.l+dl[d];
                    if(nr< 0 || nr >=R || nc <0 || nc >= C || nl < 0 || nl >=L) continue;

                    if (!visited[nl][nr][nc] && building[nl][nr][nc] != '#'){
                        visited[nl][nr][nc] = true;
                        queue.add(new Point(nl,nr,nc));
                    }
                }
            }
            cnt++;
        }

        System.out.println("Trapped!");

    }

    public static void print(){
        for (int l = 0; l<L;l++){
            for (int r = 0; r<R;r++){
                for (int c = 0; c<C;c++){
                    System.out.print(building[l][r][c]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}

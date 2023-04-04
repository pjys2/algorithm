package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_13459_구슬탈출 {
    public static int N, M;
    public static char[][] board;
    public static Marble start;
    public static Point whole;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static class Marble{
        int br,bc,rr,rc;

        public Marble(int br, int bc, int rr, int rc){
            this.br = br;
            this.bc = bc;
            this.rr = rr;
            this.rc = rc;
        }
    }

    public static class Point{
        int r, c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        int blueR = 0;
        int blueC = 0;
        int redR = 0;
        int redC = 0;
        for (int r = 0; r<N;r++){
            String input = br.readLine();
            for (int c = 0; c<M;c++){
                board[r][c] = input.charAt(c);
                if(board[r][c] == 'B'){
                    blueR = r;
                    blueC = c;
                }else if(board[r][c] == 'R'){
                    redR = r;
                    redC = c;
                }else if(board[r][c] == 'O'){
                    whole = new Point(r,c);
                }
            }

        }

        start = new Marble(blueR,blueC,redR,redC);

        BFS(start);
    }

    public static void BFS(Marble start){
        Queue<Marble> queue = new LinkedList<>();
        queue.add(start);
        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[start.br][start.bc][start.rr][start.rc] = true;

        int cnt = 0;
        boolean isPossible = false;
        while(!queue.isEmpty()){
            int size = queue.size();
//            System.out.println(cnt);
//            System.out.println("--------------------");
            for (int s = 0; s<size;s++){
                Marble current = queue.poll();
//                System.out.println("현재 위치 : "+current.br+" "+current.bc+" // "+current.rr+" "+current.rc);
//                System.out.println("-----");



                for (int d = 0; d<4;d++){
                    int nbr = current.br;
                    int nbc = current.bc;
                    int nrr = current.rr;
                    int nrc = current.rc;
                    if(nbr == nrr && ((d == 0 && nbc > nrc) || (d == 1 && nbc < nrc))){
                        nbc = slideC(nbr,nbc,nrr,nrc,d);
                        nrc = slideC(nrr,nrc,nbr,nbc,d);
                    }else if(nbr == nrr && ((d == 0 && nbc < nrc) || (d == 1 && nbc > nrc))){
                        nrc = slideC(nrr,nrc,nbr,nbc,d);
                        nbc = slideC(nbr,nbc,nrr,nrc,d);
                    }else if(nbc == nrc && ((d == 2 && nbr > nrr) || (d == 3 && nbr < nrr))){
                        nbr = slideR(nbr,nbc,nrr,nrc,d);
                        nrr = slideR(nrr,nrc,nbr,nbc,d);
                    }else if(nbc == nrc && ((d == 2 && nbr < nrr) || (d == 3 && nbc > nrc))){
                        nrr = slideR(nrr,nrc,nbr,nbc,d);
                        nbr = slideR(nbr,nbc,nrr,nrc,d);
                    }else{
                        if(d == 0 || d == 1){
                            nbc = slideC(nbr,nbc,nrr,nrc,d);
                            nrc = slideC(nrr,nrc,nbr,nbc,d);
                        }else{
                            nbr = slideR(nbr,nbc,nrr,nrc,d);
                            nrr = slideR(nrr,nrc,nbr,nbc,d);
                        }
                    }


//                    System.out.println("다음 위치 : "+nbr+" "+nbc+" // "+nrr+" "+nrc);

                    if(visited[nbr][nbc][nrr][nrc] || (nbr == whole.r && nbc == whole.c)) continue;

                    if (nrr == whole.r && nrc == whole.c){
                        System.out.println(1);
                        return;
                    }

                    queue.add(new Marble(nbr,nbc,nrr,nrc));
                    visited[nbr][nbc][nrr][nrc] = true;
                }
            }
//            System.out.println("------------------------------------------");
            cnt++;
            if(cnt > 10) break;
        }

        System.out.println(0);
    }

    public static int slideR(int currentR,int currentC, int diffR,int diffC, int d){

        while(board[currentR][currentC]!='#'){
            currentR = currentR+dr[d];

            if(board[currentR][currentC] == 'O'){
                return currentR;
            }

            if(currentR == diffR && currentC == diffC){
                return currentR-dr[d];
            }

        }

        return currentR-dr[d];
    }

    public static int slideC(int currentR,int currentC,int diffR, int diffC, int d){

        while(board[currentR][currentC]!='#'){
            currentC = currentC+dc[d];

            if(board[currentR][currentC] == 'O'){
                return currentC;
            }

            if(currentR == diffR && currentC == diffC){
                return currentC-dc[d];
            }
            
        }

        return currentC-dc[d];

    }


}

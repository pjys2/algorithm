import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_13460_구슬탈출 {
    public static int N, M;
    public static char[][] board;

    public static int[] dr = {1,-1,0,0};
    public static int[] dc = {0,0,1,-1};
    public static class Position{
        int rr,rc,br,bc;

        Position(){};

        Position(int rr,int rc,int br,int bc){
            this.rr = rr;
            this.rc = rc;
            this.br = br;
            this.bc = bc;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "rr=" + rr +
                    ", rc=" + rc +
                    ", br=" + br +
                    ", bc=" + bc +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        Position start = new Position();

        for (int i = 0; i< N;i++){
            String str= br.readLine();
            for(int j = 0; j<M;j++){
                board[i][j] = str.charAt(j);
                if(board[i][j] == 'R'){
                    start.rr = i;
                    start.rc = j;
                }else if(board[i][j] == 'B'){
                    start.br = i;
                    start.bc = j;
                }
            }
        }

//        print(board);
//        System.out.println(start.toString());
        int Ans = bfs(start);
        System.out.println(Ans);
    }

    private static int bfs(Position start) {
        Queue<Position> queue = new LinkedList<Position>();
        boolean visited[][][][] = new boolean[N][M][N][M];
        visited[start.rr][start.rc][start.br][start.bc] = true;
        queue.offer(start);
        int queueCnt = 0;
        while(!queue.isEmpty()){
            if(++queueCnt == 11){
                return -1;
            }

            int length = queue.size();

            for(int i = 0;i<length;i++){
//                System.out.printf("%d번째 큐 %d번째 \n",queueCnt,i+1);
                Position current = queue.poll();

                for(int d = 0; d<4;d++){
                    boolean redHole = false;
                    boolean blueHole = false;
                    //빨간 구슬 움직이기
                    int nrr = current.rr;
                    int nrc = current.rc;
                    int redCnt = 0;
                    while(board[nrr][nrc] != '#'){
                        nrr = nrr+dr[d];
                        nrc = nrc+dc[d];
                        redCnt++;
                        if(board[nrr][nrc] == 'O'){
                            redHole = true;
                            break;
                        }
                    }

                    //파란 구슬 움직이기
                    int nbr = current.br;
                    int nbc = current.bc;
                    int blueCnt = 0;
                    while(board[nbr][nbc] != '#'){
                        nbr = nbr+dr[d];
                        nbc = nbc+dc[d];
                        blueCnt++;
                        if(board[nbr][nbc] == 'O'){
                            blueHole = true;
                            break;
                        }
                    }

                    if(blueHole){
                        continue;
                    }else if(redHole){
                        return queueCnt;
                    }else if(nrr == nbr && nrc == nbc){
                        if(redCnt > blueCnt){
                            nrr = nrr-(dr[d]*2);
                            nrc = nrc-(dc[d]*2);
                            nbr = nbr-dr[d];
                            nbc = nbc-dc[d];
                        }else{
                            nbr = nbr-(dr[d]*2);
                            nbc = nbc-(dc[d]*2);
                            nrr = nrr-dr[d];
                            nrc = nrc-dc[d];
                        }
                    }else{
                        nrr = nrr-dr[d];
                        nrc = nrc-dc[d];
                        nbr = nbr-dr[d];
                        nbc = nbc-dc[d];
                    }

//                    System.out.printf("빨간공 위치 : [%d, %d], 파란공 위치 : [%d, %d] \n", nrr,nrc,nbr,nbc);
                    if(!visited[nrr][nrc][nbr][nbc]){
                        queue.offer(new Position(nrr,nrc,nbr,nbc));
                    }

                }

            }

        }
        return -1;

    }

    private static void print(char[][] board) {
        for (int i = 0; i< N;i++){
            for(int j =0; j<M;j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}

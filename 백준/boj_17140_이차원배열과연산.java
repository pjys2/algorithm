package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class boj_17140_이차원배열과연산 {


    public static class CntData implements Comparable<CntData>{
        int number, cnt;

        public CntData(int number, int cnt){
            this.number = number;
            this.cnt = cnt;
        }


        @Override
        public String toString() {
            return "[ "+this.number+", "+cnt+" ] ";
        }

        @Override
        public int compareTo(CntData o) {
            if(this.cnt == o.cnt){
                return this.number - o.number;
            }

            return this.cnt - o.cnt;
        }
    }



    public static int R,C,k;
    public static int[][] A;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        A = new int[3][3];

        for (int r = 0 ; r < 3; r++){
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 3; c++){
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int N = 0;
        while(!IsEnd()){
            if(A.length >= A[0].length){
                R();
            }else{
                C();
            }

            N++;
            if(N > 100){
                N = -1;
                break;
            }
        }

        System.out.println(N);
    }

    public static void C(){
        PriorityQueue<CntData>[] dataList = new PriorityQueue[A[0].length];
        int cMaxSize = 0;
        for(int c = 0; c<A[0].length;c++){
            dataList[c] = new PriorityQueue<CntData>();
            for (int r = 0; r<A.length;r++){
                if(A[r][c] == 0) continue;

                int cnt = 0;
                int number = A[r][c];
                for(int nr = r; nr<A.length; nr++){
                    if (A[nr][c] == number){
                        A[nr][c] = 0;
                        cnt++;
                    }
                }

                dataList[c].add(new CntData(number,cnt));
            }
            if(cMaxSize < dataList[c].size()*2){
                if(dataList[c].size()*2 >=100){
                    cMaxSize = 100;
                }else{
                    cMaxSize = dataList[c].size()*2;
                }
            }
        }

        A = new int[cMaxSize][A[0].length];
        for (int c = 0; c< A[0].length;c++){
            int idx = 0;
            int size = dataList[c].size();
            for (int l = 0; l < size; l++){
                if(l >= 50) break;

                CntData data = dataList[c].poll();
                A[idx][c] = data.number;
                idx++;
                A[idx][c] = data.cnt;
                idx++;
            }
        }
    }

    public static void R(){
        PriorityQueue<CntData>[] dataList = new PriorityQueue[A.length];

        int rMaxSize = 0;

        for (int r = 0 ; r<A.length;r++){
            dataList[r] = new PriorityQueue<CntData>();
            for (int c = 0; c<A[r].length;c++){
                if(A[r][c] == 0) continue;

                int cnt = 0;
                int number = A[r][c];
                for(int nc = c; nc<A[r].length; nc++){
                    if (A[r][nc] == number){
                        A[r][nc] = 0;
                        cnt++;
                    }
                }

                dataList[r].add(new CntData(number,cnt));
            }
            if(rMaxSize < dataList[r].size()*2){
                if(dataList[r].size()*2 >=100){
                    rMaxSize = 100;
                }else{
                    rMaxSize = dataList[r].size()*2;
                }
            }
        }

        A = new int[A.length][rMaxSize];
        for (int r = 0; r< A.length;r++){
            int idx = 0;
            int size = dataList[r].size();
            for (int l = 0; l < size; l++){
                if(l >= 50) break;
                CntData data = dataList[r].poll();
                A[r][idx] = data.number;
                idx++;
                A[r][idx] = data.cnt;
                idx++;
            }
        }
    }

    public static boolean IsEnd(){

        if(A[0].length < C || A.length < R ){
            return false;
        }

        if(A[R-1][C-1] == k){
            return true;
        }


        return false;
    }

    public static void print(){
        for (int r = 0; r<A.length;r++){
            for (int c = 0; c<A[r].length;c++){
                System.out.print(A[r][c]+" ");
            }
            System.out.println();
        }
    }

}

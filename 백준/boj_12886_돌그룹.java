package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_12886_돌그룹 {
    public static int A,B,C;
    public static State start;
    public static class State{
        int A, B, C;
        public State(int A, int B, int C){
            this.A = A;
            this.B = B;
            this.C = C;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        start = new State(A,B,C);

        BFS();
    }

    public static void BFS(){
        Queue<State> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[1501][1501];

        while(!queue.isEmpty()){
            State current = queue.poll();

            if(current.A == current.B && current.A == current.C){
                System.out.println(1);
                return;
            }

            if(current.A > current.B){
                int nA = current.A - current.B;
                int nB = current.B + current.B;

                if(!visited[nB][nA]){
                    queue.add(new State(nA,nB,current.C));
                    visited[nB][nA] = true;
                }
            }else if(current.A < current.B){
                int nA = current.A + current.A;
                int nB = current.B - current.A;

                if(!visited[nA][nB]){
                    queue.add(new State(nA,nB,current.C));
                    visited[nA][nB] = true;
                }
            }

            if(current.B > current.C){
                int nB = current.B - current.C;
                int nC = current.C + current.C;
                if(!visited[nC][nB]){
                    queue.add(new State(current.A,nB,nC));
                    visited[nC][nB] = true;
                }
            }else if(current.B < current.C){
                int nB = current.B + current.B;
                int nC = current.C - current.B;
                if(!visited[nB][nC]){
                    queue.add(new State(current.A,nB,nC));
                    visited[nB][nC] = true;
                }
            }

            if(current.C > current.A){
                int nC = current.C - current.A;
                int nA = current.A + current.A;
                if(!visited[nA][nC]){
                    queue.add(new State(nA,current.B,nC));
                    visited[nA][nC] = true;
                }
            }else if(current.C < current.A){
                int nC = current.C + current.C;
                int nA = current.A - current.C;
                if(!visited[nC][nA]){
                    queue.add(new State(nA,current.B,nC));
                    visited[nC][nA] = true;
                }
            }

        }

        System.out.println(0);
    }
}

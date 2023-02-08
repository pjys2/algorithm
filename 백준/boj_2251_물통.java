package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_2251_물통 {
    public static int aSize,bSize,cSize;
    public static List<Integer> ansList;
    public static boolean[][][] visited;
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
        aSize = Integer.parseInt(st.nextToken());
        bSize = Integer.parseInt(st.nextToken());
        cSize = Integer.parseInt(st.nextToken());

        ansList = new ArrayList<>();
        visited = new boolean[aSize+1][bSize+1][cSize+1];


        DFS(new State(0,0,cSize));
        Collections.sort(ansList);
        for (int num : ansList){
            System.out.print(num+" ");
        }

    }

    public static void DFS(State current){
        if(visited[current.A][current.B][current.C]) return;
        visited[current.A][current.B][current.C] = true;

        if(current.A == 0){
            ansList.add(current.C);
        }



        //물 이동

        //A -> B
        if(current.A+current.B > bSize){
            DFS(new State(current.A+current.B-bSize,bSize, current.C));
        }else{
            DFS(new State(0,current.A+current.B,current.C));
        }
        //A -> C
        if(current.A+current.C > cSize){
            DFS(new State(current.A+current.C-cSize, current.B, cSize));
        }else{
            DFS(new State(0,current.B,current.A+current.C));
        }
        //B -> C
        if(current.B+current.C > cSize){
            DFS(new State(current.A,current.B+current.C-cSize,cSize));
        }else{
            DFS(new State(current.A,0,current.B+current.C));
        }
        //B -> A
        if(current.B+current.A > aSize){
            DFS(new State(aSize,current.B+current.A-aSize, current.C));
        }else{
            DFS(new State(current.A+current.B,0,current.C));
        }
        //C -> A
        if(current.C+current.A > aSize){
            DFS(new State(aSize, current.B,current.C+current.A-aSize));
        }else{
            DFS(new State(current.A+current.C, current.B, 0));
        }
        //C -> B
        if(current.C+current.B > bSize){
            DFS(new State(current.A,bSize, current.C+current.B-bSize));
        }else{
            DFS(new State(current.A, current.B+ current.C, 0));
        }

    }
}

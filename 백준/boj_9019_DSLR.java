package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_9019_DSLR {
    public static int T,A,B;
    public static boolean[] visited;

    public static class State{
        int num;
        String str;
        public State(int num,String str){
            this.num = num;
            this.str = str;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <=T;tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            visited = new boolean[10000];


        }
    }

    public static void BFS(){
        Queue<State> queue = new LinkedList<>();
        queue.add(new State(A,""));
        visited[A] = true;

        while(!queue.isEmpty()){
            State current = queue.poll();

            if(current.num == B){
                System.out.println(current.str);
                System.out.println("출력");
                break;
            }


            int next = (current.num * 2) %10000;
            if(!visited[next]){
                queue.add(new State(next,current.str+"D"));
            }
            next = (current.num == 0) ? 9999 : current.num-1;
            if(!visited[next]){
                queue.add(new State(next,current.str+"S"));
            }
            int num = current.num;
            int d1 = current.num/1000;
            num = num%1000;
            int d2 = current.num/100;
            num = num%100;
            int d3 = current.num/10;
            num = num%10;
            int d4 = num;

            next = (d2 * 1000) + (d3 *100)+(d4*10)+d1;

            if(!visited[next]){
                queue.add(new State(next,current.str+"L"));
            }

            next = (d4 * 1000) + (d1 *100)+(d2*10)+d3;

            if(!visited[next]){
                queue.add(new State(next,current.str+"R"));
            }
        }
    }
}

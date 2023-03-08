package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1525_퍼즐 {
    public static int[][] puzzle;
    public static String goal = "123456780";
    public static String start;
    public static Set<String> visitSet;
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        puzzle = new int[3][3];
        start = "";
        for (int r = 0 ;r < 3;r++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c<3;c++){
                puzzle[r][c] = Integer.parseInt(st.nextToken());
                start += puzzle[r][c];
            }
        }
        BFS();
    }
    public static int N;
    public static void BFS(){
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visitSet = new HashSet<>();
        visitSet.add(start);

        int cnt = 0;
        while(!queue.isEmpty()){
            int size = queue.size();

            for (int s = 0; s<size;s++){
                String current = queue.poll();

                if(current.equals(goal)){
                    System.out.println(cnt);
                    return;
                }

                int pos = current.indexOf('0');
                int pr = pos/3;
                int pc = pos%3;

                for (int d = 0; d<4;d++){
                    int nr = pr+dr[d];
                    int nc = pc+dc[d];
                    if (nr < 0 || nr >= 3 || nc < 0 || nc >= 3) continue;

                    int nPos = (nr*3) + nc;
                    char temp = current.charAt(nPos);
                    String next = "";
                    next += current;
                    next = next.replace(temp,'c');
                    next = next.replace('0',temp);
                    next = next.replace('c','0');


                    if (visitSet.contains(next)) continue;

                    queue.add(next);
                    visitSet.add(next);
                }
            }

            cnt++;
        }

        System.out.println(-1);
    }

    public static boolean isPossible(int[][] puzzle){
        if (puzzle[0][0] != 1 || puzzle[0][1] != 2 || puzzle[0][2] != 3 || puzzle[1][0] != 4 || puzzle[1][1] != 5 || puzzle[1][2] != 6 || puzzle[2][0] != 7 || puzzle[2][1] != 8){
            return false;
        }
        return true;
    }


}

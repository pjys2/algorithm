package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj_1764_듣보잡 {

    public static int N, M;
    public static Set<String> noSee;
    public static Set<String> noListen;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        noSee = new HashSet<>();
        noListen = new HashSet<>();

        for (int i = 0; i<N;i++){
            noSee.add(br.readLine());
        }

        for (int i = 0; i<M;i++){
            noListen.add(br.readLine());
        }

        ArrayList<String> ansList = new ArrayList<>();
        for (String name : noSee){
            if (noListen.contains(name)){
                ansList.add(name);
            }
        }

        Collections.sort(ansList);

        System.out.println(ansList.size());
        for (String name : ansList){
            System.out.println(name);
        }


    }
}

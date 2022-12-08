package 백준;

import java.io.*;
import java.util.*;

public class boj_1655_가운데를말해요 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();

        for(int i =1; i<=N;i++){
            int input = Integer.parseInt(br.readLine());

            if(minHeap.size() == maxHeap.size()) maxHeap.offer(input);
            else minHeap.offer(input);

            if(!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()){
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(minHeap.poll());
            }
            sb.append(maxHeap.peek()+"\n");
        }

        System.out.println(sb);


    }
}

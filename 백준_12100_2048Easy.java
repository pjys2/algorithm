package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class 백준_12100_2048Easy {
	static int N, ans=0;
	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		permutation(0,new int[5]);
		//int[] sel={0,0,0,0,0};
		//start(sel);
		System.out.println(ans);
	}	
	
	
	//중복순열로 방향을 뽑아냄
	//static int cnt = 0;
	private static void permutation(int k, int[] sel) {
		if(k == sel.length) {
			start(sel);
			//cnt++;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			sel[k] = i;
			permutation(k+1,sel);
		}
	}

	private static void start(int[] sel) {
		int[][] arrCopy = copy();
		
		for (int i = 0; i < 5; i++) {
			moveBlock(arrCopy,sel[i]);
			//print(arrCopy);
			//System.out.println("---------------------------------");
		}
		
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(ans < arrCopy[r][c]) {
					ans = arrCopy[r][c];
				}
			}
		}
	}

	
	
	                                             //방향 0 위 1 아래 2 오른쪽 3 왼쪽
	private static void moveBlock(int[][] arrCopy, int d) {
		
		Stack<Integer> stack = new Stack<Integer>();
		int save = 0;
		if(d == 0) {
			for (int c = 0; c < N; c++) {
				for (int r = 0; r < N; r++) { //위쪽으로 이동이기때문에 위부터 탐색
					if(arrCopy[r][c] == 0) {
						continue;
					}
					
					if(stack.isEmpty()) {      //해당 위치가 0이 아니고 스택이비어있으면 바로 집어넣음
						stack.push(arrCopy[r][c]);   
					}else if(stack.peek() == arrCopy[r][c] && save == arrCopy[r][c]) {     //스택이 안비어있으면 최상단 값이랑 비교해서 같으면 2배해서 집어넣음
						stack.pop();
						stack.push(arrCopy[r][c]*2);
						
					}else{       
						stack.push(arrCopy[r][c]);
					}
					save = arrCopy[r][c];
					arrCopy[r][c] = 0;
				}
				
				//합친 값 집어넣기
				int slen = stack.size();
				
				if(slen == N) {
					for (int r = N-1; r >=0; r--) { 
						arrCopy[r][c] = stack.pop();
					}
				}else if(slen != 0) {
					for (int r = slen-1; r >= 0; r--) { 
						arrCopy[r][c] = stack.pop();
					}
				}
			}
		}else if(d == 1) {
			for (int c = 0; c < N; c++) {
				for (int r = N-1; r >=0; r--) {
					if(arrCopy[r][c] == 0) {
						
						continue;
					}
					
					if(stack.isEmpty()) {      
						stack.push(arrCopy[r][c]);   
					}else if(stack.peek() == arrCopy[r][c] && save == arrCopy[r][c]) {    
						stack.pop();
						stack.push(arrCopy[r][c]*2);
					}else{       
						stack.push(arrCopy[r][c]);
						
					}
					save = arrCopy[r][c];
					arrCopy[r][c] = 0;
				}
				
				//합친 값 집어넣기
				int slen = stack.size();
				
				if(slen == N) {
					for (int r = 0; r < N; r++) { 
						arrCopy[r][c] = stack.pop();
					}
				}else if(slen != 0) {
					for (int r = N-slen; r < N; r++) { 
						arrCopy[r][c] = stack.pop();
					}
				}	
			}
		}else if(d == 2) {
			for (int r = 0; r < N; r++) {
				for (int c = N-1; c >= 0; c--) {
					
					if(arrCopy[r][c] == 0) {
						
						continue;
					}
					
					if(stack.isEmpty()) {     
						stack.push(arrCopy[r][c]);     
						
					}else if(stack.peek() == arrCopy[r][c] && save == arrCopy[r][c]) {    
						stack.pop();
						stack.push(arrCopy[r][c]*2);
						
					}else{      
						stack.push(arrCopy[r][c]);
						
					}
					save = arrCopy[r][c];
					arrCopy[r][c] = 0;
				}
				
				int slen = stack.size();
				
				if(slen == N) {
					for (int c = 0; c < N; c++) { 
						arrCopy[r][c] = stack.pop();
					}
				}else if(slen != 0) {
					for (int c = N-slen; c < N; c++) { 
						arrCopy[r][c] = stack.pop();
					}
				}
			}
		}else if(d == 3) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if(arrCopy[r][c] == 0) {
						
						continue;
					}
					
					if(stack.isEmpty()) {     
						stack.push(arrCopy[r][c]);    
						
					}else if(stack.peek() == arrCopy[r][c] && save == arrCopy[r][c]) {    
						stack.pop();
						stack.push(arrCopy[r][c]*2);
						
					}else{       
						stack.push(arrCopy[r][c]);
					}
					save = arrCopy[r][c];
					arrCopy[r][c] = 0;
				}
				//System.out.println(stack.toString());
				int slen = stack.size();
				
				if(slen == N) {
					for (int c = N-1; c >=0; c--) { 
						arrCopy[r][c] = stack.pop();
					}
				}else if(slen != 0) {
					for (int c = slen-1; c >= 0; c--) { 
						stack.peek();
						arrCopy[r][c] = stack.pop();
					}
				}
			}
		}
	}

	private static int[][] copy() {
		int[][] arrCopy = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arrCopy[i][j] = arr[i][j];
			}
		}
		return arrCopy;
	}

	private static void print(int[][] arr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}

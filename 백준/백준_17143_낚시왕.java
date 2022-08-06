

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 백준_17143_낚시왕 {
	static int R, C, M;
	static Map<Integer, Shark> sharks;
	static int[][] map;

	static class Shark {
		int r; // r
		int c; // c
		int s; // 속력
		int d; // 이동방향
		int z; // 크기

		public Shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}

	static int[] dr = { -1, 1, 0, 0 }; // 상하우좌
	static int[] dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharks = new HashMap<Integer, 백준_17143_낚시왕.Shark>();
		map = new int[R + 1][C + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int r = Integer.parseInt(st.nextToken()); // r
			int c = Integer.parseInt(st.nextToken()); // c
			int s = Integer.parseInt(st.nextToken()); // 속력
			int d = Integer.parseInt(st.nextToken()); // 이동방향
			int z = Integer.parseInt(st.nextToken()); // 크기
			Shark shark = new Shark(r, c, s, d, z);
			sharks.put(shark.z, shark); // 상어의 크기를 키값으로 상어 저장
			map[shark.r][shark.c] = shark.z;
		}

		// System.out.println(sharks.toString());
		int cnt = 0;
		for (int c = 1; c <= C; c++) { // 낚시왕 이동
			for (int r = 1; r <= R; r++) {
				if (map[r][c] != 0) {
					int size = map[r][c];
					map[r][c] = 0;
					sharks.remove(size);
					cnt+=size;
					break;
				}
			}
			// 상어 위치 이동
			moveShark();
		}
		System.out.println(cnt);

	}

	private static void moveShark() {

		Queue<Integer> meal = new LinkedList<Integer>();
		int[][] tmp = new int[R + 1][C + 1];
		for (Integer key : sharks.keySet()) {
			Shark shark = sharks.get(key);
			map[shark.r][shark.c] = 0;
			for (int i = 0; i < shark.s; i++) {
				if (shark.d == 1 && shark.r == 1)
					shark.d = 2;
				else if (shark.d == 2 && shark.r == R)
					shark.d = 1;
				else if (shark.d == 3 && shark.c == C)
					shark.d = 4;
				else if (shark.d == 4 && shark.c == 1)
					shark.d = 3;
				shark.r = shark.r + dr[shark.d - 1];
				shark.c = shark.c + dc[shark.d - 1];
			}

			
			if (tmp[shark.r][shark.c] == 0) {
				tmp[shark.r][shark.c] = shark.z;
			} else if (tmp[shark.r][shark.c] < shark.z) {
				// 해당위치에 있는 상어사이즈가 작으면 삭제
				meal.add(tmp[shark.r][shark.c]);
				tmp[shark.r][shark.c] = shark.z;
			} else if (tmp[shark.r][shark.c] > shark.z) {
				meal.add(shark.z);
			}

		}

		while (!meal.isEmpty()) {
			sharks.remove(meal.poll());
		}

		for (Integer key : sharks.keySet()) {
			Shark shark = sharks.get(key);
			map[shark.r][shark.c] = shark.z;
		}

		// System.out.println(sharks.toString());
	}

}

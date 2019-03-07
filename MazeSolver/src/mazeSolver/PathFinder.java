package mazeSolver;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.PriorityQueue;

public class PathFinder {
	private int[][] map = new int[18][12];
	
	PathFinder() {
		
	}
	
	PathFinder(int[][] map){
		this.map = map;
	}
	
	
	public int[][] getPath(int[] start, int[] end) {
		Deque<GridSquare> ret;
		PriorityQueue<GridSquare> queue = new PriorityQueue<GridSquare>();
		ArrayList<int[]> discovered = new ArrayList<int[]>();
		GridSquare currentSquare = new GridSquare(start,null,0,getHeuristic(start,end));
		queue.add(currentSquare);
		discovered.add(currentSquare.coords);
		
		while (currentSquare.coords != end) {
			for (int i = 0; i < 4; i++) {
				
			}
		}
		return null;
	}
	
	private GridSquare getSquare(int direction, GridSquare square, ArrayList<int[]> discovered) {
		int[] nextCoords = square.coords;
		if (direction == 0)
			nextCoords = square.coords;
	}
	
	public int getCostUnknown(int[] coords,int direction) {
		int x = coords[0];
		int y = coords[1];
		if (map[x][y] == -1)
			return 7500;

		if (direction == 0 || direction == 270) {
			if (x % 2 == 0)
				return 10;
			else
				return 30;
		}
		else {
			if (y % 2 == 0)
				return 10;
			else
				return 30;
		}
	}
	
	public int getCostKnown(int[] coords,int direction) {
		int x = coords[0];
		int y = coords[1];
		if (map[x][y] != 1)
			return 7500;

		if (direction == 0 || direction == 270) {
			if (x % 2 == 0)
				return 10;
			else
				return 30;
		}
		else {
			if (y % 2 == 0)
				return 10;
			else
				return 30;
		}
	}
	
	public int getHeuristic(int[] start, int[] end) {
		return 20 * ( (Math.abs(start[0]-end[0])) + (Math.abs(start[1]-end[1])) );
	}
}

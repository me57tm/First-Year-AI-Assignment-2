package mazeSolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class PathFinder {
	private int[][] map = new int[19][13];
	
	PathFinder() {
		
	}
	
	PathFinder(int[][] map){
		this.map = map;
	}
	
	
	public int[][] getPathU(int[] start, int[] end) {
		//Stack<GridSquare> ret = new Stack<GridSquare>();
		PriorityQueue<GridSquare> queue = new PriorityQueue<GridSquare>();
		ArrayList<int[]> discovered = new ArrayList<int[]>();
		GridSquare currentSquare = new GridSquare(start,null,0,getHeuristic(start,end));
		discovered.add(currentSquare.coords.clone());
		
		GridSquare newSquare;
		int[] newCoords;
		while (currentSquare.coords[0] != end[0] || currentSquare.coords[1] != end[1]) {
			System.out.println(currentSquare.coords[0]+ " "+ currentSquare.coords[1]);
			for (int i = 0; i < 360; i+=90) {
				newCoords = currentSquare.coords.clone();
				if (i == 0)
					newCoords[0]++;
				if (i == 90)
					newCoords[1]++;
				if (i == 180)
					newCoords[0]--;
				if (i == 270)
					newCoords[1]--;
				if (!discovered.contains(newCoords)) {
					newSquare = new GridSquare(
							newCoords,
							currentSquare,
							currentSquare.cost + getCostUnknown(newCoords,i),
							getHeuristic(newCoords,end));
					System.out.println("Adding: "+newSquare);
					queue.add(newSquare);
					discovered.add(newCoords);
				}
			}
			//if 
			//ret.push(currentSquare);
			currentSquare = queue.poll();
			System.out.println(queue);
			System.out.println("Best Move:" + currentSquare);
		}
		//System.out.println(ret);
		Stack<GridSquare> gridStack = new Stack<GridSquare>();
		while (currentSquare.coords[0] != start[0] || currentSquare.coords[1] != start[1]) {
			System.out.println(currentSquare);
			gridStack.push(currentSquare);
			currentSquare = currentSquare.parent;
		}
		int[][] out = new int[gridStack.size()][2];
		for (int i = 0; i < out.length; i++) {
			out[i] = gridStack.pop().coords;
		}
		return out;
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
		return 50 * ( (Math.abs(start[0]-end[0])) + (Math.abs(start[1]-end[1])) );
	}
}

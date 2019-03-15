package mazeSolver;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * ?
 */
public class PathFinder {
	
	private int[][] map = new int[19][13];
	
	PathFinder() {
		
	}
	
	PathFinder(int[][] map){
		this.map = map;
	}
	
	public Stack<int[]> getPath(int[] start, int[] end, boolean unknown) {
		PriorityQueue<GridSquare> queue = new PriorityQueue<GridSquare>();
		boolean[][] discovered = new boolean[19][13];
		GridSquare currentSquare = new GridSquare(start,null,0,getHeuristic(start,end));
		discovered[currentSquare.coords[0]][currentSquare.coords[1]] = true;
		GridSquare newSquare;
		int[] newCoords;
		int[] newerCoords;
		int cuCost = 0;
		while (currentSquare.coords[0] != end[0] || currentSquare.coords[1] != end[1]) {
			System.out.println(currentSquare.coords[0]+ " " + currentSquare.coords[1]);
			for (int i = 0; i < 360; i += 90) {
				newCoords = currentSquare.coords.clone();
				newCoords = coordUpdater(i,newCoords);
				newerCoords = coordUpdater(i,newCoords.clone());
				if (unknown) {
					cuCost = currentSquare.cost + getCostUnknown(newCoords,i); 
				}
				else {
					cuCost = currentSquare.cost + getCostKnown(newCoords,i);
				}
				
				if (!discovered[newCoords[0]][newCoords[1]]) {
					newSquare = new GridSquare(
							newerCoords,
							currentSquare,
							cuCost,
							getHeuristic(newerCoords,end));
					System.out.println("Adding: " + newSquare);
					queue.add(newSquare);
					discovered[newCoords[0]][newCoords[1]] = true;
					discovered[currentSquare.coords[0]][currentSquare.coords[1]] = true;
				}
			}
			currentSquare = queue.poll();
			System.out.println(queue);
			System.out.println("Best Move:" + currentSquare);
		}
		Stack<int[]> gridStack = new Stack<int[]>();
		while (currentSquare.coords[0] != start[0] || currentSquare.coords[1] != start[1]) {
			System.out.println(currentSquare);
			gridStack.push(currentSquare.coords);
			currentSquare = currentSquare.parent;
		}
		return gridStack;
	}
	
	public Stack<int[]> getPath(int[] start,int[] end)
	{
		return getPath(start,end,false);
	}
	
	
	private int[] coordUpdater(int i,int[] c) {
		if (i == 0)
			c[0]++;
		if (i == 90)
			c[1]++;
		if (i == 180)
			c[0]--;
		if (i == 270)
			c[1]--;
		return c;
	}
	
	
	/**
	 * Find the shortest path between two squares, counting unknown tiles as driveable.
	 * @param start 
	 * The location of the robot.
	 * @param end 
	 * The destination to find the path to
	 * @return
	 * An array of coordinates of the squares that are needed to traverse to get from start to finish.
	 */
	public int[][] getPathU(int[] start, int[] end) {
		PriorityQueue<GridSquare> queue = new PriorityQueue<GridSquare>();
		boolean[][] discovered = new boolean[19][13];
		GridSquare currentSquare = new GridSquare(start,null,0,getHeuristic(start,end));
		discovered[currentSquare.coords[0]][currentSquare.coords[1]] = true;
		
		GridSquare newSquare;
		int[] newCoords;
		while (currentSquare.coords[0] != end[0] || currentSquare.coords[1] != end[1]) {
			System.out.println(currentSquare.coords[0]+ " " + currentSquare.coords[1]);
			for (int i = 0; i < 360; i += 90) {
				newCoords = currentSquare.coords.clone();
				if (i == 0)
					newCoords[0]++;
				if (i == 90)
					newCoords[1]++;
				if (i == 180)
					newCoords[0]--;
				if (i == 270)
					newCoords[1]--;
				
				if (!discovered[newCoords[0]][newCoords[1]]) {
					newSquare = new GridSquare(
							newCoords,
							currentSquare,
							currentSquare.cost + getCostUnknown(newCoords,i),
							getHeuristic(newCoords,end));
					System.out.println("Adding: " + newSquare);
					queue.add(newSquare);
					discovered[currentSquare.coords[0]][currentSquare.coords[1]] = true;
				}
			}
			currentSquare = queue.poll();
			System.out.println(queue);
			System.out.println("Best Move:" + currentSquare);
		}
		Stack<GridSquare> gridStack = new Stack<GridSquare>();
		while (currentSquare.coords[0] != start[0] || currentSquare.coords[1] != start[1]) {
			System.out.println(currentSquare);
			gridStack.push(currentSquare);
			currentSquare = currentSquare.parent;
		}
		int[][] out = new int[gridStack.size()][2];
		for (int i = 0; i < out.length; i++)
			out[i] = gridStack.pop().coords;
		
		return out;
	}
	
	/**
	 * Find the shortest path between two squares, counting unknown tiles as walls.
	 * @param start 
	 * The location of the robot.
	 * @param end 
	 * The destination to find the path to
	 * @return
	 * An array of coordinates of the squares that are needed to traverse to get from start to finish.
	 */
	public int[][] getPathK(int[] start, int[] end) {
		PriorityQueue<GridSquare> queue = new PriorityQueue<GridSquare>();
		boolean[][] discovered = new boolean[19][13];
		GridSquare currentSquare = new GridSquare(start,null,0,getHeuristic(start,end));
		discovered[currentSquare.coords[0]][currentSquare.coords[1]] = true;
		
		GridSquare newSquare;
		int[] newCoords;
		while (currentSquare.coords[0] != end[0] || currentSquare.coords[1] != end[1]) {
			System.out.println(currentSquare.coords[0]+ " " + currentSquare.coords[1]);
			for (int i = 0; i < 360; i += 90) {
				newCoords = currentSquare.coords.clone();
				if (i == 0)
					newCoords[0]++;
				if (i == 90)
					newCoords[1]++;
				if (i == 180)
					newCoords[0]--;
				if (i == 270)
					newCoords[1]--;
				if (!discovered[newCoords[0]][newCoords[1]]) {
					newSquare = new GridSquare(
							newCoords,
							currentSquare,
							currentSquare.cost + getCostKnown(newCoords,i),
							getHeuristic(newCoords,end));
					System.out.println("Adding: " + newSquare);
					queue.add(newSquare);
					discovered[currentSquare.coords[0]][currentSquare.coords[1]] = true;
				}
			}
			currentSquare = queue.poll();
			System.out.println(queue);
			System.out.println("Best Move:" + currentSquare);
		}
		Stack<GridSquare> gridStack = new Stack<GridSquare>();
		while (currentSquare.coords[0] != start[0] || currentSquare.coords[1] != start[1]) {
			System.out.println(currentSquare);
			gridStack.push(currentSquare);
			currentSquare = currentSquare.parent;
		}
		int[][] out = new int[gridStack.size()][2];
		for (int i = 0; i < out.length; i++)
			out[i] = gridStack.pop().coords;
		
		return out;
	}
	
	/**
	 * Get the cost for the robot to travel across this tile treating unknowns as driveable.
	 * @param coords
	 * The coordinates of the tile.
	 * @param direction
	 * The direction the robot will cross this tile in
	 * @return
	 * Cost to cross this tile
	 */
	public int getCostUnknown(int[] coords,int direction) {
		int x = coords[0];
		int y = coords[1];
		if (map[x][y] == -1)
			return 7500 + x + y;

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
	
	/**
	 * Get the cost for the robot to travel across this tile treating unknowns as walls.
	 * @param coords
	 * The coordinates of the tile.
	 * @param direction
	 * The direction the robot will cross this tile in
	 * @return
	 * Cost to cross this tile
	 */
	public int getCostKnown(int[] coords,int direction) {
		int x = coords[0];
		int y = coords[1];

		if (map[x][y] != 1)
			return 7500+x+y;

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
	
	/**
	 * Get the estimated cost of the journey from one tile to another.
	 * @param start
	 * Tile we are currently at.
	 * @param end
	 * Tile to get to.
	 * @return
	 * The cost.
	 */
	public int getHeuristic(int[] start, int[] end) {
		return 50 * ( (Math.abs(start[0]-end[0])) + (Math.abs(start[1]-end[1])) );
	}
}
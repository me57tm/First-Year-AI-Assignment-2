package mazeSolver;

public class GridSquare implements Comparable<GridSquare>{
	public int[] coords = new int[2];
	public int[] pre = new int[2];
	public int cost;
	public int heuristic;
	GridSquare(int[] coords,int[] parentCoords,int cost,int heuristic){
		this.coords = coords;
		this.cost = cost;
		this.heuristic = heuristic;
		pre = parentCoords;
	}
	
	public int getTotalCost() {
		return cost+heuristic;
	}

	@Override
	public int compareTo(GridSquare arg0) {
		return getTotalCost() - arg0.getTotalCost();
	}
}

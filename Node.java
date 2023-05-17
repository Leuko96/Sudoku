package pakete;

public class Node {
	
	private int fil;
	private int col;
	private int subgrid;
	private int value;
	
	public Node(int fil, int col, int subgrid, int value) {
		this.fil = fil;
		this.col = col;
		this.subgrid = subgrid;
		this.value = value;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getSubgrid() {
		return subgrid;
	}

	public void setSubgrid(int subgrid) {
		this.subgrid = subgrid;
	}

	public int getFil() {
		return fil;
	}

	public void setFil(int fil) {
		this.fil = fil;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}

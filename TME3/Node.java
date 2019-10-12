package TME3;

import java.util.ArrayList;

public class Node {

	private String label;
	private ArrayList<Position> positions;
	
	/*lien vers les fils*/
	private ArrayList<RadixTree> fils;
	
	
	public Node() {
		
		label = null;
		positions = new ArrayList<>();
		fils = new ArrayList<>();
	}
	
	
	public Node(String label) {
		
		this.label = label;
		positions = new ArrayList<>();
		fils = new ArrayList<>();
	}
	
	
	public Node(String label, ArrayList<Position> pos) {
		
		this.label = label;
		positions = pos;
		fils = new ArrayList<>();
	}
	
	public String getLabel() { return label; }
	
	public void setLabel(String l) { label = l; }
	
	public ArrayList<Position> getPositions() { return positions; }
	
	public void setPosition(ArrayList<Position> p) { positions = p; }
	
	public ArrayList<RadixTree> getFils() { return fils; }
	
	public void setFils(ArrayList<RadixTree> rt) { fils = rt; }
	
	
	
	public boolean isLeaf() { return fils.size() == 0; }
}

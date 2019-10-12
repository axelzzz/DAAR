package TME3;

import java.util.ArrayList;

public class Node {

	/*l arete reliant au pere*/
	private String substringPere;
	/*lien vers les fils*/
	private ArrayList<Edge> substringsFils;
	private ArrayList<Position> positions;
	
	
	
	public Node(String substringPere) {
		
		this.substringPere = substringPere;
		substringsFils = new ArrayList<>();
		positions = new ArrayList<>();
	}
	
	
	public Node(String substringPere, ArrayList<Edge> lf, ArrayList<Position> lp) {
		
		this.substringPere = substringPere;
		substringsFils = lf;
		positions = lp;
	}
	
	
	
	public String getSubstringPere() { return substringPere; }
	
	public void setSubstringPere(String s) { substringPere = s; }
	
	public ArrayList<Edge> getSubstringsFils() { return substringsFils; }
	
	public void setSubstringsFils(ArrayList<Edge> l) { substringsFils = l; }
	
	public ArrayList<Position> getPositions() { return positions; }
	
	public void setPositions(ArrayList<Position> p) { positions = p; }
	
	
	
	
	public boolean isLeaf() {
		
		return substringsFils.size() == 0;
	}
}
